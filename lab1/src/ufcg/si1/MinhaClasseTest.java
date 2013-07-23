package ufcg.si1;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class MinhaClasseTest {
	
	private MinhaClasse obj;
	
	@Before
	public void inicializaClasse(){
		obj = new MinhaClasse();
	}
	
	@Test
	public void testMensagemXPTO() {
		
		String expected = "Este eh o primeiro lab de SI1";
		String actual = obj.xpto();
		Assert.assertEquals(expected, actual);
	}

}
