package Algorithms;

/**
 * @author Harish T
 */
public class GCD {

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

    public static void main(String[] args) {
        System.out.println(gcd(414,662));
    }
}
