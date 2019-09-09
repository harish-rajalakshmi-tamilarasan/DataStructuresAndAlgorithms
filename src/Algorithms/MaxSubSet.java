package Algorithms;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Harish T
 */
public class MaxSubSet {
    public static int sequence(int[] arr) {
        int length = arr.length;
        ArrayList<Integer> sumArr = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                int sum = 0;
                for (int m=0; m <=i; m++) {
                    sum += arr[m+j];
                }
                sumArr.add(sum);
            }
        }
        return Collections.max(sumArr);
    }

    public static void main(String[] args) {
        int[] a={12,-6,3,-1,-7,8};
        System.out.println(sequence(a));
    }
}
