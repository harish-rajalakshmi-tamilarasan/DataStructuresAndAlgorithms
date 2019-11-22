package Algorithms.DP;

/**
 * @author Harish T
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        int[] coins = {3, 34, 4, 12, 5, 2};
        System.out.println(subsetSumOfkDp(coins, 9));
    }

    public static int coinChange(int[] coins, int total) {
        int[] cache = new int[total + 1];
        for (int i = 1; i <= total; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    if (cache[i - coin] != Integer.MAX_VALUE) {
                        min = Math.min(cache[i - coin] + 1, min);
                    } else min = Integer.MAX_VALUE;
                }
            }
            cache[i] = min;
        }
        return cache[total];
    }

    static int[][] dp;

    public static int getMaximumPath(int[][] arr, int[] initialPos) {
        dp = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length - 1; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            dp[i][arr[0].length - 1] = arr[i][arr[0].length - 1];
        }
        return getMaxWeight(arr, initialPos[0], initialPos[1], arr.length, arr[0].length);
    }

    public static int getMaxWeight(int[][] arr, int start, int end, int rowLen, int colLen) {
        if (start >= rowLen || end >= colLen) return 0;
        int lower = 0, upper = 0;
        if (end + 1 == colLen) {
            return arr[start][end];
        }
        if (start - 1 >= 0) {
            if (dp[start - 1][end + 1] == -1) {
                upper = getMaxWeight(arr, start - 1, end + 1, rowLen, colLen);
            } else upper = dp[start - 1][end + 1];
        }
        if (start + 1 < rowLen) {
            if (dp[start + 1][end + 1] == -1) {
                lower = getMaxWeight(arr, start + 1, end + 1, rowLen, colLen);
            } else {
                lower = dp[start + 1][end + 1];
            }
        }
        dp[start][end] = arr[start][end] + Math.max(lower, Math.max(upper, getMaxWeight(arr, start, end + 1, rowLen, colLen)));
        return dp[start][end];
    }

    public static int uglyNumbers(int n) {
        int[] dp = new int[n];
        int indexOf2 = 0;
        int indexOf3 = 0;
        int indexOf5 = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[indexOf2] * 2, Math.min(dp[indexOf3] * 3, dp[indexOf5] * 5));
            if (dp[i] == dp[indexOf2] * 2) {
                ++indexOf2;
            }
            if (dp[i] == dp[indexOf3] * 3) {
                ++indexOf3;
            }
            if (dp[i] == dp[indexOf5] * 5) {
                ++indexOf5;
            }
        }
        return dp[n - 1];
    }

    public static int fibonacci(int n) {
        int[] arr = new int[n];
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n - 1];
    }

    public static int nthCatalan(int n) {
        int fact = factorial(n);
        int doubleFact = fact;
        for (int i = 2 * n; i > n; i--) {
            doubleFact *= i;
        }
        int dividend = fact * (n + 1) * fact;
        return doubleFact / dividend;
    }

    private static int factorial(int n) {
        int tot = 1;
        for (int i = n; i > 0; i--) {
            tot *= i;
        }
        return tot;
    }

    public static int tiles2CrossN(int n) {
        if (n == 1 || n == 0) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int getNoOfFriendlyPairs(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = (i - 1) * dp[i - 2] + dp[i - 1];
        }
        return dp[n - 1];
    }

    public static boolean subsetSumOfK(int[] set, int k, int index) {
        if (k == 0) return true;
        if (k < 0 || index >= set.length) return false;
        return subsetSumOfK(set, k - set[index], index + 1) || subsetSumOfK(set, k, index + 1);
    }

    public static boolean subsetSumOfkDp(int[] set, int k) {
        boolean[][] dp = new boolean[set.length][k + 1];
        for (int temp = 0; temp < set.length; temp++) {
            dp[temp][0] = true;
        }
        for (int i = 0; i < set.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    if (j == set[i]) dp[i][j] = true;
                } else if (set[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i]];
                }
            }
        }
        return dp[set.length - 1][k];
    }

}
