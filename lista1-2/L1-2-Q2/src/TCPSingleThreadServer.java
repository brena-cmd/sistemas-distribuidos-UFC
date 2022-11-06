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
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		} finally{
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
		try {
			String[] newData = this.in.readUTF().split(",");
			this.out.writeUTF(calcular(newData[0], Float.parseFloat(newData[1]), Float.parseFloat(newData[2])));
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

	public String calcular(String op, float a, float b){
        switch(op){
			case "ADD":
				return a + " + " + b + " = " + (a + b);
			case "SUB":
				return a + " - " + b + " = " + (a - b);
			case "MULT":
				return a + " * " + b + " = " + (a * b);
			case "DIV":
				return a + " + " + b + " = " + (a / b);
        }

		return "Operação inválida.";
    }
}