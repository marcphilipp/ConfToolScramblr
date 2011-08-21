package de.xpdays.conftool.daten;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Einreichung {

	private Long id;
	private String typ, titel, zusammenfassung;
	private List<String> autoren, organisationen;
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

	public List<String> getAutoren() {
		return autoren;
	}

	public void setAutoren(String[] authoren) {
		this.autoren = Arrays.asList(authoren);
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getZusammenfassung() {
		return zusammenfassung;
	}

	public void setZusammenfassung(String zusammenfassung) {
		this.zusammenfassung = zusammenfassung;
	}

	public List<String> getOrganisationen() {
		return organisationen;
	}

	public void setOrganisationen(String[] organisationen) {
		this.organisationen = Arrays.asList(organisationen);
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
