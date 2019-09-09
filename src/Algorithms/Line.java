package Algorithms;

/**
 * @author Harish T
 */
public class Line {

    public static String WhoIsNext(String[] names, long n) {
        int length = names.length;
        long power = (long) (Math.log10(n / length) / Math.log10(2));
        long lowerLim = (long) ((Math.pow(2, power)) * 5+5);
        long upperLim = (long) ((Math.pow(2, power + 1)) * 5+5);
        long diffLim = (upperLim - lowerLim) / length;
        if (n == lowerLim) return names[length - 1];

        for (int k = 1; k <= length; k++) {
            if (n <= (k * diffLim + lowerLim)) {
                return names[k - 1];
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String[] a = {"1", "323", "23", "rd", "32"};
        System.out.println(WhoIsNext(a, 10));
    }
}
