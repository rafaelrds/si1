package bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import model.Contato;
import model.Telefone;
import model.Usuario;
import controller.Controladora;
import exceptions.EmptyFieldException;
import exceptions.InvalidNumberException;

@ManagedBean(name = "agendaBean", eager = true)
@SessionScoped
public class Agenda implements Serializable {

	private static final long serialVersionUID = -2944088848421718867L;
	private static final String NOME_ARQUIVO = "Agenda.txt";
	
	private String login;
	private String password;
	private String newLogin;
	private String newPassword;
	private Controladora controladora;

	private ArrayList<Contato> contatos;
	
	private String email;
	private Contato contato;
	private Telefone telefone;
	private Contato contatoSelecionado;
	private boolean telefoneInvalido;
	private String contatoValido;
	private String tipoDeBusca;
	private String busca;
	private ArrayList<Contato> resultadoBusca;

	public Agenda() {
		try {
			lerDados();
		} catch (IOException e) { }
		
		inicializaBean();
	}
	
	private void inicializaBean() {		
		this.login = "";
		this.password = "";
		this.newLogin = "";
		this.newPassword = "";
		this.email = "";
		this.contatoValido = "false";

		this.telefoneInvalido = false;
		this.contato = new Contato();
		this.telefone = new Telefone();
		this.contatoSelecionado = new Contato();
		this.busca = "";
		this.tipoDeBusca = "1";
		this.resultadoBusca = new ArrayList<Contato>();
		
		inicializeArrays(); //cria usuario|123456 com 5 telefones cadastrados
		
	}

	public void persistirDados() throws IOException {
		FileOutputStream fileSaida = null;
		ObjectOutputStream out = null;
		
		try {
			fileSaida = new FileOutputStream(NOME_ARQUIVO);
			out = new ObjectOutputStream(fileSaida);
			out.writeObject(contatos);
			out.writeObject(controladora.getUsuarios());
			out.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void lerDados() throws IOException {
		FileInputStream fileEntrada = null;
		ObjectInputStream in = null;
		
		try {
			fileEntrada = new FileInputStream(NOME_ARQUIVO);
			in = new ObjectInputStream(fileEntrada);
//			contatos = (ArrayList<Contato>) in.readObject();
			System.out.println(in.readObject());
			this.controladora = (Controladora) in.readObject();			
			in.close();
		} catch (IOException ex) {
			this.controladora = new Controladora();
			persistirDados();
		} catch (ClassNotFoundException ex) {
			this.controladora = new Controladora();
			ex.printStackTrace();
		}catch(Exception e) {
			this.controladora = new Controladora();
		}
	}

	

	public String saveButton() {
		Usuario novoUsuario = new Usuario(newLogin, newPassword);
		if(this.controladora.addUsuario(novoUsuario)){
			msgUsuario("Usuário Cadastrado", "Seja Bem-vindo " + newLogin);
			inicializaBean();
			this.contatos = novoUsuario.getContatos();
			try {
				persistirDados();
			} catch (Exception e) {
			}
			return "index.seam";
		} else{
			msgUsuario("Usuário já existente", "Escolha outro login");
			return "";
		}
		
		
	}

	public String loginButton() {
		ArrayList<Contato> contatos = this.controladora.buscaUsuario(login, password);
		if(contatos != null){
			this.contatos=contatos;
			msgUsuario("Usuário logado", "Seja Bem-vindo " + login);
			return "main.seam";
		} else{
			msgUsuario("Login não realizado", "Senha errada ou Login inexistente");
			return "";	
		}
		
	}

	public String logoffButton() {
		inicializaBean();
		return "index.seam";
	}

	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
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
		c1.setNome("Ulisses Peixoto");
		c1.setDescricao("Um mecanico hidraulico e cinefilo.");
		c2.setNome("Vania Uchoa");
		c2.setDescricao("Uma jovem estudante de medicina.");
		c3.setNome("Agenor Alencar");
		c3.setDescricao("Um senhor de idade, perito em jogos de tabuleiro.");
		c4.setNome("Otavio Figueira");
		c4.setDescricao("Um paisagista e amante de esportes de adrenalina.");
		c5.setNome("Terencio Horto");
		c5.setDescricao("Um escritor freelancer e amante dos quadrinhos");
		c1.addEmail("ulisses@github.com");
		c2.addEmail("uchoavaninha@gmail.com");
		c3.addEmail("agenora@ibm.com");
		c4.addEmail("otavio@github.com");
		c5.addEmail("terencioh@malvados.com");
		c1.setIdade("40");
		c2.setIdade("22");
		c3.setIdade("85");
		c4.setIdade("38");
		c5.setIdade("55");
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
		
		Usuario novoUsuario = new Usuario("usuario", "123456");
		novoUsuario.addContato(c1);
		novoUsuario.addContato(c2);
		novoUsuario.addContato(c3);
		novoUsuario.addContato(c4);
		novoUsuario.addContato(c5);
		controladora.addUsuario(novoUsuario);
		try {
			persistirDados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void addTelefone(ActionEvent event) {
		this.telefoneInvalido = false;
		try {
			this.contato.addTelefone(telefone);
			persistirDados();
		} catch (InvalidNumberException e) {
			this.telefoneInvalido = true;
		} catch (IOException e) {
		}
		this.telefone = new Telefone();
	}

	

	public void removeEmail(String email) {
		this.contato.removeEmail(email);
		try {
			persistirDados();
		} catch (Exception e) {
		}
	}

	public void cancelarContato(ActionEvent event) {
		this.telefoneInvalido = false;
		this.contato = new Contato();
		try {
			persistirDados();
		} catch (Exception e) {
		}

	}

	public void addEmail() {
		this.contato.addEmail(this.email);
		this.email = "";
		try {
			persistirDados();
		} catch (Exception e) {
		}
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void addContato(ActionEvent event) throws EmptyFieldException,
			InvalidNumberException {
		if (!contatos.contains(contato)) {
			contatos.add(contato);
		}
		contato = new Contato();
		this.telefone = new Telefone();
		try {
			this.persistirDados();
		} catch (Exception e) {
		}
	}

	public void removeTelefone(Telefone tel) {
		contato.removeTelefone(tel);
		try {
			persistirDados();
		} catch (Exception e) {
		}

	}

	
	public void fazBusca(AjaxBehaviorEvent event) {
		resultadoBusca = new ArrayList<Contato>();
		for (Contato cont : contatos) {

			boolean idadeBuscaValida = !(cont.getIdade().equals("") 
					|| this.busca.equals(""));

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
				boolean menor = new Integer(cont.getIdade()) < new Integer(this.busca);
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
	
	public String getNewLogin() {
		return newLogin;
	}
	
	public void setNewLogin(String newLogin) {
		this.newLogin = newLogin;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBusca() {
		return busca;
	}
	
	public void setBusca(String busca) {
		this.busca = busca;
	}
	
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
		return telefoneInvalido;
	}

	public String getContatoValido() {
		boolean temp = !(this.contato.getNome().equals("") 
				|| this.contato.getTelefones().isEmpty());
		this.contatoValido = new Boolean(temp).toString();
		return contatoValido;
	}


}