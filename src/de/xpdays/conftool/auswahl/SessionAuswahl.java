package de.xpdays.conftool.auswahl;

import static de.xpdays.conftool.daten.EinreichungsStatus.akzeptiert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.xpdays.conftool.daten.AusgewaehlteEinreichung;
import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungsStatus;

public class SessionAuswahl implements Iterable<AusgewaehlteEinreichung> {

	private final List<AusgewaehlteEinreichung> einreichungen = new LinkedList<AusgewaehlteEinreichung>();
	private final AkzeptanzKriterium[] kriterien;

	public SessionAuswahl(AkzeptanzKriterium... kriterien) {
		this.kriterien = kriterien;
	}

	public void bestimmeSessions(List<Einreichung> einreichungen) {
		for (Einreichung einreichung : einreichungen) {
			EinreichungsStatus status = bestimmeStatus(einreichung);
			if (status.isAkzeptiert()) {
				akzeptiere(einreichung);
			} else {
				lehneAb(einreichung, status);
			}
		}
	}

	private EinreichungsStatus bestimmeStatus(Einreichung einreichung) {
		EinreichungsStatus status = EinreichungsStatus.akzeptiert();
		for (AkzeptanzKriterium kriterium : kriterien) {
			status = kriterium.akzeptiert(this, einreichung);
			if (!status.isAkzeptiert()) {
				break;
			}
		}
		return status;
	}

	private void akzeptiere(Einreichung einreichung) {
		einreichungen.add(new AusgewaehlteEinreichung(einreichung, akzeptiert()));
		for (AkzeptanzKriterium kriterium : kriterien) {
			kriterium.wurdeAkzeptiert(einreichung);
		}
	}

	private void lehneAb(Einreichung einreichung, EinreichungsStatus status) {
		einreichungen.add(new AusgewaehlteEinreichung(einreichung, status));
	}

	@Override
	public Iterator<AusgewaehlteEinreichung> iterator() {
		return einreichungen.iterator();
	}
}
