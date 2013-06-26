package agenda;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAgenda {

	Agenda lista;

	@Before
	public void init(){
		lista = new Agenda();
	}

	@Test
	public void testListarcontatos(){
		Assert.assertNotNull(lista.getContatos());
	}
	
	@Test
	public void testCadastraNovoContato() {

		Assert.assertEquals(lista.getTotalDeContatos(), 0);
		lista.addContato("Joao",new ArrayList<String>(),new ArrayList<String>());
		Assert.assertFalse(lista.getTotalDeContatos() == 0);
	}

	

}