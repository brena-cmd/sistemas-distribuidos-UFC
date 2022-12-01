package br.com.ufc.server;

import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import br.com.ufc.cliente.Mensagem;

public class Despachante {
	private Esqueleto esq;
	public Despachante() {
		this.esq = new Esqueleto();
	}
	
	public byte[] invoke(byte[] message) {
		String req = new String(message,StandardCharsets.UTF_8);
//		JsonParser jsonParser = new JsonParser();
//		JsonObject obj = ((JsonArray)jsonParser.parse(req)).getAsJsonObject();
		Gson gson = new Gson();
		Mensagem msg = gson.fromJson(req, Mensagem.class);
		
		byte[] res;
		String args=gson.toJson(msg.getArgs());
		switch(msg.getObjectReference()) {
		case "servidor":
			switch(msg.getMethodId()) {
			case "login":
				
				res = esq.login(args);
				break;
			case "listarAcervo":
				res = esq.listarAcervo();
				break;
			case "cadastrarLivro":
				res = esq.cadastrarLivro(args);
				break;
			case "cadastrarAluno":
				res = esq.cadastrarAluno(args);
				break;
			case "alugar":
				res = esq.alugar(args);
				break;
			case "receberEmprestimo":
				res = esq.receberEmprestimo(args);
				break;
			
			}
		break;	
		}
	
		return "".getBytes();
	}
	
//	public byte[] getRequest () {
//		
//		
//		return "".getBytes();
//	}
//	
//	public void sendReply (byte[] reply, InetAddress clientHost, int clientPort) {
//		
//	}
}
