package testes;
import java.util.Date;

import javax.naming.TimeLimitExceededException;

import org.junit.*;

import model.Tarefa;

public class TestaTarefa {
	Tarefa t1, t2;
	
	@Test
	public void testaCriarNovaTarefa(){
		t1 = new Tarefa("Estudar");

		
		Assert.assertEquals("Estudar", t1.getNome());
		
		Assert.assertEquals(t1.getDescricao(), "");
		t1.setDescricao("estudar para todas as disciplinas");
		Assert.assertEquals(t1.getDescricao(), "estudar para todas as disciplinas");
		
		Date d = new Date();
		Assert.assertEquals(t1.getData(), null);		
		try{
			t1.setData(d);
		}catch (TimeLimitExceededException e){
			
		}
		Assert.assertEquals(t1.getData(), d);
		
		Date d2 = new Date(14,07,2013);
		t2 = new Tarefa("Missa", d2, "meu tesouro", "00:00:00");
		Assert.assertEquals("Missa", t2.getNome());
		Assert.assertEquals("meu tesouro", t2.getDescricao());
		Assert.assertEquals("00:00:00", t2.getHoraLimite());
		Assert.assertEquals(d2, t2.getData());
		
		
	}

}
