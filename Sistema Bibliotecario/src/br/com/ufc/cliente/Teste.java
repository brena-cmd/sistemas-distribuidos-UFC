package br.com.ufc.cliente;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Teste {
public static void main(String[] args) {
	Gson gson = new Gson();
	ArrayList<String> a = new ArrayList<String>();
	
	a.add(String.valueOf(true));
	
	String json = gson.toJson(a);
	
	System.out.println(json);
	
	Type type = (new TypeToken<ArrayList<String>>() {}).getType();
	ArrayList<String> b = gson.fromJson(json, type);
	
	System.out.println(b);
	
}
}
