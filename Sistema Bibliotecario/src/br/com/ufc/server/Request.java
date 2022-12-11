package br.com.ufc.server;

import java.net.InetAddress;

import br.com.ufc.message.Mensagem;

public class Request {
    private InetAddress ipClient;
    private int requestId;
    private Mensagem mensagem;

    public Request(InetAddress ipClient, int requestId, Mensagem mensagem) {
        this.ipClient = ipClient;
        this.requestId = requestId;
        this.mensagem = mensagem;
    }

    public InetAddress getIpClient() {
        return this.ipClient;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public Mensagem getMensagem() {
        return this.mensagem;
    }
}