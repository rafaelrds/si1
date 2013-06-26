package agenda;

import java.util.ArrayList;
import java.util.List;

public class Contato {

	private String nome;
	private List<Numero> telefones;
	private List<String> emails;
	private Integer idade;
	


	public Contato(String nome, List<Numero> telefones,ArrayList<String> emails) {
		this.nome=nome;
		this.telefones=telefones;
		this.emails=emails;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Numero> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Numero> telefones) {
		this.telefones = telefones;
	}


	public List<String> getEmails() {
		return emails;
	}


	public void setEmails(List<String> emails) {
		this.emails = emails;
	}


	public Integer getIdade() {
		return idade;
	}


	public void setIdade(Integer idade) {
		this.idade = idade;
	}




}