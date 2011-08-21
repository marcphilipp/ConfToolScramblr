package de.xpdays.conftool;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungId;
import de.xpdays.twiki.WikiPageWriter;

public class WikiPageGenerator {

	private final Repository repository;

	public WikiPageGenerator(Repository repository) {
		this.repository = repository;
	}

	public void schreibeWikiSeiten(File targetFolder) throws IOException {
		List<EinreichungId> akzeptierteIds = repository.readAkzeptierteSessions();
		List<Einreichung> alleEinreichungen = repository.readEinreichungen();
		targetFolder.mkdirs();
		WikiPageWriter writer = new WikiPageWriter(targetFolder);
		for (EinreichungId id : akzeptierteIds) {
			Einreichung einreichung = findeEinreichungMitId(id, alleEinreichungen);
			writer.writeWikiPage(einreichung);
		}
	}

	private Einreichung findeEinreichungMitId(EinreichungId id, List<Einreichung> alleEinreichungen) {
		for (Einreichung einreichung : alleEinreichungen) {
			if (einreichung.getId().equals(id.getValue())) {
				return einreichung;
			}
		}
		throw new IllegalArgumentException("Keine Einreichung mit Id " + id + " gefunden");
	}

	public static void main(String[] args) throws Exception {
		Repository repository = new Repository(new File("data"));
		WikiPageGenerator generator = new WikiPageGenerator(repository);
		generator.schreibeWikiSeiten(new File("wiki"));
	}

}
