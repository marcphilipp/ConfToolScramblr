package de.xpdays.conftool.mapping;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AutorenListeTest {

	@Test
	public void processesSingleAutor() {
		ListeProcessor processor = new AutorenListe();
		assertThat((String[]) processor.execute("Mustermann, Max", null), is(new String[] { "Mustermann, Max" }));
	}

	@Test
	public void processesMultipleOrgas() throws Exception {
		ListeProcessor processor = new AutorenListe();
		assertThat((String[]) processor.execute("Doe, John (1); Smith, Jane (2)", null), is(new String[] { "Doe, John",
				"Smith, Jane" }));
	}

}
