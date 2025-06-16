package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNetGraph WNG;
    private NGramMap NgramMap;
    public HyponymsHandler(WordNetGraph WNG, NGramMap NgramMap) {
        this.WNG = WNG;
        this.NgramMap = NgramMap;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        Set<String> hyponymsSet = WNG.hyponyms(words);
        if (k != 0) {
            // 出現次數: words(set)
            Map<Double, Set<String>> totalAppearTimesOfWord = new TreeMap<>(); // 用來對照，總出現次數對照年份(可能同樣的word的出現次數會一樣)
            List<Double> totalAppearTimes = new ArrayList<>(); // 放總出現次數
            Set<String> topkSet = new TreeSet<>();
            for (String w : hyponymsSet) {
                // 計算從startYear到endYear的總出現次數
                TimeSeries countHist = NgramMap.countHistory(w, startYear, endYear);
                double sum = 0;
                for (int year : countHist.years()) {
                    sum += countHist.get(year);
                }
                if (totalAppearTimesOfWord.containsKey(sum)) {
                    Set<String> newWords = totalAppearTimesOfWord.get(sum);
                    newWords.add(w);
                    totalAppearTimesOfWord.put(sum, newWords);
                } else {
                    Set<String> temp = new TreeSet<>();
                    temp.add(w);
                    totalAppearTimesOfWord.put(sum, temp);
                }
                totalAppearTimes.add(sum);
            }
            // 由大到小排序
            totalAppearTimes.sort(Collections.reverseOrder());
            for (int i = 0; i < k; i++) {
                if (totalAppearTimes.get(i) == 0) {
                    break;
                }
                // 遍歷相同出現次數(頻率)的word
                topkSet.addAll(totalAppearTimesOfWord.get(totalAppearTimes.get(i)));
            }
            return topkSet.toString();
        }
        return hyponymsSet.toString();
    }
}
