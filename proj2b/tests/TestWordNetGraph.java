import main.WordNetGraph;
import org.junit.jupiter.api.Test;

public class TestWordNetGraph {
    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";

    @Test
    public void constructTest() {
        WordNetGraph WNG = new WordNetGraph(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
    }
}
