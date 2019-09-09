package Algorithms;

public class PangramChecker {
    public static boolean check(String sentence) {
        //code
        if (sentence.length() < 26) {
            return false;
        }

        for (char i = 65, j = 97; i <= 90; i++, j++) {
            String a = String.valueOf(i);
            String b = String.valueOf(j);
            if (sentence.contains(String.valueOf(i)) || sentence.contains(String.valueOf(j))) {
                sentence = sentence.replaceAll(a, "");
                sentence = sentence.replaceAll(b, "");
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(check("The quick brown fox jumps over the lazy dog."));
    }
}