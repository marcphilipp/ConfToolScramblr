package de.xpdays.conftool.daten;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import org.junit.Test;

public class EinreichungTest {

	@Test
	public void dauerIsParsedFromTyp() {
		Einreichung einreichung = new Einreichung();

		einreichung.setTyp("Presentation 30'");
		assertThat(einreichung.getDauerInMinuten(), is(30));

		einreichung.setTyp("Interaktive Session/Sonstiges 42'");
		assertThat(einreichung.getDauerInMinuten(), is(42));
	}

	@Test(expected = IllegalStateException.class)
	public void throwsExceptionWhenTypDoesNotContainDauerInMinuten() throws Exception {
		Einreichung einreichung = new Einreichung();
		einreichung.setTyp("Murks");
		einreichung.getDauerInMinuten();
	}

	@Test
	public void autorenVornameVorNachname() throws Exception {
		Einreichung einreichung = new Einreichung();
		einreichung.setAutoren(new String[] { "Marley, Bob", "Doe", "Mustermann, ", "Lust, Keine, von" });
		assertThat(einreichung.getAutorenVornameNachname(),
				hasItems("Bob Marley", "Doe", "Mustermann", "Keine, von Lust"));
	}

}
