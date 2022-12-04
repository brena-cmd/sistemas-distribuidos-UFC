package br.com.ufc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.model.Telefone;
import br.com.ufc.model.Usuario;
import br.com.ufc.connection.ConnectionPSQL;

public class TelefoneDAO {
	private ConnectionPSQL connectionPSQL;
	private Connection connection;
	
	public TelefoneDAO(ConnectionPSQL connectionPSQL) {
		this.connectionPSQL = connectionPSQL;
	}
	
	public boolean inserir(Telefone telefone, Usuario usuario) {
		String sql = "INSERT INTO telefone(id_usr, ddd, telefone) VALUES(?, ?, ?)";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, usuario.getId());
			std.setString(2, telefone.getDdd());
			std.setString(3, telefone.getNumero());
			
			int execucao = std.executeUpdate();
			std.close();
			if(execucao > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean remover(Telefone telefone, Usuario usuario) {
		String sql = "DELETE FROM telefone WHERE id_usr = ?, ddd = ?, telefone = ?";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, usuario.getId());
			std.setString(2, telefone.getDdd());
			std.setString(3, telefone.getNumero());
			
			int execucao = std.executeUpdate();
			std.close();
			if(execucao > 0) {
				return true;
			}
			return false;
		}catch(SQLException e) {			
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		return false;
	}
	
	public ArrayList<Telefone> listaTelefones(Usuario usuario){
		String sql = "SELECT * FROM telefone WHERE id_usr = ?";
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, usuario.getId());
			ResultSet resultado = std.executeQuery();
			while(resultado.next()) {
				Telefone telefone = new Telefone();
				telefone.setDdd(resultado.getString("ddd"));
				telefone.setNumero(resultado.getString("telefone"));
				
				telefones.add(telefone);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		return telefones;
	}
}
