package agenda;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
		assertNotNull(lista.getContatos());
	}
	
	@Test
	public void testCadastraNovoContato() {

		assertEquals(lista.getQuantidadeDeNumeros(), 0);
		lista.addContato("Rafael",new ArrayList<Numero>());
		assertFalse(lista.getQuantidadeDeNumeros() == 0);
	}

	

}