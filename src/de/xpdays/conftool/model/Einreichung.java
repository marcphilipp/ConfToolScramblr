package de.xpdays.conftool.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Einreichung {

	private Long id;
	private String typ, autoren, titel;
	private Integer anzahlGutachten;
	private Double durchschnittlicheBewertung;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getAutoren() {
		return autoren;
	}

	public void setAutoren(String authoren) {
		this.autoren = authoren;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Integer getAnzahlGutachten() {
		return anzahlGutachten;
	}

	public void setAnzahlGutachten(Integer anzahlGutachten) {
		this.anzahlGutachten = anzahlGutachten;
	}

	public Double getDurchschnittlicheBewertung() {
		return durchschnittlicheBewertung;
	}

	public void setDurchschnittlicheBewertung(Double durchschnittlicheBewertung) {
		this.durchschnittlicheBewertung = durchschnittlicheBewertung;
	}

	public int getDauerInMinuten() {
		Pattern pattern = Pattern.compile(".*?(\\d+)'.*");
		Matcher matcher = pattern.matcher(typ);
		if (matcher.matches()) {
			return Integer.parseInt(matcher.group(1));
		}
		throw new IllegalStateException();
	}
}
