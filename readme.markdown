# ConfToolScramblr

This little tool emerged while planning sessions for the [XP Days Germany 2011](http://www.xpdays.de/). Authors would submit their session proposols to [ConfTool](http://www.conftool.net/). Other users gave them feedback by reviewing and rating their sessions.

There are currently two use cases. First, automatic pre-selection of sessions from the proposals according to a set of criteria, e.g. highest rating. Second, generating wiki pages with title, abstract, and other information for the sessions that have been selected by the organizers in the end.

## Session Selection

The `Scramblr` class is the entry point for the semi-automated session selection process:

1. Read papers (`Einreichung`) from an CSV file exported by ConfTool.
2. Sort the papers by average score, highest first (`HighestBewertungFirst`).
3. `SessionAuswahl`: Iterate over the papers from top to bottom and check the acceptance criteria (`Akzeptanzkriterium`).
4. A paper is accepted when all of the acceptance criteria are satisfied (`AusgewaehlteEinreichung`).
5. Write an CSV file with all sessions including their status (`EinreichungsStatus`) to `session.csv`.

### Acceptance Criteria

At the moment there are the following acceptance criteria:

- `MaximaleSessionsProAutor(limit)`: Checks that every single author has at most `limit` sessions accepted.
- `MaximaleSessionsProOrganisation(limit)`: Checks that every single organisation has at most `limit` sessions accepted.
- `PasstInZeitrahmen(minutes)`: Checks that the sum of the durations of all accepted sessions does not exceed `minutes`.

The acceptance criteria are passed to the `SessionAuswahl` class in the `main` method of the `Scramblr` class.


## Wiki Page Generation

For the conference program, you might want to generate a webpage for every accepted session.
We are using TWiki and therefore need to generate wiki pages in TWiki syntax.
This is accomplished by the `WikiPageGenerator` class which roughly works as follows:

1. Read the accepted sessions from `accepted_papers.csv' (Since the selection process is not fully automatic).
2. Read all sessions from latest `papers*.csv`.
3. Write a separate wiki page for all accepted sessions into the `wiki` folder.
4. Write an prototype index page linking to all the separate pages into the `wiki` folder.

### Uploading to TWiki

The session wiki pages can simply be copied into the `data` directory of the TWiki web you would like to add them to.
The index page is merely a place where you can copy table cells from and paste them on the program page which you will
have to create by hand. 
