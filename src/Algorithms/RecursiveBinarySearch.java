package Algorithms;

/**
 * @author Harish T
 */
public class RecursiveBinarySearch {

    public static int binaryRecursiveSearch(int key, int[] a, int i, int n) {
        if (i <= n) {
            int m = i + ((n - i) / 2);
            if (key == a[m]) {
                return m;
            }
            if (key < a[m]) {
                return binaryRecursiveSearch(key, a, i, m - 1);
            }
            if (key > a[m]) {
                return binaryRecursiveSearch(key, a, m + 1, n);

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 123, 2345, 665345, 1123432423};
        System.out.println(binaryRecursiveSearch(1123432423, a, 0, a.length - 1));

    }
}
