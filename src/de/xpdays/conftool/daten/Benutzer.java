package de.xpdays.conftool.daten;

public class Benutzer {

	private Long id;
	private String vorname, nachname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Benutzer && ((Benutzer) obj).id.equals(id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return id + " - " + vorname + " " + nachname;
	}
}
