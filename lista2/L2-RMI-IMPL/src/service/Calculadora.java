package service;

public class Calculadora {
	private static Calculadora uniqueInstance;
    private Calculadora(){}
    
    public static synchronized Calculadora getInstance() {
    	if(uniqueInstance==null)
    		uniqueInstance = new Calculadora();
    	return uniqueInstance;
    }
    
	public float soma(float a, float b) {
		return a + b;
	}

	public float sub(float a, float b) {
		return a - b;
	}

	public float mult(float a, float b) {
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