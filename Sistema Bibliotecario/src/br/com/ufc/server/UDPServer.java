package br.com.ufc.server;

import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import br.com.ufc.message.Mensagem;

public class UDPServer {
	private static ArrayList<Request> requests = new ArrayList<Request>();
	private static byte[] buffer = new byte[1000];
	private static DatagramPacket request = new DatagramPacket(buffer, buffer.length);
	private static Despachante desp = new Despachante();
	private static DatagramSocket aSocket;
	private static Gson gson = new Gson();

	public static void main(String args[]) {
		try {
			aSocket = new DatagramSocket(6789);

			while (true) {
				// pega resultado requisição
				String requisition = new String(getRequest());
				// pega os dados da requisição
				Mensagem msg = new Mensagem();
				JsonReader reader = new JsonReader(new StringReader(requisition));
				reader.setLenient(true);
				msg = gson.fromJson(reader, Mensagem.class);

				realizeRequisition(msg);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} finally {
			if (aSocket != null) {
				aSocket.close();
			}
		}
	}

	public static void realizeRequisition(Mensagem msg){
		Random gerador = new Random();
		int debug = gerador.nextInt(1);

		// se a requisicao por par entao nao manda de volta
		if (msg.getRequestId() % 2 != 0) {
			// verifica se a requisição já foi realizada
			for(Request a: requests){
				if(a.getIpClient() == request.getAddress() && a.getRequestId() == msg.getRequestId()){
					// monta a resposta para envio do resultado da requisição caso já teha sido
					// realizado
					Mensagem result = requests.get(requests.indexOf(a)).getMensagem();
					String msgGson = gson.toJson(result);
					byte[] data = msgGson.getBytes();

					System.out.println("A mensagem nº " + msg.getRequestId() + " enviada pelo host " + request.getAddress().toString() + " está duplicada, a resposta já gerada será enviada novamente.");

					// envia resposta da requisição
					sendRequest(data, request.getAddress(), request.getPort());
					return;
				}
			}

			// envia e recebe o resultado da requisição
			byte[] data = desp.invoke(msg);

			// monta o objeto para adicionar no histórico
			String dataReply = new String(data);
			Mensagem msgReply = new Mensagem();
			msgReply = gson.fromJson(dataReply, Mensagem.class);
			Request req = new Request(request.getAddress(), msg.getRequestId(), msgReply);
			requests.add(req);

			// envia resultado da requisição
			if(debug == 1)
				sendRequest(data, request.getAddress(), request.getPort());
		}
	}

	public static byte[] getRequest() {
		try {
			aSocket.receive(request);
			return request.getData();
		} catch (IOException e) {
			return null;
		}
	}

	public static void sendRequest(byte[] reply, InetAddress clientHost, int clientPort) {
		DatagramPacket res = new DatagramPacket(reply, reply.length, clientHost, clientPort);

		try {
			aSocket.send(res);
		} catch (IOException e) {
			return;
		}
	}
}