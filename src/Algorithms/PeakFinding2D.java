package Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Not fully implemented
public class PeakFinding2D {
    public static void main(String[] args) {
        LinkedList b = new LinkedList();
    }

    public static int getPeak(int[][] arr, int start, int end) {
        List list = new LinkedList();
        ArrayList a = new ArrayList();
        list = a;
        int mid = (start + end) / 2;
        int temp = arr[0][mid];
        int columHigh = 0;
        for (int i = 1; i < arr[0].length; i++) {
            if (arr[i][mid] > temp) {
                temp = arr[i][mid];
                columHigh = i;
            }
        }
        if (mid == 0) {
            return arr[columHigh][0];
        }
        if (mid == arr.length - 1) {
            return arr[columHigh][arr.length - 1];
        }
        if (mid != 0 && mid != arr.length - 1) {
            if (arr[columHigh][mid] > arr[columHigh][mid - 1] && arr[columHigh][mid] > arr[columHigh][mid + 1]) {
                return arr[columHigh][mid];
            }
            if (arr[columHigh][mid] > arr[columHigh][mid - 1] && arr[columHigh][mid] < arr[columHigh][mid + 1]) {
                return getPeak(arr, start, mid);
            }
            if (arr[columHigh][mid] < arr[columHigh][mid - 1] && arr[columHigh][mid] > arr[columHigh][mid + 1]) {
                return getPeak(arr, mid + 1, end);
            }
        }
        return -1;

    }
}