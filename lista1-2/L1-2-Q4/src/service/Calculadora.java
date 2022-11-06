package service;

public class Calculadora {
	private static Calculadora uniqueInstance;
    private Calculadora(){}
    
    public static synchronized Calculadora getInstance() {
    	if(uniqueInstance==null)
    		uniqueInstance = new Calculadora();
    	return uniqueInstance;
    }

//	public String calcular(String op, float a, float b){
//        switch(op){
//			case "ADD":
//				return a + " + " + b + " = " + (a + b);
//			case "SUB":
//				return a + " - " + b + " = " + (a - b);
//			case "MULT":
//				return a + " * " + b + " = " + (a * b);
//			case "DIV":
//				return a + " + " + b + " = " + div(a, b);
//        }
//
//		return "Operação inválida.";
//    }
	
	public static float soma(float a, float b) {
		return a + b;
	}

	public static float sub(float a, float b) {
		return a - b;
	}

	public static float mult(float a, float b) {
		return a * b;
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