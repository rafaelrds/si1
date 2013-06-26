package test;

import static org.junit.Assert.*;

import org.junit.Test;

import agenda.Numero;

public class NumeroTest {

	@Test
	public void test() {
		Numero numero = new Numero("6599254443", "TIM");
		assertEquals("6599254443", numero.getNumero());
		assertEquals("TIM", numero.getNumOperadora());
	}

	
	
}
