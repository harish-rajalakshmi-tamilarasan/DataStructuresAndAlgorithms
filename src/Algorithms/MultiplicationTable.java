package Algorithms;

/**
 * @author Harish T
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        int n = 10;
        for (int i = 1; i <=n; i++) {
            for (int j = 1, a = i; j <= i; j++, a--) {
                System.out.print(j + " * " + a + " = " + (j * a)+"\t");
            }
            System.out.println();
        }

    }
}

