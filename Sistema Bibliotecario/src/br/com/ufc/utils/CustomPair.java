package br.com.ufc.utils;

import br.com.ufc.message.Mensagem;

public class CustomPair {
    private String IP;
    private Mensagem msg;
    
    public CustomPair(String ip, Mensagem msg) {
    	this.IP=ip;
    	this.msg=msg;
    }
	public Mensagem getMsg() {
		return msg;
	}
	public void setMsg(Mensagem msg) {
		this.msg = msg;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
    
    
}
