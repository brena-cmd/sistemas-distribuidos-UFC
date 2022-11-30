package br.com.ufc.cliente;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.ufc.controller.AlunoController;
import br.com.ufc.controller.EmprestimoController;
import br.com.ufc.controller.LivroController;
import br.com.ufc.controller.UnidadeController;
import br.com.ufc.dao.ServidorDAO;
import br.com.ufc.model.Aluno;
import br.com.ufc.model.Emprestimo;
import br.com.ufc.model.Servidor;
import br.com.ufc.model.Unidade;
import com.google.gson.Gson;

public class Proxy {
	private LivroController conLivro;
	private AlunoController conAluno;
	private EmprestimoController conEmprestimo;
	private UnidadeController conUnidade;
	private Scanner obj;
	Gson gson = new Gson();	
	
	public boolean login(String senha, int siape) {
		Mensagem msg = new Mensagem();
		msg.setOp("login");
		msg.setValues(senha);
		msg.setValues(String.valueOf(siape));
		
		String json = gson.toJson(msg);
		
		return true;
	}
	
	public void buscarLivro(String titulo) {
		Mensagem msg = new Mensagem();
		msg.setOp("buscarLivro");
		msg.setValues(titulo);
		
		String json = gson.toJson(msg);
	}
	
	public void listarAcervo() {
		Mensagem msg = new Mensagem();
		msg.setOp("listarAcervo");
		
		String json = gson.toJson(msg);
	}
	
	public void cadastrarLivro() {
		Mensagem msg = new Mensagem();
		msg.setOp("cadastrarLivro");
		
		String json = gson.toJson(msg);
	}
	
	public void cadastrarAluno() {
		Mensagem msg = new Mensagem();
		msg.setOp("cadastrarAluno");
		
		String json = gson.toJson(msg);
	}
	
	public void alugar(int numAcv, int matricula) {
		Mensagem msg = new Mensagem();
		msg.setOp("alugar");
		msg.setValues(String.valueOf(numAcv));
		msg.setValues(String.valueOf(matricula));
		
		String json = gson.toJson(msg);
	}
	
	public void receberEmprestimo(int id, int matricula) {
		Mensagem msg = new Mensagem();
		msg.setOp("receberEmprestimo");
		msg.setValues(String.valueOf(id));
		msg.setValues(String.valueOf(matricula));
		
		String json = gson.toJson(msg);
	}
}