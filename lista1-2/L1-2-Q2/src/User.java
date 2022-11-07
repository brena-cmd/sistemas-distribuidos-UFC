import java.util.Scanner;

public class User {
	public static void main(String[] args) {
		TCPClient client = new TCPClient("localhost", 7896);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Escolha o servico que deseja:\n"
				+ "1-Calculadora\n"
				+ "2-Conversor de moeda\n"
				+ "3-Sair");
		int op;
		
		do {
			op = sc.nextInt();
			sc.nextLine();
			String req;
			//String[] data = op.split(",");
			switch(op) {
			case 1:
				System.out.println("Digite a operacao no seguinte formato: VAL OP VAL"); //3 + 5
				req = sc.nextLine();
				String msg[] = req.split(" ");
				req = "";
				req+="CALC,";
				switch(msg[1]) {
				case "+":
					req+="ADD";
					break;
				case "-":
					req+="SUB";
					break;
				case "*":
					req+="MULT";
					break;
				case "/":
					req+="DIV";
					break;
				default:
					System.out.println("CALC: Operacao invalida.");
					break;
				}
				req+=","+msg[0]+","+msg[2];
				client.sendRequest(req);
				System.out.println(client.getResponse());
				break;
			case 2:
				System.out.println("Digite a operacao no seguinte formato: MOEDA VAL\n"); //USD 3.50
				req = sc.nextLine();
				String msg1[] = req.split(" ");
				req = "";
				req+="CONV,"+msg1[0]+","+msg1[1];
				client.sendRequest(req);
				System.out.println(client.getResponse());
				break;
			case 3:
				System.out.println("Desconectando cliente...");
				client.close();
				break;
			default:
				System.out.println("Servico invalido.");
				break;
			}
		}while(op!=3);
	}
}
