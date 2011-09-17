package de.xpdays.conftool;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.io.File;
import java.util.List;

import org.junit.Test;

import de.xpdays.conftool.daten.Benutzer;
import de.xpdays.conftool.daten.Einreichung;

public class RepositoryTest {

	Repository repository = new Repository(new File("src/test/resources"));

	@Test
	public void readBenutzer() throws Exception {
		List<Benutzer> result = repository.readBenutzer();
		assertThat(result.size(), is(1));
		Benutzer benutzer = result.get(0);
		assertThat(benutzer.getId(), is(1234L));
		assertThat(benutzer.getVorname(), is("Max"));
		assertThat(benutzer.getNachname(), is("Mustermann"));
	}

	@Test
	public void readEinreichungen() throws Exception {
		List<Einreichung> result = repository.readEinreichungen();
		assertThat(result.size(), is(1));
		Einreichung einreichung = result.get(0);
		assertThat(einreichung.getId(), is(123L));
		assertThat(einreichung.getTyp(), is("Presentation 30'"));
		assertThat(einreichung.getAutoren(), hasItem("Einreicher, Erik"));
		assertThat(einreichung.getOrganisationen(), hasItem("Einreicherfirma"));
		assertThat(einreichung.getTitel(), is("Einreichungstitel"));
		assertThat(einreichung.getAnzahlGutachten(), is(2));
		assertThat(einreichung.getDurchschnittlicheBewertung(), is(42.23));
	}
}
