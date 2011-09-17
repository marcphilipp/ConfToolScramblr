package de.xpdays.conftool.auswahl;

import static de.xpdays.conftool.daten.EinreichungsStatus.abgelehnt;

import java.util.Map;
import java.util.TreeMap;

import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungsStatus;

public class MaximaleSessionsProOrganisation implements AkzeptanzKriterium {

	private final Map<String, Integer> organisationToAnzahl = new TreeMap<String, Integer>();
	private final int limit;

	public MaximaleSessionsProOrganisation(int limit) {
		this.limit = limit;
	}

	@Override
	public EinreichungsStatus akzeptiert(SessionAuswahl auswahl, Einreichung einreichung) {
		for (String organisation : einreichung.getOrganisationen()) {
			if (bisherigeAnzahl(organisation) < limit) {
				return EinreichungsStatus.akzeptiert();
			}
		}
		return abgelehnt("Maximale Anzahl von Sessions pro Organisation überschritten "
				+ einreichung.getOrganisationen());
	}

	@Override
	public void wurdeAkzeptiert(Einreichung einreichung) {
		for (String organisation : einreichung.getOrganisationen()) {
			incrementAnzahl(organisation);
		}
	}

	private void incrementAnzahl(String organisation) {
		organisationToAnzahl.put(organisation, bisherigeAnzahl(organisation) + 1);
	}

	private int bisherigeAnzahl(String organisation) {
		if (organisationToAnzahl.containsKey(organisation)) {
			return organisationToAnzahl.get(organisation);
		}
		return 0;
	}

}
