package de.xpdays.conftool.mapping;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrgaListeTest {

	@Test
	public void processesSingleOrga() {
		OrganisationenListe processor = new OrganisationenListe();
		assertThat((String[]) processor.execute("Musterfirma", null), is(new String[] { "Musterfirma" }));
	}

	@Test
	public void removesCountrySuffix() {
		OrganisationenListe processor = new OrganisationenListe();
		assertThat((String[]) processor.execute("Musterfirma, Deutschland", null), is(new String[] { "Musterfirma" }));
	}

	@Test
	public void processesMultipleOrgas() throws Exception {
		OrganisationenListe processor = new OrganisationenListe();
		assertThat((String[]) processor.execute("1: Musterfirma, Deutschland; 2: Firma 2", null), is(new String[] {
				"Musterfirma", "Firma 2" }));
	}

}
