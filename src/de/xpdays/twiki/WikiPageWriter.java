package de.xpdays.twiki;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import de.xpdays.conftool.daten.Einreichung;

public class WikiPageWriter {

	private final File targetFolder;
	private final LinkedHashMap<String, Einreichung> index = new LinkedHashMap<String, Einreichung>();

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
				.appendHeading2("Speaker").appendSeparatedBy("; ", einreichung.getAutoren()).newParagraph()
				//
				.appendParagraph("</noautolink>")//
				.toString();

		addToIndex(einreichung, pageName);
		writeWikiPage(pageName, pageContent);
	}

	public void writeIndexPage() throws IOException {
		WikiPageBuilder builder = new WikiPageBuilder();
		builder.appendHeading1("Programm");
		builder.openTableCell().appendBold("Header").closeTableCell().newLine();
		for (Entry<String, Einreichung> entry : index.entrySet()) {
			String sessionWikiPage = entry.getKey();
			Einreichung session = entry.getValue();
			builder.openTableCell();
			builder.append(session.getTyp()).lineBreak();
			builder.appendSeparatedBy("; ", session.getAutoren()).lineBreak();
			builder.append("%TITLE%").appendLink(sessionWikiPage, session.getTitel()).append("%ELTIT%");
			builder.closeTableCell().newLine();
		}
		builder.newParagraph().appendParagraph("<!-- Variablen für Auszeichnungen bestimmter Typen");
		builder.append("   * Set TITLE = <span style='font-size: 1.3em;'>").newLine();
		builder.append("   * Set ELTIT = </span>").newLine();
		builder.appendParagraph("-->");
		writeWikiPage("ProgrammKopiervorlagen", builder.toString());
	}

	private void addToIndex(Einreichung einreichung, String pageName) {
		index.put(pageName, einreichung);
	}

	private void writeWikiPage(String name, String content) throws IOException {
		File targetFile = new File(targetFolder, name + ".txt");
		Files.write(content, targetFile, Charsets.UTF_8);
	}

	private String changeListMarkup(String confToolMarkedUpText) {
		String wikiMarkedUpText = confToolMarkedUpText.replaceAll("\n[*-] ", "\n   * ");
		if (wikiMarkedUpText.startsWith("- ") || wikiMarkedUpText.startsWith("* ")) {
			wikiMarkedUpText = wikiMarkedUpText.replaceFirst("[*-] ", "   * ");
		}
		return wikiMarkedUpText;
	}

}
