package Algorithms.Patternsearching;

import java.util.Arrays;

/**
 * @author Harish T
 */
public class PatternSearch {
    public static void main(String[] args) {
        String text = "qwesudgcjsgdcjkhgsdjdkhcskjdhdcbkjdsvkjhsbckjhskjcbsdjj";
        String pattern = "dkhcskj";
        System.out.println(boyerMooreSearch(text.toCharArray(), pattern.toCharArray()));
    }

    public static int bruteForceSearch(char[] text, char[] pattern) {
        int textLen = text.length;
        int patternLen = pattern.length;
        for (int i = 0; i <= textLen - patternLen; i++) {
            int k = 0;
            while (k < patternLen && text[i + k] == pattern[k]) {
                k++;
            }
            if (k == patternLen) {
                return i;
            }
        }
        return -1;
    }

    public static int kmpSearch(char[] text, char[] pattern) {
        return 0;
    }

    public static int boyerMooreSearch(char[] text, char[] pattern) {
        int textLen = text.length;
        int patternLen = pattern.length;
        int[] badCharCache = new int[256];
        Arrays.fill(badCharCache, -1);
        for (int i = 0; i < pattern.length; i++) {
            badCharCache[pattern[i]] = i;
        }
        int skip = 1;
        for (int i = 0; i <= textLen - patternLen; i += skip) {
            int j = patternLen - 1;
            for (; j >= 0; j--) {
                if (pattern[j] != text[i + j]) {
                    skip = Math.max(1, j - badCharCache[text[i + j]]);
                    break;
                }
            }
            if (j == -1) return i;
        }
        return -1;
    }

    public static int search(char[] text, char[] pattern) {
        for (int i = 0; i <= text.length - pattern.length; i++) {
            int j = 0;
            for (; j < pattern.length && text[i + j] == pattern[j]; j++) {
            }
            if (j == pattern.length) return i;

        }
        return -1;
    }
}
