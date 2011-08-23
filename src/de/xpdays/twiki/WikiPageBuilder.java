package de.xpdays.twiki;

import com.google.common.base.Joiner;

public class WikiPageBuilder {

	private static final String NEW_LINE = "\n";
	private static final String PARAGRAPH_SEPARATOR = NEW_LINE + NEW_LINE;
	private final StringBuilder builder = new StringBuilder();

	@Override
	public String toString() {
		return builder.toString();
	}

	public WikiPageBuilder appendHeading1(String heading) {
		return append("---+ ").append(heading).newParagraph();
	}

	public WikiPageBuilder appendHeading2(String heading) {
		return append("---++ ").append(heading).newParagraph();
	}

	public WikiPageBuilder newParagraph() {
		return append(PARAGRAPH_SEPARATOR);
	}

	public WikiPageBuilder appendParagraph(String text) {
		return append(text).newParagraph();
	}

	public WikiPageBuilder appendSeparatedBy(String separator, Iterable<?> parts) {
		Joiner.on(separator).appendTo(builder, parts);
		return this;
	}

	public WikiPageBuilder openTableCell() {
		return append("| ");
	}

	public WikiPageBuilder append(String text) {
		builder.append(text);
		return this;
	}

	public WikiPageBuilder appendLink(String target, String linkText) {
		return append("[[").append(target).append("][").append(linkText).append("]]");
	}

	public WikiPageBuilder closeTableCell() {
		return append(" |");
	}

	public WikiPageBuilder newLine() {
		return append(NEW_LINE);
	}

	public WikiPageBuilder lineBreak() {
		return append("<br />");
	}

	public WikiPageBuilder appendBold(String text) {
		return append("*").append(text).append("*");
	}

}
