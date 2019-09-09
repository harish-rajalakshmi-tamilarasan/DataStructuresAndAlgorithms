package Algorithms;

import java.util.LinkedList;

/**
 * @author Harish T
 */
public class BinaryAdd {
    public static int[] binaryAdd(int[] a, int[] b) {
        int leng = a.length;
        int[] c = new int[leng+1];
        int carry = 0;
        for (int i = leng; i > 0; i--) {
            c[i] = (a[i-1] + b[i-1] + carry) % 2;
            carry = (a[i-1] + b[i-1] + carry) / 2;
        }
        if (carry == 1) {
            c[0] = 1;
        }
        return c;
    }

    public static void main(String[] args) {
        int[] add1 = {1, 1, 1, 1, 1};
        int[] add2 = {1, 0, 0, 0, 0};
        int[] a = binaryAdd(add1, add2);
        for (int temp : a) {
            System.out.println(temp);
        }
    }
}
