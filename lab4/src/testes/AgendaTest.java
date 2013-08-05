package testes;

import static org.junit.Assert.*;
import model.Contato;
import model.Telefone;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidNumberException;
import bean.Agenda;


public class AgendaTest {
	Agenda agenda;
	Contato contato;
	Contato contato2;
	Telefone telefone;
	Telefone telefone2;

	@Before
	public void init() {
		this.agenda = new Agenda();
		this.contato = new Contato();
		this.contato2 = new Contato();
		this.telefone = new Telefone();
		this.telefone2 = new Telefone();
	}

	@Test
	public void testaddTelefone() {
		try {
			telefone.setOperadoraLigar("82");
			telefone.setOperadora("oi");
			contato.addTelefone(telefone);
			fail("not InvalidNumberException");
		} catch (InvalidNumberException e) {
		}

		try {
			telefone.setCodigoRegional("13");
			contato.addTelefone(telefone);
			fail("not InvalidNumberException");
		} catch (InvalidNumberException e) {
		}

		try {
			telefone.setNumero("88888888");
			contato.addTelefone(telefone);
		} catch (InvalidNumberException e) {
			fail("InvalidNumberException");
		}

	}

	@Test
	public void testContatoValido() {
		assertFalse(contato.isContatoValido());
		telefone.setOperadora("oi");
		telefone.setNumero("888888888");
		try {
			contato.addTelefone(telefone);
		} catch (InvalidNumberException e) {}
		
		assertFalse(contato.isContatoValido());
//		contato.setNome("Renan");
		assertTrue(contato.isContatoValido());

	}
	
	@Test
	public void testTelefoneValido() {
		assertFalse(telefone.ehTelefoneValido());
		telefone.setNumero("8888");
		assertFalse(telefone.ehTelefoneValido());
		telefone.setNumero("88888888");
		assertTrue(telefone.ehTelefoneValido());
		telefone2.setOperadora("31");
		assertFalse(telefone2.ehTelefoneValido());
		telefone2.setCodigoRegional("44");
		assertFalse(telefone2.ehTelefoneValido());
		telefone2.setNumero("88888888");
		assertFalse(telefone2.ehTelefoneValido());
		telefone2.setOperadoraLigar("21");
		assertTrue(telefone2.ehTelefoneValido());
		
	}
	@Test
	public void testBuscaNomeTel() {
	
		agenda.getContatos().add(contato);
		agenda.setBusca("mar");
		agenda.fazBusca(null);
		assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setBusca("Rob");
		agenda.fazBusca(null);
		assertEquals(2, agenda.getResultadoBusca().size());
		agenda.setBusca("xe");
		agenda.fazBusca(null);
		assertEquals(0, agenda.getResultadoBusca().size());
		agenda.setBusca("99");
		agenda.fazBusca(null);
		assertEquals(3, agenda.getResultadoBusca().size());
	}
	@Test
	public void testBuscaIdade() {
		agenda.setTipoDeBusca("2");
		agenda.setBusca("40");
		agenda.fazBusca(null);
		assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("3");
		assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("4");
		assertEquals(1, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("2");
		agenda.setBusca("100");
		agenda.fazBusca(null);
		assertEquals(3, agenda.getResultadoBusca().size());
		agenda.setTipoDeBusca("4");
		agenda.setBusca("10");
		agenda.fazBusca(null);
		assertEquals(3, agenda.getResultadoBusca().size());
	}

}