package DataStructures.DS.LinkedList;

/**
 * @author Harish T
 */
public class RandomLinkedList {
    public static class Node {
        Node next;
        Node rand;
        int data;

        Node(Node next, Node rand, int data) {
            this.next = next;
            this.rand = rand;
            this.data = data;
        }

        Node(Node rand, int data) {
            this.rand = rand;
            this.data = data;
        }

        Node(int data) {
            this.data = data;
        }
    }

    public Node root;

    public void createNode() {
        root = new Node(null, 0);
        Node node = root;
        int[] a = {1, 2, 3, 4};
        for (int temp : a) {
            node.next = new Node(null, temp);
            node = node.next;
        }
        root.rand = root.next.next.next.next;
        root.next.rand = root.next.next.next;
        root.next.next.rand = root;
        root.next.next.next.rand = root.next;
        root.next.next.next.next.rand = root.next.next;

    }

    public static RandomLinkedList cloneNode(RandomLinkedList a) {
        if (a.root == null) return null;
        RandomLinkedList b = new RandomLinkedList();
        Node curr;
        Node currRoot = null;
        Node node = a.root;
        while (node != null) {
            curr = new Node(null, node.data);
            if (currRoot == null) {
                currRoot = curr;
            }
            curr.next = node.next;
            node.next = curr;
            node = curr.next;
        }
        b.root = currRoot;
        Node node1 = a.root;
        while (node1 != null) {
            node1.next.rand = node1.rand.next;
            node1 = node1.next.next;
        }
        while (currRoot.next != null) {
            currRoot.next = currRoot.next.next;
            currRoot = currRoot.next;
        }
        return b;
    }

    public static void main(String[] args) {
        RandomLinkedList a = new RandomLinkedList();
        a.createNode();
        cloneNode(a);
    }
}
