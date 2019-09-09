package Algorithms;

import java.util.Stack;

public class Main{
    public static void main(String[] args) {
        Stack<Integer> t=new Stack<>();
        t.push(123);
        t.push(1234);
        System.out.println(t.peek());
        System.out.println(t.search(123));

        System.out.println(t.pop());
    }
}