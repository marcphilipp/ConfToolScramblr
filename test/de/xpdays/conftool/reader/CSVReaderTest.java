package de.xpdays.conftool.reader;

import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.writeLines;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.supercsv.util.CSVContext;

public class CSVReaderTest {

	public static class DummyBean {
		private String foo, bar;

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}

		public String getBar() {
			return bar;
		}

		public void setBar(String bar) {
			this.bar = bar;
		}
	}

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void readsBeansAccordingToSpecification() throws Exception {
		File csvFile = folder.newFile("input.csv");
		writeLines(csvFile, asList("foo,baz", "123,\"4,56\"", "abcd,defghijk"));
		CSVColumn[] columns = { column("foo", "foo"), column("baz", "bar") };

		List<DummyBean> result = new CSVReader<DummyBean>(csvFile.getAbsolutePath(), DummyBean.class, columns).read();
		assertThat(result.size(), is(2));
		assertThat(result.get(0).getFoo(), is("123"));
		assertThat(result.get(0).getBar(), is("4,56"));
		assertThat(result.get(1).getFoo(), is("abcd"));
		assertThat(result.get(1).getBar(), is("defghijk"));
	}

	private CSVColumn column(final String expectedHeader, final String property) {
		return new CSVColumn() {

			@Override
			public Object execute(Object value, CSVContext context) {
				return value;
			}

			@Override
			public boolean hasHeader(String header) {
				return header.equals(expectedHeader);
			}

			@Override
			public String getBeanProperty() {
				return property;
			}

		};
	}
}
