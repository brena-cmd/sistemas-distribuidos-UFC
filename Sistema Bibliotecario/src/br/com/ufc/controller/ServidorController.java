package br.com.ufc.controller;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;
import br.com.ufc.controller.LivroController;

public class ServidorController {
	private ConnectionPSQL connectionPSQL;
	private ServidorDAO servidordao;
	private LivroController conLivro;
	private AlunoController conAluno;
	private EmprestimoController conEmprestimo;
	private UnidadeController conUnidade;
	
	public ServidorController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.servidordao = new ServidorDAO(connectionPSQL);
		this.conLivro = new LivroController();
		this.conAluno = new AlunoController();
		this.conEmprestimo = new EmprestimoController();
		this.conUnidade = new UnidadeController();
	}
	
	public boolean login(String senha, int siape) {
		Servidor servidor = servidordao.buscar(senha, siape);
		if(servidor != null)
			return true;
		return false;
	}
	
	public void buscarLivro(String titulo) {
		conLivro.buscar(titulo);
	}
	
	public void listarAcervo() {
		conLivro.listarAll();
	}
	
	public void cadastrarLivro() {
		conLivro.cadastrarLivro();
	}
	
	public void cadastrarAluno() {
		conAluno.cadastrarAluno();
	}
	
	public boolean alugar(int numAcv, int matricula) {
		Aluno aluno = conAluno.buscar(matricula);
		ArrayList<Unidade> unidades = conUnidade.buscarNumAcv(numAcv);
		ArrayList<Emprestimo> emprestimos = conEmprestimo.getEmprestimos();
		boolean verificador = false;
		for(int a = 0; a < unidades.size(); a++) {
			verificador = true;
			for(int x = 0; x < emprestimos.size(); x++) {
				if(emprestimos.get(x).getNumReg() == unidades.get(a).getNumReg()) {
					verificador = false;
					break;
				}
			}
			if(verificador) {
				conEmprestimo.alugar(aluno, unidades.get(a));
				return true;
			}else {
				return false;
			}
		}
		
		return false;
		
//		if(!verificador) {
//			System.out.println("Livro não disponível. Gostaria de realizar uma reserva?");
//			System.out.println("\t 1 - Sim");
//			System.out.println("\t 2 - Não");
//			System.out.print("Digite a opção: ");
//			int opcao;
//			opcao = obj.nextInt();
//			
//			if(opcao == 1) {
//				conEmprestimo.reservar(numAcv, matricula);
//			}else
//				return;
//		}
	}
	
	public boolean receberEmprestimo(int id, int matricula) {
		Aluno aluno = conAluno.buscar(matricula);
		ArrayList<Emprestimo> emprestimos = conEmprestimo.getEmprestimos(aluno);
		for(int a = 0; a < emprestimos.size(); a++) {
			if(emprestimos.get(a).getNumReg() == id) {
				if(conEmprestimo.getDebito(emprestimos.get(a)) == 0)
					conEmprestimo.removerEmprestimo(emprestimos.get(a));
					return true;
			}
		}
		
		return false;
	}
}