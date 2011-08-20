package de.xpdays.conftool;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.xpdays.conftool.auswahl.MaximaleSessionsProAutor;
import de.xpdays.conftool.auswahl.MaximaleSessionsProOrganisation;
import de.xpdays.conftool.auswahl.PasstInZeitrahmen;
import de.xpdays.conftool.auswahl.SessionAuswahl;
import de.xpdays.conftool.daten.Einreichung;
import de.xpdays.conftool.daten.EinreichungId;
import de.xpdays.twiki.WikiPageWriter;

public class Scramblr {

	private final Repository repository;
	private final SessionAuswahl sessionAuswahl;

	public Scramblr(Repository repository, SessionAuswahl sessionAuswahl) {
		this.repository = repository;
		this.sessionAuswahl = sessionAuswahl;
	}

	public void bestimmeSessions() throws IOException {
		sessionAuswahl.bestimmeSessions(repository.readEinreichungen());
		repository.writeAusgewaehlteEinreichungen(sessionAuswahl);
		repository.writeSessionTexte(sessionAuswahl);
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

		SessionAuswahl sessionAuswahl = new SessionAuswahl(new PasstInZeitrahmen(60 * 4 * 8),
				new MaximaleSessionsProAutor(1), new MaximaleSessionsProOrganisation(8));

		Scramblr scramblr = new Scramblr(repository, sessionAuswahl);
		scramblr.bestimmeSessions();
		scramblr.schreibeWikiSeiten(new File("wiki"));
	}
}
