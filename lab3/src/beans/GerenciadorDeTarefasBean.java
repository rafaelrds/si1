package beans;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

import model.GerenciadorDeTarefas;
import model.Tarefa;

@ManagedBean(name="gerenciadorBean")
public class GerenciadorDeTarefasBean {
	private GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
	private List<Tarefa> completas;
	private List<Tarefa> incompletas;
	private String nome;
	private String descricao;
	private Date data;
	
	public String pageNewTask(){
		return "nova_tarefa?faces-redirect=true";
	}
	
	public String index(){
		return "index?faces-redirect=true";
	}

	public void addNewTask(){
		gerenciador.addNovaTarefa(nome, descricao, "", "");
		System.out.println(gerenciador.qntdDeTarefas());
		setNome("");
		setDescricao("");
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
