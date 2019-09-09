package Algorithms;

/**
 * @author Harish T
 */
public class TestMain {
    public static void main(String[] args) {
        Dogs a = new Dogs();
        Animals b = new Animals();
        a.inherited();
        a.seocnd();
        Dogs.test();
        b = a;
        b.test();
    }
}
