package service;

public class Conversor {
	private static Conversor uniqueInstance;
    private Conversor(){}
    
    public static synchronized Conversor getInstance() {
    	if(uniqueInstance==null)
    		uniqueInstance = new Conversor();
    	return uniqueInstance;
    }

    public String converter(String moeda, float x){
        switch(moeda){
            case "EUR":
                return x + " to EUR: " + (x * (float)5.23);
            case "USD":
                return x + " to USD: " + (x * (float)5.30);
            case "GPB":
                return x + " to GBP: " + (x * (float)5.98);
            case "ARS":
                return x + " to ARS: " + (x * (float)0.034);
            case "KRW":
                return x + " to KRW: " + (x * (float)0.0037);
            case "JPY":
                return x + " to JPY: " + (x * (float)0.036);
            case "CNY":
                return x + " to CNY: " + (x * (float)0.73);
        }

        return "Operção Inválida.";
    }
}