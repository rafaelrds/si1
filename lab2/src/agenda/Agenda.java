package agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

	List<Contato> listaDeContatos;

	public Agenda(){
		listaDeContatos= new ArrayList<Contato>();
	}

	public int getTotalDeContatos() {
		return listaDeContatos.size();
	}

	public void addContato(String nome, ArrayList<Numero> telefones, ArrayList<String> emails) {
		Contato newContato = new Contato(nome,telefones,emails);
		listaDeContatos.add(newContato);
	}

	public List<Contato> getContatos() {
		return listaDeContatos;
	}

}