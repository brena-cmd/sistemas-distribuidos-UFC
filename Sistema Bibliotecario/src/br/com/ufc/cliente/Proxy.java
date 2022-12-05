package br.com.ufc.cliente;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import br.com.ufc.message.Mensagem;

public class Proxy {
	private int requestID=1;
	private Gson gson = new Gson();
	public byte[] doOperation(String objRef, String methodId, byte[] args) {
		Mensagem msg = new Mensagem();
		msg.setMessageType(0);
		msg.setRequestId(this.requestID);
		msg.setObjectReference(objRef);
		msg.setMethodId(methodId);
		
		String arguments = new String(args);
		String[] args_sep = arguments.split(",");
		
		for(String a:args_sep) {
			msg.setArgs(a);
		}
		
		//mensagem empacotada
		String json = gson.toJson(msg);
		//envia para servidor e recebe do servidor
		byte[] response = UDPCliente.sendRequest(json);

		//desempacota resposta
		String responseUnpacked = new String(response, StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(new StringReader(responseUnpacked));
		reader.setLenient(true);
		msg = gson.fromJson(reader, Mensagem.class);
	
		//Transforma a lista de args em JSON
		Type type = new TypeToken<ArrayList<String>>() {}.getType();
		String jsonArgs = gson.toJson(msg.getArgs(), type);
		
		//incrementa do requestID
		requestID+=1;
		
		//retorna os argumentos da resposta para o metodo proxy em JSON
		return jsonArgs.getBytes();
	}
	
	public ArrayList<String> jsonToArray(String json){
		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);
		Type type = (new TypeToken<ArrayList<String>>() {}).getType();
		ArrayList<String> b = gson.fromJson(reader, type);

		return b;
	}
	
	public boolean login(String senha, int siape) {
		String args = senha + ',' + String.valueOf(siape);
		byte[] res = doOperation("servidor","login",args.getBytes());
		
		String response = new String(res, StandardCharsets.UTF_8);
		
		ArrayList<String> arrArgs = jsonToArray(response);
		
		//se a response for true retorna true, se não retorna false
		if(arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}

	public ArrayList<String>  buscarLivro(String titulo) {
		byte[] res = doOperation("servidor","login",titulo.getBytes());
		String response = new String(res, StandardCharsets.UTF_8);
		
		return jsonToArray(response);
	}
	
	public ArrayList<String> listarAcervo() {
		byte [] args = "".getBytes();
		byte[] res = doOperation("servidor","listarAcervo",args);
		
		String response = new String(res, StandardCharsets.UTF_8);
		
		return jsonToArray(response);
	}
	
	public boolean cadastrarLivro(String titulo, int numAcv, int edicao, String ano_lancamento, int quantidade) {
		byte[] args = (titulo + "," + String.valueOf(numAcv) + "," + String.valueOf(edicao) + "," + ano_lancamento + "," + String.valueOf(quantidade)).getBytes();
		byte[] res = doOperation("servidor","cadastrarLivro",args);
		
		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);
		
		//se a response for true retorna true, se não retorna false
		if(arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}

	public boolean cadastrarUnidade(int numReg, int numAcv) {
		byte[] args = (String.valueOf(numReg) + "," + String.valueOf(numAcv)).getBytes();
		byte[] res = doOperation("servidor","cadastrarUnidade",args);
		
		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);
		
		//se a response for true retorna true, se não retorna false
		if(arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}
	
	public boolean cadastrarAluno(String nome, String senha, String email, String cpf, String data_nasc, String rua, String numero, String cidade, String estado, int matricula, String curso, String ddd, String num) {
		byte[] args = (nome + "," + senha + "," + email + "," + cpf + "," + data_nasc + "," + rua + "," + numero + "," + cidade + "," + estado + "," + matricula + "," + curso + "," + ddd  + "," + num).getBytes();
		byte[] res = doOperation("servidor","cadastrarAluno",args);
		
		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);
		
		//se a response for true retorna true, se não retorna false
		if(arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}
	
	public boolean alugar(int numAcv, int matricula) {
		byte [] args = (""+numAcv+","+matricula).getBytes();
		byte[] res = doOperation("servidor","alugar",args);
		
		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);
		
		//se a response for true retorna true, se não retorna false
		if(arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}
	
	public boolean receberEmprestimo(int id, int matricula) {
		byte [] args = (id+","+matricula).getBytes();
		byte[] res = doOperation("servidor","receberEmprestimo",args);
		
		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);
		
		//se a response for true retorna true, se não retorna false
		if(arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}
}