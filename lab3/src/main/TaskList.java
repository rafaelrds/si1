package main;

import java.util.Scanner;

import model.*;
import model.data.Data;
import model.data.DateConclusion;
import model.data.DateCriation;
import model.data.Hour;
import bean.Controller;
public class TaskList{

	private static Controller controller = new Controller();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Task List \n");
		while (true) {
			System.out.println("O que deseja? \n "
					+ "1-Cadastrar tarefa \n "
					+ "2-Listar Tarefas Completas \n "
					+ "3-Listar Tarefas Incompletas \n "
					+ "4-Remover Tarefa \n " + "5-Editar Task \n "
					+ "6-Concluir Tarefa \n " 
					+"7-Sair");

			String escolha = sc.next();
			switch (escolha) {
			case "1":
				cadastraTask();
				break;
			case "2":
				listarTasksCompletas();
				break;
			case "3":
				listarTasksIncompletas();
				break;
			case "4":
				remove();
				break;
			case "5":
				editTask();
				break;
			case "6":
				concludeTask();
				break;
			case "7":
				System.out.println("Sistema encerrado pelo usuario");
				System.exit(1);
			}
		}
	}

	private static void remove() {
		Task Task = removeTask();
		if (Task != null) {
			controller.removeTarefa(Task);
			System.out.println("A tarefa foi Removida");
		} else {
			System.out.println("A tarefa NAO pode ser removida");
		}
	}

	public static void cadastraTask() {
		System.out.println("Nome: ");
		String nome = sc.next();

		System.out.println("Descricao: ");
		String descricao = sc.next();

		System.out.println("Prazo de conclusao (dd/mm/yyyy): ");
		String data = sc.next();

		System.out.println("Horario de conclusao (hh:mm):");
		String hora = sc.next();
		
			try {
				Task Task = new Task(nome);
				Task.setDescricao(descricao);
				Task.setDataConclusao(formateDate(data));
				Task.setHoraConclusao(formateHour(hora));

				controller.adicionaTarefa(Task);
				System.out.println("Tarefa cadastrada com sucesso \n");
			} catch (Exception e) {
				System.out.println("A tarefa NAO foi criada ");
			}
	}

	private static Data formateDate(String data) throws IllegalArgumentException {
		return new Data(Integer.parseInt(data.substring(0, 2)),
				Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data
						.substring(6, 10)));
	}

	private static Hour formateHour(String hora) throws IllegalArgumentException {
		return new Hour(Integer.parseInt(hora.substring(0, 2)),
				Integer.parseInt(hora.substring(3, 5)));
	}

	public static void listarTasksCompletas() {
		if (controller.getTarefasCompletas().size() > 0) {
			System.out
					.println(" 1 - Data de conclusao \n 2 - Data de Criacao");
			String escolha = sc.next();
			if (escolha.equals("1")) {
				controller.ordenaCompletas(new DateConclusion());
			} else if (escolha.equals("2")) {
				controller.ordenaCompletas(new DateCriation());
			}
			System.out.println("Tarefas concluidas: \n");
			for (Task Task : controller.getTarefasCompletas()) {
				System.out.println(Task);
			}
		} else {
			System.out.println("Nao ha tarefas concluidas");
		}
	}

	public static void listarTasksIncompletas() {
		if (controller.getTarefasIncompletas().size() > 0) {
			System.out
					.println("Ver por: \n 1 - Data de Conclusao \n 2 - Data de Criacao");
			String escolha = sc.next();
			if (escolha.equals("1")) {
				controller.ordenaIncompletas(new DateConclusion());
			} else if (escolha.equals("2")) {
				controller.ordenaIncompletas(new DateCriation());
			}
			System.out.println("Tarefas incompletas: \n");
			for (Task Task : controller.getTarefasIncompletas()) {
				System.out.println(Task);
			}
		} else {
			System.out.println("Nao ha tarefas incompletas");
		}

	}

	private static Task searchEditionTask() {
		System.out.println("Digite o nome da tarefa: \n");
		String nome = sc.next();
		for (Task Task : controller.getTarefas()) {
			if (Task.getNome().equals(nome)) {
				System.out.println("Deseja editar? (s/n) \n"
						+ Task);
				String escolha = sc.next();
				if (escolha.equals("s")) {
					return Task;
				} else if (escolha.equals("n")) {
					System.out.println("Cancelado!");
					return null;
				}
			}
		}
		System.out.println("Nao foi encontrada");
		return null;
	}

	private static Task removeTask() {
		System.out.println("Digite o nome da Tarefa: \n");
		String nome = sc.next();
		for (Task task : controller.getTarefas()) {
			if (task.getNome().equals(nome)) {
				System.out.println("Deseja remover? (s/n) \n"
						+ task);
				String escolha = sc.next();
				if (escolha.equals("s") | escolha.equals("sim")) {
					controller.removeTarefa(task);
					return task;
				} else if (escolha.equals("n") | escolha.equals("nao")) {
					System.out.println("Cancelado");
					return null;
				}
			}
		}
		System.out.println("Tarefa nao encontrada");
		return null;
	}

	private static Task searchCTask() {
		System.out.println("Digite o nome da Tarefa: \n");
		String nome = sc.next();
		for (Task Task : controller.getTarefas()) {
			if (Task.getNome().equals(nome)) {
				System.out
						.println("Deseja concluir? (s/n) \n"
								+ Task);
				String escolha = sc.next();
				if (escolha.equals("s")) {
					return Task;
				} else if (escolha.equals("n")) {
					System.out.println("Cancelada");
					return null;
				}
			}
		}
		System.out.println("Tarefa nao encontrada");
		return null;
	}

	private static void concludeTask() {
		Task Task = searchCTask();
		if (Task != null) {
			controller.mudaStatusDaTask(Task);
		}
	}

	private static void editTask() {
		Task Task = searchEditionTask();
		if (Task != null) {
			System.out.println("Nome:");
			String nome = sc.next();

			System.out.println("Descricao:");
			String descricao = sc.next();

			System.out.println("Data:");
			String data = sc.next();

			System.out.println("Hora:");
			String hora = sc.next();

				try {
					Task.setNome(nome);
					Task.setDescricao(descricao);
					Task.setDataConclusao(formateDate(data));
					Task.setHoraConclusao(formateHour(hora));
				} catch (Exception ex) {
					System.out.println("Edicao NAO concluida");
				}
			
		}
	}
}