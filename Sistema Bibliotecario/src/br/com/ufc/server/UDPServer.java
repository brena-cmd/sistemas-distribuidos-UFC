package br.com.ufc.server;

import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import br.com.ufc.message.Mensagem;

public class UDPServer {
	private static Map<Integer, Mensagem> requests = new HashMap<Integer, Mensagem>();
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
				String req = new String(getRequest());
				// pega os dados da requisição
				Mensagem msg = new Mensagem();
				JsonReader reader = new JsonReader(new StringReader(req));
				reader.setLenient(true);
				msg = gson.fromJson(reader, Mensagem.class);

				// verifica se a requisição já foi realizada
				if (requests.get(msg.getRequestId()) == null) {
					// envia e recebe o resultado da requisição
					byte[] data = desp.invoke(msg);

					// monta o objeto para adicionar no histórico
					String dataReply = new String(data);
					Mensagem msgReply = new Mensagem();
					msgReply = gson.fromJson(dataReply, Mensagem.class);
					requests.put(msgReply.getRequestId(), msgReply);

					// envia resultado da requisição
					sendRequest(data, request.getAddress(), request.getPort());
				} else {
					// monta a resposta para envio do resultado da requisição caso já teha sido
					// realizado
					Mensagem result = requests.get(msg.getRequestId());
					String msgGson = gson.toJson(result);
					byte[] data = msgGson.getBytes();

					// envia resposta da requisição
					sendRequest(data, request.getAddress(), request.getPort());
				}
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} finally {
			if (aSocket != null) {
				aSocket.close();
			}
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