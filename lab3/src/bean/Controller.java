package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Task;
import model.data.Data;
import model.data.Hour;
public class Controller {

	private List<Task> tarefas;
	private List<Task> tarefasCompletas;
	private List<Task> tarefafsIncompletas;


	public List<Task> getTarefasCompletas() {
		return tarefasCompletas;
	}

	public void setTarefasCompletas(List<Task> TasksCompletas) {
		this.tarefasCompletas = TasksCompletas;
	}

	public List<Task> getTarefasIncompletas() {
		return tarefafsIncompletas;
	}

	public void setTarefasIncompletas(List<Task> TasksIncompletas) {
		this.tarefafsIncompletas = TasksIncompletas;
	}

	public void adicionaTarefa(Task Task) {
		this.tarefas.add(Task);
		this.addTarefaIncompleta(Task);
	}

	public List<Task> getTarefas() {
		return this.tarefas;
	}

	public void ordena(Comparator<Task> comparador) {
		Collections.sort(this.tarefas, comparador);
	}

	public void ordenaCompletas(Comparator<Task> comparador) {
		Collections.sort(this.tarefasCompletas, comparador);
	}

	public void ordenaIncompletas(Comparator<Task> comparador) {
		Collections.sort(this.tarefafsIncompletas, comparador);
	}

	public void addTarefaIncompleta(Task Task) {
		this.tarefafsIncompletas.add(Task);
	}

	public void addTarefaCompleta(Task Task) {
		this.tarefasCompletas.add(Task);
	}

	public void removeTarefaIncompleta(Task Task) {
		this.tarefafsIncompletas.remove(Task);
		this.tarefas.remove(Task);
	}
	public Controller() {
		this.tarefas = new ArrayList<Task>();
		this.tarefasCompletas = new ArrayList<Task>();
		this.tarefafsIncompletas = new ArrayList<Task>();
		try {
			Task task1 = new Task("Aula de SI");
			Task task2 = new Task("Terminar a graduacao");
			Task task3 = new Task("Terminar mestrado");
			Task task4 = new Task("Abrir uma empresa");
			Task task5 = new Task("Comprar a Google");
			task1.setDataConclusao(new Data(16,07,2013));
			task2.setDataConclusao(new Data(12,8,2015));
			task3.setDataConclusao(new Data(14,5,2017));
			task4.setDataConclusao(new Data(16,9,2017));
			task5.setDataConclusao(new Data(23,7,2030));
			Hour hora1 = new Hour(23, 23);
			Hour hora2 = new Hour(22, 21);
			Hour hora3 = new Hour(20, 15);
			task1.setHoraConclusao(hora1);
			task2.setHoraConclusao(hora2);
			task3.setHoraConclusao(hora1);
			task4.setHoraConclusao(hora3);
			task5.setHoraConclusao(hora1);
			task1.setDescricao("Terças e sextas");
			task2.setDescricao("Though work");
			task3.setDescricao("Definir a area");
			task4.setDescricao("Conseguir um bom sócio");
			task5.setDescricao("hahaha");
			adicionaTarefa(task1);
			adicionaTarefa(task2);
			adicionaTarefa(task5);
			adicionaTarefa(task3);
			adicionaTarefa(task4);
		} catch (Exception e) {
		}
	}
	
	public void mudaStatusDaTask(Task task1) {
		for (Task task : this.tarefas) {
			if (task1.getNome().equals(task.getNome())) {
				if (!task.getStatus()) {
					task.setStatus(true);
					addTarefaCompleta(task);
					removeTarefaIncompleta(task);
				}
				break;
			}
		}
	}

	public void removeTarefaCompleta(Task Task) {
		this.tarefasCompletas.remove(Task);
	}

	public void removeTarefa(Task Task) {
		if (Task.getStatus() == true) {
			this.removeTarefaCompleta(Task);
		} else {
			this.removeTarefaIncompleta(Task);
		}
		this.getTarefas().remove(Task);
	}

	public void editTarefa(Task oldTask, Task newTask) throws IllegalArgumentException{

		if (this.getTarefas().contains(newTask)) {
			if (!oldTask.getNome().equals(newTask.getNome())) {
				throw new IllegalArgumentException("Nome invalido");
			}
		}
		for (int i = 0; i < this.getTarefas().size(); i++) {
			if (oldTask.equals(this.getTarefas().get(i))) {
				this.removeTarefa(oldTask);
				this.getTarefas().add(newTask);
				if (newTask.getStatus()) {
					this.addTarefaCompleta(newTask);
				} else {
					this.addTarefaIncompleta(newTask);
				}
				break;
			}
		}
	}
}