package DataStructures.DS;

/**
 * @author Harish T
 */
public class StackLinkedList {
    private class Node {
        Node next;
        int data;
    }

    private int size = 0;
    private Node root;

    public void push(int data) {
        if (size == 0) {
            root = new Node();
            root.next = null;
            root.data = data;
        } else {
            Node node = new Node();
            node.data = data;
            node.next = root;
            root = node;
        }
        ++size;
    }

    public int pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("StackDS is empty");
        }
        int elem = root.data;
        root = root.next;
        --size;
        return elem;
    }

    public int top() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("StackDS is empty");
        }
        return root.data;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int getSize() {
        return size;
    }
}
