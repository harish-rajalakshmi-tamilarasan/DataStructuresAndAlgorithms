package DataStructures.DS.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author Harish T
 */
public class SubArray {
    public static void main(String[] args) {
        int[] arr = {2, 7, 6, 1, 4, 5};
        System.out.println(maxChocolatesDistributedEquallyToK(arr, 3));
    }

    public static int maxChocolatesDistributedEquallyToK(int[] a, int k) {
        int[] modArr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            modArr[i] = a[i] % k;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int currSum = 0;
        int startIndex = 0;
        int endIndex = -1;
        for (int j = 0; j < a.length; j++) {
            currSum += a[j];
            int mod = currSum % k;
            if (mod == 0) {
                if (!hashMap.containsKey(0)) {
                    hashMap.put(0, j);
                }
                endIndex = j;
                startIndex = 0;
            } else {
                if (hashMap.containsKey(mod)) {
                    int index = hashMap.get(mod);
                    if (j - index > endIndex - startIndex + 1) {
                        startIndex = index + 1;
                        endIndex = j;
                    }
                } else {
                    hashMap.put(mod, j);
                }
            }
        }
        System.out.println(startIndex);
        System.out.println(endIndex);
        return endIndex - startIndex + 1;
    }

    public static int subArrayWithEqual2s1sAnd0sCount(int[] arr) {
        int currSum = 0;
        int count = 0;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                currSum += -3;
            } else {
                currSum += arr[i];
            }
            if (currSum == 0) {
                ++count;
            }
            if (hashMap.containsKey(currSum)) {
                ArrayList<Integer> tempList = hashMap.get(currSum);
                count += tempList.size();
                tempList.add(i);
            } else {
                hashMap.put(currSum, new ArrayList<>(Arrays.asList(i)));
            }
        }
        return count;
    }

    public static int subArrayWithEqual1sAnd0sCount(int[] arr) {
        int currSum = 0;
        int count = 0;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                currSum += -1;
            } else {
                currSum += 1;
            }
            if (currSum == 0) {
                ++count;
            }
            else if (hashMap.containsKey(currSum)) {
                ArrayList<Integer> tempList = hashMap.get(currSum);
                tempList.add(i);
                count += tempList.size();
            } else {
                hashMap.put(currSum, new ArrayList<>(Arrays.asList(i)));
            }
        }
        return count;
    }

    public static int maxSubArrayWithEqual1sAnd0sCount(int[] arr) {
        int currSum = 0;
        int max = 0;
        int temp = 0;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                currSum += -1;
            } else {
                currSum += 1;
            }
            if (currSum == 0) {
                max = i + 1;
                hashMap.put(currSum, new ArrayList<>(Arrays.asList(i)));
            } else if (hashMap.containsKey(currSum)) {
                ArrayList<Integer> tempList = hashMap.get(currSum);
                temp = i - tempList.get(tempList.size() - 1);
                if (temp > max) max = temp;
                tempList.add(i);
            } else {
                hashMap.put(currSum, new ArrayList<>(Arrays.asList(i)));
            }
        }
        return max;
    }

    public static int[] maxSubArrayWithEqual1sAnd0s(int[] arr) {
        int currSum = 0;
        int temp = 0;
        int startIndex = -1;
        int endIndex = -1;

        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                currSum += -1;
            } else {
                currSum += 1;
            }
            if (currSum == 0) {
                startIndex = 0;
                endIndex = i;
                hashMap.put(currSum, new ArrayList<>(Arrays.asList(i)));
            } else if (hashMap.containsKey(currSum)) {
                ArrayList<Integer> tempList = hashMap.get(currSum);
                temp = tempList.get(tempList.size() - 1);
                if (i - temp > endIndex - startIndex + 1) {
                    startIndex = temp + 1;
                    endIndex = i;
                }
                tempList.add(i);
            } else {
                hashMap.put(currSum, new ArrayList<>(Arrays.asList(i)));
            }
        }
        int[] output = new int[endIndex - startIndex + 1];
        for (int i = startIndex, j = 0; i <= endIndex; i++, j++) {
            output[j] = arr[i];
        }
        return output;
    }


    public static int[] smallestSubArrayWithExactlyKDistinctElem(int[] arr, int k) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            updateValueToMap(indexMap, arr[i]);
            if (indexMap.size() > k) {
                endIndex = i;
                while (indexMap.size() > k) {
                    int temp = indexMap.get(arr[startIndex]);
                    indexMap.replace(arr[startIndex], temp - 1);
                    if (temp == 1) {
                        indexMap.remove(arr[startIndex]);
                    }
                    ++startIndex;
                }
            }
            if (indexMap.size() == k) {
                endIndex = i;
                while (indexMap.get(arr[startIndex]) > 1) {
                    indexMap.replace(arr[startIndex], indexMap.get(arr[startIndex]) - 1);
                    ++startIndex;
                }
                int temp = endIndex - startIndex + 1;
                if (temp < min) {
                    min = temp;
                }
            }
            if (min == k) break;
        }
        int[] output = new int[min];
        for (
                int i = startIndex, j = 0;
                i <= endIndex; i++, j++) {
            output[j] = arr[i];
        }
        return output;
    }

    public static int maxSumSubArray(int[] arr) {
        int length = arr.length;
        int sumSoFar = 0;
        int maxSum = Integer.MIN_VALUE;
        int tempIndex = 0;
        int endIndex = -1;
        int startIndex = 0;
        int lowestNeg = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            lowestNeg = lowestNeg > arr[i] ? lowestNeg : arr[i];
            sumSoFar += arr[i];
            if (sumSoFar <= 0) {
                sumSoFar = 0;
                tempIndex = i + 1;
            }
            if (maxSum < sumSoFar) {
                maxSum = sumSoFar;
                if (tempIndex != startIndex) startIndex = tempIndex;
                endIndex = i;
            }
        }
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.println(arr[i]);
        }
        if (maxSum == 0 && lowestNeg != 0) maxSum = lowestNeg;
        return maxSum;
    }

    public static boolean isArrDividedIntoPairsDivByK(int[] arr, int k) {
        if (arr.length % 2 == 1) return false;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int temp;
        for (int i = 0; i < arr.length; i++) {
            temp = arr[i] % k;
            if (hashMap.containsKey(temp)) {
                hashMap.replace(temp, hashMap.get(temp) + 1);
            } else {
                hashMap.put(temp, 1);
            }
        }
        for (int i = 0; i <= k / 2; i++) {
            if (hashMap.containsKey(i)) {
                int tmp = (k - i) % k;
                if (i == tmp) {
                    if (hashMap.get(i) % 2 != 0) return false;
                } else {
                    int start = hashMap.get(i);
                    if (!hashMap.containsKey(tmp)) return false;
                    int end = hashMap.get(tmp);
                    if (start != end) return false;
                }
            }
        }
        return true;
    }

    public static int[] longestSubArrayDivByK(int[] arr, int k) {
        int startIndex = -1;
        int endIndex = 0;
        int sumSoFar = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] modArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            modArr[i] = arr[i] % k;
        }
        for (int i = 0; i < arr.length; i++) {
            sumSoFar += modArr[i];
            int temp = sumSoFar % k;
            if (temp == 0) {
                if (!hashMap.containsKey(temp)) {
                    hashMap.put(0, i);
                }
                startIndex = 0;
                endIndex = i;
            } else {
                if (!hashMap.containsKey(temp)) {
                    hashMap.put(temp, i);
                } else {
                    int index = hashMap.get(temp);
                    if (i - index > endIndex - startIndex) {
                        startIndex = index + 1;
                        endIndex = i;
                    }
                }
            }
        }

        int[] indexArr = {startIndex, endIndex};
        return indexArr;
    }

    public static int[] subArrayWithGivenSum(int[] arr, int k) {
        int maxSumSoFar = 0;
        int startIndex = -1;
        int endIndex = 0;
        HashMap<Integer, Integer> sumToIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            maxSumSoFar += arr[i];
            if (maxSumSoFar == k) {
                startIndex = 0;
                endIndex = i;
            } else if (sumToIndexMap.containsKey(maxSumSoFar - k)) {
                int tempIndex = sumToIndexMap.get(maxSumSoFar - k);
                if (i - tempIndex > endIndex - startIndex) {
                    startIndex = sumToIndexMap.get(maxSumSoFar - k) + 1;
                    endIndex = i;
                }
            }
            sumToIndexMap.put(maxSumSoFar, i);
        }
        int[] a = {startIndex, endIndex};
        return a;
    }

    public static int subArrayWithGivenSumCount(int[] arr, int k) {
        int maxSumSoFar = 0;
        int count = 0;
        HashMap<Integer, Integer> sumCountMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            maxSumSoFar += arr[i];
            if (maxSumSoFar == k) {
                count++;
            }
            if (sumCountMap.containsKey(maxSumSoFar - k)) {
                count = sumCountMap.get(maxSumSoFar - k) + count;
            }
            if (sumCountMap.containsKey(maxSumSoFar)) {
                sumCountMap.put(maxSumSoFar, sumCountMap.get(maxSumSoFar) + 1);
            } else {
                sumCountMap.put(maxSumSoFar, 1);

            }
        }
        return count;
    }

    public static void printAllSubArraysWithGivenSumZero(int[] arr) {
        int currentSum = 0;
        HashMap<Integer, ArrayList<Integer>> pairMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (currentSum == 0) {
                System.out.println(0 + " to " + i);
            }
            if (pairMap.containsKey(currentSum)) {
                ArrayList<Integer> list = pairMap.get(currentSum);
                for (int temp : list) {
                    System.out.println((temp + 1) + " to " + i);
                }
                list.add(i);
            } else {
                pairMap.put(currentSum, new ArrayList<>(Arrays.asList(i)));
            }
        }
    }

    public static void printAllSubArraysWithGivenSum(int[] arr, int k) {
        int currentSum = 0;
        HashMap<Integer, ArrayList<Integer>> pairMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            if (currentSum == k) {
                System.out.println(0 + " to " + i);
            }
            if (pairMap.containsKey(currentSum - k)) {
                ArrayList<Integer> list = pairMap.get(currentSum - k);
                for (int temp : list) {
                    System.out.println((temp + 1) + " to " + i);
                }
                list.add(i);
            } else {
                pairMap.put(currentSum, new ArrayList<>(Arrays.asList(i)));
            }
        }
    }

    public static int subArrayNoPairSum(int[] arr, int k) {
        int startIndex = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            int temp = startIndex;
            int iterCount = 0;
            while (temp < i) {
                if ((arr[i] + arr[temp]) % k == 0) {
                    startIndex = iterCount + 1;
                    iterCount = 0;
                } else {
                    ++iterCount;
                }
                ++temp;
            }
            if (iterCount + 1 > max) {
                max = iterCount + 1;
            }
        }
        return max;
    }

    public static int[] subArrayWithLessThanKDistinctElem(int[] arr, int k) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        int tempStart = 0;
        int tempEnd = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            updateValueToMap(indexMap, arr[i]);
            if (indexMap.size() <= k) {
                tempEnd = i;
                if (tempEnd - tempStart + 1 > max) {
                    startIndex = tempStart;
                    endIndex = tempEnd;
                    max = endIndex - startIndex + 1;
                }
            } else {
                while (indexMap.size() > k) {
                    indexMap.replace(arr[tempStart], indexMap.get(arr[tempStart]) - 1);
                    if (indexMap.get(arr[tempStart]) == 0) indexMap.remove(arr[tempStart]);
                    ++tempStart;
                }
            }
        }
        int[] output = new int[max];
        for (int i = startIndex, j = 0; i <= endIndex; i++, j++) {
            output[j] = arr[i];
        }
        return output;
    }

    public static void updateValueToMap(HashMap<Integer, Integer> indexMap, int value) {
        if (indexMap.containsKey(value)) {
            indexMap.replace(value, indexMap.get(value) + 1);
        } else {
            indexMap.put(value, 1);
        }
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

}
