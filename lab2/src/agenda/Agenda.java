package agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

	List<Contato> listaDeContatos;

	public Agenda(){
		listaDeContatos = new ArrayList<Contato>();
	}


	public List<Contato> getContatos() {
		return listaDeContatos;
	}

	public long getQuantidadeDeNumeros(String nome) {

		for (Contato contato : listaDeContatos) {
			if(contato.getNome().equals(nome)) return contato.getQuantTelefones();
		}

		return 0;
	}


	public Contato searchContato(String nome) {

		for (Contato contato : listaDeContatos) {
			if(contato.getNome().equals(nome)) return contato;
		}

		return null;
	}

	public void addContato(String string, List<Numero> numeros) throws Exception {
		listaDeContatos.add(new Contato(string, numeros));

	}

	public boolean hasContato(String nome) {
		for (Contato contato : listaDeContatos) {
			if(contato.getNome().equals(nome)) return true;
		}

		return false;
	}

	public void setCamposOpcionais(String nome, String email, int idade) {

		Contato c1 = searchContato(nome);
		c1.setEmail(email);
		c1.setIdade(idade);
	}

	public List<Contato> searchContatos(String nome) {
		List<Contato> results = new ArrayList<Contato>();

		for (Contato contato : listaDeContatos) {
			if(contato.getNome().startsWith(nome)) results.add(contato);
		}
		return results;
	}

}