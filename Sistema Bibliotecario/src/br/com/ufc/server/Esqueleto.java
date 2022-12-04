package br.com.ufc.server;

import java.util.ArrayList;

import com.google.gson.Gson;

import br.com.ufc.controller.ServidorController;
import br.com.ufc.message.Mensagem;

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
		String[] args_sep = args.split(",");
		String titulo = args_sep[0];
		
		ArrayList<String> res = servidor.buscarLivro(titulo);
		
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
		String[] args_sep = args.split(",");
		String titulo = args_sep[0];
		int numAcv = Integer.parseInt(args_sep[1]);
		int edicao = Integer.parseInt(args_sep[2]);
		String ano=args_sep[3];
		int qtd = Integer.parseInt(args_sep[4]);
		
		boolean res = servidor.cadastrarLivro(titulo, numAcv, edicao, ano, qtd);
		
		Mensagem response = new Mensagem();
		response.setMessageType(1);
		response.setArgs(String.valueOf(res));
		return empacotaMensagem(response);
	}
	
	public byte[] cadastrarAluno(String args) {
		String[] args_sep = args.split(",");
		String nome = args_sep[0];
		String senha = args_sep[1];
		String email = args_sep[2];
		String cpf = args_sep[3];
		String data_nasc = args_sep[4];
		String rua = args_sep[5];
		String num = args_sep[6];
		String cidade = args_sep[7];
		String estado = args_sep[8];
		int matricula = Integer.parseInt(args_sep[9]);
		String curso = args_sep[10];
		String ddd = args_sep[11];
		String telefone = args_sep[12];

		boolean res = servidor.cadastrarAluno(nome, senha, email, cpf, data_nasc, rua, num, cidade, estado, matricula, curso, ddd, telefone);
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