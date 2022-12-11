package br.com.ufc.cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.model.*;

public class User {
	private Scanner obj;
	private Proxy conServidor;

	public User() {
		this.obj = new Scanner(System.in);
		this.conServidor = new Proxy();
	}

	public void telaLogin() throws ParseException {
		int opcao;
		System.out.println("\n------------Login------------");
		System.out.println("\n	1 - Servidor");
		System.out.println("	2 - Sair");
		System.out.println("\n-----------------------------");
		System.out.print("\nDigite a opção desejada: ");

		do {
			opcao = obj.nextInt();
			switch (opcao) {
				case 1:
					loginServidor();
					break;
				case 2:
					System.exit(0);
					break;
				default:
					System.out.print("Opção inválida, digite novamente: ");
					break;
			}

		} while (true);
	}

	public void loginServidor() throws ParseException {
		int opcao;
		System.out.println("\n------------Servidor------------");
		System.out.print("\nSiape: ");
		int siape = obj.nextInt();
		obj.nextLine();
		System.out.print("Senha: ");
		String senha = obj.nextLine();

		System.out.println("\n	1 - Login");
		System.out.println("	2 - Voltar");
		System.out.println("\n--------------------------------");
		System.out.print("\nDigite a opção desejada: ");

		do {
			opcao = obj.nextInt();
			if (opcao == 1) {
				try {
					boolean logged = conServidor.login(senha, siape);
					if (logged)
						menuServidor();
					else {
						System.out.println("Senha ou matrícula inválida. Tente novamente mais tarde.");
						telaLogin();
						break;
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			} else if (opcao == 2) {
				telaLogin();
				break;
			} else
				System.out.print("Opção inválida, digite novamente: ");
		} while (true);
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
			if (opcao == 1) {
				ArrayList<String> acervo = conServidor.listarAcervo();
				if (acervo != null) {
					System.out.println("");
					for (String acv : acervo)
						System.out.println(acv);
				}
				menuServidor();
				break;
			} else if (opcao == 2) {
				int id;
				int matricula;
				System.out.print("Digite o número de registro do livro a ser entregue: ");
				id = obj.nextInt();
				System.out.print("Digite a matricula do aluno: ");
				matricula = obj.nextInt();
				if (conServidor.receberEmprestimo(id, matricula)) {
					System.out.println("Entregue com sucesso!");
				} else {
					System.out.println("Erro ao entregar livro!");
				}
				menuServidor();
				break;
			} else if (opcao == 3) {
				cadastros();
				break;
			} else if (opcao == 4) {
				String titulo;
				System.out.print("Digite o titulo do livro que deseja buscar: ");
				obj.nextLine();
				titulo = obj.nextLine();
				ArrayList<String> busca = conServidor.buscarLivro(titulo);
				if (busca != null)
					for (String livro : busca)
						System.out.println(livro);
				menuServidor();
				break;
			} else if (opcao == 5) {
				telaLogin();
				break;
			} else
				System.out.print("Opção inválida, digite novamente: ");
		} while (true);
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
			if (opcao == 1) {
				Aluno aluno = new Aluno();
				System.out.println("\n------------Cadastro Aluno------------");
				obj.nextLine();
				System.out.print("Nome: ");
				aluno.setNome(obj.nextLine());
				System.out.print("Senha: ");
				aluno.setSenha(obj.nextLine());
				System.out.print("E-mail: ");
				aluno.setEmail(obj.nextLine());
				System.out.print("CPF: ");
				aluno.setCpf(obj.nextLine());
				System.out.print("Data de Nascimento(yyyy-mm-dd): ");
				aluno.setDataNasc(obj.nextLine());
				System.out.print("Rua: ");
				aluno.setRua(obj.nextLine());
				System.out.print("Numero: ");
				aluno.setNumero(obj.nextLine());
				System.out.print("Cidade: ");
				aluno.setCidade(obj.nextLine());
				System.out.print("Estado: ");
				aluno.setEstado(obj.nextLine());
				System.out.print("Matricula: ");
				aluno.setMatricula(obj.nextInt());
				obj.nextLine();
				System.out.print("Curso: ");
				aluno.setCurso(obj.nextLine());

				Telefone tel = new Telefone();
				System.out.print("DDD: ");
				tel.setDdd(obj.nextLine());
				System.out.print("Número, lembre-se do 9: ");
				tel.setNumero(obj.nextLine());

				aluno.setTelefone(tel);

				if (conServidor.cadastrarAluno(aluno)) {
					System.out.println("Aluno cadastrado com sucesso!");
				} else {
					System.out.println("Erro ao cadastrar aluno!");
				}
				cadastros();
			} else if (opcao == 2) {
				Livro livro = new Livro();
				System.out.println("\n------------Cadastro Livro------------");
				obj.nextLine();
				System.out.print("Titulo: ");
				livro.setTitulo(obj.nextLine());
				System.out.print("Número do acervo: ");
				livro.setNumAcv(obj.nextInt());
				System.out.print("Edição: ");
				livro.setEdicao(obj.nextInt());
				obj.nextLine();
				System.out.print("Ano de Lançamento: ");
				livro.setAno_lancamento(obj.nextLine());
				System.out.print("Quantidade de livros: ");
				int qtd = obj.nextInt();

				boolean suc_cad_livro = conServidor.cadastrarLivro(livro);

				if (suc_cad_livro) {
					for (int a = 0; a < qtd; a++) {
						Unidade unidade = new Unidade();
						unidade.setNumAcv(livro.getNumAcv());
						System.out.println("Unidade " + (a + 1) + ":");
						System.out.print("Numero de registro: ");
						unidade.setNumReg(obj.nextInt());

						if (conServidor.cadastrarUnidade(unidade)) {
							System.out.println("Unidade" + unidade.getNumReg() + " cadastrada!");
						}
					}
					System.out.println("Livro cadastrado com sucesso!");
				} else {
					System.out.println("Erro ao cadastrar livro!");
				}
				cadastros();
			} else if (opcao == 3) {
				int numAcv;
				int matricula;
				System.out.print("Digite o número do acervo:");
				numAcv = obj.nextInt();
				System.out.print("Digite o número da matrícula: ");
				matricula = obj.nextInt();
				boolean suc_alugar = conServidor.alugar(numAcv, matricula);
				if (suc_alugar) {
					System.out.println("Livro alugado com sucesso!");
				} else {
					System.out.println("Erro ao alugar livro!");
				}
				cadastros();
			} else if (opcao == 4) {
				menuServidor();
			} else
				System.out.print("Opção inválida. Digite novamente: ");
		} while (true);
	}

	public static void main(String[] args) {
		ConnectionPSQL connection = new ConnectionPSQL();
		Connection a = connection.getConnection();

		User usr = new User();

		try {
			usr.telaLogin();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}