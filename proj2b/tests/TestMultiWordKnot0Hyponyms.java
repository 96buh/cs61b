import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestMultiWordKnot0Hyponyms {
    // ngrams files
    public static final String VERY_SHORT_WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String SMALL_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";

    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";

    // EECS files
    private static final String FREQUENCY_EECS_FILE = "data/ngrams/frequency-EECS.csv";
    private static final String HYPONYMS_EECS_FILE = "data/wordnet/hyponyms-EECS.txt";
    private static final String SYNSETS_EECS_FILE = "data/wordnet/synsets-EECS.txt";

    @Test
    public void testCakeFoodK5() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("cake");
        words.add("food");

        NgordnetQuery nq = new NgordnetQuery(words, 1950, 1990, 5);
        String actual = studentHandler.handle(nq);
        String expected = "[cake, cookie, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultiWordsMultiQueries() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                FREQUENCY_EECS_FILE, TOTAL_COUNTS_FILE, SYNSETS_EECS_FILE, HYPONYMS_EECS_FILE);
        List<String> words = new ArrayList<>();
        words.add("CS61B");
        words.add("CS170");

        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 5);
        String actual = studentHandler.handle(nq);
        String expected = "[CS170, CS172, CS174, CS176, CS191]";
        assertThat(actual).isEqualTo(expected);

        List<String> words2 = new ArrayList<>();
        words2.add("EECS16A");
        words2.add("EE140");

        NgordnetQuery nq2 = new NgordnetQuery(words2, 2010, 2020, 5);
        String actual2 = studentHandler.handle(nq2);
        String expected2 = "[EE140, EE142]";
        assertThat(actual2).isEqualTo(expected2);
    }
}
