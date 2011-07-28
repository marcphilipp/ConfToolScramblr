package de.xpdays.conftool.mapping;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.Unique;

import de.xpdays.conftool.reader.CSVColumn;

public class CSVMapping {

	public static CSVColumn[] forBenutzer() {
		return builder() //
				.add("personID", "id", id()) //
				.add("firstname", "vorname", new NotNull()) //
				.add("name", "nachname", new NotNull())//
				.toColumns();
	}

	private static Unique id() {
		return new Unique(new ParseLong());
	}

	public static CSVColumn[] forEinreichungen() {
		return builder()//
				.add("paperID", "id", id())//
				.add("contribtion_type", "typ", new NotNull())//
				.add("authors", "autoren", new NotNull())//
				.add("title", "titel", new NotNull())//
				.add("reviews_received", "anzahlGutachten", new NotNull(new ParseInt()))//
				.add("score_average", "durchschnittlicheBewertung", new NotNull(new ParseDouble()))//
				.toColumns();
	}

	private static CSVColumnBuilder builder() {
		return new CSVColumnBuilder();
	}
}
