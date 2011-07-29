package de.xpdays.conftool.mapping;

public class AutorenListe extends ListeProcessor {

	public AutorenListe() {
		super("(.+?)(\\s+\\(\\d+\\))?(; |$)");
	}

	@Override
	protected String postprocess(String item) {
		return item;
	}

}
