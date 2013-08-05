package model;

import java.io.Serializable;

public class Telefone implements Serializable {

	private static final long serialVersionUID = -8642232220756721267L;

	private String codigoRegional;
	private String operadora;
	private String operadoraLigar;
	private String numero;
	private String codigoNacional;

	public String getCodigoNacional() {
		return codigoNacional;
	}

	public void setCodigoNacional(String codigoNacional) {
		this.codigoNacional = codigoNacional;
	}

	public String getCodigoRegional() {
		return codigoRegional;
	}

	public void setCodigoRegional(String c) {
		this.codigoRegional = c;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getOperadoraLigar() {
		return operadoraLigar;
	}

	public void setOperadoraLigar(String operadoraLigar) {
		this.operadoraLigar = operadoraLigar;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	private static boolean xOR(boolean x, boolean y) {
		return ((x || y) && !(x && y));
	}

	public boolean ehTelefoneValido() {
		boolean notAmbosValidos = xOR(this.codigoRegional == null, this.operadoraLigar == null);
		if (notAmbosValidos) {
			return false;
		}
		boolean numeroValido = !(this.numero == null || (this.numero.length() < 8));
		return numeroValido;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Telefone) {
			boolean isNameEqual = this.numero.equals(((Telefone) obj).getNumero());
			
			return isNameEqual;
		}
		
		return false;
	}
}
