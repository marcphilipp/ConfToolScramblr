package de.xpdays.conftool.mapping;

import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;

import de.xpdays.conftool.reader.CSVColumn;

public class CSVColumnBuilder {

	private final List<CSVColumn> columns = new ArrayList<CSVColumn>();

	public CSVColumnBuilder add(String header, String propertyName, CellProcessor cellProcessor) {
		columns.add(new SimpleCSVColumn(header, propertyName, cellProcessor));
		return this;
	}

	public CSVColumn[] toColumns() {
		return columns.toArray(new CSVColumn[columns.size()]);
	}
}
