package br.com.ufc.server;

import java.util.ArrayList;

import br.com.ufc.message.Mensagem;

public class Despachante {
	private Esqueleto esq;

	public Despachante() {
		this.esq = new Esqueleto();
	}

	public byte[] invoke(Mensagem message) {
		byte[] res = "".getBytes();
		
		ArrayList<String> listaArgs = message.getArgs();
		String args = "";
		
		if(message.getMethodId().equals("cadastrarAluno") || message.getMethodId().equals("cadastrarLivro")|| message.getMethodId().equals("cadastrarUnidade")){
			args = listaArgs.get(0);
		}else{
			for (int i = 0; i < listaArgs.size(); i++) {
				args += listaArgs.get(i);
				if (i != listaArgs.size() - 1)
					args += ",";
			}
		}

		switch (message.getObjectReference()) {
			case "servidor":
				switch (message.getMethodId()) {
					case "login":
						res = esq.login(args);
						break;
					case "listarAcervo":
						res = esq.listarAcervo();
						break;
					case "buscarLivro":
						res = esq.buscarLivro(args);
						break;
					case "cadastrarLivro":
						res = esq.cadastrarLivro(args);
						break;
					case "cadastrarAluno":
						res = esq.cadastrarAluno(args);
						break;
					case "alugar":
						res = esq.alugar(args);
						break;
					case "receberEmprestimo":
						res = esq.receberEmprestimo(args);
						break;
					case "cadastrarUnidade":
						res = esq.cadastrarUnidade(args);
						break;
				}
				break;
		}
		return res;
	}
}