package Algorithms.SearchAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Harish T
 */
public class SearchAlgorithms {
    public static void main(String[] args) {
        int[] arr = {2, 1, 2, 3, 2, 5, 3, 7, 9, 3, 11, 0};
        ArrayList<Integer> arrayList = findkTimesOccuringNum(arr, 6);
        for(int trmp:arrayList){
            System.out.println(trmp);
        }
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
}
