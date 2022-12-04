package br.com.ufc.model;

public class Livro {
	private int numAcv;
	private String titulo;
	private String ano_lancamento;
	private int edicao;
	private int quantidade;
	
	public int getNumAcv() {
		return numAcv;
	}

	public void setNumAcv(int numAcv) {
		this.numAcv = numAcv;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAno_lancamento() {
		return ano_lancamento;
	}
	
	public void setAno_lancamento(String ano_lancamento) {
		this.ano_lancamento = ano_lancamento;
	}
	
	public int getEdicao() {
		return edicao;
	}
	
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
}
