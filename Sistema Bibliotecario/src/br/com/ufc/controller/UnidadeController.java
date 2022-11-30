package br.com.ufc.controller;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;

public class UnidadeController {
	private ConnectionPSQL connectionPSQL;
	private UnidadeDAO unidadedao;
	private Scanner obj;
	
	public UnidadeController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.unidadedao = new UnidadeDAO(connectionPSQL);
		this.obj = new Scanner(System.in);
	}
	
	public boolean inserir(Livro livro) {
		for(int a = 0; a < livro.getQuantidade(); a++) {
			Unidade unidade = new Unidade();
			System.out.println("Unidade " + (a + 1) + ":");
			System.out.print("Numero de registro: ");
			unidade.setNumReg(obj.nextInt());
			unidade.setNumAcv(livro.getNumAcv());
			if(!unidadedao.inserir(unidade))
				return false;
		}
		return true;
	}
	
	public Livro buscarRegistro(int reg) {
		Livro livro = unidadedao.buscar(reg);
		return livro;
	}
	
	public ArrayList<Unidade> buscarNumAcv(int numacv){
		return unidadedao.listaUnidades(numacv);
	}
}
