package de.xpdays.conftool;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import de.xpdays.conftool.mapping.CSVMapping;
import de.xpdays.conftool.model.Benutzer;
import de.xpdays.conftool.reader.CSVReader;

public class ReadBenutzerTest {

	@Test
	public void testName() throws Exception {
		List<Benutzer> result = new CSVReader<Benutzer>("testdata/users.csv", Benutzer.class, CSVMapping.forBenutzer())
				.read();
		assertThat(result.size(), is(1));
		Benutzer benutzer = result.get(0);
		assertThat(benutzer.getId(), is(1234L));
		assertThat(benutzer.getVorname(), is("Max"));
		assertThat(benutzer.getNachname(), is("Mustermann"));
	}
}
