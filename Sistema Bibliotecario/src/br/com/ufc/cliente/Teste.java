package br.com.ufc.cliente;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import br.com.ufc.message.*;

public class Teste {
	public static void main(String[] args) {
		Gson gson = new Gson();

		String s = "{\"messageType\":0,\"requestId\":1,\"objectReference\":\"servidor\",\"methodId\":\"login\",\"args\":[\"1234\",\"1\"]}";

		Mensagem msg = gson.fromJson(s, Mensagem.class);
		System.out.println(msg.getArgs());
		System.out.println(msg.getMethodId());
	}
}