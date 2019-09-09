package Algorithms;

public class DigPow {

    public static long digPow(int n, int p) {
        int i = 4;
        System.out.println(Math.pow(n / (int)Math.pow(10, i), p));
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(digPow(46288, 3));
    }
}
