package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    private HashMap<String, TimeSeries> NGM;
    private TimeSeries countTS;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        NGM = new HashMap<>();
        countTS = new TimeSeries();

        readWordsFile(wordsFilename);
        readCountsFile(countsFilename);
    }

    private void readWordsFile(String wordsFilename) {
        TimeSeries wordTS = new TimeSeries();
        String word = "";
        In in = new In(wordsFilename);
        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split("\t");
            if (!splitLine[0].equals(word)) {
                wordTS = new TimeSeries();
            }
            word = splitLine[0];
            int wordYear = Integer.parseInt(splitLine[1]);
            double wordAppearedTimes = Double.parseDouble(splitLine[2]);
            wordTS.put(wordYear, wordAppearedTimes);

            NGM.put(word, wordTS);
        }
    }

    private void readCountsFile(String countsFilename) {
        In in = new In(countsFilename);
        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");

            int countYear = Integer.parseInt(splitLine[0]);
            double totalNumberOfWords = Double.parseDouble(splitLine[1]);
            countTS.put(countYear, totalNumberOfWords);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries returnTS = new TimeSeries();
        TimeSeries allWordHistory = NGM.get(word);
        if (allWordHistory == null) {
            return returnTS;
        }
        for (int y : allWordHistory.years()) {
            if (y >= startYear && y <= endYear) {
                returnTS.put(y, allWordHistory.get(y));
            }
        }
        return returnTS;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries returnTS = new TimeSeries();
        if (NGM.get(word) == null) {
            return returnTS;
        }
        returnTS = NGM.get(word);
        return returnTS;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return countTS;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries countHist = countHistory(word, startYear, endYear);
        TimeSeries totalCount = totalCountHistory();

        return countHist.dividedBy(totalCount);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries countHist = countHistory(word);
        TimeSeries totalCount = totalCountHistory();

        return countHist.dividedBy(totalCount);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        for (String word : words) {
            TimeSeries weightOfWord = weightHistory(word, startYear, endYear);
            result = weightOfWord.plus(result);
        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();
        for (String word : words) {
            result = result.plus(weightHistory(word));
        }
        return result;
    }
}
