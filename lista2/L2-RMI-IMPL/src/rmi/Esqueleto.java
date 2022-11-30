package rmi;

import service.Calculadora;

public class Esqueleto {
	Calculadora calc;
	public Esqueleto() {
		this.calc =  Calculadora.getInstance();	
	}
	public String soma(String args) {
		String msg[] = args.split(",");
		float val1 = Float.parseFloat(msg[0]);
		float val2 = Float.parseFloat(msg[1]);
		return ""+calc.soma(val1, val2);
	}

	public String sub(String args) {
		String msg[] = args.split(",");
		float val1 = Float.parseFloat(msg[0]);
		float val2 = Float.parseFloat(msg[1]);
		return ""+calc.sub(val1, val2);
	}

	public String mult(String args) {
		String msg[] = args.split(",");
		float val1 = Float.parseFloat(msg[0]);
		float val2 = Float.parseFloat(msg[1]);
		return ""+calc.mult(val1, val2);
	}


	public String div(String args) throws ArithmeticException{
		String msg[] = args.split(",");
		float val1 = Float.parseFloat(msg[0]);
		float val2 = Float.parseFloat(msg[1]);
		return ""+calc.div(val1, val2);
	}

	
}
