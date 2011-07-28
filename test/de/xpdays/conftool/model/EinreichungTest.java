package de.xpdays.conftool.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

}
