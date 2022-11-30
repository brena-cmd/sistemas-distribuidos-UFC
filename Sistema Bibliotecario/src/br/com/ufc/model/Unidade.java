package br.com.ufc.model;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Unidade{
	private int numReg;
	private int numAcv;
	
	public Unidade() {
		this.numReg = 0;
		this.numAcv = 0;
	}
	
	public int getNumReg() {
		return numReg;
	}
	public void setNumReg(int numReg) {
		this.numReg = numReg;
	}
	public int getNumAcv() {
		return numAcv;
	}
	public void setNumAcv(int numAcv) {
		this.numAcv = numAcv;
	}
	
//	public int getMatricula() {
//		return matricula;
//	}
//	public void setMatricula(int matricula) {
//		this.matricula = matricula;
//	}
//	public Date getDataEmprestimo() throws ParseException {
//		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmprestimo);
//		return date;
//	}
//	public void setDataEmprestimo(String dataEmprestimo) {
//		this.dataEmprestimo = dataEmprestimo;
//	}
//	public Date getDataDevolucao() throws ParseException {
//		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataDevolucao);
//		return date;
//	}
//	public void setDataDevolucao(String dataDevolucao) {
//		this.dataDevolucao = dataDevolucao;
//	}
//	public int getQtdRenovacao() {
//		return qtdRenovacao;
//	}
//	public void setQtdRenovacao(int qtdRenovacao) {
//		this.qtdRenovacao = qtdRenovacao;
//	}	
}
