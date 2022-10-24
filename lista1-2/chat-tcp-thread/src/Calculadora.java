public class Calculadora {
    public Calculadora(){

    }

    public static float soma(float a, float b) {
		return a + b;
	}

	public static float sub(float a, float b) {
		return a - b;
	}

	public static float mult(float a, float b) {
		return a * b;
	}

	public static float div(float a, float b) throws ArithmeticException{
		float res = 0;
		try{
			res = a/b;
		}catch(ArithmeticException e){
			System.out.println(e.getMessage());
		}
		return res;
	}
}