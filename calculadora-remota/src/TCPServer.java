import java.net.*;
import java.io.*;

public class TCPServer {
	public static void main(String args[]) {
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);

			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}

	public float soma(float a, float b) {
		return a + b;
	}

	public float sub(float a, float b) {
		return a - b;
	}

	public float mult(float a, float b) {
		return a * b;
	}

	public float div(float a, float b) throws ArithmeticException{
		float res = 0;
		try{
			res = a/b;
		}catch(ArithmeticException e){
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public int fat(int a){
		int res=1;
		while(a>1){
				res*=a;
				a-=1;
		}
		return res;
	}

	public int exp(int a, int b){
		int exp = a;
		for(int i = 0; i < b; i++){
			exp *= a;
		}
		return exp;
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
			} catch (IOException e) {
				/* close failed */}
		}
	}
}