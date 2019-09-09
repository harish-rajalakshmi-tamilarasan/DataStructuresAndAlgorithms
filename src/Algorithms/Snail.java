package Algorithms;

import java.util.ArrayList;

/**
 * @author Harish T
 */
public class Snail {

    public static void main(String[] args) {
        int a[][] = {{1, 2, 3, 4,100}, {5, 6, 7, 8,101}, {9, 10, 11, 12,102}, {13, 14, 15, 16,103},{1000,1001,1002,1003,1004}};
        int b[][]={{}};
        System.out.println(b[0].length);
                System.out.println(b.length);
        ArrayList<Integer> arrayList = new ArrayList<>();
       // snail(a, 0, arrayList);
      //  for (int temp : arrayList) {
        //    System.out.println(temp);
        //}
    }

    public static void snail(int[][] array, int index, ArrayList<Integer> a) {
        if (index<=(array.length/2)){
            int n = array.length - 1 - index;
            for (int i = index; i <= n; i++) {
                a.add(array[index][i]);
            }
            for (int i = index + 1; i <= n; i++) {
                a.add(array[i][n]);
            }
            for (int i = n - 1; i >= index; i--) {
                a.add(array[n][i]);
            }
            for (int i = n - 1; i > index; i--) {
                a.add(array[i][index]);
            }
            snail(array, index + 1, a);
        }
    }
}
