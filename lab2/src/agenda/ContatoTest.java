package agenda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


public class ContatoTest extends TestCase {


	
	@Test
	public void testaNome(){
		ArrayList<String> telefones = new ArrayList<String>();
		telefones.add("96246511");
		telefones.add("86253015");
		
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("crafards@gmail.com");
		
		Contato contato = new Contato("Rafael", telefones, emails);
		assertEquals("Rafael", contato.getNome());
	}



}
