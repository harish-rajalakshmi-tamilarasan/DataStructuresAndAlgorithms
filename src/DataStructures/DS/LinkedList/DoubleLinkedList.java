package DataStructures.DS.LinkedList;

import DataStructures.DS.Tree.BinaryTree;

/**
 * @author Harish T
 */
public class DoubleLinkedList {
    public static class Node {
        public int data;
        public Node prev;
        public Node next;

        Node(int data, Node prev) {
            this.data = data;
            this.prev = prev;
        }
    }

    public int size;
    public Node root;
    public Node tail;

    public Node addRoot(int data) {
        if (root == null) {
            root = new Node(data, null);
            tail = root;
            root.prev = null;
            return root;
        }
        Node temp = new Node(data, null);
        temp.next = root;
        root.prev = temp;
        root = temp;
        return root;
    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data, null);
            tail = root;
            root.prev = null;
            return;
        }
        Node temp = tail;
        Node curr = new Node(data, temp);
        curr.prev = tail;
        tail.next = curr;
        tail = curr;
    }

    public boolean delete(int data) {
        Node node = root;
        while (node.next != root) {
            if (node.next.data == data) {
                Node temp = node.next;
                node.next = temp.next;
                temp.next.prev = node;
                return true;
            }
            node = node.next;
        }
        if (root.data == data) {
            node.next = root.next;
            root.next.prev = node;
            root = root.next;
            return true;
        }
        return false;
    }

    public static void reverseList(DoubleLinkedList list) {
        Node curr = list.root;
        while (curr != null && curr.next != null) {
            Node temp = curr.next;
            curr.next = temp.next;
            if (temp.next != null) {
                temp.next.prev = curr;
            }
            temp.next = list.root;
            list.root.prev = temp;
            temp.prev = null;
            list.root = temp;
        }
    }

    public static void printAll(DoubleLinkedList list) {
        Node node = list.root;
        while (node != null) {
            System.out.println("Current Data=" + node.data);
            System.out.println("Next Data=" + node.next);
            System.out.println("Prev Data=" + node.prev);
            System.out.println("-----");
            node = node.next;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] a = {1, 2, 3, 4, 5};
        BinaryTree tree = new BinaryTree();
    }

    public static void findPairWithGivenSum(DoubleLinkedList list, int k) {
        Node start = list.root;
        Node end = list.tail;
        while (start != null && end != null && start != end && end.next != start) {
            int data = start.data + end.data;
            if (data == k) {
                System.out.println(start.data + "-" + end.data);
                start = start.next;
                end = end.prev;
            } else if (data > k) {
                end = end.prev;
            } else {
                start = start.next;
            }
        }
    }

    public static DoubleLinkedList treeToList(BinaryTree tree) {
        inorder(tree.root);
        return list;
    }

    private static DoubleLinkedList list = new DoubleLinkedList();
    private static DoubleLinkedList.Node listNode = list.root;
    private static DoubleLinkedList.Node prev = null;

    public static void inorder(BinaryTree.BinaryNode node) {
        if (node != null) {
            inorder(node.left);
            inorder(node.right);
            listNode = new Node(node.data, prev);
            prev = listNode;
            listNode = listNode.next;
        }
    }
}


