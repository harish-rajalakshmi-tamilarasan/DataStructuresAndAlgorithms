package DataStructures.DS.Arrays;

import java.util.*;
import java.util.Arrays;

/**
 * @author Harish T
 */
public class ArrayProblems {
    public static void main(String[] args) throws Exception {
        int[] arr = {1, 3, 1, 7, 9, 2, 7, 9};
        zigZagArray(arr);
        for (int temp : arr) {
            System.out.println(temp);
        }
    }

    public static int[] sortBasedOnGivenOrder(int[] arr, int[] arrB) {
        //o(log(n))
        Arrays.sort(arr);
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        //o(n)
        for (int i = 0; i < arr.length; i++) {
            if (frequencyMap.containsKey(arr[i])) {
                frequencyMap.replace(arr[i], frequencyMap.get(arr[i]) + 1);
            } else {
                frequencyMap.put(arr[i], 1);
            }
        }
        int[] out = new int[arr.length];
        int index = 0;
        //o(m)
        for (int i = 0; i < arrB.length; i++) {
            if (frequencyMap.containsKey(arrB[i])) {
                int total = frequencyMap.get(arrB[i]);
                while (total > 0) {
                    out[index] = arrB[i];
                    ++index;
                    --total;
                }
                frequencyMap.remove(arrB[i]);
            }
        }
        //o(n)
        for (int j = 0; j < arr.length; j++) {
            if (frequencyMap.containsKey(arr[j])) {
                out[index] = arr[j];
                ++index;
            }
        }
        return out;
    }

    public static void factors(int[] arr) {

    }

    /**
     * Method should satisfy arr[i]=i and arr[i]=-1 where value i not found
     *
     * @param arr Array to handle
     */
    public static void mapIndexWithValue(int[] arr) {
        int a = arr.length;
        int[] newArr = new int[a];
        Arrays.sort(arr);
        for (int i = a - 1; i >= 0; i--) {
            if (arr[i] != -1) {
                newArr[arr[i]] = arr[i];
            } else {
                newArr[arr[i]] = -1;
            }
        }
    }

    public static int gcd(int a, int b) {
        if (a >= b) {
            int r = a % b;
            if (r == 0) {
                return b;
            }
            return gcd(b, r);
        }
        int r = b % a;
        if (r == 0) {
            return a;
        }
        return gcd(a, r);
    }

    public static void arrayJuggleRot(int[] arr, int n) throws Exception {
        if (n <= 0 || arr.length == 0) throw new Exception("Invalid input");
        int setLength = gcd(arr.length, n);
        for (int k = 0; k < n / setLength; k++) {
            for (int i = 0; i < setLength; i++) {
                int a = i;
                int temp = arr[i];
                while (true) {
                    if (a + setLength <= arr.length - 1) {
                        arr[a] = arr[a + setLength];
                        a += setLength;
                    } else {
                        arr[a] = temp;
                        break;
                    }

                }
            }
        }
    }

    public static void arrayReversalRot(int[] arr, int n) {
        reverseArr(arr, 0, n - 1);
        reverseArr(arr, n, arr.length - 1);
        reverseArr(arr, 0, arr.length - 1);
    }

