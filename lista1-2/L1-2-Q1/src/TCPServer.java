import java.net.*;
import java.io.*;
import service.Calculadora;
import service.Conversor;

public class TCPServer {
	public static void main(String args[]) throws IOException {
		ServerSocket listenSocket = null;

		try {
			int serverPort = 7896;
			listenSocket = new ServerSocket(serverPort);
			System.out.println("Padrão de Mensagens:\nSERVIÇO, OPERAÇÃO, OP1, OP2");

			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				System.out.println("Conectado ao cliente " + clientSocket.getInetAddress().getHostAddress());
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		} finally {
			listenSocket.close();
		}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		Calculadora calc = new Calculadora();
		Conversor conv = new Conversor();
		while(true) {
			try {
				String msg = this.in.readUTF();
				String[] data = msg.split(",");
				if(data[0].equals("CALC")) {
						System.out.println("Calculando...");
						this.out.writeUTF(calc.calcular(data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3])));
					
				}
				else if(data[0].equals("CONV")) {
						this.out.writeUTF(conv.converter(data[1], Float.parseFloat(data[2])));
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	}
		

	public void calc() {
		while (true) {
			try {
				String[] data = this.in.readUTF().split(",");
				if (data[0] == "CALC") {
					Calculadora calc = new Calculadora();
					this.out.writeUTF(calc.calcular(data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3])));
				}
			} catch (SocketException e) {
				System.out.println("\nO cliente " + clientSocket.getInetAddress().getHostAddress() + " foi desconectado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void conv() {
		while (true) {
			try {
				String[] data = this.in.readUTF().split(",");
				if (data[0] == "CONV") {
					Conversor conv = new Conversor();
					this.out.writeUTF(conv.converter(data[1], Float.parseFloat(data[2])));
					
				}
			} catch (SocketException e) {
				System.out.println("\nO cliente " + clientSocket.getInetAddress().getHostAddress() + " foi desconectado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}