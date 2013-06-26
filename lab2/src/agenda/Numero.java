package agenda;

public class Numero {
	private String numero;
	private String numOperadora;
	private String nomeOperadora;
	
	public Numero(String numero, String numOperadora, String nomeOperadora) {
		
		this.numero = numero;
		this.numOperadora = numOperadora;
		this.nomeOperadora = nomeOperadora;
	}

	public String getNumero() {
		return numero;
	}

	public String getNumOperadora() {
		return numOperadora;
	}

	public String getNomeOperadora() {
		return nomeOperadora;
	}

	@Override
	public String toString() {
		return "Numero [numero=" + numero + ", numOperadora=" + numOperadora
				+ ", nomeOperadora=" + nomeOperadora + "]";
	}
	
	
	
	
}
