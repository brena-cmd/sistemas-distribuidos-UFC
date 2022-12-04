package br.com.ufc.cliente;

import java.net.*;
import java.io.*;
public class UDPCliente{
//	public static void main(String args[]){ 
	// args give message contents and server hostname
//	DatagramSocket aSocket = null;
//		try {
//			aSocket = new DatagramSocket();    
//			byte [] m = args[0].getBytes();
//			InetAddress aHost = InetAddress.getByName(args[1]);
//			int serverPort = 6789;		                                                 
//			DatagramPacket request = new DatagramPacket(m,  args[0].length(), aHost, serverPort);
////			aSocket.send(request);			                        
//			byte[] buffer = new byte[1000];
////			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
////			aSocket.receive(reply);
//			aSocket.setSoTimeout(10000);
//			while(true) {
//			    DatagramPacket getack = new DatagramPacket(buffer, buffer.length);
//			    try {
//			        aSocket.receive(getack);
//			    } catch (SocketTimeoutException e) {
//			       // resend
//			       aSocket.send(request);
//			       continue;
//			    }
//			    // check received data...
//			}
////			System.out.println("Reply: " + new String(reply.getData()));	
//		} catch (SocketException e){
//			System.out.println("Socket: " + e.getMessage());
//		} catch (IOException e){
//			System.out.println("IO: " + e.getMessage());
//		} finally {
//			if(aSocket != null) {
//				aSocket.close();
//			}
//		}
//	}
	
	public static byte[] sendRequest(String req) {
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();    
			byte [] m = req.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;		                                                 
			DatagramPacket request = new DatagramPacket(m,  1000, aHost, serverPort);
			aSocket.send(request);			                        
			byte[] buffer = new byte[1000];
			aSocket.setSoTimeout(10000);
			while(true) {
			    DatagramPacket getack = new DatagramPacket(buffer, buffer.length);
			    try {
			        aSocket.receive(getack);
			    } catch (SocketTimeoutException e) {
			    	// resend
			    	//Se o timeout expirou entao manda novamente
			    	aSocket.send(request);
			    	continue;
			    }
			    // Se chegar aqui é porque recebeu corretamente
			    return getack.getData();
			}
			
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		} finally {
			if(aSocket != null) {
				aSocket.close();
			}
		}
		return "".getBytes();
	}
	
}