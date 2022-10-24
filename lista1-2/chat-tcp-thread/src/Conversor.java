public class Conversor {
    public Conversor(){

    }

    public float converter(float x, String moeda){
        switch(moeda){
            case "EUR":
                return x * (float)5.23;
            case "USD":
                return x * (float)5.30;
            case "GPB":
                return x * (float)5.98;
            case "ARS":
                return x * (float)0.034;
            case "KRW":
                return x * (float)0.0037;
            case "JPY":
                return x * (float)0.036;
            case "CNY":
                return x * (float)0.73;
            default:
                return (float)0.0;
        }
    }
}