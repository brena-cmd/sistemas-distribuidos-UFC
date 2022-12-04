package br.com.ufc.controller;

import java.util.ArrayList;

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
	
	public ArrayList<String> buscarLivro(String titulo) {
		return conLivro.buscar(titulo);
	}
	
	public ArrayList<String> listarAcervo() {
		return conLivro.listarAll();
	}
	
	public boolean cadastrarLivro(String titulo, int numAcv, int edicao, String ano_lancamento, int quantidade) {
		return conLivro.cadastrarLivro(titulo, numAcv, edicao, ano_lancamento, quantidade);
	}
	
	public boolean cadastrarAluno(String nome, String senha, String email, String cpf, String data_nasc, String rua, String numero, String cidade, String estado, int matricula, String curso, String ddd, String num) {
		return conAluno.cadastrarAluno(nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado, matricula, curso, ddd, num);
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