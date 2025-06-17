import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.*/
public class TestOneWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    // ngrams files
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";

    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    @Test
    public void testActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testChangeK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, demotion, increase, jump, leap, modification, saltation, transition, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testOneHyponym() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("demotion");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[demotion]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNoHyponyms() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("WOW");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testOneWordMultipleQuery() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("tire");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[auto_tire, automobile_tire, car_tire, flat, flat_tire, pneumatic_tire, pneumatic_tyre, radial, radial-ply_tire, radial_tire, recap, retread, rubber_tire, snow_tire, tire, tubeless, tubeless_tire, tyre, wagon_tire]";
        assertThat(actual).isEqualTo(expected);

        List<String> words2 = new ArrayList<>();
        words2.add("wow");

        NgordnetQuery nq2 = new NgordnetQuery(words2, 0, 0, 0);
        String actual2 = studentHandler.handle(nq2);
        String expected2 = "[belly_laugh, howler, riot, scream, sidesplitter, thigh-slapper, wow]";
        assertThat(actual2).isEqualTo(expected2);
    }
}
