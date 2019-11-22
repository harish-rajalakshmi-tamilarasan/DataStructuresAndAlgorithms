package DataStructures.DS.DynamicProgramming;

import java.util.HashMap;


public class DPBasics {
    public static void main(String[] args) {
        boolean[][] arr={{true,true,true,true},{true,true,true,true},{false,true,true,true}};
        System.out.println(squareSubMatrixDP(arr));
    }

    public static int fibonacci(int n) {
        int last = 1;
        int penultimate = 0;
        if (n < 2) return n;
        for (int i = 2; i <= n; i++) {
            int temp = last + penultimate;
            penultimate = last;
            last = temp;
        }
        return last;
    }

    private static int[] cache;

    public static int fibonacciDP(int n) {
        if (n < 2) return n;
        cache = new int[n + 1];
        for (int i = 2; i < cache.length; i++) {
            cache[i] = -1;
        }
        cache[0] = 0;
        cache[1] = 1;
        return fibonacciTopDown(n);
    }

    public static int fibonacciTopDown(int n) {
        if (cache[n] >= 0) return cache[n];
        cache[n] = fibonacciTopDown(n - 1) + fibonacciTopDown(n - 2);
        return cache[n];
    }

    //Top down approach
    public static HashMap<Integer, Integer> totalMap = new HashMap<>();

    public static int coinChange(int[] coins, int total) {
        if (total <= 0) return 0;
        if (totalMap.containsKey(total)) return totalMap.get(total);
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            //  System.out.println(total);
            if (total - coin >= 0) {
                int totalCoins = coinChange(coins, total - coin);
                min = Math.min(totalCoins, min);
            }
        }
        totalMap.put(total, min + 1);
        return min + 1;
    }

    //BottomUp Approach
    public static int coinChangeBottomUp(int[] coins, int total) {
        int[] cache = new int[total + 1];
        for (int i = 1; i < total + 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    int minChange = cache[i - coin] + 1;
                    min = Math.min(min, minChange);
                }
            }
            cache[i] = min;
        }
        return cache[total];
    }

    //Square SubMatrix
    public static int squareSubMatrix(boolean[][] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                max = Math.max(max, squareSubMatrix(array, i, j));
            }
        }
        return max;
    }

    public static int squareSubMatrix(boolean[][] array, int i, int j) {
        if (i >= array.length || j >= array.length) return 0;
        if (array[i][j]) {
            return 1 + Math.min(squareSubMatrix(array, i, j + 1), Math.min(squareSubMatrix(array, i + 1, j), squareSubMatrix(array, i + 1, j + 1)));
        }
        return 0;
    }

    //Square SubMatrix DP
    public static int squareSubMatrixDP(boolean[][] array){
        int[][] cache=new int[array.length][array[0].length];
        int max=0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                if(i==0||j==0){
                    cache[i][j]=array[i][j]?1:0;
                }else if(array[i][j]){
                    cache[i][j]= 1+Math.min(cache[i-1][j],Math.min(cache[i][j-1],cache[i-1][j-1]));
                }
                max=cache[i][j]>max?cache[i][j]:max;
            }
        }
        return max;
    }


}