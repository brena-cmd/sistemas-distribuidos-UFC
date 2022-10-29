package services;

public class Calculadora {
    public Calculadora(){}

	public String calcular(String op, float a, float b){
        switch(op){
			case "ADD":
				return a + " + " + b + " = " + (a + b);
			case "SUB":
				return a + " - " + b + " = " + (a - b);
			case "MULT":
				return a + " * " + b + " = " + (a * b);
			case "DIV":
				return a + " + " + b + " = " + div(a, b);
        }

		return "Operação inválida.";
    }

	public float div(float a, float b) throws ArithmeticException{
		float res = 0;
		try{
			res = a/b;
		}catch(ArithmeticException e){
			System.out.println(e.getMessage());
		}
		return res;
	}
}