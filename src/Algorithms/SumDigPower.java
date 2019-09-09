package Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harish T
 */
public class SumDigPower {
    public static List<Long> sumDigPow(long a, long b) {
        List<Long> validNo = new ArrayList<>();
        for (long i = a; i <= b; i++) {
            String s = String.valueOf(i);
            char[] c = new char[s.length()];
            s.getChars(0, s.length(), c, 0);
            long sum = 0;
            for (int w = 0; w <= c.length - 1; w++) {
                sum += Math.pow(Character.getNumericValue(c[w]), w + 1);
            }
            if (i == sum) {
                validNo.add(i);
            }
        }
        return validNo;
    }


    public static void main(String[] args) {

        System.out.println(sumDigPow(2646798, 90).get(0));
    }
}
