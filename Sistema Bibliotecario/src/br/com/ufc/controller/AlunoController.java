package br.com.ufc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;

public class AlunoController {
	private ConnectionPSQL connectionPSQL;
	private AlunoDAO alunodao;
	private Scanner obj;
	private TelefoneController conTelefone;
	private EmprestimoController conEmprestimo;
	private LivroController conLivro;
	
	public AlunoController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.alunodao = new AlunoDAO(connectionPSQL);
		this.obj = new Scanner(System.in);
		this.conTelefone = new TelefoneController();
		this.conEmprestimo = new EmprestimoController();
		this.conLivro = new LivroController();
	}
	
	public boolean login(String senha, int matricula) {
		Aluno aluno = alunodao.buscar(senha, matricula);
		if(aluno != null)
			return true;
		return false;
	}
	
	public boolean cadastrarAluno(String nome, String senha, String email, String cpf, String data_nasc, String rua, String numero, String cidade, String estado, int matricula, String curso, String ddd, String num) {
		Aluno aluno = new Aluno();
		
		aluno.setNome(nome);
		aluno.setSenha(senha);
		aluno.setEmail(email);
		aluno.setCpf(cpf);
		aluno.setDataNasc(data_nasc);
		aluno.setRua(rua);
		aluno.setNumero(numero);
		aluno.setCidade(cidade);
		aluno.setEstado(estado);
		aluno.setMatricula(matricula);
		aluno.setCurso(curso);
		
		Telefone telefone = new Telefone();
		telefone.setDdd(ddd);
		telefone.setNumero(num);
		aluno.setTelefone(telefone);			
		
		if(alunodao.inserir(aluno, aluno)) {
			if(conTelefone.inserir(aluno)) {
//				System.out.println("Aluno " + aluno.getNome() + " cadastrado com sucesso!");
				return true;
			}else {
//				System.out.println("Erro, aluno não cadastrado.");
				return false;
			}
		}else {
//			System.out.println("Erro, aluno não cadastrado.");
			return false;
		}
	}
	
	public void buscarLivro() {
		String titulo;
		System.out.print("Digite o titulo do livro que deseja buscar: ");
		titulo = obj.nextLine();
		conLivro.buscar(titulo);
	}
	
	public boolean listarEmprestimos(Aluno aluno) throws ParseException {
		if(conEmprestimo.listarEmprestimos(aluno))
			return true;
		return false;
	}
	
	public void renovarEmprestimo(Aluno aluno) {
		int id;
		System.out.print("Digite o número de registro do livro a ser renovado: ");
		id = obj.nextInt();
		ArrayList<Emprestimo> emprestimos = conEmprestimo.getEmprestimos(aluno);
		for(int a = 0; a < emprestimos.size(); a++) {
			if(emprestimos.get(a).getNumReg() == id) {
				conEmprestimo.renovarEmprestimo(emprestimos.get(a));
				System.out.println("Renovado com sucesso!\n");
				return;
			}
		}
	}

	public Aluno buscar(int matricula) {
		Aluno aluno = alunodao.buscar(matricula);
		if(aluno != null)
			return aluno;
		return null;
	}
}