package br.com.ufc.controller;

import java.util.ArrayList;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;
import br.com.ufc.connection.ConnectionPSQL;

public class LivroController {
	private ConnectionPSQL connectionPSQL;
	private LivroDAO livrodao;

	public LivroController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.livrodao = new LivroDAO(connectionPSQL);
	}

	public ArrayList<String> buscar(String titulo) {
		ArrayList<Livro> livros = livrodao.buscar(titulo);
		ArrayList<String> res = new ArrayList<String>();
		if (livros != null) {
			for (int a = 0; a < livros.size(); a++) {
				String aux = "Livro " + (a + 1) + ": " + livros.get(a).getTitulo() + "\n" + "\t " + livros.get(a).getEdicao() + "\n" + "\t " + livros.get(a).getAno_lancamento();
				res.add(aux);
			}
			return res;
		}
		return null;
	}

	public ArrayList<String> listarEspecificos(ArrayList<Livro> livros) {
		ArrayList<String> res = new ArrayList<String>();

		return res;
	}

	public ArrayList<String> listarAll() {
		ArrayList<Livro> livros = livrodao.listarLivros();
		ArrayList<String> res = new ArrayList<String>();
		if (livros != null) {
			for (int a = 0; a < livros.size(); a++) {
				String aux = "Livro " + (a + 1) + ": " + livros.get(a).getTitulo() + "\n" + "\t " + livros.get(a).getEdicao() + "\n" + "\t " + livros.get(a).getAno_lancamento();
				res.add(aux);
			}
			return res;
		}
		return null;
	}

	public boolean cadastrarLivro(Livro livro) {
		if (livrodao.inserir(livro))
			return true;
		else
			return false;
	}
}