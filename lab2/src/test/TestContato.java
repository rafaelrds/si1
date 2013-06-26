package test;

import java.util.ArrayList;
import org.junit.Test;

import agenda.Contato;
import agenda.Numero;

import junit.framework.TestCase;


public class TestContato extends TestCase {
	
	@Test
	public void testaCampos(){
		
		ArrayList<Numero> telefones = new ArrayList<Numero>();
		Numero num1 = new Numero("96246511", "TIM");
		Numero num2 = new Numero("86253015", "Oi");
		telefones.add(num1);
		telefones.add(num2);
		
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("crafards@gmail.com");
		
		Contato contato = new Contato("Rafael", telefones);
		assertEquals("Rafael", contato.getNome());
	}



}
