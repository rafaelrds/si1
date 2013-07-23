package agenda;

import java.util.List;

public class Contato {

	private String nome;
	private List<Numero> telefones;
	private Integer idade;
	private String email;
	


	public Contato(String nome, List<Numero> telefones) {
		if(nome.equals("") | nome == null) 
			throw new IllegalArgumentException();

		this.nome=nome;
		this.telefones=telefones;
	}
	
	public int getQuantTelefones() {
		return telefones.size();
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


	public Integer getIdade() {
		return idade;
	}


	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	


}