package Algorithms;



import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<Integer>(Arrays.asList(4, 3, 12, 0, 1142, 4, 3, 444, 1));
        mergeSort(test, 0, test.size() - 1);
        for(int temp:test){
            System.out.println(temp);
        }
    }

    public static void mergeSort(ArrayList<Integer> arrayToSort, int start, int end) {
        if (start < end) {
            int m = (start + end) / 2;
            mergeSort(arrayToSort, start, m);
            mergeSort(arrayToSort, m + 1, end);
            merge(arrayToSort, start, m, end);
        }
    }

    public static void merge(ArrayList<Integer> arr, int start, int mid, int end) {
        int i = 0;
        int j = 0;
        int k = start;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (int x = start; x <= mid; x++) {
            left.add(arr.get(x));
        }
        for (int y = mid + 1; y <= end; y++) {
            right.add(arr.get(y));
        }
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                arr.set(k, left.get(i));
                i++;
            } else {
                arr.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < left.size()) {
            arr.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < right.size()) {
            arr.set(k, right.get(j));
            j++;
            k++;
        }

    }
}