package de.xpdays.conftool;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.filefilter.PrefixFileFilter;

import de.xpdays.conftool.mapping.CSVMapping;
import de.xpdays.conftool.model.Benutzer;
import de.xpdays.conftool.model.Einreichung;
import de.xpdays.conftool.reader.CSVReader;

public class Scramblr {

	private final File directory;

	public Scramblr(File directory) {
		this.directory = directory;
	}

	public List<Benutzer> readBenutzer() throws IOException {
		return new CSVReader<Benutzer>(findMostRecentFile("users"), Benutzer.class, CSVMapping.forBenutzer()).read();
	}

	public List<Einreichung> readEinreichungen() throws IOException {
		return new CSVReader<Einreichung>(findMostRecentFile("papers"), Einreichung.class,
				CSVMapping.forEinreichungen()).read();
	}

	protected File findMostRecentFile(String prefix) {
		String[] fileNames = directory.list(new PrefixFileFilter(prefix));
		Arrays.sort(fileNames);
		return fileNames.length > 0 ? new File(directory, fileNames[fileNames.length - 1]) : null;
	}

	public static void main(String[] args) throws Exception {
		Scramblr scramblr = new Scramblr(new File("data"));
		System.out.println(scramblr.readBenutzer());
		System.out.println(scramblr.readEinreichungen());
	}
}
