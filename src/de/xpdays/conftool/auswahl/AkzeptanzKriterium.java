package de.xpdays.conftool.auswahl;

import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungsStatus;

public interface AkzeptanzKriterium {

	EinreichungsStatus akzeptiert(SessionAuswahl auswahl, Einreichung einreichung);

	void wurdeAkzeptiert(Einreichung einreichung);

}
