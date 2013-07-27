package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import model.Contato;
import model.Emptyfieldexception;
import model.InvalidNumberException;
import model.Telefone;

@ManagedBean(name = "agendaBean", eager = true)
@SessionScoped
public class Agenda {
	private String email;
	private Contato contato;
	private Telefone telefone;
	private Contato contatoSelecionado;
	private ArrayList<Contato> contatos;
	private boolean telefoneInvalido;
	private String contatoValido;
	private String tipoDeBusca;
	private String busca;
	private ArrayList<Contato> resultadoBusca;

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Agenda() {
		inicializeArrays();
		this.email = "";
		this.contatoValido = "false";
		this.telefoneInvalido = false;
		this.contato = new Contato();
		this.telefone = new Telefone();
		this.contatoSelecionado = new Contato();
		contatoSelecionado.setNome("Renan");
		this.busca = "";
		this.tipoDeBusca = "1";
		this.resultadoBusca = new ArrayList<Contato>();
	}

	public void inicializeArrays() {

		Contato c1 = new Contato();
		Contato c2 = new Contato();
		Contato c3 = new Contato();
		Contato c4 = new Contato();
		Contato c5 = new Contato();
		Telefone t = new Telefone();
		Telefone t2 = new Telefone();
		Telefone t3 = new Telefone();
		Telefone t4 = new Telefone();
		Telefone t5 = new Telefone();
		t.setNumero("88221533");
		t2.setNumero("88332343");
		t3.setNumero("99832343");
		t4.setNumero("99990000");
		t5.setNumero("89343231");
		t.setOperadora("OI");
		t3.setCodigoRegional("81");
		t3.setOperadoraLigar("31");
		c1.setNome("Robert Silva Alves");
		c2.setNome("Marta");
		c3.setNome("Chris Santos");
		c4.setNome("Roberto Carlos Campos da Silva");
		c5.setNome("Gilberto");
		c1.addEmail("RobertSA@gmail.com");
		c3.addEmail("ChrisSantos@gmail.com");
		c1.setIdade("40");
		c2.setIdade("20");
		c5.setIdade("60");
		try {
			c1.addTelefone(t);
			c1.addTelefone(t3);
			c1.addTelefone(t4);
			c2.addTelefone(t2);
			c3.addTelefone(t3);
			c3.addTelefone(t2);
			c4.addTelefone(t4);
			c5.addTelefone(t5);
		} catch (InvalidNumberException e) {
		}
		this.contatos = new ArrayList<Contato>(
				Arrays.asList(c1, c2, c3, c4, c5));

	}

	public void addTelefone(ActionEvent event) {
		this.telefoneInvalido = false;
		try {
			this.contato.addTelefone(telefone);
		} catch (InvalidNumberException e) {
			this.telefoneInvalido = true;
		}

		this.telefone = new Telefone();
	}

	public void removeEmail(String email) {
		this.contato.removeEmail(email);

	}

	public void cancelarContato(ActionEvent event) {
		this.telefoneInvalido = false;
		this.contato = new Contato();
	}

	public void addEmail() {
		this.contato.addEmail(this.email);
		this.email = "";

	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void addContato(ActionEvent event) throws Emptyfieldexception,
			InvalidNumberException {

		if(!contatos.contains(contato)){
			contatos.add(contato);

		}
		contato = new Contato();
		this.telefone= new Telefone();

	}


	public void removeTelefone(Telefone tel) {
		contato.removeTelefone(tel);	}

	

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {

		this.contatoSelecionado = contatoSelecionado;
	}

	public ArrayList<Contato> getContatos() {
		return contatos;
	}

	

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setSelectedContact(Contato selectedContact) {
		this.contatoSelecionado = selectedContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contato getContato() {
		return contato;
	}

	public String getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(String tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public boolean getTelefoneInvalido() {
		System.out.println("dddddddddddd");
		return telefoneInvalido;
	}

	public String getContatoValido() {
		boolean temp = !(this.contato.getNome().equals("") || this.contato
				.getTelefones().isEmpty());
		this.contatoValido = new Boolean(temp).toString();
		return contatoValido;
	}

	public void fazBusca(AjaxBehaviorEvent event) {
		resultadoBusca = new ArrayList<Contato>();
		for (Contato cont : contatos) {

			boolean idadeBuscaValida = !(cont.getIdade().equals("") || this.busca
					.equals(""));

			if (tipoDeBusca.equals("1")) {
				boolean ehsubnome = cont.getNome().toLowerCase()
						.contains(busca.toLowerCase());
				boolean ehsubnumero = false;

				for (Telefone tel : cont.getTelefones()) {
					if (tel.getNumero().contains(busca.toLowerCase())) {
						ehsubnumero = true;
						break;
					}
				}
				if (ehsubnome || ehsubnumero) {
					resultadoBusca.add(cont);
				}
			}
			if (tipoDeBusca.equals("2") && idadeBuscaValida) {
				boolean menor = new Integer(cont.getIdade()) < new Integer(
						this.busca);
				if (menor) {
					resultadoBusca.add(cont);
				}
			}
			if (tipoDeBusca.equals("3")) {
				if (cont.getIdade().equals(this.busca) && idadeBuscaValida) {
					resultadoBusca.add(cont);
				}
			}
			if (tipoDeBusca.equals("4") && idadeBuscaValida) {
				boolean maior = new Integer(cont.getIdade()) > new Integer(
						this.busca);
				if (maior) {
					resultadoBusca.add(cont);
				}
			}

		}
	}

	public ArrayList<Contato> getResultadoBusca() {
		return resultadoBusca;
	}
}
