package br.com.ufc.cliente;

import java.util.ArrayList;

public class Mensagem {
	private String op;
	private ArrayList<String> values;
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(String value) {
		this.values.add(value);
	}
}
