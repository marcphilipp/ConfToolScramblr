package de.xpdays.conftool.auswahl;

import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungsStatus;

public class PasstInZeitrahmen implements AkzeptanzKriterium {

	private final int verfuegbareMinuten;
	private int akzeptierteMinuten;

	public PasstInZeitrahmen(int verfuegbareMinuten) {
		this.verfuegbareMinuten = verfuegbareMinuten;
	}

	@Override
	public EinreichungsStatus akzeptiert(SessionAuswahl auswahl, Einreichung einreichung) {
		if (akzeptierteMinuten > verfuegbareMinuten) {
			return EinreichungsStatus.abgelehnt("Zeitlimit überschritten (" + verfuegbareMinuten + " Minuten)");
		}
		return EinreichungsStatus.akzeptiert();
	}

	@Override
	public void wurdeAkzeptiert(Einreichung einreichung) {
		akzeptierteMinuten += einreichung.getDauerInMinuten();
	}

}
