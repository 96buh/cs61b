package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNetGraph {
    private Graph wordNetGraph;
    private Map<String, Set<Integer>> wordToIDs;
    private Map<Integer, String> idToWords;
    private int V;

    public WordNetGraph(String synsetFile, String hyponymsFile) {
        wordToIDs = new HashMap<>();
        idToWords = new HashMap<>();

        this.V = readSynset(synsetFile);
        wordNetGraph = new Graph(this.V);

        readHyponyms(hyponymsFile);
    }


    /** 讀取synset得到有幾個vertices
     */
    private int readSynset(String synsetFile) {
        In in = new In(synsetFile);
        int vertices = 0;
        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            String word = splitLine[1];
            int synsetID = Integer.parseInt(splitLine[0]);
            String[] splitSpace = word.split(" "); // 把word依照空格切分

            // 如果word有空格就遍歷每個subword，如果該subword已經在Map裡面就把synsetID加到Set(避免重複)
            // 如果還沒有在Map，就新增一個Set把subword
            if (splitSpace.length != 1) {
                for (String s : splitSpace) {
                    if (wordToIDs.containsKey(s)) {
                        wordToIDs.get(s).add(synsetID);
                    } else {
                        Set<Integer> IDs = new HashSet<>();
                        IDs.add(synsetID);
                        wordToIDs.put(s, IDs);
                    }
                }
            } else if (wordToIDs.containsKey(word)) {
                wordToIDs.get(word).add(synsetID);
            } else {
                Set<Integer> IDs = new HashSet<>();
                IDs.add(synsetID);
                wordToIDs.put(word, IDs);
            }
            idToWords.put(synsetID, word);
            vertices += 1;
        }
        return vertices;
    }

    private void readHyponyms(String hyponymsFile) {
        In in = new In(hyponymsFile);
        while (in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            int synsetID = Integer.parseInt(splitLine[0]);
            /* 連接synsetID到hyponyms */
            for (int i = 1; i < splitLine.length; i++) {
                wordNetGraph.addEdge(synsetID, Integer.parseInt(splitLine[i]));
            }
        }
    }

    public String getWord(int id) {
        return idToWords.get(id);
    }

    /* 根據輸入的word(String)得到他的snyset ID */
    public Set<Integer> getSynsetID(String word) {
        return wordToIDs.get(word);
    }

    /*  從輸入的word找到含有該個字的node並返回他的synset ID
     *  e.g. 假設有"change":1和"change alteration modification":2 兩個的node和對應的ID
     *       當輸入"change"會返回[1, 2] (因為"change alteration modification"也含有change)
     */
    private Set<Integer> getHyponymsIDs(String word) {
        Set<Integer> returnIDs = new HashSet<>();
        Set<Integer> wordIDs = getSynsetID(word);
        for (int id : wordIDs) {
            DepthFirstPaths dfs = new DepthFirstPaths(wordNetGraph, id);
            returnIDs.addAll(dfs.verticesThatHasVisited());
        }
        return returnIDs;
    }

    // 將輸入的synset ids轉換成words
    private String idsToWords(Set<Integer> IDs) {
        Set<String> wordSet = new TreeSet<>();
        for (int id : IDs) {
            String word = getWord(id);
            if (word.split(" ").length != 1) {
                Collections.addAll(wordSet, word.split(" "));
            } else {
                wordSet.add(word);
            }
        }
        return wordSet.toString();
    }

    public String hyponyms(String word) {
        Set<Integer> hyponymsIDs = getHyponymsIDs(word);
        return idsToWords(hyponymsIDs);
    }
}
