package main;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

public class WordNetGraph {
    private Graph wordNetGraph;
    private Map<String, Integer> wordToID;
    private Map<Integer, String> iDToWord;
    private int V;

    public WordNetGraph(String synsetFile, String hyponymsFile) {
        wordToID = new HashMap<>();
        iDToWord = new HashMap<>();

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

            wordToID.put(splitLine[1], Integer.parseInt(splitLine[0]));
            iDToWord.put(Integer.parseInt(splitLine[0]), splitLine[1]);
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

}
