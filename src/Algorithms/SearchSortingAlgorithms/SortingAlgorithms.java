package Algorithms.SortingAlgorithms;

/**
 * @author Harish T
 */
public class SortingAlgorithms {
    public static void main(String[] args) {
        int[] arr = {5, 4, 8, 2, 1, 6, 9, 10, 3};
        System.out.println(kthLargestElem(arr, 0, 8, 3));
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int partition = partition(arr, start, end);
            quickSort(arr, start, partition - 1);
            quickSort(arr, partition + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int j = start - 1;
        int temp = 0;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                j++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[end] = arr[j + 1];
        arr[j + 1] = pivot;
        return j + 1;
    }

    public static int kthSmallestElem(int[] arr, int start, int end, int k) {
        if (start <= end) {
            int partition = partition(arr, start, end);
            if (partition == k) return arr[k];
            if (partition > k) return kthSmallestElem(arr, start, partition - 1, k);
            return kthSmallestElem(arr, partition + 1, end, k);
        }
        return Integer.MIN_VALUE;
    }

    public static void quickSortDesc(int[] arr, int start, int end) {
        if (start < end) {
            int partition = partitionDesc(arr, start, end);
            quickSortDesc(arr, start, partition - 1);
            quickSortDesc(arr, partition + 1, end);
        }
    }

    public static int partitionDesc(int[] arr, int start, int end) {
        int pivot = arr[end];
        int j = start - 1;
        int temp = 0;
        for (int i = start; i < end; i++) {
            if (arr[i] > pivot) {
                j++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[end] = arr[j + 1];
        arr[j + 1] = pivot;
        return j + 1;
    }

    public static int kthLargestElem(int[] arr, int start, int end, int k) {
        if (start <= end) {
            int partition = partitionDesc(arr, start, end);
            if (partition == k) return arr[k];
            if (partition > k) return kthLargestElem(arr, start, partition - 1, k);
            return kthLargestElem(arr, partition + 1, end, k);
        }
        return Integer.MIN_VALUE;
    }
}
