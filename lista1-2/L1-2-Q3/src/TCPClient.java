import java.net.*;
import java.io.*;

//Criar conexão
//Loop que cria as threads e calcula o tempo
//Para cada thread:
// 	Enviar a requisição
//  Receber a requisição e tratar os erros
//  Calcular quantas requisições deram certo e quantas deram errado
//  Retornar esses dados e printar

public class TCPClient {
	public static void main (String args[]) {
		//Conectar ao servidor SingleThread
		connectServer(7896);
		//Conectar ao servidor MultiThread
		connectServer(8000);
	}

	public static void connectServer(int port){
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;

	    try{
			socket = new Socket("localhost", port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			int success = 0, error = 0;

			long start = System.currentTimeMillis();
			
			for(int a = 0; a < 2; a++){
				ClientThread thread = new ClientThread(in, out);

				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if(thread.success == true) success++;
				else error++;
			}

			long elapsed = System.currentTimeMillis() - start;
			showAnalytics(port, elapsed, success, error);
		} catch (UnknownHostException e){System.out.println("Sock:"+e.getMessage()); 
	    } catch (EOFException e){ System.out.println("EOF:"+e.getMessage());
	    } catch (IOException e){ System.out.println("IO:"+e.getMessage());
		} finally { 
			if(socket != null) {
				try { 
					socket.close(); 
				} catch (IOException e){
					System.out.println("close:"+e.getMessage());
				}
			}
		}
	}

	public static void showAnalytics(int port, long time, int success, int error){
		System.out.println("--------------------------------------------");
		System.out.println("Servidor conectado na porta: " + port);
		System.out.printf("Tempo de processamento: %-15.3f", time/1000.0);
		System.out.println("\nRequisições processadas: " + success);
		System.out.println("Requisições com erro: " + error);
		System.out.println("--------------------------------------------");
	}
}

class ClientThread extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Boolean success;

	public ClientThread(DataInputStream in, DataOutputStream out) {
		this.in = in;
		this.out = out;
		this.success = false;
		this.start();
	}

	public void run() {
		sendRequest();
		receiveRequest();
	}

	public void receiveRequest(){
		try{
			this.in.readFloat();
			this.success = true;
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void sendRequest(){
		try{
			this.out.writeUTF("ADD,10,11");
		}catch(IOException e){
			System.out.println("IO: "+e.getMessage());
		}
	}
}