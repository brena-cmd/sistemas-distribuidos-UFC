package br.com.ufc.controller;

import java.util.ArrayList;
import java.util.Scanner;
import br.com.ufc.controller.*;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;
import br.com.ufc.connection.ConnectionPSQL;

public class LivroController {
	private ConnectionPSQL connectionPSQL;
	private LivroDAO livrodao;
	private Scanner obj;
	private UnidadeController conUnidade;
	
	public LivroController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.livrodao = new LivroDAO(connectionPSQL);
		this.obj = new Scanner(System.in);
		this.conUnidade = new UnidadeController();
	}
	
	public ArrayList<String> buscar(String titulo){
		ArrayList<Livro> livros = livrodao.buscar(titulo);
		
		if(livros != null)
			return listarEspecificos(livros);
		else
			System.out.println("Livro não encontrado.");
		return null;
	}
	
	public ArrayList<String> listarEspecificos(ArrayList<Livro> livros) {
		ArrayList<String> res = new ArrayList<String>();
		System.out.println("");
		for(int a = 0; a < livros.size(); a++) {
			res.add((livros.get(a).toString()));
//			System.out.println("Livro " + (a + 1) + ": " + livros.get(a).getTitulo());
//			System.out.println("\t " + livros.get(a).getEdicao());
//			System.out.println("\t " + livros.get(a).getAno_lancamento());
		}
		return res;
	}
	
	public ArrayList<String> listarAll() {
		ArrayList<Livro> livros = livrodao.listarLivros();
		ArrayList<String> res = new ArrayList<String>();
		if(livros != null) {
			System.out.println("");
			for(int a = 0; a < livros.size(); a++) {
				res.add((livros.get(a).toString()));
//				System.out.println("Livro " + (a + 1) + ": " + livros.get(a).getTitulo());
//				System.out.println("\t " + livros.get(a).getEdicao());
//				System.out.println("\t " + livros.get(a).getAno_lancamento());
				
			}
			return res;
		}else
			System.out.println("Nenhum livro no acervo.");
		return null;
	}
	
	public boolean cadastrarLivro(String titulo, int numAcv, int edicao, String ano_lancamento, int quantidade) {
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setNumAcv(numAcv);
		livro.setEdicao(edicao);
		livro.setAno_lancamento(ano_lancamento);
		livro.setQuantidade(quantidade);
		
		if(!livrodao.inserir(livro) || conUnidade.inserir(livro)) {
			return false;
		}
		else {
			return true;
		}
	}
}