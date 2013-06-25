package ufcg.si1;

public class MinhaClasse {
	
	public String xpto(){
		
		return "Este eh o primeiro lab de SI1";
	}
	
	public static void main(String[] args) {
		MinhaClasse obj = new MinhaClasse();
		String mensagem = obj.xpto();
		System.out.println(mensagem);
	}
}
