package DataStructures.DS;

/**
 * @author Harish T
 */
public class DoubleLinkedListStack {
    class Node {
        Node prev;
        Node next;
        int data;
    }

    private int size;
    private Node mid;
    private Node tail;

    void push(int element) {
        if (size == 0) {
            tail = new Node();
            tail.data = element;
            mid = tail;
            ++size;
            return;
        }
        tail.next = new Node();
        Node temp = tail;
        tail = tail.next;
        tail.prev = temp;
        tail.data=element;
        ++size;
        if (size % 2 == 1) {
            mid = mid.next;
        }
    }

    int pop() throws Exception {
        if (size == 0) {
            throw new Exception("StackDS is Empty");
        }
        Node node = tail;
        tail = tail.prev;
        --size;
        if (size % 2 == 0) {
            mid = mid.prev;
        }
        return node.data;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int mid() throws Exception {
        if (size == 0) {
            throw new Exception("StackDS is Empty");
        }
        return mid.data;
    }

    int delMid() throws Exception {
        if (size == 0) {
            throw new Exception("StackDS is Empty");
        }
        int data=mid.data;
        if (size % 2 == 0) {
            Node temp = mid.prev;
            mid = mid.next;
            temp.next = mid;
            mid.prev = temp;
        } else {
            Node temp = mid.next;
            mid = mid.prev;
            temp.prev = mid;
            mid.next = temp;
        }
        return data;
    }
}
