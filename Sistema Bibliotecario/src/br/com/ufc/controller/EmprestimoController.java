package br.com.ufc.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.ufc.connection.*;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;

public class EmprestimoController {
	private ConnectionPSQL connectionPSQL;
	private EmprestimoDAO emprestimodao;
	private UnidadeController conUnidade; 
	
	public EmprestimoController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.emprestimodao = new EmprestimoDAO(connectionPSQL);
		this.conUnidade = new UnidadeController();
	}
	
	public boolean listarEmprestimos(Aluno aluno) throws ParseException{
		ArrayList<Emprestimo> emprestimos = emprestimodao.listarEmprestimos(aluno);
		if(emprestimos != null) {
			System.out.println("");
			for(int a = 0; a < emprestimos.size(); a++) {
				System.out.println("Empréstimo " + emprestimos.get(a).getNumReg() + ":");
				System.out.println("\t Livro: " + conUnidade.buscarRegistro(emprestimos.get(a).getNumReg()).getTitulo());
				System.out.println("\t Data de empréstimo: " + emprestimos.get(a).getStringDataEmp());
				System.out.println("\t Data de devolução: " + emprestimos.get(a).getStringDataDevo());
				System.out.println("\t Quantidade de renovações: " + emprestimos.get(a).getQtdReno());
			}
			return true;
		}else {
			System.out.println("O aluno não tem nenhum empréstimo em aberto.");
			return false;
		}
	}
	
	public ArrayList<Emprestimo> getEmprestimos(Aluno aluno) {
		return emprestimodao.listarEmprestimos(aluno);
	}
	
	public void renovarEmprestimo(Emprestimo emprestimo) {
		try {
			emprestimodao.atualizar(emprestimo);
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}	
	
	public void removerEmprestimo(Emprestimo emprestimo) {
		emprestimodao.remover(emprestimo.getNumReg());
	}
	
	public int getDebito(Emprestimo emprestimo) {
		return (int) emprestimodao.getDebito(emprestimo);
	}
	
	public ArrayList<Emprestimo> getEmprestimos() {
		return emprestimodao.getEmprestimos();
	}
	
	public String pegarData(){
        java.util.Date dataAgora = new java.util.Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        return formatador.format(dataAgora);
    }
	
	public void alugar(Aluno aluno, Unidade unidade) {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setMatricula(aluno.getMatricula());
		emprestimo.setNumReg(unidade.getNumReg());
		String data = pegarData();
		emprestimo.setDataEmp(data);
		try {
			java.util.Date date = emprestimo.getDataEmp();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, 30);
			date = cal.getTime();
			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
			String dataDevo = formatador.format(date);
			emprestimo.setDataDevo(dataDevo);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(emprestimodao.inserir(emprestimo))
			System.out.println("Alugado com sucesso!");
	}
	
	public void reservar(int numacv, int matricula) {
		Reserva reserva = new Reserva();
		reserva.setNumAcv(numacv);
		reserva.setMatricula(matricula);
		reserva.setDataRsv(pegarData());
		try {
			if(emprestimodao.reservar(reserva))
				System.out.println("Reservado com sucesso!");
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}
}