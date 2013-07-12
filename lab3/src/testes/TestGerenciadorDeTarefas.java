package testes;


import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;
import model.GerenciadorDeTarefas;

public class TestGerenciadorDeTarefas {
	
	@Test
	public void testaCriaNovaTarefa(){
		GerenciadorDeTarefas g1 = new GerenciadorDeTarefas();
		
		try{
		g1.addNovaTarefa("lab3", new Date(2013,12,14), "", "");
		} catch(Exception e){
			Assert.assertEquals("Voce nao pode criar uma tarefa com data anterior a atual", e.getMessage());
		}
		Assert.assertEquals(1, g1.qntdDeTarefas());
		
		try{
		g1.addNovaTarefa("", new Date(2013,11,14), "", "");
		}catch (IllegalArgumentException e){
			
		}
		Assert.assertEquals(1, g1.qntdDeTarefas());
		
		try{
			g1.addNovaTarefa("ano novo, ops!", new Date(2012,11,14), "", "");
		}catch (IllegalArgumentException e){				
		}
		//Assert.assertEquals(1, g1.qntdDeTarefas());
		
		Date d1 = new Date(2012,20,14);
		Date d2 = new Date(2000,10,14);
		Date d3 = new Date(2013,12,14);
		Date d4 = new Date(2017,2,20);
		Date d = new Date();
		
		System.out.println(d.before(d1));
		System.out.println(d.before(d2));
		System.out.println(d.before(d3));
		System.out.println(d.before(d4));
		System.out.println(d.before(d));
		
		
		try{
			g1.addNovaTarefa("ano novo, ops!", new Date(2015,11,14), "", "");
		}catch (IllegalArgumentException e){
				
		}
		Assert.assertEquals(2, g1.qntdDeTarefas());
		
		
		
		
	}

}
