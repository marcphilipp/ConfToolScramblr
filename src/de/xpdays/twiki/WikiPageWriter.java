package de.xpdays.twiki;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import de.xpdays.conftool.daten.Einreichung;

public class WikiPageWriter {

	private final File targetFolder;

	public WikiPageWriter(File targetFolder) {
		this.targetFolder = targetFolder;
	}

	public void writeWikiPage(Einreichung einreichung) throws IOException {
		String pageName = new WikiWordConverter().toWikiWord(einreichung.getTitel());

		String pageContent = "---+ " + einreichung.getTitel();

		File targetFile = new File(targetFolder, pageName + ".txt");
		Files.write(pageContent, targetFile, Charsets.UTF_8);
	}

}
