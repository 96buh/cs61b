import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the case where the list of words is length greater than 1, but k is still zero. */
public class TestMultiWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    // ngrams files
    public static final String VERY_SHORT_WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";

    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    /** This is an example from the spec.*/
    @Test
    public void testOccurrenceAndChangeK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                VERY_SHORT_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("occurrence");
        words.add("change");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testVideoAndRecordingK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("video");
        words.add("recording");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[video, video_recording, videocassette, videotape]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPastryAndTart() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("pastry,");
        words.add("tart");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[apple_tart, lobster_tart, quiche, quiche_Lorraine, tart, tartlet]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testString() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String input = "change, occurrence";
        List<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleWordsMultipleQuery() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        String input = "hello, hi";
        List<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[hello, hi, how-do-you-do, howdy, hullo]";
        assertThat(actual).isEqualTo(expected);

        String input2 = "farewell, bye";
        List<String> words2 = new ArrayList<>(Arrays.asList(input2.split(" ")));

        NgordnetQuery nq2 = new NgordnetQuery(words2, 0, 0, 0);
        String actual2 = studentHandler.handle(nq2);
        String expected2 = "[adieu, adios, arrivederci, au_revoir, auf_wiedersehen, bye, bye-bye, cheerio, good-by, good-bye, good_day, goodby, goodbye, sayonara, so_long]";
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void testMoreWords() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        String input = "art, relief, figure";
        List<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alto_relievo, alto_rilievo, bas_relief, basso_relievo, basso_rilievo, embossment, half-relief, high_relief, low_relief, mezzo-relievo, mezzo-rilievo, relief, relievo, rilievo, sculptural_relief]";
        assertThat(actual).isEqualTo(expected);

        String input2 = "creature, animal, goose, brent";
        List<String> words2 = new ArrayList<>(Arrays.asList(input2.split(" ")));
        NgordnetQuery nq2 = new NgordnetQuery(words2, 0, 0, 0);
        String actual2 = studentHandler.handle(nq2);
        String expected2 = "[Branta_bernicla, brant, brant_goose, brent, brent_goose, common_brant_goose]";
        assertThat(actual2).isEqualTo(expected2);
    }
}
