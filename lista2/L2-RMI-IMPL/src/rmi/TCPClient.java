package rmi;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
	private Socket s;
	private int serverPort;
	private DataInputStream in;
	private DataOutputStream out;
//	public static void main(String args[]) {
//		try {
//			int serverPort = 7896;
//			final Socket s = new Socket("localhost", serverPort);
//			DataInputStream in = new DataInputStream(s.getInputStream());
//			DataOutputStream out = new DataOutputStream(s.getOutputStream());
//			new Thread(()->write(out)).start();
//			new Thread(()->read(s, in)).start();
//		} catch (UnknownHostException e) {
//			System.out.println("Sock:" + e.getMessage());
//		} catch (EOFException e) {
//			System.out.println("EOF:" + e.getMessage());
//		} catch (IOException e) {
//			System.out.println("IO:" + e.getMessage());
//		}
//	}
	public TCPClient(String IP, int PORT ) {
		try {
			this.serverPort = PORT;
			this.s = new Socket(IP, serverPort);
			this.in = new DataInputStream(s.getInputStream());
			this.out = new DataOutputStream(s.getOutputStream());
			
//			new Thread(()->write(out)).start();
//			new Thread(()->read(s, in)).start();
		} catch (UnknownHostException e) {
			System.out.println("Sock:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		}
	}
	
	public void sendRequest(String req) {
		try {
			this.out.writeUTF(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getResponse() {
		String resp="";
		try {
			resp = this.in.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
	public void close() {
		try {
			sendRequest("sair");
			this.s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Nao foi possivel fechar conexao");
			//e.printStackTrace();
		}
	}
	public static void read(Socket clientSocket, DataInputStream in){
		while(true){
			try {
				System.out.println(in.readUTF());
			} catch (SocketException e){
				System.out.println("Servidor desconectado!");
				System.exit(0);
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}

	public static void write(DataOutputStream out){
		Scanner msg = new Scanner(System.in);

		while(true){
			try {
				String a=msg.nextLine();
				//System.out.println(a);
				out.writeUTF(a);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}