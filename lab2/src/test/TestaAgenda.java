package test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import agenda.Agenda;
import agenda.Numero;
public class TestaAgenda {
	
	@Test
	public void testaNome(){
		Agenda agenda2 = new Agenda();

		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(new Numero("311288675432", "oi"));

		try{
			agenda2.addContato("", numeros);
			fail();
		}catch(IllegalArgumentException e){

		}catch (Exception e) {

		}

	}
	
	@Test
	public void testaParametrosMinimos(){
		Agenda agenda1 = new Agenda();

		Assert.assertEquals(0, agenda1.getQuantidadeDeNumeros("João"));
		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(new Numero("311288675432", "oi"));

		try{
			agenda1.addContato("Joao", numeros);
		}catch(IllegalArgumentException e){

		}catch (Exception e) {

		}

		Assert.assertEquals(1, agenda1.getQuantidadeDeNumeros("Joao"));

		List<Numero> numeros2 = new ArrayList<Numero>();
		numeros2.add(new Numero("311288675455", "oi"));
		Assert.assertEquals(0, agenda1.getQuantidadeDeNumeros("Joana"));

		try{
			agenda1.addContato("Joana", numeros2);
		}catch(IllegalArgumentException e){

		}catch(Exception e){

		}

		Assert.assertEquals(1, agenda1.getQuantidadeDeNumeros("Joana"));

	}


}