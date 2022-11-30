package br.com.ufc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufc.model.Livro;
import br.com.ufc.connection.ConnectionPSQL;

public class LivroDAO{
	private ConnectionPSQL connectionPSQL;
	private Connection connection;
	
	public LivroDAO(ConnectionPSQL connectionPSQL) {
		this.connectionPSQL = connectionPSQL;
	}
	
	public boolean inserir(Livro livro) {
		String sql = "INSERT INTO livro(num_acv, titulo, edicao, ano_lan) VALUES(?, ?, ?, ?);";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, livro.getNumAcv());
			std.setString(2, livro.getTitulo());
			std.setInt(3, livro.getEdicao());
			std.setString(4, livro.getAno_lancamento());
			
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
	
	public boolean atualizar(Livro livro) {
		String sql = "UPDATE livro SET num_acv = ?, titulo = ?, edicao = ?, ano_lan = ? WHERE num_acv = ?";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, livro.getNumAcv());
			std.setString(2, livro.getTitulo());
			std.setInt(3, livro.getEdicao());
			std.setString(4, livro.getAno_lancamento());
			std.setInt(5, livro.getNumAcv());
			
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
	
	public boolean remover(Livro livro) {
		String sql = "DELETE FROM livro WHERE num_acv = ?";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, livro.getNumAcv());
			
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
	
	public ArrayList<Livro> buscar(String key){
		String sql = "SELECT * FROM livro WHERE titulo ILIKE ?";
		ArrayList<Livro> livros = new ArrayList<Livro>();
		boolean verificador = false;
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setString(1, "%" + key + "%");
			ResultSet resultado = std.executeQuery();			
			while(resultado.next()) {
				verificador = true;
				Livro livro = new Livro();
				livro.setNumAcv(resultado.getInt("num_acv"));
				livro.setTitulo(resultado.getString("titulo"));
				livro.setAno_lancamento(resultado.getString("ano_lan"));
				livro.setEdicao(resultado.getInt("edicao"));
				livro.setQuantidade(resultado.getInt("qtd"));
				
				livros.add(livro);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		if(verificador)
			return livros;
		return null;
	}
	
	public ArrayList<Livro> listarLivros(){
		String sql = "SELECT * FROM livro;";
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			ResultSet resultado = std.executeQuery();
			while(resultado.next()) {
				Livro livro = new Livro();
				livro.setNumAcv(resultado.getInt("num_acv"));
				livro.setTitulo(resultado.getString("titulo"));
				livro.setAno_lancamento(resultado.getString("ano_lan"));
				livro.setEdicao(resultado.getInt("edicao"));
				livro.setQuantidade(resultado.getInt("qtd"));
				
				livros.add(livro);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		return livros;
	}
}