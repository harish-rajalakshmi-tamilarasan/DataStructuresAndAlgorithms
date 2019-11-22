package Algorithms.SearchSortingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * @author Harish T
 */
public class SearchAlgorithms {
    public static void main(String[] args) {
        int[] a = {0, -1, 2, -3, 1};
        quadraticEquation(1, 1, -14);
    }

    public static int missingNum(int[] n) {
        int temp = n[0];
        for (int i = 1; i < n.length; i++) {
            temp ^= n[i];
        }
        int mod = (n.length + 1) % 4;
        if (mod == 0) {
            return temp ^ (n.length + 1);
        }
        if (mod == 1) {
            return temp ^ 1;

        }
        if (mod == 2) {
            return temp ^ (n.length + 2);
        }
        return temp;
    }

    public static void getMedian(int[] arrA, int[] arrB, int firstStart, int firstEnd, int secondStart,
                                 int secondEnd) {

        if ((firstEnd - firstStart == 1) && (secondEnd - secondStart == 1)) {
            int median = (Math.max(arrA[firstStart], arrB[secondStart] + Math.min(arrA[firstEnd], arrB[secondEnd])) / 2);
            System.out.println(median);
            return;
        }
        int frstMid = getMid(arrA, firstStart, firstEnd);
        int secondMid = getMid(arrB, secondStart, secondEnd);
        if (arrA[frstMid] > arrB[secondMid]) {
            if (firstEnd - firstStart == 1) {
                getMedian(arrA, arrB, firstStart, firstEnd, secondMid, secondEnd);
            } else if (secondEnd - secondStart == 1) {
                getMedian(arrA, arrB, firstStart, frstMid, secondStart, secondEnd);
            } else
                getMedian(arrA, arrB, firstStart, frstMid, secondMid, secondEnd);
        } else {
            if (firstEnd - firstStart == 1) {
                getMedian(arrA, arrB, firstStart, firstEnd, secondStart, secondMid);
            } else if (secondEnd - secondStart == 1) {
                getMedian(arrA, arrB, frstMid, firstEnd, secondStart, secondEnd);
            } else
                getMedian(arrA, arrB, frstMid, firstEnd, secondStart, secondMid);
        }
    }

    public static int getMid(int[] arr, int start, int end) {
        return (start + end) / 2;
    }

    public static void getMaxMin(int[] arr) {
        int max = 0;
        int min = 0;
        if (arr[0] > arr[1]) {
            max = arr[0];
            min = arr[1];
        } else {
            max = arr[1];
            min = arr[0];
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            else if (arr[i] < min) min = arr[1];
        }
        System.out.println("min= " + min);
        System.out.println("max= " + max);
    }


    public static int findKthLargest(int[] arr, int k) {
        return 0;
    }

    public static ArrayList<Integer> findkTimesOccuringNum(int[] num, int k) {
        k = num.length / k;
        HashMap<Integer, Integer> hashedMap = new HashMap<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (!hashedMap.containsKey(num[i])) {
                hashedMap.put(num[i], 1);
            } else {
                if (!(hashedMap.get(num[i]) == -1)) {
                    if (hashedMap.get(num[i]) == k) {
                        arr.add(num[i]);
                        hashedMap.replace(num[i], -1);
                    } else {
                        hashedMap.replace(num[i], hashedMap.get(num[i]) + 1);
                    }
                }
            }
        }
        return arr;
    }

    public static void addSmallerNum(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[0]);
        arr[0] = -1;
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            int temp = arr[i];
            if (stack.isEmpty()) {
                arr[i] = -1;
            } else {
                arr[i] = stack.peek();
            }
            stack.push(temp);
        }
    }

    public static int binarySearchInfinite(ArrayList<Integer> list, int start, int end, int key) {
        int max = (int) Math.pow(2, end);
        if (key > list.get(max)) {
            return binarySearchInfinite(list, max + 1, end + 1, key);
        }
        return binarySearch(list, start, max, key);
    }

    public static int binarySearch(ArrayList<Integer> list, int start, int end, int key) {
        int pivot = (start + end) / 2;
        if (list.get(pivot) == key) return pivot;
        if (list.get(pivot) > key) {
            return binarySearch(list, start, pivot - 1, key);
        }
        return binarySearch(list, pivot + 1, end, key);
    }

    public static void commonThreeSortedArr(int[] a, int[] b, int[] c, ArrayList<Integer> result) {
        int j = 0, k = 0;
        for (int i = 0; i < a.length && j < b.length && k < c.length; i++) {
            int num = a[i];
            while (j < b.length && num > b[j]) {
                j++;
            }
            if (j == b.length) break;
            if (b[j] == a[i]) {
                while (k < c.length && num > c[k]) {
                    k++;
                }
                if (k == c.length) break;
                if (c[k] == a[i]) result.add(c[k]);
            }
        }
    }

    public static void commonThreeSortedArrays(int[] a, int[] b, int[] c, ArrayList<Integer> result) {
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length && k < c.length) {
            if (a[i] == b[j] && b[j] == c[k]) {
                result.add(a[i]);
                i++;
                j++;
                k++;
            } else if (a[i] > b[j]) {
                j++;
                if (a[i] > c[k]) {
                    k++;
                } else {
                    i++;
                }
            } else {
                i++;
                if (b[i] > c[k]) {
                    k++;
                }
                else{
                    j++;
                }
            }
        }
    }

    public static int value = Integer.MIN_VALUE;

    public static void adjacentDiffOneSearch(int[] arr, int start, int key) {
        if (start >= 0 && start < arr.length) {
            if (arr[start] == key) {
                value = start;
                return;
            }
            int diff = Math.abs(arr[start] - key);
            adjacentDiffOneSearch(arr, start - diff, key);
            adjacentDiffOneSearch(arr, start + diff, key);
        }
    }

public static void sumEqualsToSum(int[] arr, int sum) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(arr[0]);

        for (int j = 1; j < arr.length - 1; j++) {
            for (int k = j + 1; k < arr.length; k++) {
                if (hashSet.contains(sum - (arr[j] + arr[k]))) {
                    System.out.println(arr[j] + " " + arr[k] + " " + (sum - (arr[j] + arr[k])));
                }
            }
            hashSet.add(arr[j]);
        }
    }

    public static void findPairToSum(int[] arr, int sum) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (hashSet.contains(sum - arr[i])) {
                System.out.println(arr[i] + " " + (sum - arr[i]));
            }
            hashSet.add(arr[i]);
        }
    }

    public static int[] quadraticEquation(int a, int b, int c) {
        int first = (int) (-b - Math.sqrt(Math.pow(b, 2) - (4 * a * c)));
        int second = (int) (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c)));
        int[] arr = new int[2];
        arr[0] = first / (2 * a);
        arr[1] = second / (2 * a);
        System.out.println(arr[0]);
        System.out.println(second / (2 * a));
        return arr;
    }

}

