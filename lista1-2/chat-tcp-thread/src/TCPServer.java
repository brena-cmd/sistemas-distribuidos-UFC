import java.net.*;
import java.util.Scanner;
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
				System.out.println("\nConectado ao cliente " + clientSocket.getInetAddress().getHostAddress());
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
		new Thread(()->op()).start();
	}

	public void op(){
		while(true){
			try {
				System.out.println("Mensagem do cliente " + clientSocket.getInetAddress().getHostAddress() + ": " + this.in.readUTF());
			}catch (SocketException e){ 
				System.out.println("\nO cliente " + clientSocket.getInetAddress().getHostAddress() + " foi desconectado.");
				System.out.println("-------Operação finalizada-------");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}