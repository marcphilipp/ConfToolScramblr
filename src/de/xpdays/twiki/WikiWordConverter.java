package de.xpdays.twiki;

import static java.lang.Character.toUpperCase;

import java.text.Normalizer;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;

public class WikiWordConverter {

	private static final ImmutableMap<String, String> SONDERZEICHEN = new ImmutableMap.Builder<String, String>()
			.put("Ä", "Ae").put("ä", "ae")//
			.put("Ö", "Oe").put("ö", "oe")//
			.put("Ü", "Ue").put("ü", "ue")//
			.put("ß", "ss")//
			.put("_", "")//
			.put("'", "")//
			.build();

	private static final Splitter WORD_SPLITTER = Splitter.on(Pattern.compile("\\W")).trimResults().omitEmptyStrings();

	public String toWikiWord(String text) {
		String textOhneSonderzeichenUndAkzente = removeAkzente(removeSonderzeichen(text));
		String wikiWord = splitAndCapitalizeWords(textOhneSonderzeichenUndAkzente);
		return removeDigitPrefix(wikiWord);
	}

	private String removeAkzente(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	private String splitAndCapitalizeWords(String textOhneSonderzeichen) {
		String wikiWord = "";
		for (String word : WORD_SPLITTER.split(textOhneSonderzeichen)) {
			wikiWord += capitalize(word);
		}
		return wikiWord;
	}

	private String removeSonderzeichen(String text) {
		String textOhneSonderzeichen = text;
		for (Entry<String, String> entry : SONDERZEICHEN.entrySet()) {
			textOhneSonderzeichen = textOhneSonderzeichen.replace(entry.getKey(), entry.getValue());
		}
		return textOhneSonderzeichen;
	}

	private String removeDigitPrefix(String wikiWord) {
		Matcher matcher = Pattern.compile("^\\d+(.*)$").matcher(wikiWord);
		if (matcher.matches()) {
			wikiWord = matcher.group(1);
		}
		return wikiWord;
	}

	private String capitalize(String word) {
		for (int index = 0; index < word.length(); index++) {
			if (Character.isLetter(word.charAt(index))) {
				String prefix = word.substring(0, Math.max(0, index));
				String suffix = word.substring(index + 1).toLowerCase();
				return prefix + toUpperCase(word.charAt(index)) + suffix;
			}
		}
		return word;
	}
}
