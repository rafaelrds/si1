package test;

import org.junit.Test;

import bean.AgendaBean;


import junit.framework.Assert;

public class TestBean {

	@Test
	public void testaAdd(){
		AgendaBean c1 = new AgendaBean();


		c1.addContato("Pedro", "558333226658", "OI", "", "");
		Assert.assertFalse(c1.hasContato("Cicero"));

		Assert.assertTrue(c1.hasContato("Pedro"));
	}

}