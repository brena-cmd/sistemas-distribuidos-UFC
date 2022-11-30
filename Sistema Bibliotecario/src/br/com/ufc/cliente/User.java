package br.com.ufc.cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.controller.*;
import br.com.ufc.model.*;

public class User {
	private Scanner obj;
	private AlunoController conAluno;
	private ServidorController conServidor;
	Aluno alunoLogado = new Aluno();
	Servidor servidorLogado = new Servidor();
	Proxy proxy = new Proxy();
	
	public User() {
		this.obj = new Scanner(System.in);
		this.conAluno = new AlunoController();
		this.conServidor = new ServidorController();
		this.proxy = new Proxy();
	}
	
	public void telaLogin() throws ParseException {
		int opcao;
		System.out.println("\n------------Login------------");
		System.out.println("\n	1 - Aluno");
		System.out.println("	2 - Servidor");
		System.out.println("	3 - Sair");
		System.out.println("\n-----------------------------");
		System.out.print("\nDigite a opção desejada: ");
		
		do {
			opcao = obj.nextInt();
			if(opcao == 1) {
				loginAluno();
				break;
			}else if(opcao == 2) {
				loginServidor();
				break;
			}else if(opcao == 3)
				System.exit(0);
			else 
				System.out.print("Opção inválida, digite novamente: ");
		}while(true);
	} 
	
	public void loginAluno() throws ParseException {
		int opcao;
		System.out.println("\n------------Aluno------------");
		System.out.print("\nMatricula: ");
		alunoLogado.setMatricula(obj.nextInt());
		System.out.print("Senha: ");
		alunoLogado.setSenha(obj.nextLine());
		alunoLogado.setSenha(obj.nextLine());
		System.out.println("\n	1 - Login");
		System.out.println("	2 - Voltar");
		System.out.println("\n-----------------------------");
		System.out.print("\nDigite a opção desejada: ");
		
		do {
			opcao = obj.nextInt();
			if(opcao == 1) {
				if(conAluno.login(alunoLogado.getSenha(), alunoLogado.getMatricula()))
					menuAluno();
				else {
					System.out.println("Senha ou matrícula inválida. Tente novamente mais tarde.");
					telaLogin();
					break;
				}
			}else if(opcao == 2) {
				telaLogin();
				break;
			}else 
				System.out.print("Opção inválida, digite novamente: ");
		}while(true);
	}
	
	public void loginServidor() throws ParseException {
		int opcao;
		System.out.println("\n------------Servidor------------");
		System.out.print("\nSiape: ");
		servidorLogado.setSiape(obj.nextInt());
		System.out.print("Senha: ");
		servidorLogado.setSenha(obj.nextLine());
		servidorLogado.setSenha(obj.nextLine());
		System.out.println("\n	1 - Login");
		System.out.println("	2 - Voltar");
		System.out.println("\n--------------------------------");
		System.out.print("\nDigite a opção desejada: ");
		
		do {
			opcao = obj.nextInt();
			if(opcao == 1) {
				if(conServidor.login(servidorLogado.getSenha(), servidorLogado.getSiape()))
					menuServidor();
				else {
					System.out.println("Senha ou matrícula inválida. Tente novamente mais tarde.");
					telaLogin();
					break;
				}
				break;
			}else if(opcao == 2) {
				telaLogin();
				break;
			}else 
				System.out.print("Opção inválida, digite novamente: ");
		}while(true);
	}
	
	public void menuAluno() throws ParseException {
		int opcao;
		System.out.println("\n------------Menu Aluno------------");
		System.out.println("\n	1 - Meus livros");
		System.out.println("	2 - Pesquisar livros");
		System.out.println("	3 - Sair");
		System.out.println("\n----------------------------------");
		System.out.print("\nDigite a opção desejada: ");
		
		do {
			opcao = obj.nextInt();
			if(opcao == 1) {
				if(!conAluno.listarEmprestimos(alunoLogado))
					menuAluno();
				else {
					int opcao1;
					System.out.println("\n\t 1 - Renovar");
					System.out.println("\t 2 - Voltar");
					System.out.print("\nDigite a opção: ");
					opcao1 = obj.nextInt();
					if(opcao1 == 1) {
						conAluno.renovarEmprestimo(alunoLogado);
						menuAluno();
					}else
						menuAluno();
				}
				break;
			}else if(opcao == 2) {
				conAluno.buscarLivro();
				menuAluno();
			}else if(opcao == 3) {
				telaLogin();
				break;
			}else 
				System.out.print("Opção inválida, digite novamente: ");
		}while(true);
	}
	
	public void menuServidor() throws ParseException {
		int opcao;
		System.out.println("\n------------Menu Servidor------------");
		System.out.println("\n	1 - Listar Acervo");
		System.out.println("	2 - Recebimetos");
		System.out.println("	3 - Cadastros");
		System.out.println("	4 - Pesquisar");
		System.out.println("	5 - Sair");
		System.out.println("\n-------------------------------------");
		System.out.print("\nDigite a opção desejada: ");
		
		do {
			opcao = obj.nextInt();
			if(opcao == 1) {
				conServidor.listarAcervo();
				menuServidor();
				break;
			}else if(opcao == 2) {
				int id;
				int matricula;
				System.out.print("Digite o número de registro do livro a ser entregue: ");
				id = obj.nextInt();
				System.out.print("Digite a matricula do aluno: ");
				matricula = obj.nextInt();
				conServidor.receberEmprestimo(id, matricula);
				menuServidor();
				break;
			}else if(opcao == 3) {
				cadastros();
				break;
			}else if(opcao == 4) {
				String titulo;
				System.out.print("Digite o titulo do livro que deseja buscar: ");
				titulo = obj.nextLine();
				conServidor.buscarLivro(titulo);
				menuServidor();
				break;
			}else if(opcao == 5) {
				telaLogin();
				break;
			}else 
				System.out.print("Opção inválida, digite novamente: ");
		}while(true);
	}
	
	public void cadastros() throws ParseException {
		int opcao;
		System.out.println("\n---------------Cadastro--------------");
		System.out.println("\n	1 - Aluno");
		System.out.println("	2 - Livro");
		System.out.println("	3 - Emprestimo");
		System.out.println("	4 - Voltar");
		System.out.println("\n-------------------------------------");
		System.out.print("\nDigite a opção desejada: ");
		
		do {
			opcao = obj.nextInt();
			if(opcao == 1) {
				conServidor.cadastrarAluno();
				cadastros();
			}else if(opcao == 2) {
				conServidor.cadastrarLivro();
				cadastros();
			}else if(opcao == 3) {
				int numAcv;
				int matricula;
				System.out.print("Digite o número do acervo:");
				numAcv = obj.nextInt();
				System.out.print("Digite o número da matrícula: ");
				matricula = obj.nextInt();
				conServidor.alugar(numAcv, matricula);
				cadastros();
			}else if(opcao == 4) {
				menuServidor();
			}else
				System.out.print("Opção inválida. Digite novamente: ");
		}while(true);
	}
	
	public static void main(String[] args){
		ConnectionPSQL connection = new ConnectionPSQL();
		Connection a = connection.getConnection();
		System.out.println("Conectado");

		User usr = new User();
		
		try {
			usr.telaLogin();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}