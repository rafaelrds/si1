package bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.*;
import model.data.Data;
import model.data.DateConclusion;
import model.data.DateCriation;
import model.data.Hour;

@ManagedBean(name = "taskbean")
@SessionScoped
public class TaskBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String conclusionDate;
	private Controller controller;
	private String conclusionTime;
	private Comparator<Task> comparator;
	private String ordenationWay;
	private boolean status;
	private Task task;
	
	public TaskBean() throws IllegalArgumentException {
		this.controller = new Controller();
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public Task getTarefa() {
		return task;
	}
	
	public void setTarefa(Task Task) {
		this.task = Task;
	}
	
	public String getOrdenacao() {
		return ordenationWay;
	}
	
	public void setOrdenacao(String ordenacao) {
		this.ordenationWay = ordenacao;
	}
	
	public String getHoraConclusao() {
		return conclusionTime;
	}
	
	public void setHoraConclusao(String horaConclusao) {
		this.conclusionTime = horaConclusao;
	}
	
	public Comparator<Task> getComparador() {
		return comparator;
	}
	
	public void setComparador(Comparator<Task> comparador) {
		this.comparator = comparador;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public String getDataConclusao() {
		return conclusionDate;
	}
	
	public void setDataConclusao(String dataConclusao) {
		this.conclusionDate = dataConclusao;
	}
	
	public String getNome() {
		return name;
	}
	
	public void setNome(String nome) {
		this.name = nome;
	}
	
	public String getDescricao() {
		return description;
	}
	
	public void setDescricao(String descricao) {
		this.description = descricao;
	}
	
	public List<Task> getTarefas() {
		return controller.getTarefas();
	}

	public String voltar() {
		return "index.xhtml";
	}
	
	public String addTarefa() {
		return "cadastro.xhtml";
	}
	
	public void cadastraTarefa() throws IllegalArgumentException {
		if (!validateNome()) {
			msgUsuario("Eh necessario dar um nome a tarefa");
			return;
		}
		Task Task = new Task(this.getNome());
		Task.setDescricao(this.getDescricao());

		if (!this.getDataConclusao().isEmpty()) {
			try {
				Data data = getConclusionDate(this.getDataConclusao());
				Task.setDataConclusao(data);
			} catch (Exception e) {
				msgUsuario("Data invalida");
				return;
			}
		}

		if (!this.getHoraConclusao().isEmpty()) {
			try {
				Hour hora = getConclusionTime(this.getHoraConclusao());
				Task.setHoraConclusao(hora);
			} catch (Exception e) {
				msgUsuario("Hora invalida!");
				return;
			}
		}
		if (!this.controller.getTarefas().contains(Task)) {
			this.controller.adicionaTarefa(Task);
		} else {
			msgUsuario("Nome da tarefa Invalido!");
			return;
		}
		clearSpaces();
	}

	public void mudaStatus() {
		if (this.task == null) {
			msgUsuario("Invalida");
			return;
		} else if (this.task != null && this.getTarefa().getStatus() == false) {
			this.task.setStatus(true);
			this.controller.addTarefaCompleta(this.getTarefa());
			this.controller.removeTarefaIncompleta(this.getTarefa());
			
		} else if (this.getTarefa().getStatus() == true) {
			msgUsuario("operacao invalida");
			return;
		} else {
			msgUsuario("Invalida");
			return;
		}
		
	}
	
	public void removeTarefa() {
		if (this.task == null) {
			msgUsuario("Invalida");
			return;
		} else {
			this.getController().removeTarefa(task);
		}
	}

	public String editarTarefa() {
		System.out.println(this.getTarefa());
		if (this.getTarefa() == null) {
			msgUsuario("Invalida");
			return "";
		}
		return "editor.xhtml";
	}

	public void salvarTarefa() {

		validateNome();
		try {
			Task newTask = new Task(this.getNome());
			newTask.setDataCriacao(this.getTarefa().getDataCriacao());
			newTask.setStatus(this.getTarefa().getStatus());
			if (!this.getDataConclusao().isEmpty()) {
				try {
					Data data = new Data(Integer.parseInt(this
							.getDataConclusao().substring(0, 2)),
							Integer.parseInt(this.getDataConclusao().substring(
									3, 5)), Integer.parseInt(this
									.getDataConclusao().substring(6, 10)));
					newTask.setDataConclusao(data);
				} catch (Exception e) {
					msgUsuario("Data Invalida");
					return;
				}
			} else {
				Data data = new Data();
				newTask.setDataConclusao(data);
			}
			if (!this.getHoraConclusao().isEmpty()) {
				try {
					Hour hora = new Hour(Integer.parseInt(this
							.getHoraConclusao().substring(0, 2)),
							Integer.parseInt(this.getHoraConclusao().substring(
									3, 5)));
					task.setHoraConclusao(hora);
				} catch (Exception e) {
					msgUsuario("Hora Invalida");
					return;

				}
			} else {
				Hour hora = new Hour();
				newTask.setHoraConclusao(hora);
			}
			newTask.setDescricao(this.getDescricao());
			this.getController().editTarefa(this.getTarefa(), newTask);
		} catch (Exception e) {
			msgUsuario("Nome Invalido");
			return;
		}

	}

	public void ordena() {
		if (this.getOrdenacao().equals("")) {
			msgUsuario("Invalida");
			return;
		} else if (this.getOrdenacao().equals("dataCriacao")) {
			this.setComparador(new DateCriation());
		} else {
			this.setComparador(new DateConclusion());
		}
		this.controller.ordena(this.getComparador());
	}

	public void ordenaCompletas() {
		if (this.getOrdenacao().equals("")) {
			msgUsuario("Invalida");
			return;
		} else if (this.getOrdenacao().equals("dataCriacao")) {
			this.setComparador(new DateCriation());
		} else {
			this.setComparador(new DateConclusion());
		}
		this.controller.ordenaCompletas(this.getComparador());
	}

	public void ordenaIncompletas() {
		if (this.getOrdenacao().equals("")) {
			msgUsuario("Invalida");
			return;
		} else if (this.getOrdenacao().equals("dataCriacao")) {
			this.setComparador(new DateCriation());
		} else {
			this.setComparador(new DateConclusion());
		}
		this.controller.ordenaIncompletas(this.getComparador());
	}

	private Data getConclusionDate(String conclusiondate) throws IllegalArgumentException {
		return new Data(Integer.parseInt(conclusiondate.substring(0, 2)),
				Integer.parseInt(conclusiondate.substring(3, 5)),
				Integer.parseInt(conclusiondate.substring(6, 10)));
	}

	private Hour getConclusionTime(String conclusionTime) throws IllegalArgumentException {
		return new Hour(Integer.parseInt(conclusionTime.substring(0, 2)),
				Integer.parseInt(conclusionTime.substring(3, 5)));
	}

	private void clearSpaces() {
		this.setNome("");
		this.setDescricao("");
		this.setDataConclusao("");
		this.setHoraConclusao("");
	}

	private boolean validateNome() {
		return !this.getNome().isEmpty();
	}

	private void msgUsuario(String string1) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1));
	}

}
