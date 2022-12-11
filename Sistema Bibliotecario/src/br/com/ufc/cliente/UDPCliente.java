package br.com.ufc.cliente;

import java.net.*;
import java.io.*;

import br.com.ufc.exceptions.ServerUnavaibleException;

public class UDPCliente {
	public static byte[] sendRequest(String req) throws ServerUnavaibleException {
		DatagramSocket aSocket = null;

		try {
			aSocket = new DatagramSocket();
			byte[] m = req.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
			aSocket.send(request);
			byte[] buffer = new byte[1000];
			aSocket.setSoTimeout(1000);

			for (int i = 0; i < 5; i++) {
				DatagramPacket getack = new DatagramPacket(buffer, buffer.length);
				
				try {
					aSocket.receive(getack);
				} catch (SocketTimeoutException e) {
					// Se o timeout expirou entao manda novamente
					aSocket.send(request);
					continue;
				}

				// Se chegar aqui é porque recebeu corretamente
				return getack.getData();
			}
			
			System.out.println(new ServerUnavaibleException().getMessage());

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			aSocket.close();
		}
		return "".getBytes();
	}
}