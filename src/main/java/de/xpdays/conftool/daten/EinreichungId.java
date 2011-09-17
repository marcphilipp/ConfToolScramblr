package de.xpdays.conftool.daten;

public class EinreichungId {

	private Long value;

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object that) {
		return that instanceof EinreichungId && ((EinreichungId) that).value.equals(this.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
