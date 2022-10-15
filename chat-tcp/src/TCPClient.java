import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
	public static void main(String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		try {
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			Scanner msg = new Scanner(System.in);

			while (true) {
				System.out.print("Digite uma mensagem: ");
				out.writeUTF("Mensagem Cliente: " + msg.nextLine()); // UTF is a string encoding see Sn 4.3
				String data = in.readUTF();
				System.out.println(data);
				msg.close();
			}
		} catch (UnknownHostException e) {
			System.out.println("Sock:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
			}
		}
	}
}
