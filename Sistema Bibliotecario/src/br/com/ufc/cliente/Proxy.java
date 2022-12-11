package br.com.ufc.cliente;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import br.com.ufc.exceptions.ServerUnavaibleException;
import br.com.ufc.message.Mensagem;
import br.com.ufc.model.*;

public class Proxy {
	private int requestID = 1;
	private Gson gson = new Gson();

	public byte[] doOperation(String objRef, String methodId, byte[] args) {
		Mensagem msg = new Mensagem();
		msg.setMessageType(0);
		msg.setRequestId(this.requestID);
		msg.setObjectReference(objRef);
		msg.setMethodId(methodId);

		String arguments = new String(args);
		
		if(msg.getMethodId().equals("cadastrarAluno") || msg.getMethodId().equals("cadastrarLivro")|| msg.getMethodId().equals("cadastrarUnidade")){
			//arguments = gson.toJson(arguments);
			msg.setArgs(arguments);
		}else{
			String[] args_sep = arguments.split(",");

			for (String a : args_sep) {
				msg.setArgs(a);
			}
		}
			
		// mensagem empacotada
		String json = gson.toJson(msg);

		// envia para servidor e recebe do servidor
		byte[] response = "".getBytes();

		// envia requisição
		try {
			response = UDPCliente.sendRequest(json);
		} catch (ServerUnavaibleException e) {
			//e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		// desempacota resposta
		String responseUnpacked = new String(response, StandardCharsets.UTF_8);
		JsonReader reader = new JsonReader(new StringReader(responseUnpacked));
		reader.setLenient(true);
		msg = gson.fromJson(reader, Mensagem.class);
		
		String jsonArgs = "";
		// Transforma a lista de args em JSON
		if (msg != null) {
			Type type = new TypeToken<ArrayList<String>>() {}.getType();
			jsonArgs = gson.toJson(msg.getArgs(), type);
		}

		// incrementa do requestID
		requestID += 1;

		// retorna os argumentos da resposta para o metodo proxy em JSON
		return jsonArgs.getBytes();
	}

	public ArrayList<String> jsonToArray(String json) {
		JsonReader reader = new JsonReader(new StringReader(json));
		reader.setLenient(true);
		Type type = (new TypeToken<ArrayList<String>>() {}).getType();
		ArrayList<String> b = gson.fromJson(reader, type);

		return b;
	}

	public boolean login(String senha, int siape) {
		String args = senha + ',' + String.valueOf(siape);
		try {
			byte[] res = doOperation("servidor", "login", args.getBytes());
			String response = new String(res, StandardCharsets.UTF_8);

			ArrayList<String> arrArgs = jsonToArray(response);

			// se a response for true retorna true, se não retorna false

			if (arrArgs.get(0).equals("true"))

				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println(e);
		}
		return false;
	}

	public ArrayList<String> buscarLivro(String titulo) {
		byte[] res = doOperation("servidor", "buscarLivro", titulo.getBytes());
		String response = new String(res, StandardCharsets.UTF_8);

		return jsonToArray(response);
	}

	public ArrayList<String> listarAcervo() {
		byte[] args = "".getBytes();
		byte[] res = doOperation("servidor", "listarAcervo", args);

		String response = new String(res, StandardCharsets.UTF_8);

		return jsonToArray(response);
	}

	public boolean cadastrarLivro(Livro livro) {
		String livroJson = gson.toJson(livro);
		byte[] args = livroJson.getBytes();
		byte[] res = doOperation("servidor", "cadastrarLivro", args);

		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);

		// se a response for true retorna true, se não retorna false
		if (arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}

	public boolean cadastrarUnidade(Unidade unidade) {
		String unidadeJson = gson.toJson(unidade);
		byte[] args = unidadeJson.getBytes();
		byte[] res = doOperation("servidor", "cadastrarUnidade", args);

		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);

		// se a response for true retorna true, se não retorna false
		if (arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}

	public boolean cadastrarAluno(Aluno aluno) {
		String alunoJson = gson.toJson(aluno);
		byte[] args = alunoJson.getBytes();
		byte[] res = doOperation("servidor", "cadastrarAluno", args);

		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);

		// se a response for true retorna true, se não retorna false
		if (arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}

	public boolean alugar(int numAcv, int matricula) {
		byte[] args = ("" + numAcv + "," + matricula).getBytes();
		byte[] res = doOperation("servidor", "alugar", args);

		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);

		// se a response for true retorna true, se não retorna false
		if (arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}

	public boolean receberEmprestimo(int id, int matricula) {
		byte[] args = (id + "," + matricula).getBytes();
		byte[] res = doOperation("servidor", "receberEmprestimo", args);

		String response = new String(res, StandardCharsets.UTF_8);
		ArrayList<String> arrArgs = jsonToArray(response);

		// se a response for true retorna true, se não retorna false
		if (arrArgs.get(0).equals("true"))
			return true;
		else
			return false;
	}
}