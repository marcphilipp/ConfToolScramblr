package de.xpdays.conftool.mapping;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.Token;
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

	public static CSVColumn[] forEinreichungen() {
		return builder() //
				.add("paperID", "id", id()) //
				.add("contribtion_type", "typ", new NotNull()) //
				.add("authors", "autoren", new AutorenListe()) //
				.add("title", "titel", new NotNull()) //
				.add("reviews_received", "anzahlGutachten", new Optional(new ParseInt())) //
				.add("score_average", "durchschnittlicheBewertung", new Token("", Double.NaN, new ParseDouble())) //
				.add("organisations", "organisationen", new OrganisationenListe()).toColumns();
	}

	public static CSVColumnBuilder forAusgewaehlteEinreichungen() {
		return builder() //
				.add("Bewertung", "durchschnittlicheBewertung", new Token("", Double.NaN, new ParseDouble())) //
				.add("Anzahl Gutachten", "anzahlGutachten", new Optional(new ParseInt())) //
				.add("Status", "statusText", new NotNull()) //
				.add("ID", "id", id()) //
				.add("Sessiontyp", "typ", new NotNull()) //
				.add("Autoren", "autoren", new AutorenListe()) //
				.add("Titel", "titel", new NotNull()) //
				.add("Organisationen", "organisationen", new OrganisationenListe());
	}

	private static Unique id() {
		return new Unique(new ParseLong());
	}

	private static CSVColumnBuilder builder() {
		return new CSVColumnBuilder();
	}

	public static String[] toBeanProperties(CSVColumn[] columns) {
		String[] nameMapping = new String[columns.length];
		int index = 0;
		for (CSVColumn csvColumn : columns) {
			nameMapping[index++] = csvColumn.getBeanProperty();
		}
		return nameMapping;
	}
}
