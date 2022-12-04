package br.com.ufc.model;

public class Servidor extends Usuario{
	private int siape;
	private int nivel_acc;
	
	public int getSiape() {
		return siape;
	}
	
	public void setSiape(int siape) {
		this.siape = siape;
	}
	
	public int getNivel_acc() {
		return nivel_acc;
	}
	
	public void setNivel_acc(int nivel_acc) {
		this.nivel_acc = nivel_acc;
	}
}