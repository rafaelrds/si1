package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import bean.AgendaBean;

import agenda.Agenda;
import agenda.Numero;


@ManagedBean(name = "controller")
@RequestScoped
public class AgendaBean {
	Agenda agenda = new Agenda();




	public void addContato(String nome, String numero, String operadora,
			 String idade, String email) {

		Numero nmero = new Numero(numero, operadora);
		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(nmero);
		agenda.addContato(nome, numeros);


	}

	public String index(){
		return "index.xhtml"; //"/faces/index2?faces-redirect=true";
	}


	public boolean hasContato(String nome) {
		return agenda.hasContato(nome);
	}
}