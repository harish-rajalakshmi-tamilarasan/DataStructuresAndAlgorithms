package DataStructures.DS.Arrays;

import java.util.HashMap;

/**
 * @author Harish T
 */
public class ArraySubProblems {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 0, 4, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(longestIncreasingSumSequence(a));
    }

    public static int minimumJumpsToEnd(int[] arr) {
        int[] jumpDP = new int[arr.length];
        int[] jumpIndex = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] >= i - j) {
                    if (jumpDP[i] == 0) {
                        jumpDP[i] = jumpDP[j] + 1;
                        jumpIndex[i] = j;
                    } else if (jumpDP[i] > jumpDP[j] + 1) {
                        jumpDP[i] = jumpDP[j] + 1;
                        jumpIndex[i] = j;
                    }
                }
            }
        }
        return jumpDP[arr.length - 1];
    }

    public static int[] sortedSubSequenceOf3(int[] arr) {
        int length = arr.length;
        int[] increasingList = new int[length];
        int[] dereasingList = new int[length];
        increasingList[0] = arr[0];
        int max = arr[0];
        dereasingList[length - 1] = arr[length - 1];
        int min = arr[length - 1];
        int increIndex = 1;
        int decreIndex = arr.length - 2;
        int[] sortedSeq = new int[3];
        for (int i = 1; i < length; i++) {
            if (arr[i] < max) {
                increasingList[increIndex] = arr[i];
                max = arr[i];
            } else {
                increasingList[increIndex] = max;
            }
            ++increIndex;
        }
        for (int i = length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                dereasingList[decreIndex] = arr[i];
                min = arr[i];
            } else {
                dereasingList[decreIndex] = min;
            }
            --decreIndex;
        }
        for (int j = 1; j < arr.length - 1; j++) {
            if (increasingList[j] != dereasingList[j]) {
                sortedSeq[0] = increasingList[j];
                sortedSeq[1] = arr[j];
                sortedSeq[2] = dereasingList[j];
                break;
            }
            if (j == arr.length - 2) {
                return new int[0];
            }
        }
        return sortedSeq;
    }

    public static int maxCircularArray(int[] arr) {
        int linearMax = maxSumSubArray(arr);
        System.out.println("Linear Max = " + linearMax);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            arr[i] = -arr[i];
        }
        int wrappedMax = maxSumSubArray(arr);
        wrappedMax = sum + wrappedMax;
        System.out.println("Wrapped Max = " + wrappedMax);
        return Math.max(wrappedMax, linearMax);
    }

    public static int maxProdSubArray(int[] arr) {
        int max = arr[0];
        int maxSoFar = max;
        int minSoFar = max;
        int currMax;
        int currMin;

        for (int i = 1; i < arr.length; i++) {
            currMax = Math.max(arr[i], Math.max(maxSoFar * arr[i], minSoFar * arr[i]));
            currMin = Math.min(arr[i], Math.min(maxSoFar * arr[i], minSoFar * arr[i]));
            if (max < maxSoFar) max = maxSoFar;
            maxSoFar = currMax;
            minSoFar = currMin;
        }
        return max;
    }

    public static int maxSumSubArray(int[] arr) {
        int maxSoFar = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            if (maxSoFar < currSum) maxSoFar = currSum;
            if (currSum < 0) currSum = 0;
        }
        return maxSoFar;
    }

    public static int maxSubArrayOf0sAnd1s(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int sum_so_far = 0;
        int max = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            sum_so_far += arr[i] == 0 ? -1 : 1;
            if (sum_so_far == 0) max = i;
            else if (hashMap.containsKey(sum_so_far)) {
                temp = i - hashMap.get(sum_so_far);
                max = Math.max(temp, max);
            } else {
                hashMap.put(sum_so_far, i);
            }
        }
        return max;
    }

    public static int[] maxSubArrayOf0sAnd1sArr(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int sum_so_far = 0;
        int temp = 0;
        int start = 0;
        int end = -1;
        for (int i = 0; i < arr.length; i++) {
            sum_so_far += arr[i] == 0 ? -1 : 1;
            if (sum_so_far == 0) {
                start = 0;
                end = i;
            } else if (hashMap.containsKey(sum_so_far)) {
                temp = hashMap.get(sum_so_far);
                if (i - temp + 1 > end - start) {
                    start = temp + 1;
                    end = i;
                }
            } else {
                hashMap.put(sum_so_far, i);
            }
        }
        int[] range = new int[2];
        range[0] = start;
        range[1] = end;
        return range;
    }

    public static int longestIncreasingSequence(int[] arr) {
        int[] temp = new int[arr.length];
        for (int k = 0; k < arr.length; k++) {
            temp[k] = 1;
        }
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && (temp[i] < temp[j] + 1)) {
                    temp[i] = temp[j] + 1;
                    if (arr[i] > max) max = temp[i];
                }
            }
        }
        return max;
    }

    public static int longestIncreasingSumSequence(int[] arr) {
        int length = arr.length;
        int max = Integer.MIN_VALUE;
        int[] lis = new int[length];
        lis[0] = arr[0];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + arr[i]) {
                    lis[i] = lis[j] + arr[i];
                    if (lis[i] > max) max = lis[i];
                }
            }
        }
        return max;
    }

    public static int longestDecreasingSequence(int[] arr) {
        int[] temp = new int[arr.length];
        for (int k = 0; k < arr.length; k++) {
            temp[k] = 1;
        }
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && (temp[i] < temp[j] + 1)) {
                    temp[i] = temp[j] + 1;
                    if (arr[i] > max) max = temp[i];
                }
            }
        }
        return max;
    }

    public static int bitonicSequence(int[] arr) {
        int length = arr.length;

        int[] lis = new int[length];
        int[] lds = new int[length];
        for (int i = 0; i < length; i++) {
            lis[i] = 1;
            lds[i] = 1;
        }
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && (lis[i] < lis[j] + 1)) {
                    lis[i] = lis[j] + 1;
                }
                if (arr[length - 1 - i] > arr[length - 1 - j] && (lds[length - 1 - i] < lds[length - 1 - j] + 1)) {
                    lds[length - 1 - i] = lds[length - 1 - j] + 1;
                }
            }
        }
        int max = 1;
        for (int k = 0; k < arr.length; k++) {
            int val = lds[k] + lis[k] - 1;
            if (max < val) max = val;
        }
        return max;
    }

    public static int findMinSwapBringAllElemBelowK(int[] arr, int k) {
        int count = 0;
        for (int temp : arr) {
            if (temp <= k) {
                ++count;
            }
        }
        int windCount = 0;
        for (int j = 0; j < count; j++) {
            if (arr[j] <= k) {
                ++windCount;
            }
        }
        int lastIndex = 0;
        int maxSwap = windCount;
        for (int l = count; l < arr.length && arr.length - l >= k; l++) {
            if (arr[l] <= k) ++windCount;
            if (arr[lastIndex] <= k) --windCount;
            ++lastIndex;
            if (windCount > maxSwap) maxSwap = windCount;
        }
        return count - maxSwap;
    }

    public static int indexToPut1ToGetMaxContiguous1s(int[] arr) {
        int max = 0;
        int prev = -1;
        int prev_Second = -1;
        int maxIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (i - prev_Second > max) {
                    max = i - prev_Second;
                    maxIndex = prev;
                }
                prev_Second = prev;
                prev = i;
            }

        }
        if (arr.length - prev_Second > max) return prev;
        return maxIndex;
    }

    public static int maximumSubArrayAverageForK(int[] arr,int k){
        return 0;
    }

}
