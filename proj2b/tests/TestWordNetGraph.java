import main.WordNetGraph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestWordNetGraph {
    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";

    @Test
    public void hyponymsTest() {
        WordNetGraph WNG = new WordNetGraph(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        String expected = "[alteration, change, demotion, increase, jump, leap, modification, saltation, transition, variation]";

        assertThat(WNG.hyponyms("change")).isEqualTo(expected);
    }
}
