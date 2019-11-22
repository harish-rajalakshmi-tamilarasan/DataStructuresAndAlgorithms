package DataStructures.DS.LinkedList;

/**
 * @author Harish T
 */
public class CircularLinkList {
    private static class Node {
        Node next;
        int data;

        Node() {
        }

        Node(int data) {
            this.data = data;
        }
    }

    public Node tail;

    public void addArr(int[] arr) {
        Node start = null;
        Node curr = null;
        for (int temp : arr) {
            if (start == null) {
                curr = new Node(temp);
                start = curr;
            } else {
                curr.next = new Node(temp);
                curr = curr.next;
            }
        }
        tail = curr;
        tail.next = start;
    }

    public void insertAtEnd(int data) {
        Node temp = tail.next;
        tail.next = new Node(data);
        tail.next.next = temp;
        tail = tail.next;
    }

}

