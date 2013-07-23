package agenda;

public class Numero {
	private String numero;
	private String operadora;
	
	public Numero(String numero, String numOperadora) {
		this.numero = numero;
		this.operadora = numOperadora;
	}

	public String getNumero() {
		return numero;
	}

	public String getNumOperadora() {
		return operadora;
	}


	@Override
	public String toString() {
		return "Numero [numero=" + numero + ", numOperadora=" + operadora + "]";
	}
	
	
	
	
}
