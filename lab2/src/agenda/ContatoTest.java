package agenda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


public class ContatoTest extends TestCase {


	
	@Test
	public void testaNome(){
		
		ArrayList<Numero> telefones = new ArrayList<Numero>();
		Numero num1 = new Numero("96246511", "41", "TIM");
		Numero num2 = new Numero("86253015", "31", "Oi");
		telefones.add(num1);
		telefones.add(num2);
		
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("crafards@gmail.com");
		
		Contato contato = new Contato("Rafael", telefones, emails);
		assertEquals("Rafael", contato.getNome());
	}



}
