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

		String pageContent = new WikiPageBuilder()//
				.appendParagraph("<noautolink>")
				//
				.appendHeading1(einreichung.getTitel())//
				.appendParagraph(einreichung.getTyp())
				//
				.appendHeading2("Abstract")//
				.appendParagraph(changeListMarkup(einreichung.getZusammenfassung()))
				//
				.appendHeading2("Speaker")//
				.appendParagraphSeparatedBy("; ", einreichung.getAutoren())//
				//
				.appendParagraph("</noautolink>")//
				.toString();

		File targetFile = new File(targetFolder, pageName + ".txt");
		Files.write(pageContent, targetFile, Charsets.UTF_8);
	}

	private String changeListMarkup(String confToolMarkedUpText) {
		String wikiMarkedUpText = confToolMarkedUpText.replaceAll("\n[*-] ", "\n   * ");
		if (wikiMarkedUpText.startsWith("- ") || wikiMarkedUpText.startsWith("* ")) {
			wikiMarkedUpText = wikiMarkedUpText.replaceFirst("[*-] ", "   * ");
		}
		return wikiMarkedUpText;
	}

}
