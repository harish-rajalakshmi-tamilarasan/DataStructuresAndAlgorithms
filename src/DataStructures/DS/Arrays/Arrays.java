package DataStructures.DS.Arrays;

/**
 * @author Harish T
 */
public class Arrays {

    public static void main(String[] args) {
        System.out.println("Hello World");
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 9};
    }


    public static int modifiedBinarySearchToFindMinMissingNum(int[] arr, int start, int end) {
        if (start == end) {
            if (arr[start] == start) return -1;
            return arr[start] - 1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] == mid) {
            return modifiedBinarySearchToFindMinMissingNum(arr, mid + 1, end);
        }
        if (arr[mid] > mid) {
            return modifiedBinarySearchToFindMinMissingNum(arr, start, mid);
        }
        return -1;
    }

    public static void findInSortedRotatedArr() {
        int[] arr = {7, 8, 9, 1, 2, 3, 4, 5, 6};
        int num = 2;
        int pivot = findPivot(arr, 0, arr.length - 1);
        if (arr[0] > num) System.out.println(binarySearch(arr, pivot + 1, arr.length - 1, num));
        else if (arr[0] < num) System.out.println(binarySearch(arr, 0, pivot, num));
        else System.out.println(arr[0]);
    }

    public static int findPivot(int[] arr, int start, int end) {
        if (end - start == 1) return end;
        int mid = (start - end) / 2 + end;
        if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
            return mid - 1;
        }
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid;
        }
        if (arr[mid] < arr[0]) {
            return findPivot(arr, start, mid - 1);
        }
        return findPivot(arr, mid + 1, end);
    }

    public static int binarySearch(int[] arr, int start, int end, int num) {
        int mid = (start - end) / 2 + end;
        if (arr[mid] == num) return mid;
        if (arr[mid] > num) return binarySearch(arr, start, mid - 1, num);
        return binarySearch(arr, mid + 1, end, num);
    }

    public static boolean findPairSum(int[] arr, int start, int end, int sum) {
        if (start == end) return false;
        if (sum == arr[start] + arr[end]) return true;
        if (sum < arr[start] + arr[end]) return findPairSum(arr, start, (arr.length + end - 1) % arr.length, sum);
        return findPairSum(arr, (start + 1) % arr.length, end, sum);
    }

    public static void lexMin(String str) {
        char[] chars = str.toCharArray();
        int strLen = str.length();
        int lex = 0;
        for (int i = 0; i < strLen; i++) {
            int temp = 0;
            for (int j = 0; j < strLen; j++) {
                int offset = (i + j + 1) % strLen;
                int mainOffset = (lex + j) % strLen;
                if (chars[offset] > chars[mainOffset]) {
                    break;
                } else if (chars[offset] < chars[mainOffset]) {
                    lex = i + 1;
                    break;
                }
            }

        }
        for (int temp = 0; temp < strLen; temp++) {
            System.out.println(chars[lex % strLen]);
            ++lex;
        }
    }

    public static int divByFour(int[] arr) {
        int count = 0;
        if (arr.length < 2) return arr[0] % 4 == 0 ? 1 : 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if ((arr[(arr.length + i - 1) % arr.length] * 10 + arr[i]) % 4 == 0) ++count;
        }
        return count;
    }

    public static int divByEight(int[] arr) {
        int count = 0;
        if (arr.length == 0) return 0;
        if (arr.length == 1) return arr[0] % 8 == 0 ? 1 : 0;
        if (arr.length == 2) {
            count = arr[1] * 10 + arr[0] % 8 == 0 ? 1 : 0;
            count = count + (arr[0] * 10 + arr[1] % 8 == 0 ? 1 : 0);
            return count;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if ((arr[(arr.length + i - 2) % arr.length] * 100 + arr[(arr.length + i - 1) % arr.length] * 10 + arr[i]) % 4 == 0)
                ++count;
        }
        return count;
    }

    public static boolean rotatedStr(String a, String b) {
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        if (a.length() != b.length()) return false;
        if (a.length() <= 2) return a.equals(b);
        for (int i = 0; i < a.length(); i++) {
            if (bChar[i] != aChar[(i + 2) % a.length()]) {
                return false;
            }
        }
        return true;
    }

    //search
    public static int[] seg(int[] a) {
        java.util.Arrays.sort(a);
        int len = a.length;
        int[] arr = new int[len];
        for (int i = 0; i < len / 2; i++) {
            arr[a.length - 1 - (i * 2)] = a[i];
            arr[a.length - 2 - (i * 2)] = a[len - 1 - i];
        }
        if (len % 2 != 0) arr[0] = a[len / 2];
        return arr;
    }

    public static int maxSubArray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        int startIndex = 0;
        int endIndex = -1;
        int minIndex = -1;
        int temp = startIndex;
        int max = Integer.MIN_VALUE;
        if (arr.length == 0) return 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                minIndex = i;
            }
            currSum = currSum + arr[i];
            if (currSum <= 0) {
                currSum = 0;
                temp = i + 1;
            } else {
                if (currSum > maxSum) {
                    startIndex = temp;

                    endIndex = i;
                    maxSum = currSum;
                }
            }
        }
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.println(arr[i]);
        }
        maxSum = maxSum > max ? maxSum : max;
        return maxSum;
    }


    public static void minDistanceBetweenTwo(int[] arr, int x, int y) {
        int a = -1, b = -1;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                a = i;
                if (b != -1) max = Math.min(max, Math.abs(a - b));

            } else if (arr[i] == y) {
                b = i;
                if (a != -1) max = Math.min(max, Math.abs(a - b));
            }
        }
        System.out.println(max);
    }

}
