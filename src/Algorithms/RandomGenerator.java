package Algorithms;

import java.security.SecureRandom;

/**
 * @author Harish T
 */
public class RandomGenerator {
    public static void main(String[] args) {
        SecureRandom sec=new SecureRandom();
        System.out.println(sec.nextInt(900000));
    }
}
