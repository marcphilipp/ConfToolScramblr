package de.xpdays.conftool;

import java.io.IOException;
import java.util.List;

import de.xpdays.conftool.mapping.CSVMapping;
import de.xpdays.conftool.model.Benutzer;
import de.xpdays.conftool.reader.CSVReader;

public class Scramblr {

	public static void main(String[] args) throws Exception {
		System.out.println(readBenutzer("data/users_2011-07-27_21-07-15.csv"));
	}

	private static List<Benutzer> readBenutzer(String path) throws IOException {
		return new CSVReader<Benutzer>(path, Benutzer.class, CSVMapping.forBenutzer()).read();
	}
}
