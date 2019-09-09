package Algorithms;

import java.util.Arrays;

/**
 * @author Harish T
 */
public class Kata {

    public static double findUniq(double arr[]) {
        Arrays.sort(arr);
        if (arr[0] == arr[1]) {
            return arr[arr.length - 1];
        }
        return arr[0];
    }


    public static void main(String[] args) {
        long a =7230702951l;
        long b = a%(long)(Math.pow(2,32));
        System.out.println(b);
    }
}
