package rmi;

public class ProxyCalculadora {
	
	private TCPClient client;
	
	public ProxyCalculadora() {
		this.client = new TCPClient("localhost", 7896);
	}
	public float soma(float a, float b) {
		getClient().sendRequest(buildRequest("ADD", a, b));
		float res = Float.parseFloat(getClient().getResponse());
		return res;
	}

	public float sub(float a, float b) {
		getClient().sendRequest(buildRequest("SUB", a, b));
		float res = Float.parseFloat(getClient().getResponse());
		return res;
	}

	public float mult(float a, float b) {
		getClient().sendRequest(buildRequest("MULT", a, b));
		float res = Float.parseFloat(getClient().getResponse());
		return res;
	}


	public float div(float a, float b) throws ArithmeticException{
		getClient().sendRequest(buildRequest("DIV", a, b));
		float res = Float.parseFloat(getClient().getResponse());
		return res;
		
	}
	public void close() {
		getClient().close();
	}
	public String buildRequest(String op, float val1, float val2 ) {
		return op+","+val1+","+val2;
	}
	
	public TCPClient getClient() {
		return this.client;
	}
	
}
