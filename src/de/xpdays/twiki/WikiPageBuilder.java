package de.xpdays.twiki;

import com.google.common.base.Joiner;

public class WikiPageBuilder {

	private static final String PARAGRAPH_SEPARATOR = "\n\n";
	private final StringBuilder builder = new StringBuilder();

	@Override
	public String toString() {
		return builder.toString();
	}

	public WikiPageBuilder appendHeading1(String heading) {
		builder.append("---+ ").append(heading).append(PARAGRAPH_SEPARATOR);
		return this;
	}

	public WikiPageBuilder appendHeading2(String heading) {
		builder.append("---++ ").append(heading).append(PARAGRAPH_SEPARATOR);
		return this;
	}

	public WikiPageBuilder appendParagraph(String text) {
		builder.append(text).append(PARAGRAPH_SEPARATOR);
		return this;
	}

	public WikiPageBuilder appendParagraphSeparatedBy(String separator, Iterable<?> parts) {
		Joiner.on(separator).appendTo(builder, parts);
		builder.append(PARAGRAPH_SEPARATOR);
		return this;
	}

}
