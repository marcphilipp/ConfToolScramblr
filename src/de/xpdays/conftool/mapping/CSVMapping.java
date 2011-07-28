package de.xpdays.conftool.mapping;

import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.Unique;

import de.xpdays.conftool.reader.CSVColumn;

public class CSVMapping {

	public static CSVColumn[] forBenutzer() {
		return builder() //
				.add("personID", "id", new Unique(new ParseLong())) //
				.add("firstname", "vorname", new NotNull()) //
				.add("name", "nachname", new NotNull()).toColumns();
	}

	private static CSVColumnBuilder builder() {
		return new CSVColumnBuilder();
	}
}
