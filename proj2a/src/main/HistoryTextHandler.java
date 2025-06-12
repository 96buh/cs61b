package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {

    private NGramMap map;

    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();


        String response = "";
        for (String word : words) {
            TimeSeries result = map.weightHistory(word, startYear, endYear);
            response += word;
            response += ": ";
            response += result.toString();
            response += "\n";
        }
        return response;
    }
}
