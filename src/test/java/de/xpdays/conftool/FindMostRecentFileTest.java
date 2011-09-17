package de.xpdays.conftool;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FindMostRecentFileTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void findsMostRecentFileByPrefix() throws Exception {
		folder.newFile("papers_2011-07-27_22-34-07.csv");
		File mostRecentFileByName = folder.newFile("papers_2011-07-27_22-34-08.csv");
		folder.newFile("papers_2011-07-27_22-34-05.csv");

		Repository repository = new Repository(folder.getRoot());
		assertThat(repository.findMostRecentFile("papers"), is(mostRecentFileByName));
	}

	@Test
	public void returnsNullWithoutAnyMatchingFiles() throws Exception {
		folder.newFile("paperS_2011-07-27_22-34-07.csv");
		folder.newFile("authors_2011-07-27_22-34-08.csv");
		folder.newFile("1papers_2011-07-27_22-34-05.csv");

		Repository repository = new Repository(folder.getRoot());
		assertThat(repository.findMostRecentFile("papers"), is(nullValue()));
	}
}
