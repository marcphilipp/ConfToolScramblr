package de.xpdays.conftool.mapping;

import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;

import de.xpdays.conftool.reader.CSVColumn;

public class CSVColumnBuilder {

	private final List<SimpleCSVColumn> columns = new ArrayList<SimpleCSVColumn>();
	private final List<String> headers = new ArrayList<String>();

	public CSVColumnBuilder add(String header, String propertyName, CellProcessor cellProcessor) {
		headers.add(header);
		columns.add(new SimpleCSVColumn(header, propertyName, cellProcessor));
		return this;
	}

	public CSVColumn[] toColumns() {
		return columns.toArray(new CSVColumn[columns.size()]);
	}

	public CellProcessor[] toProcessors() {
		return columns.toArray(new CellProcessor[columns.size()]);
	}

	public String[] toHeaders() {
		return headers.toArray(new String[headers.size()]);
	}
}
