package task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Task {

	private String name;
	private String description;
	private Date actualDate;
	private Date date;
	private boolean isCompleted;

	public Task (String name, String description,int year,int month, int date) throws IllegalArgumentException{
		Calendar calendar = new GregorianCalendar();
		actualDate= calendar.getTime();
		calendar.set(year, month, date);
		this.date = calendar.getTime();

		if(name == null){
			throw new IllegalArgumentException("Nome deve ser válido");
		}
		if(this.date.compareTo(actualDate)<0){
			throw new IllegalArgumentException("Data incorreta");
		}
		
		calendar.getTime();
		this.name=name;
		this.description=description;
		this.isCompleted=false;
	}

	public Task (String name, String description,Date date) throws IllegalArgumentException{
		Calendar calendar = new GregorianCalendar();
		actualDate= calendar.getTime();
		this.date=date;		
		if(name == null){
			throw new IllegalArgumentException("Nome deve ser válido");
		}
		if(date.compareTo(actualDate)<0){
			throw new IllegalArgumentException("The date has passed");
		}
		
		calendar.getTime();
		this.name=name;
		this.description=description;
		this.isCompleted=false;
	}


	public Task (String name) throws IllegalArgumentException{
		if(name == null){
			throw new IllegalArgumentException("Nome deve ser válido");
		}

		this.isCompleted=false;
		this.name=name;
		this.description=null;
		date=null;
	}

	public Task(){
		this.name=null;description=null;date=null;isCompleted=false;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean IsCompleted(){
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted){
		this.isCompleted=isCompleted;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Task)){
			return false;
		}
		Task task = (Task)obj;
		return (task.getName().equals(this.name));
	}


}