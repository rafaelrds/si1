package model.data;


public class Data implements Comparable<Data> {

	private int day;
	private int year;
	private int month;

	public Data(int day, int month, int year) throws IllegalArgumentException {
		if (day < 1 || day > 31)
			throw new IllegalArgumentException("Dia invalido");
		if (month == 2 && day > 29)
			throw new IllegalArgumentException("Mes invalido");
		if (month == 2 && day == 29) {
			if (!(year % 4 == 0)) {
				throw new IllegalArgumentException("Ano invalido");
			} else {
				if ((year % 100 == 0))
					throw new IllegalArgumentException("Ano invalido");
			}
		}
		if (day == 31) {
			if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)
				throw new IllegalArgumentException("M�s invalido");
		}		

		this.day = day;
		this.month = month;
		this.year = year;
	}


	public void setAno(int ano) throws IllegalArgumentException {
		if (ano > 1900) {
			this.year = ano;
		} else {
			throw new IllegalArgumentException("Ano invalido");
		}
	}

	public Data() {
	}
	
	public int getDia() {
		return day;
	}
	
	public void setDia(int dia) throws IllegalArgumentException {
		if (dia >= 1 && dia <= 31) {
			this.day = dia;
		} else {
			throw new IllegalArgumentException("Dia invalido");
		}
	}
	
	public int getAno() {
		return year;
	}
	public int getMes() {
		return month;
	}

	public void setMes(int mes) throws IllegalArgumentException {
		if (mes >= 1 && mes <= 12) {
			this.month = mes;
		} else {
			throw new IllegalArgumentException("Mes invalido");
		}
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Data))
			return false;
		return this.getDia() == ((Data) obj).getDia()
				&& this.getMes() == ((Data) obj).getMes()
				&& this.getAno() == ((Data) obj).getAno();
	}

	@Override
	public int compareTo(Data data) {
		if (this.getAno() == 0 && this.getMes() == 0 && this.getDia() == 0)
			return 2;
		if (data.getAno() > this.getAno()) {
			return -1;
		} else if (data.getAno() < this.getAno()) {
			return 1;
		} else {
			if (data.getMes() > this.getMes()) {
				return -1;
			} else if (data.getMes() < this.getMes()) {
				return 1;
			} else {
				if (data.getDia() > this.getDia()) {
					return -1;
				} else if (data.getDia() < this.getDia()) {
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	public String toString() {
		return this.getDia() + "/" + this.getMes() + "/" + this.getAno();
	}
}
