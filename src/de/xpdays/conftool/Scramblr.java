package de.xpdays.conftool;

import java.io.File;
import java.io.IOException;

import de.xpdays.conftool.auswahl.MaximaleSessionsProAutor;
import de.xpdays.conftool.auswahl.MaximaleSessionsProOrganisation;
import de.xpdays.conftool.auswahl.PasstInZeitrahmen;
import de.xpdays.conftool.auswahl.SessionAuswahl;

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

	public static void main(String[] args) throws Exception {
		Repository repository = new Repository(new File("data"));

		SessionAuswahl sessionAuswahl = new SessionAuswahl(new PasstInZeitrahmen(60 * 4 * 8),
				new MaximaleSessionsProAutor(1), new MaximaleSessionsProOrganisation(8));

		Scramblr scramblr = new Scramblr(repository, sessionAuswahl);
		scramblr.bestimmeSessions();
	}
}
