package Revision.Heap;


import java.util.*;

/**
 * @author Harish T
 */
public class HeapProblems {
    public static void main(String[] args) {
        int[] a = {10, -10, 20, -40};
        //   MinHeap.heapSort(a);
        String ab = "aaaabb";
        System.out.println(separateElementsWithoutRepeating(ab));
        System.out.println(ab);
    }

    public static int[] kLargestElem(int[] arr, int k) {
        //  if(k>arr.length)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        if (k < arr.length) {
            // Time complexity - (k)
            for (int i = 0; i < k; i++) {
                queue.add(arr[i]);
            }
            // Time complexity - (n-k)logk
            for (int j = k; j < arr.length; j++) {
                if (queue.peek() < arr[j]) {
                    queue.remove();
                    queue.add(arr[j]);
                }
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                queue.add(arr[i]);
            }
        }
        int[] output = new int[Math.min(k, arr.length)];
        int iter = 0;
        // Time complexity - (k)
        while (!queue.isEmpty()) {
            output[iter] = queue.remove();
            ++iter;
        }
        return output;
    }

    public static int[] kSmallestElem(int[] arr, int k) {
        //  if(k>arr.length)
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        if (k < arr.length) {
            // Time complexity - (k)
            for (int i = 0; i < k; i++) {
                queue.add(arr[i]);
            }
            // Time complexity - (n-k)logk
            for (int j = k; j < arr.length; j++) {
                if (queue.peek() > arr[j]) {
                    queue.remove();
                    queue.add(arr[j]);
                }
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                queue.add(arr[i]);
            }
        }
        int[] output = new int[Math.min(k, arr.length)];
        int iter = 0;
        // Time complexity - (k)
        while (!queue.isEmpty()) {
            output[iter] = queue.remove();
            ++iter;
        }
        return output;
    }

    public static void sortAlmostSortedArray(int[] arr, int offset) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i <= offset; i++) {
            queue.add(arr[i]);
        }
        int k = 0;
        for (int j = offset + 1; j < arr.length; j++, k++) {
            arr[k] = queue.remove();
            queue.add(arr[j]);
        }
        while (!queue.isEmpty()) {
            arr[k++] = queue.remove();
        }
        for (int temp : arr) {
            System.out.println(temp);
        }
    }

    public static boolean isBinaryHeap(int[] arr) {
        int nodeCount = arr.length / 2;
        if (nodeCount % 2 == 0) --nodeCount;
        for (int i = 0; i <= nodeCount; i++) {
            if (arr[i] > arr[2 * i + 1]) return false;
            if (2 * i + 2 < arr.length) {
                if (arr[i] > arr[2 * i + 2]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] smallestDearrangement(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int[] output = new int[arr.length];
        for (int j = 0; j < arr.length; j++) {
            int value = queue.remove();
            if (arr[j] != value || j == arr.length - 1) {
                output[j] = value;
                if (output[j] == arr[j]) {
                    output[j] = output[j - 1];
                    output[j - 1] = arr[j];
                }
            } else {
                int temp = queue.remove();
                output[j] = temp;
                queue.add(value);
            }

        }
        return output;
    }

    public static int[] largestDearrangement(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int[] output = new int[arr.length];
        for (int j = 0; j < arr.length; j++) {
            int value = queue.remove();
            if (arr[j] != value || j == arr.length - 1) {
                output[j] = value;
                if (output[j] == arr[j]) {
                    output[j] = output[j - 1];
                    output[j - 1] = arr[j];
                }
            } else {
                int temp = queue.remove();
                output[j] = temp;
                queue.add(value);
            }

        }
        return output;
    }

    public static int kthLargestSubArray(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] currSum = new int[len];
        currSum[0] = arr[0];
        queue.add(arr[0]);
        for (int i = 1; i < len; i++) {
            currSum[i] += currSum[i - 1] + arr[i];
            if (queue.size() < k) {
                queue.add(currSum[i]);
            } else {
                if (queue.peek() < currSum[i]) {
                    queue.remove();
                    queue.add(currSum[i]);
                }
            }
        }
        int sum = 0;
        for (int j = 1; j < len; j++) {
            for (int m = j; m < len; m++) {
                sum = currSum[m] - currSum[j - 1];
                if (queue.size() < k) {
                    queue.add(sum);
                } else {
                    if (queue.peek() < sum) {
                        queue.remove();
                        queue.add(sum);
                    }
                }
            }
        }
        return queue.remove();
    }

    public static boolean separateElementsWithoutRepeating(String str) {
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.containsKey(chars[i])) {
                hashMap.replace(chars[i], hashMap.get(chars[i]) + 1);
            } else {
                hashMap.put(chars[i], 1);
            }
        }
        CharFreqHeap heap = new CharFreqHeap(hashMap.size());
        for (Map.Entry<Character, Integer> entries : hashMap.entrySet()) {
            heap.insert(new CharFreqHeap.KeyFreq(entries.getKey(), entries.getValue()));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(heap.getMin().key);
        CharFreqHeap.KeyFreq prev = heap.removeMin();
        CharFreqHeap.KeyFreq temp = null;
        while (heap.size() != 0) {
            temp = heap.removeMin();
            stringBuilder.append(temp.key);
            if (prev.freq > 1) {
                prev.freq--;
                heap.insert(prev);
            }
            prev = temp;
        }
        System.out.println(stringBuilder);
        return stringBuilder.length() < str.length();
    }

}
