package Algorithms.SearchSortingAlgorithms;

import java.util.*;

/**
 * @author Harish T
 */
public class SortingAlgorithms {
    public static void main(String[] args) {
        int[] arr = {7,9,4,5,14, 9, 10, 11, 12, 7};
        //1,5,10,6,8,9,2,3
        //int[] arr = {1, 3, 4, 2};
        pairsumArray(arr);
    }

    private static class ArrayWithFreq {
        int value;
        int freq;

        ArrayWithFreq(int value, int freq) {
            this.value = value;
            this.freq = freq;
        }
    }

    public static void sortByFrequency(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int temp : arr) {
            if (hashMap.containsKey(temp)) {
                hashMap.replace(temp, hashMap.get(temp) + 1);
            } else {
                hashMap.put(temp, 1);
            }
        }
        PriorityQueue<ArrayWithFreq> queue = new PriorityQueue<>(new Comparator<SortingAlgorithms.ArrayWithFreq>() {
            @Override
            public int compare(ArrayWithFreq o1, ArrayWithFreq o2) {
                if (o1.freq > o2.freq) return 1;
                if (o1.freq < o2.freq) return -1;
                if (o1.value > o2.value) return 1;
                if (o1.value < o2.value) return -1;
                return 0;
            }
        });
        for (Map.Entry<Integer, Integer> entries : hashMap.entrySet()) {
            queue.add(new ArrayWithFreq(entries.getKey(), entries.getValue()));
        }
        int i = 0;
        while (!queue.isEmpty()) {
            ArrayWithFreq freq = queue.remove();
            int limit = freq.freq;
            while (limit > 0) {
                arr[i++] = freq.value;
                --limit;
            }
        }

        for (int temp : arr) {
            System.out.println(temp);
        }
    }

    public static void sortKsortedArray(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k + 1; i++) {
            queue.add(arr[i]);
        }
        int index = 0;
        for (int j = k + 1; j < arr.length; j++) {
            arr[index++] = queue.remove();
            queue.add(arr[j]);
        }
        while (!queue.isEmpty()) {
            arr[index++] = queue.remove();
        }
    }

    public static void quickSortBasedOnK(int[] arr, int start, int end, int k) {
        if (start < end) {
            randomPartition(arr, start, end);
            int partition = partitionBasedOnK(arr, start, end, k);
            quickSortBasedOnK(arr, start, partition - 1, k);
            quickSortBasedOnK(arr, partition + 1, end, k);
        }
    }

    public static int partitionBasedOnK(int[] arr, int start, int end, int k) {
        int pivot = arr[end];
        int j = start - 1;
        int temp = 0;
        for (int i = start; i < end; i++) {
            if (comparatorBasedOnk(arr[i], pivot, k) == 0) {
                temp = arr[i];
                arr[i] = arr[++j];
                arr[j] = temp;
            }
        }
        arr[end] = arr[j + 1];
        arr[j + 1] = pivot;
        return j + 1;
    }

    public static int comparatorBasedOnk(int a, int b, int comparator) {
        int absOfA = Math.abs(comparator - a);
        int absOfB = Math.abs(comparator - b);
        return absOfA > absOfB ? 1 : 0;
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            randomPartition(arr, start, end);
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

    public static void randomPartition(int[] arr, int start, int end) {
        Random random = new Random();
        int pivot = random.nextInt((end - start) + 1) + start;
        int temp = arr[pivot];
        arr[pivot] = arr[end];
        arr[end] = temp;
    }

    public static int[] twoElementsClosestToZero(int[] arr) {
        if (arr.length <= 2) return arr;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE - 1;
        for (int temp : arr) {
            int modVal = Math.abs(temp);
            if (modVal < Math.abs(second)) {
                if (modVal < Math.abs(first)) {
                    second = first;
                    first = temp;
                } else {
                    second = temp;
                }
            }
        }
        int[] o = new int[2];
        o[1] = second;
        o[0] = first;
        return o;
    }

    public static int[] shortestUnorderedArray(int[] arr) {
        int start = -1, end = -1;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i - 1] > arr[i] && arr[i - 1] > arr[i - 2]) {
                start = i - 2;
                end = i;
                break;
            }
        }
        if (start != -1) {
            int[] op = new int[2];
            op[0] = start;
            op[1] = end;
            return op;
        }
        return new int[0];
    }

    public static void unsortedSubArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        int len = arr.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[i] = arr[i];
        }
        Arrays.sort(temp);
        while (left < len) {
            if (arr[left] != temp[left]) break;
            ++left;
        }
        while (right >= 0) {
            if (arr[right] != temp[right]) break;
            --right;
        }
        System.out.println(left);
        System.out.println(right);
    }

    public static void pairsumArray(int[] pairArr) {

        int len = pairArr.length - 1;
        int newLen = (1 + (int) Math.sqrt(1 + 4 * 2 * (len + 1))) / 2;
        int[] op = new int[newLen];
        op[newLen - 1] = (pairArr[len] + pairArr[len - 1] - pairArr[len - 2]) / 2;
        int last = op[newLen - 1];
        op[newLen - 2] = pairArr[len] - last;
        for (int i = 2, j = 1; i <= newLen - 1; j = j + i, i++) {
            op[newLen-1 - i] = pairArr[len - j] - last;
        }
        for (int t : op) {
            System.out.println(t);
        }
    }

}
