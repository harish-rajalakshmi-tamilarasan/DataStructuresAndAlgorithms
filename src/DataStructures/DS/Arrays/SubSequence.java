package DataStructures.DS.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Harish T
 */
public class SubSequence {
    public static void main(String[] args) {
        int[] a = {0, 1, 2, 0, 2};
        int[] b = {5, 6, 7, 8, 2};
        System.out.println(maximumSumWindowUniqueInAuxArr(a, b));
    }

    public static int maxSumContiguousSubArray(int[] arr) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int tmp : arr) {
            currentSum += currentSum + tmp;
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public static int longestIncreasingSubsequence(int[] a) {
        int[] lis = new int[a.length];
        for (int x = 0; x < lis.length; x++) {
            lis[x] = 1;
        }
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((a[i] > a[j]) && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        int max = 0;
        for (int temp : lis) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public static int longestDecreasingSubsequence(int[] a) {
        int[] lis = new int[a.length];
        for (int x = 0; x < lis.length; x++) {
            lis[x] = 1;
        }
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((a[i] < a[j]) && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        int max = 0;
        for (int temp : lis) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public static int bitonicSeq(int[] a) {
        int len = a.length;
        int[] lis = new int[len];
        int[] lds = new int[len];
        for (int x = 0; x < len; x++) {
            lds[x] = 1;
            lis[x] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if ((a[i] > a[j]) && (lis[i] < lis[j] + 1)) {
                    lis[i] = lis[j] + 1;
                }
            }
            int offset = len - 1 - i;
            for (int k = len - 1; k > offset; k--) {
                if ((a[offset] > a[k]) && (lis[offset] < lis[k] + 1)) {
                    lds[offset] = lds[k] + 1;
                }
            }
        }
        /**  for (int i = len - 2; i >= 0; i--) {
         for (int j = len - 1; j > i; j--) {
         if ((a[i] > a[j]) && (lds[i] < lds[j] + 1)) {
         lds[i] = lds[j] + 1;
         }
         }
         }**/
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (max < lds[i] + lis[i] - 1) {
                max = lds[i] + lis[i] - 1;
            }
        }
        return max;
    }

    public static int[] maxConsecutiveSubSequence(int[] arr) {
        int max = 0;
        int maxIndex = -1;
        int length = arr.length;
        int[] dp = new int[length];
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (hashmap.containsKey(arr[i] - 1)) {
                dp[i] = dp[hashmap.get(arr[i] - 1)] + 1;
                if (max < dp[i]) {
                    maxIndex = i;
                    max = dp[i];
                }
            } else {
                dp[i] = 1;
            }
            hashmap.put(arr[i], i);
        }
        int[] sequence = new int[max];
        int seqMax = arr[maxIndex];
        for (int j = 0; j < max; j++) {
            sequence[max - j - 1] = seqMax;
            --seqMax;
        }
        return sequence;
    }

    public static int maxAdjacentSubSequenceCount(int[] arr) {
        int max = 0;
        int length = arr.length;
        int[] dp = new int[length];
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (hashmap.containsKey(arr[i] - 1) || hashmap.containsKey(arr[i] + 1)) {
                if (hashmap.containsKey(arr[i] - 1) && hashmap.containsKey(arr[i] + 1)) {
                    int a = dp[hashmap.get(arr[i] - 1)];
                    int b = dp[hashmap.get(arr[i] + 1)];
                    dp[i] = (a > b ? a : b) + 1;
                    if (dp[i] > max) {
                        max = dp[i];
                    }
                } else if (hashmap.containsKey(arr[i] - 1)) {
                    dp[i] = dp[hashmap.get(arr[i] - 1)] + 1;
                    if (max < dp[i]) {
                        max = dp[i];
                    }
                } else {
                    dp[i] = dp[hashmap.get(arr[i] + 1)] + 1;
                    if (max < dp[i]) {
                        max = dp[i];
                    }
                }
            } else {
                dp[i] = 1;
            }
            hashmap.put(arr[i], i);
        }
        return max;
    }

    public static int[] maxAdjacentSubSequence(int[] arr) {
        int max = 0;
        int maxIndex = -1;
        int length = arr.length;
        int[] dp = new int[length];
        HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (hashmap.containsKey(arr[i] - 1) || hashmap.containsKey(arr[i] + 1)) {
                if (hashmap.containsKey(arr[i] - 1) && hashmap.containsKey(arr[i] + 1)) {
                    int a = dp[hashmap.get(arr[i] - 1).get(hashmap.get(arr[i] - 1).size() - 1)];
                    int b = dp[hashmap.get(arr[i] + 1).get(hashmap.get(arr[i] + 1).size() - 1)];
                    dp[i] = (a > b ? a : b) + 1;
                    if (dp[i] > max) {
                        max = dp[i];
                        maxIndex = i;
                    }
                } else if (hashmap.containsKey(arr[i] - 1)) {
                    dp[i] = dp[hashmap.get(arr[i] - 1).get(hashmap.get(arr[i] - 1).size() - 1)] + 1;
                    if (max < dp[i]) {
                        max = dp[i];
                        maxIndex = i;
                    }
                } else {
                    dp[i] = dp[hashmap.get(arr[i] + 1).get(hashmap.get(arr[i] + 1).size() - 1)] + 1;
                    if (max < dp[i]) {
                        max = dp[i];
                        maxIndex = i;
                    }
                }
            } else {
                dp[i] = 1;
            }
            if (hashmap.containsKey(arr[i])) {
                hashmap.get(arr[i]).add(i);
            } else {
                hashmap.put(arr[i], new ArrayList<>(Arrays.asList(i)));
            }
        }
        int[] output = new int[max];
        for (int i = 0; i < max; i++) {
            output[max - 1 - i] = arr[maxIndex];
            ArrayList<Integer> currentList = hashmap.get(arr[maxIndex]);
            int listSize = currentList.size();
            currentList.remove(currentList.size() - 1);
            if (listSize == 1) hashmap.remove(arr[maxIndex]);
            int a = 0, b = 0;
            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Integer> temp2 = new ArrayList<>();
            if (hashmap.containsKey(arr[maxIndex] - 1)) {
                temp = hashmap.get(arr[maxIndex] - 1);
                a = temp.get(temp.size() - 1);
            }
            if (hashmap.containsKey(arr[maxIndex] + 1)) {
                temp2 = hashmap.get(arr[maxIndex] + 1);
                a = temp2.get(temp2.size() - 1);
            }
            maxIndex = a > b ? a : b;
        }
        return output;
    }

    //1,3,2,4,6,5
    public static int[] consecutiveSubSequence(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            hashSet.add(arr[i]);
        }
        int max = 1;
        int maxValue = 0;
        for (int j = 0; j < arr.length; j++) {
            int temp = arr[j];
            int tempMax = 1;
            if (!hashSet.contains(temp - 1)) {
                while (hashSet.contains(temp + 1)) {
                    temp = temp + 1;
                    tempMax = tempMax + 1;
                }
                if (tempMax > max) {
                    max = tempMax;
                    maxValue = temp;
                }
            }
        }
        int[] consecutiveArray = new int[max];
        for (int i = 0; i < max; i++) {
            consecutiveArray[max - 1 - i] = maxValue;
            --maxValue;
        }
        return consecutiveArray;
    }

    //{1,2,3,4,5,6} => 2,4,6,(2,4),(2,6),(2,4,6)
    public static int subsetsWithDistinctCount(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                hashSet.add(arr[i]);
            }
        }
        return (int) Math.pow(2, hashSet.size()) - 1;
    }

    public static int[] countDistinctElementsInWindow(int[] arr, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] windowCount = new int[arr.length - k + 1];
        for (int j = 0; j < k; j++) {
            if (hashMap.containsKey(arr[j])) {
                hashMap.replace(arr[j], hashMap.get(arr[j]) + 1);
            } else {
                hashMap.put(arr[j], 1);
            }
        }
        windowCount[0] = hashMap.size();
        int start = 0;

        for (int i = k; i < arr.length; i++) {
            if (hashMap.get(arr[start]) == 1) {
                hashMap.remove(arr[start]);
            } else {
                hashMap.replace(arr[start], hashMap.get(arr[start]) - 1);
            }
            ++start;
            if (hashMap.containsKey(arr[i])) {
                windowCount[start] = hashMap.size();
                hashMap.replace(arr[i], hashMap.get(arr[i]) + 1);
            } else {
                windowCount[start] = hashMap.size() + 1;
                hashMap.put(arr[i], 1);
            }
        }
        return windowCount;
    }

    //2,3,4 - 5,6,1 => 5,6,4
    public static void maximizeArrWithAnotherArr(int[] a, int[] b) {
        int length = b.length;
        int[] auxArr = new int[length * 2];
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            indexMap.put(b[i], i);
        }
        for (int j = 0; j < length; j++) {
            auxArr[j] = a[j];
            auxArr[j + length] = b[j];
        }
        Arrays.sort(auxArr);
        for (int x = 0; x < length; x++) {
            b[x] = auxArr[length + x];
        }
        int len = 0;
        while (len < length) {
            if (indexMap.containsKey(b[len])) {
                int index = indexMap.get(auxArr[length + len]);
                if (index != len) {
                    int temp = b[index];
                    b[index] = b[len];
                    b[len] = temp;
                }
            } else {
                ++len;
            }
        }
        for (int temp : b) {
            System.out.println(temp);
        }
    }

    //0,1,2,3,0,1,4 - 9,8,1,2,3,4,5 => 20 (9,8,1,2)
    public static int maximumSumWindowUniqueInAuxArr(int[] a, int[] b) {
        int length = a.length;
        int startIndex = 0;
        int maxSum = 0;
        int currSum = 0;
        int[] currSumArr = new int[length];
        currSumArr[0] = b[0];
        for (int i = 1; i < length; i++) {
            currSumArr[i] = currSumArr[i - 1] + b[i];
        }
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (indexMap.containsKey(a[i])) {
                startIndex = indexMap.get(a[i])+1;
                currSum = currSumArr[i] - currSumArr[startIndex-1];
                if (maxSum < currSum) maxSum = currSum;
            } else {
                if (startIndex != 0) {
                    currSum = currSumArr[i] - currSumArr[startIndex - 1];
                } else {
                    currSum = currSumArr[i];
                }
                if (maxSum < currSum) maxSum = currSum;
            }
            indexMap.put(a[i], i);
        }
        return maxSum;
    }




}
