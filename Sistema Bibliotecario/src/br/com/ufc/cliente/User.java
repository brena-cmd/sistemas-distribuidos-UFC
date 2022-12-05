package br.com.ufc.cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import br.com.ufc.connection.ConnectionPSQL;

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
		System.out.print("Senha: ");
		obj.nextLine();
		String senha = obj.nextLine();
		System.out.println("\n	1 - Login");
		System.out.println("	2 - Voltar");
		System.out.println("\n--------------------------------");
		System.out.print("\nDigite a opção desejada: ");

		do {
			opcao = obj.nextInt();
			if (opcao == 1) {
				if (conServidor.login(senha, siape))
					menuServidor();
				else {
					System.out.println("Senha ou matrícula inválida. Tente novamente mais tarde.");
					telaLogin();
					break;
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
				for (String acv : conServidor.listarAcervo())
					System.out.println(acv);
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
				titulo = obj.nextLine();
				if (conServidor.buscarLivro(titulo) != null)
					for (String livro : conServidor.buscarLivro(titulo))
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
				System.out.println("------------Cadastro Aluno------------");
				obj.nextLine();
				System.out.print("Nome: ");
				String nome = obj.nextLine();
				System.out.print("Senha: ");
				String senha = obj.nextLine();
				System.out.print("E-mail: ");
				String email = obj.nextLine();
				System.out.print("CPF: ");
				String cpf = obj.nextLine();
				System.out.print("Data de Nascimento(yyyy-mm-dd): ");
				String data_nasc = obj.nextLine();
				System.out.print("Rua: ");
				String rua = obj.nextLine();
				System.out.print("Numero: ");
				String numero = obj.nextLine();
				System.out.print("Cidade: ");
				String cidade = obj.nextLine();
				System.out.print("Estado: ");
				String estado = obj.nextLine();
				System.out.print("Matricula: ");
				int matricula = obj.nextInt();
				obj.nextLine();
				System.out.print("Curso: ");
				String curso = obj.nextLine();
				System.out.print("DDD: ");
				String ddd = obj.nextLine();
				System.out.print("Número, lembre-se do 9: ");
				String num = obj.nextLine();

				boolean suc_cad = conServidor.cadastrarAluno(nome, senha, email, cpf, data_nasc, rua, numero, cidade,
						estado, matricula, curso, ddd, num);
				if (suc_cad) {
					System.out.println("Aluno cadastrado com sucesso!");
				} else {
					System.out.println("Erro ao cadastrar aluno!");
				}
				cadastros();
			} else if (opcao == 2) {
				System.out.println("------------Cadastro Livros------------");
				obj.nextLine();
				System.out.print("Titulo: ");
				String titulo = obj.nextLine();
				System.out.print("Número do acervo: ");
				int numAcv = obj.nextInt();
				obj.nextLine();
				System.out.print("Edição: ");
				int edicao = obj.nextInt();
				obj.nextLine();
				System.out.print("Ano de Lançamento: ");
				String ano_lancamento = obj.nextLine();
				System.out.print("Quantidade de livros: ");
				int quantidade = obj.nextInt();
				boolean suc_cad_livro = conServidor.cadastrarLivro(titulo, numAcv, edicao, ano_lancamento, quantidade);
				if (suc_cad_livro) {
					for (int a = 0; a < quantidade; a++) {
						System.out.println("Unidade " + (a + 1) + ":");
						System.out.print("Numero de registro: ");
						int numReg = obj.nextInt();
						if (conServidor.cadastrarUnidade(numReg, numAcv)) {
							System.out.println("Unidade" + numReg + " cadastrada!");
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
		// System.out.println("Conectado");

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