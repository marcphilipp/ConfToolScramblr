package de.xpdays.conftool.auswahl;

import static de.xpdays.conftool.daten.EinreichungsStatus.abgelehnt;

import java.util.Map;
import java.util.TreeMap;

import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungsStatus;

public class MaximaleSessionsProAutor implements AkzeptanzKriterium {

	private final Map<String, Integer> autorToAnzahl = new TreeMap<String, Integer>();
	private final int limit;

	public MaximaleSessionsProAutor(int limit) {
		this.limit = limit;
	}

	@Override
	public EinreichungsStatus akzeptiert(SessionAuswahl auswahl, Einreichung einreichung) {
		for (String autor : einreichung.getAutoren()) {
			if (bisherigeAnzahl(autor) < limit) {
				return EinreichungsStatus.akzeptiert();
			}
		}
		return abgelehnt("Maximale Anzahl von Sessions pro Autor überschritten " + einreichung.getAutoren());
	}

	@Override
	public void wurdeAkzeptiert(Einreichung einreichung) {
		for (String autor : einreichung.getAutoren()) {
			incrementAnzahl(autor);
		}
	}

	private void incrementAnzahl(String autor) {
		autorToAnzahl.put(autor, bisherigeAnzahl(autor) + 1);
	}

	private int bisherigeAnzahl(String autor) {
		if (autorToAnzahl.containsKey(autor)) {
			return autorToAnzahl.get(autor);
		}
		return 0;
	}

}
