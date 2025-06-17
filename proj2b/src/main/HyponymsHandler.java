package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private final WordNetGraph wng;
    private final NGramMap ngramMap;
    public HyponymsHandler(WordNetGraph wng, NGramMap ngramMap) {
        this.wng = wng;
        this.ngramMap = ngramMap;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        Set<String> hyponymsSet = wng.hyponyms(words);
        if (k != 0) {
            // 出現次數: words(set)
            Map<Double, Set<String>> totalAppearTimesOfWord = new TreeMap<>(); // 用來對照，總出現次數對照年份(可能同樣的word的出現次數會一樣)
            List<Double> totalAppearTimes = new ArrayList<>(); // 放總出現次數
            Set<String> topkSet = new TreeSet<>();
            for (String w : hyponymsSet) {
                // 計算從startYear到endYear的總出現次數
                TimeSeries countHist = ngramMap.countHistory(w, startYear, endYear);
                double totalCountOfWord = totalCountOfTS(countHist);

                if (totalAppearTimesOfWord.containsKey(totalCountOfWord)) {
                    Set<String> newWords = totalAppearTimesOfWord.get(totalCountOfWord);
                    newWords.add(w);
                    totalAppearTimesOfWord.put(totalCountOfWord, newWords);
                } else {
                    Set<String> temp = new TreeSet<>();
                    temp.add(w);
                    totalAppearTimesOfWord.put(totalCountOfWord, temp);
                }
                totalAppearTimes.add(totalCountOfWord);
            }
            if (totalAppearTimes.isEmpty()) {
                return "[]";
            }
            // 由大到小排序
            totalAppearTimes.sort(Collections.reverseOrder());
            for (Double totalAppearTime : totalAppearTimes) {
                if (totalAppearTime == 0) {
                    break;
                }
                // 遍歷相同出現次數(頻率)的words
                Set<String> wordSet = totalAppearTimesOfWord.get(totalAppearTime);
                for (String s : wordSet) {
                    if (topkSet.size() == k) {
                        break;
                    }
                    topkSet.add(s);
                }
            }
            return topkSet.toString();
        }
        return hyponymsSet.toString();
    }

    private Double totalCountOfTS(TimeSeries ts) {
        return ts.values().stream().mapToDouble(Double::doubleValue).sum();
    }
}
