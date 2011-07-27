package de.xpdays.conftool;

import java.util.List;

import de.xpdays.conftool.mapping.BenutzerCSVColumn;
import de.xpdays.conftool.model.Benutzer;
import de.xpdays.conftool.reader.CSVReader;

public class Scramblr {

	public static void main(String[] args) throws Exception {
		List<Benutzer> benutzer = new CSVReader<Benutzer>("data/users_2011-07-27_21-07-15.csv", Benutzer.class,
				BenutzerCSVColumn.values()).read();
		System.out.println(benutzer);
	}
}
