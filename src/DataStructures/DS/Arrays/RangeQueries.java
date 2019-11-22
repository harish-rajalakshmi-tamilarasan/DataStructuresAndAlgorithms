package DataStructures.DS.Arrays;


import java.util.ArrayList;

/**
 * @author Harish T
 */
public class RangeQueries {
    private static void preProcess(int[] arr, ArrayList<Integer> block) {
        int square = (int) Math.sqrt(arr.length);
        int blockIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i % square == 0) {
                ++blockIndex;
                block.add(arr[i]);
            } else {
                block.set(blockIndex, block.get(blockIndex) + arr[i]);
            }
        }
    }

    public static void queryProcessor(int[] arr, int l, int r, ArrayList<Integer> list) {
       // int ceilOfL=
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> list = new ArrayList<>();
        preProcess(a, list);
        for (int temp : list) {
            System.out.println(temp);
        }
    }
}
