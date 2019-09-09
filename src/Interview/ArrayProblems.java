package Interview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Harish T
 */
public class Basics {
    public static void main(String[] args) throws Exception {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        arrayReversalRot(arr, 2);
        for (int a : arr) {
            System.out.println(a);
        }
    }

    public static void factors(int[] arr) {

    }

    /**
     * Method should satisfy arr[i]=i and arr[i]=-1 where value i not found
     *
     * @param arr Array to handle
     */
    public static void mapIndexWithValue(int[] arr) {
        int a = arr.length;
        int[] newArr = new int[a];
        Arrays.sort(arr);
        for (int i = a - 1; i >= 0; i--) {
            if (arr[i] != -1) {
                newArr[arr[i]] = arr[i];
            } else {
                newArr[arr[i]] = -1;
            }
        }
    }

    public static int gcd(int a, int b) {
        if (a >= b) {
            int r = a % b;
            if (r == 0) {
                return b;
            }
            return gcd(b, r);
        }
        int r = b % a;
        if (r == 0) {
            return a;
        }
        return gcd(a, r);
    }

    public static void arrayJuggleRot(int[] arr, int n) throws Exception {
        if (n <= 0 || arr.length == 0) throw new Exception("Invalid input");
        int setLength = gcd(arr.length, n);
        for (int k = 0; k < n / setLength; k++) {
            for (int i = 0; i < setLength; i++) {
                int a = i;
                int temp = arr[i];
                while (true) {
                    if (a + setLength <= arr.length - 1) {
                        arr[a] = arr[a + setLength];
                        a += setLength;
                    } else {
                        arr[a] = temp;
                        break;
                    }

                }
            }
        }
    }

    public static void arrayReversalRot(int[] arr, int n) {
        reverseArr(arr, 0, n - 1);
        reverseArr(arr, n, arr.length - 1);
        reverseArr(arr, 0, arr.length - 1);
    }

    public static void reverseArr(int[] arr, int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
            --end;
        }
    }

    public static void searchRotatedArr(int[] arr, int n) {

    }
}

