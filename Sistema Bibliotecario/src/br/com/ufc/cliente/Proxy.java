package br.com.ufc.cliente;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.gson.Gson;

import br.com.ufc.controller.AlunoController;
import br.com.ufc.controller.EmprestimoController;
import br.com.ufc.controller.LivroController;
import br.com.ufc.controller.UnidadeController;

public class Proxy {
	private LivroController conLivro;
	private AlunoController conAluno;
	private EmprestimoController conEmprestimo;
	private UnidadeController conUnidade;
	private Scanner obj;
	private int requestID=1;
	Gson gson = new Gson();
	
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
		//envia para servidor
		//recebe do servidor
		//desempacota resposta
		
		//incrementa do requestID
		requestID+=1;
		
		//retorna a resposta do servidor
		byte[] res = "".getBytes();
		return res;
	}
	
	public boolean login(String senha, int siape) {
		String args = senha+","+String.valueOf(siape);
		byte[] res = doOperation("servidor","login",args.getBytes());
		String response = new String(res, StandardCharsets.UTF_8);
		
		//se a response for true retorna true, se não retorna false
		//fazer isso depois que souber como o servidor retorna.
		return true;
	}
	//add retorno
	public void buscarLivro(String titulo) {
		byte[] res = doOperation("servidor","login",titulo.getBytes());
		String response = new String(res, StandardCharsets.UTF_8);
	}
	
	public void listarAcervo() {
		byte [] args = "".getBytes();
		byte[] res = doOperation("servidor","listarAcervo",args);
	}
	
	public void cadastrarLivro() {
		byte [] args = "".getBytes();
		byte[] res = doOperation("servidor","cadastrarLivro",args);
	}
	
	public void cadastrarAluno() {
		byte [] args = "".getBytes();
		byte[] res = doOperation("servidor","cadastrarAluno",args);
	}
	
	public void alugar(int numAcv, int matricula) {
		byte [] args = (""+numAcv+","+matricula).getBytes();
		byte[] res = doOperation("servidor","alugar",args);
	}
	
	public void receberEmprestimo(int id, int matricula) {
		byte [] args = (""+id+","+matricula).getBytes();
		byte[] res = doOperation("servidor","receberEmprestimo",args);
	}
}