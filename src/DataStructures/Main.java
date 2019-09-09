package DataStructures;

import java.util.LinkedList;

/**
 * @author Harish T
 */
public class Main {
    public static void main(String[] args) {
        CircularSingleLinkedList<Integer> circ=new CircularSingleLinkedList<>();
        circ.addLast(12);
        circ.addLast(123);
        System.out.println(circ.getElement(circ.getTail()));
        circ.removeFirst();
        System.out.println(circ.getElement(circ.getTail()));
    }
}
