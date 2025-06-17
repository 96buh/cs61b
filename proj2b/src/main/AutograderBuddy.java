package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        WordNetGraph wng = new WordNetGraph(synsetFile, hyponymFile);
        NGramMap ngm = new NGramMap(wordFile, countFile);

        return new HyponymsHandler(wng, ngm);
    }
}
