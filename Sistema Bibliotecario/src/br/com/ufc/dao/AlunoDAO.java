package br.com.ufc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import br.com.ufc.model.Aluno;
import br.com.ufc.model.Usuario;
import br.com.ufc.connection.ConnectionPSQL;

public class AlunoDAO{
	private ConnectionPSQL connectionPSQL;
	private Connection connection;
	
	public AlunoDAO(ConnectionPSQL connectionPSQL) {
		this.connectionPSQL = connectionPSQL;
	}
	
	public boolean inserir(Usuario usuario, Aluno aluno) {
		String sql1 = "INSERT INTO usuario(nome, senha, email, cpf, data_nasc, rua, numero, cidade, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String sql2 = "SELECT id FROM usuario WHERE cpf = ?;";
		String sql3 = "INSERT INTO aluno(matricula, id_usr, curso) VALUES(?, ?, ?);";
		
		try {
			this.connection = connectionPSQL.getConnection();
			//INSERT INTO USUARIO
			PreparedStatement std = connection.prepareStatement(sql1);
			std.setString(1, usuario.getNome());
			std.setString(2, usuario.getSenha());
			std.setString(3, usuario.getEmail());
			std.setString(4, usuario.getCpf());
			try {
				std.setDate(5, new java.sql.Date((usuario.getDataNasc()).getTime()));
			}catch(ParseException e) {
				e.printStackTrace();
			}
			std.setString(6, usuario.getRua());
			std.setString(7, usuario.getNumero());
			std.setString(8, usuario.getCidade());
			std.setString(9, usuario.getEstado());
			int execucao = std.executeUpdate();
			if(execucao > 0) {
				//SELECT ID FROM USUARIO
				std = connection.prepareStatement(sql2);
				std.setString(1, usuario.getCpf());
				ResultSet resultado = std.executeQuery();
				while(resultado.next()) {
					//INSERT INTO ALUNO
					usuario.setId(resultado.getInt("id"));
					int id = resultado.getInt("id");
					std = connection.prepareStatement(sql3);
					std.setInt(1, aluno.getMatricula());
					std.setInt(2, id);
					std.setString(3, aluno.getCurso());
					int insert = std.executeUpdate();
					if(insert > 0) {
						return true;
					}
					return false;
				}
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

	/*public boolean atualizar(Aluno t) {
		// TODO Auto-generated method stub
		return false;
	}*/

	public boolean remover(Aluno aluno) {
		String sql1 = "SELECT * FROM aluno WHERE matricula = ?;";
		String sql2 = "DELETE FROM aluno WHERE matricula = ?;";
		String sql3 = "DELETE FROM usuario WHERE id = ?;";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql1);
			std.setInt(1, aluno.getMatricula());
			ResultSet resultado = std.executeQuery();
			while(resultado.next()) {
				int id = resultado.getInt("id_usr");
				std = connection.prepareStatement(sql2);
				std.setInt(1, aluno.getMatricula());
				int execucao = std.executeUpdate();
				if(execucao > 0) {
					std = connection.prepareStatement(sql3);
					std.setInt(1, id);
					execucao = std.executeUpdate();
					if(execucao > 0) {
						return true;
					}
					return false;
				}
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

	public Aluno buscar(String senha, int matricula) {
		String query = "SELECT * FROM (aluno JOIN usuario ON id_usr = id) WHERE matricula = ? AND senha = ?;";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(query);
			std.setInt(1, matricula);
			std.setString(2, senha);
			ResultSet resultado = std.executeQuery();
			
			while(resultado.next()) {
				Aluno aluno = new Aluno();
				aluno.setCidade(resultado.getString("cidade"));
				aluno.setCpf(resultado.getString("cpf"));
				aluno.setCurso(resultado.getString("curso"));
				aluno.setEmail(resultado.getString("email"));
				aluno.setEstado(resultado.getString("estado"));
				aluno.setMatricula(resultado.getInt("matricula"));
				aluno.setNome(resultado.getString("nome"));
				aluno.setRua(resultado.getString("rua"));
				aluno.setNumero(resultado.getString("numero"));
				aluno.setSenha(resultado.getString("senha"));
				aluno.setId(resultado.getInt("id"));
				return aluno;
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
		return null;
	}
	
	public Aluno buscar(int matricula) {
		String query = "SELECT * FROM (aluno JOIN usuario ON id_usr = id) WHERE matricula = ?;";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(query);
			std.setInt(1, matricula);
			ResultSet resultado = std.executeQuery();
			
			while(resultado.next()) {
				Aluno aluno = new Aluno();
				aluno.setCidade(resultado.getString("cidade"));
				aluno.setCpf(resultado.getString("cpf"));
				aluno.setCurso(resultado.getString("curso"));
				aluno.setEmail(resultado.getString("email"));
				aluno.setEstado(resultado.getString("estado"));
				aluno.setMatricula(resultado.getInt("matricula"));
				aluno.setNome(resultado.getString("nome"));
				aluno.setRua(resultado.getString("rua"));
				aluno.setNumero(resultado.getString("numero"));
				aluno.setSenha(resultado.getString("senha"));
				aluno.setId(resultado.getInt("id"));
				return aluno;
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
		return null;
	}
}