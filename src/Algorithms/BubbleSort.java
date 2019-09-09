package Algorithms;

/**
 * @author Harish T
 */
public class BubbleSort {
    public static void BubbleSort(int[] list) {
        int n = list.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= n-i; j++) {
                int k = list[j];
                if (k < list[j-1]) {
                    list[j] = list[j-1];
                    list[j-1] = k;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {123, 323, 1, 423, 1, 1, 0, 234};
        BubbleSort(a);
        for (int k = 0; k < a.length; k++) {
            System.out.println(a[k]);
        }
    }
}
