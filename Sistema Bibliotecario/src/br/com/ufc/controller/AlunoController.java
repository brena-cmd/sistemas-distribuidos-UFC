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
	
	public void cadastrarAluno() {
		Usuario usuario = new Usuario();
		Aluno aluno = new Aluno();
		int qtd;
		System.out.println("------------Cadastro Aluno------------");
		System.out.print("Nome: ");
		usuario.setNome(obj.nextLine());
		System.out.print("Senha: ");
		usuario.setSenha(obj.nextLine());
		System.out.print("E-mail: ");
		usuario.setEmail(obj.nextLine());
		System.out.print("CPF: ");
		usuario.setCpf(obj.nextLine());
		System.out.print("Data de Nascimento(yyyy-mm-dd): ");
		usuario.setDataNasc(obj.nextLine());
		System.out.print("Rua: ");
		usuario.setRua(obj.nextLine());
		System.out.print("Numero: ");
		usuario.setNumero(obj.nextLine());
		System.out.print("Cidade: ");
		usuario.setCidade(obj.nextLine());
		System.out.print("Estado: ");
		usuario.setEstado(obj.nextLine());
		System.out.print("Matricula: ");
		aluno.setMatricula(obj.nextInt());
		System.out.print("Curso: ");
		aluno.setCurso(obj.nextLine());
		aluno.setCurso(obj.nextLine());
		System.out.print("Quantos telefones deseja inserir? ");
		qtd = obj.nextInt();
		for(int a = 0; a < qtd; a++) {
			Telefone telefone = new Telefone();
			System.out.print("DDD: ");
			telefone.setDdd(obj.nextLine());
			telefone.setDdd(obj.nextLine());
			System.out.print("Número, lembre-se do 9: ");
			telefone.setNumero(obj.nextLine());
			usuario.setTelefone(telefone);
		}
		if(alunodao.inserir(usuario, aluno)) {
			if(conTelefone.inserir(usuario)) {
				System.out.println("Aluno " + usuario.getNome() + " cadastrado com sucesso!");
			}else {
				System.out.println("Erro, aluno não cadastrado.");
			}
		}else {
			System.out.println("Erro, aluno não cadastrado.");
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