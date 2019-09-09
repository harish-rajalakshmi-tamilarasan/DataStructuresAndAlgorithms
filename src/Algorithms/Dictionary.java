package Algorithms;

import java.util.HashMap;

/**
 * @author Harish T
 */
public class Dictionary {

    private final String[] words;

    public Dictionary(String[] words) {
        this.words = words;
    }

    public static void createMap(String[] words) {
        HashMap<Integer, String> hashmap = new HashMap<>();
        for (String temp : words) {
            hashmap.put(temp.length(), temp);
        }
    }

    public String findMostSimilar(String to) {

        return null;
    }
}
