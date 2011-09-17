package de.xpdays.conftool.reader;

import org.supercsv.cellprocessor.ift.CellProcessor;

public interface CSVColumn extends CellProcessor {

	boolean hasHeader(String header);

	String getBeanProperty();
}
