package Algorithms;

/**
 * @author Harish T
 */
public class Kata1 {
    public static void main(String[] args) {
        System.out.println(nextSmaller(1453647432));
    }

    public static long nextSmaller(long n) {
        String number = String.valueOf(n);
        char[] chars = new char[number.length()];
        number.getChars(0, chars.length, chars, 0);
        int index = valleyIndex(chars, chars.length - 1);
        if (index == -1) {
            return -1;
        }
        char temp = chars[index];
        chars[index] = chars[index - 1];
        chars[index - 1] = temp;
        return Long.valueOf(new String(chars));
    }

    public static int valleyIndex(char[] chars, int i) {
        if (i == 0) {
            return -1;
        }
        if (chars[i - 1] > chars[i]) {
            return i;
        }
        return valleyIndex(chars, i - 1);
    }
}
