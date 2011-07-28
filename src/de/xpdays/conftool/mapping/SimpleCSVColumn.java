package de.xpdays.conftool.mapping;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CSVContext;

import de.xpdays.conftool.reader.CSVColumn;

public class SimpleCSVColumn implements CSVColumn {

	private final String header;
	private final String beanProperty;
	private final CellProcessor cellProcessor;

	public SimpleCSVColumn(String header, String beanProperty, CellProcessor cellProcessor) {
		this.header = header;
		this.beanProperty = beanProperty;
		this.cellProcessor = cellProcessor;
	}

	@Override
	public Object execute(Object value, CSVContext context) {
		return cellProcessor.execute(value, context);
	}

	@Override
	public boolean hasHeader(String header) {
		return this.header.equalsIgnoreCase(header);
	}

	@Override
	public String getBeanProperty() {
		return beanProperty;
	}

}
