package de.xpdays.conftool.daten;

import java.util.Comparator;

public final class HighestBewertungFirst implements Comparator<Einreichung> {

	@Override
	public int compare(Einreichung first, Einreichung second) {
		return second.getDurchschnittlicheBewertung().compareTo(first.getDurchschnittlicheBewertung());
	}
}