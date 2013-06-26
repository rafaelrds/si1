package agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

	List<Contato> listaDeContatos;

	public Agenda(){
		listaDeContatos = new ArrayList<Contato>();
	}

	public int getQuantidadeDeNumeros() {
		return listaDeContatos.size();
	}

	public void addContato(String nome, ArrayList<Numero> telefones) {
		Contato contato = new Contato(nome,telefones);
		listaDeContatos.add(contato);
	}

	public List<Contato> getContatos() {
		return listaDeContatos;
	}
	
	public boolean hasContato(String nome) {
		for (Contato contato : listaDeContatos) {
			if(contato.getNome().equals(nome))
				return true;
		}

		return false;
	}

}