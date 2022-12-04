package br.com.ufc.message;

import java.util.ArrayList;

public class Mensagem {
	private int messageType;
	private int requestId;
	private String objectReference;
	private String methodId;
	private ArrayList<String> args;
	
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getObjectReference() {
		return objectReference;
	}
	public void setObjectReference(String objectReference) {
		this.objectReference = objectReference;
	}
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public ArrayList<String> getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args.add(args);
	}
	public void setArgs(ArrayList<String> args) {
		this.args=args;
	}
	
//	public String getOp() {
//		return op;
//	}
//	public void setOp(String op) {
//		this.op = op;
//	}
//	public ArrayList<String> getValues() {
//		return values;
//	}
//	public void setValues(String value) {
//		this.values.add(value);
//	}
}
