package de.xpdays.conftool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import de.xpdays.conftool.daten.AusgewaehlteEinreichung;
import de.xpdays.conftool.daten.Benutzer;
import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.HighestBewertungFirst;
import de.xpdays.conftool.mapping.CSVColumnBuilder;
import de.xpdays.conftool.mapping.CSVMapping;
import de.xpdays.conftool.reader.CSVColumn;
import de.xpdays.conftool.reader.CSVReader;

public class Repository {

	private final File directory;

	public Repository(File directory) {
		this.directory = directory;
	}

	public List<Benutzer> readBenutzer() throws IOException {
		return new CSVReader<Benutzer>(findMostRecentFile("users"), Benutzer.class, CSVMapping.forBenutzer()).read();
	}

	public List<Einreichung> readEinreichungen() throws IOException {
		List<Einreichung> einreichungen = new CSVReader<Einreichung>(findMostRecentFile("papers"), Einreichung.class,
				CSVMapping.forEinreichungen()).read();
		Collections.sort(einreichungen, new HighestBewertungFirst());
		return einreichungen;
	}

	public void writeAusgewaehlteEinreichungen(Iterable<AusgewaehlteEinreichung> einreichungen) throws IOException {
		write(einreichungen, CSVMapping.forAusgewaehlteEinreichungen(), "session.csv");
	}

	public void writeSessionTexte(Iterable<AusgewaehlteEinreichung> einreichungen) throws IOException {
		write(einreichungen, CSVMapping.forSessionTexte(), "sessionTexte.csv");
	}

	protected File findMostRecentFile(String prefix) {
		String[] fileNames = directory.list(new PrefixFileFilter(prefix));
		Arrays.sort(fileNames);
		return fileNames.length > 0 ? new File(directory, fileNames[fileNames.length - 1]) : null;
	}

	private void write(Iterable<AusgewaehlteEinreichung> einreichungen, CSVColumnBuilder mapping, String filename)
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		File file = new File(directory, filename);
		CSVColumn[] columns = mapping.toColumns();
		String[] nameMapping = CSVMapping.toBeanProperties(columns);
		CsvBeanWriter writer = new CsvBeanWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"),
				CsvPreference.EXCEL_PREFERENCE);
		try {
			writer.writeHeader(mapping.toHeaders());
			for (AusgewaehlteEinreichung einreichungMitStatus : einreichungen) {
				writer.write(einreichungMitStatus, nameMapping, columns);
			}
		} finally {
			writer.close();
		}
	}

}
