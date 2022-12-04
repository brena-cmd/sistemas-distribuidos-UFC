package br.com.ufc.controller;

import br.com.ufc.connection.ConnectionPSQL;
import br.com.ufc.dao.*;
import br.com.ufc.model.*;

public class TelefoneController {
	private ConnectionPSQL connectionPSQL;
	private TelefoneDAO telefone;
	
	public TelefoneController() {
		this.connectionPSQL = new ConnectionPSQL();
		this.telefone = new TelefoneDAO(connectionPSQL);
	}
	
	public boolean inserir(Usuario usuario) {
		for(int a = 0; a < usuario.getTelefone().size(); a++) {
			if(!telefone.inserir(usuario.getTelefone().get(a), usuario))
				return false;
		}
		return true;
	}
}
