package DataStructures.DS.Arrays;

/**
 * @author Harish T
 */
public class MatrixArray {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotateBy180BySwap(a);
        for (int[] temp : a) {
            for (int tem : temp) {
                System.out.println(tem);
            }
        }
    }

    public static int[][] rotateBy90(int[][] a) {
        int[][] output = new int[a.length][a.length];
        int len = a.length - 1;
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= len; j++) {
                output[len - j][i] = a[i][j];
            }
        }
        return output;
    }

    public static int[][] rotateBy180(int[][] a) {
        int[][] output = new int[a.length][a.length];
        int len = a.length - 1;
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= len; j++) {
                output[len - i][len - j] = a[i][j];
            }
        }
        return output;
    }

    public static void transpose(int[][] a) {
        int len = a.length - 1;
        for (int i = 0; i <= len; i++) {
            for (int j = i; j <= len; j++) {
                if (i != j) {
                    int temp = a[i][j];
                    a[i][j] = a[j][i];
                    a[j][i] = temp;
                }
            }
        }
    }

    public static void rotateBy180BySwap(int[][] a) {
        int len = a.length - 1;
        for (int i = 0; i <= len / 2; i++) {
            for (int j = 0; j <= len; j++) {
                int temp = a[i][j];
                a[i][j] = a[len - i][len - j];
                a[len - i][len - j] = temp;
            }
        }
    }

    public static void rotateMatrixByOne(int[][] a){
        int temp=0;

    }

}
