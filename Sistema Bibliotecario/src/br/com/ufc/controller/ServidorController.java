package br.com.ufc.controller;

import java.util.ArrayList;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;

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

		if (servidor != null)
			return true;
		return false;
	}

	public ArrayList<String> buscarLivro(String titulo) {
		return conLivro.buscar(titulo);
	}

	public ArrayList<String> listarAcervo() {
		return conLivro.listarAll();
	}

	public boolean cadastrarLivro(Livro livro) {
		return conLivro.cadastrarLivro(livro);
	}

	public boolean cadastrarUnidade(Unidade unidade) {
		return conUnidade.cadastrarUnidade(unidade);
	}

	public boolean cadastrarAluno(Aluno aluno) {
		return conAluno.cadastrarAluno(aluno);
	}

	public boolean alugar(int numAcv, int matricula) {
		Aluno aluno = conAluno.buscar(matricula);
		ArrayList<Unidade> unidades = conUnidade.buscarNumAcv(numAcv);
		ArrayList<Emprestimo> emprestimos = conEmprestimo.getEmprestimos();

		boolean verificador = false;

		for (int a = 0; a < unidades.size(); a++) {
			verificador = true;
			for (int x = 0; x < emprestimos.size(); x++) {
				if (emprestimos.get(x).getNumReg() == unidades.get(a).getNumReg()) {
					verificador = false;
					break;
				}
			}
			if (verificador) {
				return conEmprestimo.alugar(aluno, unidades.get(a));
			} else {
				return false;
			}
		}

		return false;
	}

	public boolean receberEmprestimo(int id, int matricula) {
		Aluno aluno = conAluno.buscar(matricula);
		ArrayList<Emprestimo> emprestimos = conEmprestimo.getEmprestimos(aluno);
		for (int a = 0; a < emprestimos.size(); a++) {
			if (emprestimos.get(a).getNumReg() == id) {
				if (conEmprestimo.getDebito(emprestimos.get(a)) == 0)
					conEmprestimo.removerEmprestimo(emprestimos.get(a));
				return true;
			}
		}

		return false;
	}
}