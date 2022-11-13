package rmi;

public class Despachante {
	private Esqueleto esq;
	
	public Despachante() {
		// TODO Auto-generated constructor stub
		this.esq = new Esqueleto();
	}
	public String invoke(String message) {
		String msg[]=message.split(",");
		String args = msg[1]+","+msg[2];
		String res="";
		switch(msg[0]){
			case "ADD":
				res = esq.soma(args);
				break;
			case "SUB":
				res = esq.sub(args);
				break;
			case "MULT":
				res = esq.mult(args);
				break;
			case "DIV":
				res = esq.div(args);
				break;
		}
		return res;
	}
}
