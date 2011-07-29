package de.xpdays.conftool.daten;

import java.util.List;

public class AusgewaehlteEinreichung {

	private final Einreichung einreichung;
	private final EinreichungsStatus status;

	public AusgewaehlteEinreichung(Einreichung einreichung, EinreichungsStatus status) {
		this.einreichung = einreichung;
		this.status = status;
	}

	public Einreichung getEinreichung() {
		return einreichung;
	}

	public EinreichungsStatus getStatus() {
		return status;
	}

	public String getStatusText() {
		return status.toString();
	}

	public Long getId() {
		return einreichung.getId();
	}

	public String getTyp() {
		return einreichung.getTyp();
	}

	public List<String> getAutoren() {
		return einreichung.getAutoren();
	}

	public String getTitel() {
		return einreichung.getTitel();
	}

	public List<String> getOrganisationen() {
		return einreichung.getOrganisationen();
	}

	public Integer getAnzahlGutachten() {
		return einreichung.getAnzahlGutachten();
	}

	public Double getDurchschnittlicheBewertung() {
		return einreichung.getDurchschnittlicheBewertung();
	}

	public int getDauerInMinuten() {
		return einreichung.getDauerInMinuten();
	}
}
