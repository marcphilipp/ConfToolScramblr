package de.xpdays.twiki;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WikiWordConverterTest {

	WikiWordConverter converter = new WikiWordConverter();

	@Test
	public void sonderzeichenWerdenErsetzt() throws Exception {
		assertThat(converter.toWikiWord("Ää"), is("Aeae"));
		assertThat(converter.toWikiWord("Öö"), is("Oeoe"));
		assertThat(converter.toWikiWord("Üü"), is("Ueue"));
		assertThat(converter.toWikiWord("Áá"), is("Aa"));
		assertThat(converter.toWikiWord("Àà"), is("Aa"));
		assertThat(converter.toWikiWord("Ââ"), is("Aa"));
		assertThat(converter.toWikiWord("Éé"), is("Ee"));
		assertThat(converter.toWikiWord("Èè"), is("Ee"));
		assertThat(converter.toWikiWord("Êê"), is("Ee"));
		assertThat(converter.toWikiWord("Óó"), is("Oo"));
		assertThat(converter.toWikiWord("Òò"), is("Oo"));
		assertThat(converter.toWikiWord("Ôô"), is("Oo"));
		assertThat(converter.toWikiWord("Íí"), is("Ii"));
		assertThat(converter.toWikiWord("Ìì"), is("Ii"));
		assertThat(converter.toWikiWord("Îî"), is("Ii"));
		assertThat(converter.toWikiWord("Úú"), is("Uu"));
		assertThat(converter.toWikiWord("Ùù"), is("Uu"));
		assertThat(converter.toWikiWord("Ûû"), is("Uu"));
		assertThat(converter.toWikiWord("Ññ"), is("Nn"));
		assertThat(converter.toWikiWord("Gesä'ß_"), is("Gesaess"));
	}

	@Test
	public void removesNonLetters() {
		assertThat(converter.toWikiWord("_Wo.Rd_!!!"), is("WoRd"));
	}

	@Test
	public void removesWhiteSpace() {
		assertThat(converter.toWikiWord("  Hallo\tWelt\n\nHello   "), is("HalloWeltHello"));
	}

	@Test
	public void capitalizesWords() {
		assertThat(converter.toWikiWord("  hallo\twelt\n\nhello   "), is("HalloWeltHello"));
		assertThat(converter.toWikiWord("  hallo\t2welt\n\n3hello   "), is("Hallo2Welt3Hello"));
		assertThat(converter.toWikiWord("aa 1zz"), is("Aa1Zz"));
	}

	@Test
	public void decapitalizesWordsInAllCaps() {
		assertThat(converter.toWikiWord("HALLO"), is("Hallo"));
	}

	@Test
	public void removesLeadingNumbers() {
		assertThat(converter.toWikiWord("123Test"), is("Test"));
	}

}
