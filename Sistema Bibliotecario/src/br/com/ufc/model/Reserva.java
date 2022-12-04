package br.com.ufc.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
	int matricula;
    int numAcv;
    String dataRsv;
    
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public int getNumAcv() {
		return numAcv;
	}
	public void setNumAcv(int numAcv) {
		this.numAcv = numAcv;
	}
	public Date getDataRsv() throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataRsv);
		return date;
	}
	public void setDataRsv(String dataRsv) {
		this.dataRsv = dataRsv;
	}
    
    
    
}
