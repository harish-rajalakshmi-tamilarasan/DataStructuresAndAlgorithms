package Algorithms;

import java.math.BigDecimal;

/**
 * @author Harish T
 */
public class Mathematical {
    public static void main(String[] args) throws Exception {
        BigDecimal bigDecimal = new BigDecimal(0.5);
        BigDecimal bigDecimal1 = new BigDecimal(0.15);
        System.out.println(gcdDecimal(bigDecimal, bigDecimal1));
    }

    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) return 0;
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static BigDecimal gcdDecimal(BigDecimal a, BigDecimal b) {
        if (a.equals(b)) return a;
        if (a.compareTo(BigDecimal.ZERO) == 0) return b;
        if (b.compareTo(BigDecimal.ZERO) == 0) return a;
        if (a.compareTo(b) > 0) return gcdDecimal(a.subtract(b), b);
        return gcdDecimal(b.subtract(a), a);
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return a * b / gcd(a, b);
    }

    public static int lcm(int[] array) {
        int lastLcm = array[0];
        for (int i = 1; i < array.length; i++) {
            lastLcm = lastLcm * array[i] / gcd(lastLcm, array[i]);
        }
        return lastLcm;
    }

    public static int gcd(int[] array) {
        int lastGcd = array[0];
        for (int i = 1; i < array.length; i++) {
            lastGcd = gcd(array[i], lastGcd);
        }
        return lastGcd;
    }

    public static int[] denNum(int[] numerator, int[] denominator) throws Exception {
        if (numerator.length != denominator.length) throw new Exception("Invalid Input");
        int num = 1, den = 1;
        for (int temp : numerator) {
            num *= temp;
        }
        for (int temp : denominator) {
            den *= temp;
        }
        int gcd = gcd(num, den);
        int[] output = new int[2];
        output[0] = num / gcd;
        output[1] = den / gcd;
        return output;
    }

    public static int gcdByBitOperations(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a == b) return a;
        boolean isAEven = (a & 1) == 0;
        boolean isBEven = (b & 1) == 0;
        if (isAEven && isBEven) {
            a = a >> 1;
            b = b >> 1;
            return gcdByBitOperations(a, b);
        }
        if (isAEven) {
            a = a >> 1;
            return gcdByBitOperations(a, b);
        }
        if (isBEven) {
            b = b >> 1;
            return gcdByBitOperations(a, b);
        }
        return gcdByBitOperations(Math.abs(a - b) >> 1, Math.min(a, b));
    }

}
