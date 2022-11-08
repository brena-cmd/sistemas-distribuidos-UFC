package interfaceUsuario;
import java.util.Scanner;

import rmi.ProxyCalculadora;

public class User {
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o servico que deseja:\n"
				+ "1-Calculadora\n"
				+ "2-Conversor de moeda\n"
				+ "3-Sair");
		int op;
		ProxyCalculadora calc = new ProxyCalculadora();
		do {
			op = sc.nextInt();
			sc.nextLine();
			String req;
			switch(op) {
			case 1:
				System.out.println("Digite a operacao no seguinte formato: VAL OP VAL"); //3 + 5
				req = sc.nextLine();
				String msg[] = req.split(" ");
				req = "";
				float val1=0;
				float val2=0;
				switch(msg[1]) {
				case "+":
					val1 = Float.parseFloat(msg[0]);
					val2 = Float.parseFloat(msg[2]);
					System.out.println(calc.soma(val1, val2));
					break;
				case "-":
					val1 = Float.parseFloat(msg[0]);
					val2 = Float.parseFloat(msg[2]);
					System.out.println(calc.sub(val1, val2));
					break;
				case "*":
					val1 = Float.parseFloat(msg[0]);
					val2 = Float.parseFloat(msg[2]);
					System.out.println(calc.mult(val1, val2));
					break;
				case "/":
					val1 = Float.parseFloat(msg[0]);
					val2 = Float.parseFloat(msg[2]);
					System.out.println(calc.div(val1, val2));
					break;
				default:
					System.out.println("CALC: Operacao invalida.");
					break;
				}
				break;
			case 2:
				System.out.println("Fechando calculadora...");
				calc.close();
				break;
			default:
				System.out.println("Servico invalido.");
				break;
			}
		}while(op!=2);
	}
}
