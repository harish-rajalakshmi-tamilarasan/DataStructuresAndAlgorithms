package Algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harish T
 */
public class CoinChange {

    public static void coinChange(int[] changeSet, int N) {
        HashMap<Integer, Integer> greedyMap = new HashMap<>();
        for (int i = changeSet.length - 1; i >= 0; i--) {
            int a = 0;
            while (N >= changeSet[i]) {
                N = N - changeSet[i];
                a++;
            }
            greedyMap.put(changeSet[i], a);
        }
        for (Map.Entry<Integer, Integer> entry : greedyMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        int[] changeSet = {50, 100, 5000, 10000, 100000};
        coinChange(changeSet, 12345600);
        String a = new String("ada");
    }

    public static void jsonParser() {

    }
}
