package br.com.ufc.controller;

import java.util.ArrayList;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;

public class UnidadeController {
	private ConnectionPSQL connectionPSQL;
	private UnidadeDAO unidadedao;

	public UnidadeController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.unidadedao = new UnidadeDAO(connectionPSQL);
	}

	public boolean cadastrarUnidade(Unidade unidade) {
		if (!unidadedao.inserir(unidade))
			return false;
		return true;
	}

	public Livro buscarRegistro(int reg) {
		Livro livro = unidadedao.buscar(reg);
		return livro;
	}

	public ArrayList<Unidade> buscarNumAcv(int numacv) {
		return unidadedao.listaUnidades(numacv);
	}
}
