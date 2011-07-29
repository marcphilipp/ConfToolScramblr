package de.xpdays.conftool.daten;

public class EinreichungsStatus {

	private final String ablehnungsgrund;

	public static EinreichungsStatus akzeptiert() {
		return new EinreichungsStatus(null);
	}

	public static EinreichungsStatus abgelehnt(String grund) {
		return new EinreichungsStatus(grund);
	}

	private EinreichungsStatus(String grund) {
		ablehnungsgrund = grund;
	}

	public boolean isAkzeptiert() {
		return ablehnungsgrund == null;
	}

	@Override
	public String toString() {
		return isAkzeptiert() ? "Akzeptiert" : "Abgelehnt: " + ablehnungsgrund;
	}
}
