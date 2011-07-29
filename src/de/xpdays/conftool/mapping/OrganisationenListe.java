package de.xpdays.conftool.mapping;

public class OrganisationenListe extends ListeProcessor {

	public OrganisationenListe() {
		super("(?:\\d+: )?(.+?)(; |$)");
	}

	@Override
	protected String postprocess(String item) {
		return item.contains(",") ? item.substring(0, item.indexOf(",")) : item;
	}

}
