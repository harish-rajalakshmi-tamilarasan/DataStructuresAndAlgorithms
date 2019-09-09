package Algorithms;

import java.util.Arrays;

/**
 * @author Harish T
 */
public class Solution {

    public static int solveSuperMarketQueue(int[] customers, int n) {
        if (n == 1) {
            int sum = 0;
            for (int temp : customers) {
                sum += temp;
            }
            return sum;
        }
        if (n >= customers.length) {
             Arrays.sort(customers);
             return customers[customers.length-1];
        }
        Arrays.sort(customers, 0, n);
        for (int a = n; a < customers.length; a++) {
            customers[0] += customers[a];
            Arrays.sort(customers, 0, n);
        }

        return customers[n - 1];
    }

    public static void main(String[] args) {
        int[] customers = {3, 2, 1, 3, 4, 4};

        System.out.println(solveSuperMarketQueue(customers, 2));
    }
}
