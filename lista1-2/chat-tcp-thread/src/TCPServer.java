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
				System.out.println("--------Chat iniciado--------");
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
		new Thread(()->read()).start();
		new Thread(()->write()).start();
		// read();
		// write();
	}

	public void read(){
		while(true){
			if(!clientSocket.isClosed()){
				try {
					System.out.println("Mensagem do cliente " + clientSocket.getInetAddress().getHostAddress() + ": " + this.in.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			else{
				System.out.println("\nO cliente " + clientSocket.getInetAddress().getHostAddress() + " foi desconectado.");
				System.out.println("-------Chat finalizado-------");
				break;
			}

			// try {
			// 	System.out.println("Mensagem do cliente " + clientSocket.getInetAddress().getHostAddress() + ": " + this.in.readUTF());
			// } catch (SocketException e){
			// 	System.out.println("\nO cliente " + clientSocket.getInetAddress().getHostAddress() + " foi desconectado.");
			// 	System.out.println("-------Chat finalizado-------");
			// 	return;
			// } catch (IOException e) {
			// 	e.printStackTrace();
			// }
		}
	}

	public void write(){
		Scanner msg = new Scanner(System.in);
	
		while(true){
			if(!clientSocket.isClosed()){
				try {
					this.out.writeUTF("Mensagem do servidor: " + msg.nextLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Chat finalizado por não há um cliente conectado.");
				break;
			}

			// try {
			// 	//System.out.print("\rDigite uma mensagem: ");
			// 	this.out.writeUTF("Mensagem do servidor: " + msg.nextLine());
			// } catch (SocketException e){
			// 	System.out.println("Chat finalizado por não há um cliente conectado.");
			// 	return;
			// } catch (IOException e) {
			// 	e.printStackTrace();
			// }
		}
	}
}