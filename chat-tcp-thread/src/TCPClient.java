import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
	public static void main(String args[]) {
		try {
			int serverPort = 7896;
			final Socket s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			new Thread(()->write(out)).start();
			new Thread(()->read(s, in)).start();
		} catch (UnknownHostException e) {
			System.out.println("Sock:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		}
	}

	public static void read(Socket clientSocket, DataInputStream in){
		while(true){
			try {
				System.out.println("\r" + in.readUTF());
			} catch (SocketException e){
				System.out.println("\rServidor desconectado!");
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void write(DataOutputStream out){
		Scanner msg = new Scanner(System.in);

		while(true){
			try {
				//System.out.print("\rDigite uma mensagem: ");
				out.writeUTF(msg.nextLine());
			} catch (SocketException e){
				System.out.println("\rServidor desconectado!");
				System.exit(0);
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
}