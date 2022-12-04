import java.net.*;
import java.io.*;
import service.Calculadora;

public class TCPMultiThreadServer {
	public static void main(String args[]) throws IOException {
		ServerSocket listenSocket = null;
		try {
			int serverPort = 8000;
			listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
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
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try {
			Calculadora calc = new Calculadora();
			String[] newData = this.in.readUTF().split(",");
			float a = Float.parseFloat(newData[1]), b = Float.parseFloat(newData[2]), res = (float) 0.0;

			switch (newData[0]) {
				case "ADD":
					res = calc.soma(a, b);
					break;
				case "SUB":
					res = calc.sub(a, b);
					break;
				case "MULT":
					res = calc.mult(a, b);
					break;
				case "DIV":
					res = calc.div(a, b);
					break;
			}

			this.out.writeFloat(res);
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
			}
		}
	}
}