package model;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.TimeLimitExceededException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import org.primefaces.component.calendar.Calendar;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class Tarefa {
	String nome;
	String descricao;
	String horaLimite;
	Date date; 
	
	public Tarefa(String nome) {
		this(nome,null, "", "");
	}

	
	public Tarefa(String nome, Date data, String descricao, String horaLimite) {
		if(nome.equals("")) throw new IllegalArgumentException("O nome da tarefa nao pode ser vazio");
		if(!isRegular(horaLimite)) throw new IllegalArgumentException("A hora limite não é uma hora válida");
		//System.out.println((new Date()).compareTo(data));
		if(!verificaValidadeData(data)) throw new IllegalArgumentException("Voce nao pode criar uma tarefa com data anterior a atual");
		
		this.nome = nome;
		this.descricao = descricao;
		this.horaLimite = horaLimite;
		this.date = data;
		if(!horaLimite.equals(""))adicionaHoraLimite(horaLimite);
	}

	private boolean verificaValidadeData(Date data) {
		return (new Date()).before(data);
	}


	private boolean isRegular(String horaLimite) {
		if(horaLimite.equals("")) return true;
		String[] hora = horaLimite.split(":");
		
		return((hora.length == 3) && !verificaFormatNumero(hora));
	}


	private boolean verificaFormatNumero(String[] hora) {
		int[] padrao = {23,59,59};
		int numero;
		for(int i = 0; i < hora.length; i++){
			try{
				numero = Integer.parseInt(hora[i]);
			}catch(NumberFormatException e){
				return false;
			}

			if(!(numero > 0 && numero <= padrao[i])) return false;
		}
		return true;
	}

	private void adicionaHoraLimite(String horaLimite) {		
		date.setHours(Integer.parseInt(horaLimite.split(":")[0]));
		date.setMinutes(Integer.parseInt(horaLimite.split(":")[1]));
		date.setSeconds(Integer.parseInt(horaLimite.split(":")[2]));
	}

	private Date criaData(String data) {
		if(data.equals("")) return null;
		return new Date();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}

	public Date getData() {
		return date;
	}

	public void setData(Date date) throws TimeLimitExceededException{
		if(date.compareTo(new Date()) > 0){
			throw new TimeLimitExceededException();
		}
		this.date = date;		
	}

	public String getHoraLimite() {
		// TODO Auto-generated method stub
		return horaLimite;
	}
	
	
	

}
