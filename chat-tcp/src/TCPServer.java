import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPServer {
	public static void main(String args[]) {
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				System.out.println("Cliente conectado do IP: " + clientSocket.getInetAddress().getHostAddress());
				Connection c = new Connection(clientSocket);
				while (true) {
					System.out.println(c.getIn().readUTF());
					System.out.print("Digite uma mensagem: ");
					Scanner msg = new Scanner(System.in);
					c.getOut().writeUTF("Mensagem Servidor: " + msg.nextLine());
					msg.close();
				}
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
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
			// this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = in.readUTF();
			out.writeUTF(data);
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				/* close failed */}
		}
	}

	public DataInputStream getIn() {
		return this.in;
	}

	public DataOutputStream getOut() {
		return this.out;
	}
}