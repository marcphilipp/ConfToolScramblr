package de.xpdays.conftool.reader;

import static org.supercsv.prefs.CsvPreference.EXCEL_PREFERENCE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.input.BOMInputStream;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;

public class CSVReader<T> {

	private final String path;
	private final Class<T> beanClass;
	private final CSVColumn[] columns;

	private List<String> allHeaders;
	private ArrayList<CellProcessor> allProcessors;

	public CSVReader(String path, Class<T> beanClass, CSVColumn... columns) {
		this.path = path;
		this.beanClass = beanClass;
		this.columns = columns;
	}

	public List<T> read() throws IOException {
		Reader in = new InputStreamReader(new BOMInputStream(new FileInputStream(path)), "UTF-8");
		CsvBeanReader reader = new CsvBeanReader(in, EXCEL_PREFERENCE);
		try {
			readHeaders(reader);
			return readBeans(reader);
		} finally {
			reader.close();
		}
	}

	private void readHeaders(CsvBeanReader inFile) throws IOException {
		String[] originalHeaders = inFile.getCSVHeader(true);
		allProcessors = new ArrayList<CellProcessor>(originalHeaders.length);
		allHeaders = new ArrayList<String>(originalHeaders.length);

		for (String header : originalHeaders) {
			CSVColumn matchingColumn = findColumnWithHeader(header);
			addColumn(header, matchingColumn);
		}
	}

	private void addColumn(String header, CSVColumn matchingColumn) {
		if (matchingColumn != null) {
			allProcessors.add(matchingColumn);
			allHeaders.add(matchingColumn.getBeanProperty());
		} else {
			allProcessors.add(null);
			allHeaders.add(null);
		}
	}

	private CSVColumn findColumnWithHeader(String header) {
		CSVColumn matchingColumn = null;
		for (CSVColumn column : columns) {
			if (column.hasHeader(header)) {
				matchingColumn = column;
				break;
			}
		}
		return matchingColumn;
	}

	private List<T> readBeans(CsvBeanReader reader) throws IOException {
		String[] header = allHeaders.toArray(new String[allHeaders.size()]);
		CellProcessor[] processors = allProcessors.toArray(new CellProcessor[allProcessors.size()]);

		List<T> result = new LinkedList<T>();
		T bean;
		while ((bean = reader.read(beanClass, header, processors)) != null) {
			result.add(bean);
		}
		return result;
	}
}
