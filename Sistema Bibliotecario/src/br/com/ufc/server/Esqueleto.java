package br.com.ufc.server;

import br.com.ufc.controller.ServidorController;


public class Esqueleto {
	private ServidorController servidor;
	
	public Esqueleto() {
		this.servidor = new ServidorController();
	}
	
	public byte[] login(String args) {
		return "".getBytes();
	}
	
	public byte[] buscarLivro(String args) {
		return "".getBytes();
	}
	
	public byte[] listarAcervo() {
		return "".getBytes();
	}
	
	public byte[] cadastrarLivro(String args) {
		return "".getBytes();
	}
	
	public byte[] cadastrarAluno(String args) {
		return "".getBytes();
	}
	
	public byte[] alugar(String args) {
		return "".getBytes();
	}
	
	public byte[] receberEmprestimo(String args) {
		return "".getBytes();
	}

}
