package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNetGraph WNG;
    public HyponymsHandler(WordNetGraph WNG) {
        this.WNG = WNG;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String result = "";

        for (String w : words) {
            result = WNG.hyponyms(w);
        }

        return result;
    }
}
