package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorDeTarefas {
	List<Tarefa> tarefas;

	public GerenciadorDeTarefas() {
		this.tarefas = new ArrayList<Tarefa>();
	}
	
	public void addNovaTarefa(String nome, Date data, String descricao, 
			String horaLimite) throws IllegalArgumentException{
	
		tarefas.add(new Tarefa(nome, data, descricao, horaLimite));
	}

	public int qntdDeTarefas() {
		return tarefas.size();
	}

}
