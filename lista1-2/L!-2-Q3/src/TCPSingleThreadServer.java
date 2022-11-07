import java.net.*;
import service.Calculadora;
import java.io.*;

public class TCPSingleThreadServer {
	public static void main(String args[]) throws IOException {
		ServerSocket listenSocket = null;
		try {
			int serverPort = 7896;
			listenSocket = new ServerSocket(serverPort);

			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				String[] data = c.getIn().readUTF().split(",");
				float a = Float.parseFloat(data[1]), b = Float.parseFloat(data[2]), res = (float)0.0;
				Calculadora calc = new Calculadora();

				switch(data[0]){
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

				c.getOut().writeFloat(res);

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("Connection:" + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		} finally {
			if (listenSocket != null) {
				try {
					listenSocket.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
			}
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
			e.printStackTrace();
		}
	}

	public void run() {
		
	}

	public DataInputStream getIn() {
		return this.in;
	}

	public DataOutputStream getOut() {
		return this.out;
	}
}