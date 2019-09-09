package Algorithms;


/**
 * @author Harish T
 */
public class PeakFinding1D {
    public static void main(String[] args) {
        int[] arr = {2134234777, 1212, 33, 4, 1, 0};
        System.out.println(peakOf1DArr(arr, 0, arr.length - 1));

    }

    public static int peakOf1DArr(int[] arr, int start, int end) {
        int mid = start + (end - start) / 2;
        if (start == end) {
            return arr[start];
        }
        if (end - start == 1) {
            return arr[start] > arr[end] ? arr[start] : arr[end];
        }
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return arr[mid];
        }
        if (arr[mid] < arr[mid - 1]) {
            return peakOf1DArr(arr, start, mid);
        }
        if (arr[mid] > arr[mid - 1]) {
            return peakOf1DArr(arr, mid + 1, end);
        }
        return -1;
    }
}
