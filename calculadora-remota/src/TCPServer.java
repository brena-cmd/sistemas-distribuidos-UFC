import java.net.*;
import java.io.*;

public class TCPServer {
	public static void main(String args[]) throws IOException {
		ServerSocket listenSocket = null;
		try {
			int serverPort = 7896;
			listenSocket = new ServerSocket(serverPort);

			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);

				String[] newData = c.getIn().readUTF().split(",");
				String op = newData[0];
				Float num1 = Float.parseFloat(newData[1]);
				Float num2 = Float.parseFloat(newData[2]);
				Float res = 0f;

				switch(op){
					case "ADD":
						res = soma(num1, num2);
						break;
					case "SUB":
						res = sub(num1, num2);
						break;
					case "MULT":
						res = mult(num1, num2);
						break;
					case "DIV":
						res = div(num1, num2);
						break;
				}

				String result = "O resultado da operação " + op + " entre " + num1 + " e " + num2 + " é: " + res;
				c.getOut().writeUTF(result);
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		} finally{
			listenSocket.close();
		}
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
		try { // an echo server
			String data = in.readUTF();
			out.writeUTF(data);
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {}
		}
	}

	public DataInputStream getIn(){
		return this.in;
	}

	public DataOutputStream getOut(){
		return this.out;
	}
}