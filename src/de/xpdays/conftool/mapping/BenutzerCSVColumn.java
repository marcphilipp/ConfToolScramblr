package de.xpdays.conftool.mapping;

import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CSVContext;

import de.xpdays.conftool.reader.CSVColumn;

public enum BenutzerCSVColumn implements CSVColumn {

	ID("personID", "id", new Unique(new ParseLong())),

	VORNAME("firstname", "vorname", new NotNull()),

	NACHNAME("name", "nachname", new NotNull());

	private final CellProcessor cellProcessor;
	private final String csvHeader;
	private final String beanProperty;

	private BenutzerCSVColumn(String csvHeader, String beanProperty, CellProcessor cellProcessor) {
		this.csvHeader = csvHeader;
		this.beanProperty = beanProperty;
		this.cellProcessor = cellProcessor;
	}

	@Override
	public Object execute(Object value, CSVContext context) {
		return cellProcessor.execute(value, context);
	}

	@Override
	public boolean hasHeader(String header) {
		return csvHeader.equalsIgnoreCase(header);
	}

	@Override
	public String getBeanProperty() {
		return beanProperty;
	}
}