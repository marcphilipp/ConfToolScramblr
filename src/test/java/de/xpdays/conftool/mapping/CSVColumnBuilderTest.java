package de.xpdays.conftool.mapping;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CSVContext;

import de.xpdays.conftool.reader.CSVColumn;

public class CSVColumnBuilderTest {

	@Test
	public void addSingleColumn() {
		CSVColumn[] columns = new CSVColumnBuilder().add("header", "property", processorWithResult(42)).toColumns();
		assertThat(columns.length, is(1));
		assertThat(columns[0].hasHeader("header"), is(true));
		assertThat(columns[0].getBeanProperty(), is("property"));
		assertThat(columns[0].execute("input", null), is((Object) 42));
	}

	private CellProcessor processorWithResult(final Object outcome) {
		return new CellProcessor() {
			@Override
			public Object execute(Object value, CSVContext context) {
				return outcome;
			}
		};
	}

}
