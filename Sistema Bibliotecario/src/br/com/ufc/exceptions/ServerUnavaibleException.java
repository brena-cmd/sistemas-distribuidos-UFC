package br.com.ufc.exceptions;

public class ServerUnavaibleException extends Exception{
	
	public ServerUnavaibleException() {
		super("Servidor indisponível");
	}
//	@Override
//	  public String getMessage(){
//	    return "Servidor indisponível";
//	  }
}
