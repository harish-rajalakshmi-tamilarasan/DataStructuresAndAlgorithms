package DataStructures.DS;

/**
 * @author Harish T
 */
public class MultiLevelSinglyLinkedList {

    static class Node {
        Node(int elem) {
            this.elem = elem;
        }

        void setNext(Node n) {
            this.next = n;
        }

        Node next;
        int elem;
        MultiLevelSinglyLinkedList down;
    }

    public int size;
    public Node root;
    public Node tail;

    public void addElem(int a) {
        if (root == null) {
            root = new Node(a);
            tail = root;
        } else {
            tail.next = new Node(a);
            tail = tail.next;
        }
        ++size;
    }

    public void addElemList(int[] a) {
        for (int temp : a) {
            addElem(temp);
        }
    }


    public static void setDown(Node node, MultiLevelSinglyLinkedList list) {
        node.down = list;
    }

}
