package br.com.ufc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.ufc.model.Aluno;
import br.com.ufc.model.Emprestimo;
import br.com.ufc.model.Reserva;
import br.com.ufc.connection.ConnectionPSQL;

public class EmprestimoDAO {
	private ConnectionPSQL connectionPSQL;
	private Connection connection;
	
	public EmprestimoDAO(ConnectionPSQL connectionPSQL){
		this.connectionPSQL = connectionPSQL;
	}
	
	public boolean inserir(Emprestimo emprestimo) {
		String sql = "INSERT INTO emprestimo VALUES(?, ?, ?, ?);";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, emprestimo.getNumReg());
			std.setInt(2, emprestimo.getMatricula());
			try {
				std.setDate(3, new java.sql.Date((emprestimo.getDataEmp()).getTime()));
				std.setDate(4, new java.sql.Date((emprestimo.getDataDevo()).getTime()));
			}catch(ParseException e) {
				e.printStackTrace();
			}
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
	
	public boolean remover(int numReg) {
		String sql = "DELETE FROM emprestimo WHERE num_reg = ?;";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, numReg);
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
	
	public double getDebito(Emprestimo emprestimo) {
		String sql = "SELECT * FROM debitos WHERE num_reg = ? AND matricula = ? AND data_emp = ?;";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, emprestimo.getNumReg());
			std.setInt(2, emprestimo.getMatricula());
			try {
				std.setDate(3, new java.sql.Date((emprestimo.getDataEmp()).getTime()));
			}catch(ParseException e) {
				e.printStackTrace();
			}
			ResultSet resultado = std.executeQuery();
			while(resultado.next()) {
				return resultado.getDouble("debito");
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
		return 0.0;
	}
	
	public boolean atualizar(Emprestimo emprestimo) throws ParseException {
		String sql = "UPDATE emprestimo SET data_devo = ?, qtd_reno = ? WHERE num_reg = ?;";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			
			java.util.Date date = emprestimo.getDataDevo();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, 30);
			date = cal.getTime();
			
			std.setDate(1, new java.sql.Date(date.getTime()));
			std.setInt(2, emprestimo.getQtdReno() + 1);
			std.setInt(3, emprestimo.getNumReg());
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
	
	public boolean reservar(Reserva reserva) throws ParseException {
		String sql = "INSERT INTO reserva VALUES(?, ?, ?);";
		
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, reserva.getMatricula());
			std.setInt(2, reserva.getNumAcv());
			std.setDate(3, new java.sql.Date((reserva.getDataRsv()).getTime()));
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
	
	public ArrayList<Emprestimo> listarEmprestimos(Aluno aluno){
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		String sql = "SELECT * FROM emprestimo WHERE matricula = ?;";
		boolean verificador = false;
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			std.setInt(1, aluno.getMatricula());
			ResultSet resultado = std.executeQuery();
			while(resultado.next()) {
				verificador = true;
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setNumReg(resultado.getInt("num_reg"));
				emprestimo.setMatricula(resultado.getInt("matricula"));
				emprestimo.setQtdReno(resultado.getInt("qtd_reno"));
				emprestimo.setDataEmp(resultado.getString("data_emp"));
				emprestimo.setDataDevo(resultado.getString("data_devo"));
				
				emprestimos.add(emprestimo);
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
		if(verificador)
			return emprestimos;
		return null;
	}
	
	public ArrayList<Emprestimo> getEmprestimos(){
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		String sql = "SELECT * FROM emprestimo;";
		boolean verificador = false;
		try {
			this.connection = connectionPSQL.getConnection();
			PreparedStatement std = connection.prepareStatement(sql);
			ResultSet resultado = std.executeQuery();
			while(resultado.next()) {
				verificador = true;
				Emprestimo emprestimo = new Emprestimo();
				emprestimo.setNumReg(resultado.getInt("num_reg"));
				emprestimo.setMatricula(resultado.getInt("matricula"));
				emprestimo.setQtdReno(resultado.getInt("qtd_reno"));
				emprestimo.setDataEmp(resultado.getString("data_emp"));
				emprestimo.setDataDevo(resultado.getString("data_devo"));
				
				emprestimos.add(emprestimo);
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
		if(verificador)
			return emprestimos;
		return null;
	}
	
}
