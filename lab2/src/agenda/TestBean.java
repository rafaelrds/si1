package agenda;

import org.junit.Test;

import junit.framework.Assert;

public class TestBean {

	@Test
	public void testaAdd(){
		AgendaBean c1 = new AgendaBean();


		c1.addContato("Joao", "876523456789", "oi", "", "");
		Assert.assertEquals(true, c1.hasContato("Joao"));

		Assert.assertEquals(false, c1.hasContato("joana"));
	}

}