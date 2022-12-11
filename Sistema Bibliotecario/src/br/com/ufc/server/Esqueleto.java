package br.com.ufc.server;

import java.io.StringReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import br.com.ufc.controller.ServidorController;
import br.com.ufc.message.Mensagem;
import br.com.ufc.model.*;

public class Esqueleto {
	private ServidorController servidor;
	private Gson gson;
	
	public Esqueleto() {
		this.servidor = new ServidorController();
		this.gson = new Gson();
	}
	
	public byte[] login(String args) {
		//desempacotamensagem
		String[] args_sep = args.split(",");
		String senha = args_sep[0];
		int siape = Integer.parseInt(args_sep[1]);
		
		//login retorna boolean
		boolean res = servidor.login(senha, siape);
		
		//empacotar a mensagem json com esse retorno e transformar em bytes
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		
		return empacotaMensagem(response);
	}
	
	public byte[] buscarLivro(String args) {		
		ArrayList<String> res = servidor.buscarLivro(args);
		
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(res);
		
		return empacotaMensagem(response);
	}
	
	public byte[] listarAcervo() {
		ArrayList<String> res = servidor.listarAcervo();
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(res);
		
		return empacotaMensagem(response);
	}
	
	public byte[] cadastrarLivro(String args) {
		JsonReader reader = new JsonReader(new StringReader(args));
		reader.setLenient(true);
		Livro livro = gson.fromJson(reader, Livro.class);
		
		boolean res = servidor.cadastrarLivro(livro);
		
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		return empacotaMensagem(response);
	}

	public byte[] cadastrarUnidade(String args) {
		JsonReader reader = new JsonReader(new StringReader(args));
		reader.setLenient(true);
		Unidade unidade = gson.fromJson(reader, Unidade.class);

		boolean res = servidor.cadastrarUnidade(unidade);
		
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		return empacotaMensagem(response);
	}
	
	public byte[] cadastrarAluno(String args) {
		JsonReader reader = new JsonReader(new StringReader(args));
		reader.setLenient(true);
		Aluno aluno = gson.fromJson(reader, Aluno.class);
		boolean res = servidor.cadastrarAluno(aluno);
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		return empacotaMensagem(response);
	}
	
	public byte[] alugar(String args) {
		String[] args_sep = args.split(",");
		int numAcv = Integer.parseInt(args_sep[0]);
		int matricula = Integer.parseInt(args_sep[1]);
		
		boolean res = servidor.alugar(numAcv, matricula);
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		return empacotaMensagem(response);
	}
	
	public byte[] receberEmprestimo(String args) {
		String[] args_sep = args.split(",");
		int id = Integer.parseInt(args_sep[0]);
		int matricula = Integer.parseInt(args_sep[1]);
		
		boolean res = servidor.receberEmprestimo(id, matricula);
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		return empacotaMensagem(response);
	}
	
	public byte[] empacotaMensagem(Mensagem msg) {
		String json = gson.toJson(msg);
		return json.getBytes();
	}
}