    public static void reverseArr(int[] arr, int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            ++start;
            --end;
        }
    }

    public static int searchRotatedArr(int[] arr, int n) {
        int pivot = findPivot(arr, 0, arr.length - 1);

        if (n > arr[0]) {
            return binarySearch(arr, 0, pivot, n);
        }
        return binarySearch(arr, pivot + 1, arr.length - 1, n);
    }

    public static int binarySearch(int[] arr, int start, int end, int n) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == n) {
                return mid;
            }
            if (arr[mid] < n) {
                return binarySearch(arr, mid + 1, end, n);
            }
            return binarySearch(arr, start, mid - 1, n);
        }
        return -1;
    }

    public static int findPivot(int[] arr, int start, int end) {
        int mid = (start + end) / 2;
        if (start == end) {
            return start;
        }
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid;
        }
        if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
            return mid - 1;
        }
        if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
            if (arr[mid] > arr[0]) {
                return findPivot(arr, mid + 1, end);
            }
            return findPivot(arr, start, mid - 1);
        }
        return -1;
    }

    public static boolean findPair(int[] arr, int start, int end, int sum) {
        if (start == end) return false;
        int pairSum = arr[start] + arr[end];
        if (pairSum == sum) return true;
        if (pairSum > sum) return findPair(arr, start, ((arr.length + end - 1) % arr.length), sum);
        return findPair(arr, (start + 1) % arr.length, end, sum);
    }

    public static int findIndexAfterKRotations(int[] arr, int[] right, int[] left, int index) {
        int netRightRotation = 0;
        int netLeftRotation = 0;
        for (int temp : right) {
            netRightRotation += temp;
        }
        for (int temp : left) {
            netLeftRotation += temp;
        }
        int totalNetRotation = netLeftRotation % right.length - netRightRotation % left.length;
        return 0;
    }

    public static int[] hammingDis(int[] arr) {
        int[] doubleArr = new int[arr.length * 2];
        for (int i = 0; i < arr.length * 2; i++) {
            doubleArr[i] = arr[i % (arr.length)];
        }
        int maxHam = 0;
        int[] maxArr = new int[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            int count = 0;
            int[] tempArr = new int[arr.length];
            for (int j = i + 1, k = 0; k < arr.length; j++, k++) {
                if (arr[k] != doubleArr[j]) {
                    ++count;
                }
                tempArr[k] = doubleArr[j];
            }
            if (count > maxHam) {
                maxArr = tempArr;
                maxHam = count;
            }
        }
        return maxArr;
    }

    public static String rotateAndSwapString(String str, int a, int b) {
        return "";
    }

    public static void pushZeroAtEnd(int[] a) {

        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            if (a[i] == 0) {
                a[i] = a[j];
                a[j] = 0;
                --j;
            } else {
                i++;
            }
        }
    }

    public static void segregateNegPos(int[] a) {

        int i = 0;
        int j = a.length - 1;
        int temp = 0;
        while (i < j) {
            if (a[i] < 0) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                --j;
            } else {
                i++;
            }
        }
    }

    public static void rearrangeAofIequalsI(int[] a) {
        int i = 0;
        int temp = 0;
        while (i < a.length) {
            if (a[i] == -1 || a[i] == i) {
                ++i;
            } else {
                temp = a[a[i]];
                a[a[i]] = a[i];
                a[i] = temp;
            }
        }
    }

    public static void rearrangeAltPosNeg(int[] arr) {
        int i = 0;
        int j = 1;
        int len = arr.length;
        while (j < len && i < len) {
            while (arr[i] > 0) {
                i = i + 2;
                if (i >= len) break;
            }
            while (arr[j] < 0) {
                j = j + 2;
                if (j >= len) break;
            }
            if (i < len && j < len) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i = i + 2;
                j = j + 2;
            }
        }
        if (j < len) {
            int temp = j;
            j = (len % 2) == 1 ? len - 2 : len - 1;
            while (j > temp) {
                if (arr[j] < 0) {
                    int off = arr[temp];
                    arr[temp] = arr[j];
                    arr[j] = off;
                    temp += 2;
                } else {
                    j -= 2;
                }
            }
        }
        if (i < len) {
            int temp = i;
            i = (len % 2) == 1 ? len - 1 : len - 2;
            while (i > temp) {
                if (arr[i] > 0) {
                    int off = arr[temp];
                    arr[temp] = arr[i];
                    arr[i] = off;
                    temp += 2;
                } else {
                    i -= 2;
                }
            }
        }
    }

    public static void rearrangeSorting(int[] arr) {
        Arrays.sort(arr);

    }

    //max sum(i*arr[i) with rotations
    public static int maxSum(int[] a) {
        int sum = 0;
        int len = a.length;
        for (int i = 0; i < len; i++) {
            sum = sum + (i * a[i]);
        }
        int max = sum;
        for (int i = 0; i < len - 1; i++) {
            sum = sum + (3 * (a[i % len]));
            int temp = 0;
            for (int j = i + 1; j < (i + len); j++) {
                temp = temp - a[j % len];
            }
            sum = sum + temp;
            max = max > sum ? max : sum;
        }
        return max;
    }
//    public static void 7

    public static int[] segregateAtOddEvenPositionAlternateLength(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length / 2;
        int[] array = new int[arr.length];
        for (int i = 0; i < len; i++) {
            array[i * 2] = arr[i];
            array[i * 2 + 1] = arr[i + len];
        }
        if (arr.length % 2 == 1) {
            array[arr.length - 1] = arr[arr.length - 1];
        }
        return array;
    }

    public static void sepPosNegSegregate(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] > 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                --j;
            } else {
                i++;
            }
        }
    }

    public static void sepPosZeroSegregate(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                --j;
            } else {
                i++;
            }
        }
    }

    public static void greatestElemOnRightSide(int[] arr) {
        int length = arr.length - 1;
        Stack<Integer> stack = new Stack<>();
        stack.add(arr[length]);
        arr[length] = -1;
        for (int i = length - 1; i >= 0; i--) {
            if (arr[i] > stack.peek()) {
                int temp = arr[i];
                arr[i] = stack.pop();
                stack.add(temp);
            } else {
                arr[i] = stack.peek();
            }
        }
    }

    public static void suffleABCDtoACBD(int[] a) {
        for (int i = a.length / 2 - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int temp = a[i + j];
                a[i + j] = a[i + j + 1];
                a[i + j + 1] = temp;
            }
        }
        for (int temp : a) {
            System.out.println(temp);
        }
    }

    public static void randomShuffling(int[] a) {
        Random r = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int temp = r.nextInt(i);
            int t = a[temp];
            a[temp] = a[i];
            a[i] = t;
        }
    }

    public static void zigZagArray(int[] arr) {
        int length = arr.length;
        boolean flag = true;
                for (int i = 1; i < length ; i++) {
            if (flag && arr[i] < arr[i - 1]) {
                int temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
            }
            if (!flag && arr[i] > arr[i - 1]) {
                int temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
            }
            flag = !flag;
        }
    }

}

