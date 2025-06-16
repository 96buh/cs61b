import main.WordNetGraph;
import ngrams.NGramMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestWordNetGraph {
    public static final String VERY_SHORT_WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String SMALL_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";
    private static final String RANDOM_WORDS_25 = "data/ngrams/random_freq_25.csv";
    private static final String RANDOM_WORDS_10 = "data/ngrams/random_freq_10.csv";
    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";

    @Test
    public void singleWordHyponymsTest() {
        WordNetGraph WNG = new WordNetGraph(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> input = new ArrayList<>();
        input.add("change");

        assertThat(WNG.hyponyms(input)).isEqualTo(Set.of(
                "alteration", "change", "demotion", "increase",
                "jump", "leap", "modification", "saltation", "transition", "variation"
        ));
    }

    @Test
    public void multipleWordsSingleQueryTest() {
        WordNetGraph WNG = new WordNetGraph(LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        String input = "video, recording";
        assertThat(WNG.hyponyms(Arrays.asList(input.split(" ")))).isEqualTo(
                Set.of("video", "video_recording", "videocassette", "videotape")
        );

        String input2 = "pastry, tart";
        assertThat(WNG.hyponyms(Arrays.asList(input2.split(" ")))).isEqualTo(
                Set.of("apple_tart", "lobster_tart", "quiche", "quiche_Lorraine", "tart", "tartlet")
        );
    }
}
