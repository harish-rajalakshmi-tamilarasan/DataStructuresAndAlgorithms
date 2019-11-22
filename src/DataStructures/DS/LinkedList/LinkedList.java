package DataStructures.DS.LinkedList;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Harish T
 */
public class LinkedList {
    public int size = 0;
    public Node root = null;
    public Node tail = null;

    public class Node {
        public int element;
        public Node next;

        private Node(int element, Node nextNode) {
            this.element = element;
            this.next = nextNode;
        }

        private Node(int element) {
            this.element = element;
        }
    }

    public void insert(int element) {
        if (size == 0) {
            root = new Node(element);
            tail = root;
            ++size;
        } else {
            tail.next = new Node(element);
            tail = tail.next;
            ++size;
        }
    }

    public static void traversal(LinkedList linkedList) {
        int[] array = new int[linkedList.size];
        Node node = linkedList.root;
        int i = 0;
        while (node != null) {
            array[i] = node.element;
            node = node.next;
            ++i;
        }
        for (int temp : array) {
            System.out.println(temp);
        }
    }

    public int getTail() {
        Node node = root;
        Node tail = null;
        while (node != null) {
            tail = node;
            node = node.next;
        }
        return tail.element;
    }

    public void buildList(int[] arr) {
        int size = arr.length;
        Node node = null;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                root = new Node(arr[0]);
                node = root;
                tail = root;
            } else {
                node.next = new Node(arr[i]);
                node = node.next;
            }
        }
        this.size = size;
    }

    public boolean deleteKey(int element) {
        boolean isDeleted = false;
        if (element == root.element) {
            root = root.next;
        }
        Node node = root;
        while (node.next != null) {
            if (node.next.element == element) {
                node.next = node.next.next;
                --size;
                isDeleted = true;
                break;
            }
            node = node.next;
        }
        return isDeleted;
    }

    public boolean deleteAtPosition(int index) {
        //  index = index - 1;
        if (size <= index) return false;
        if (index == 0) {
            Node next = root;
            root = root.next;
            next = null;
            --size;
            return true;
        }
        Node node = root;
        for (int i = 0; i < index; i++) {
            node = node.next;
            --index;
        }
        node.next = node.next.next;
        --size;
        return true;
    }

    public int getLength() {
        Node node = root;
        int length = 0;
        while (node != null) {
            ++length;
            node = node.next;
        }
        return length;
    }

    public int getLen() {
        return getLengthRecursive(root, -1);

    }

    public int getLengthRecursive(Node node, int length) {
        if (node == null) return length + 1;
        return getLengthRecursive(node.next, length + 1);
    }

    public int getPosition(int element) {
        Node node = root;
        int index = -1;
        while (node != null) {
            ++index;
            if (node.element == element) break;
            node = node.next;
        }
        return index;
    }

    public int getPos(int element) {
        return getPositionRecursive(root, element, 0);
    }

    public int getPositionRecursive(Node node, int element, int index) {
        if (node == null) return -1;
        if (element == node.element) {
            return index;
        }
        return getPositionRecursive(node.next, element, index + 1);
    }

    public int getNthNode(int n) {
        Node node = root;
        int index = -1;
        while (node != null) {
            ++index;
            if (index == n) return node.element;
            node = node.next;
        }
        return index;
    }

    public int getNthFromEnd(int n) {
        if (n >= size) return -1;
        Node firstNode = root;
        Node secondNode = root;
        while (n > 0) {
            firstNode = firstNode.next;
            --n;
        }
        while (firstNode.next != null) {
            secondNode = secondNode.next;
            firstNode = firstNode.next;
        }
        return secondNode.element;
    }

    public Node getMiddle() {
        Node firstNode = root;
        Node secondNode = root;
        while (secondNode.next != null && secondNode.next.next != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next.next;
        }
        return firstNode;
    }

    public int noOfRecurrence(int n) {
        Node node = root;
        int i = 0;
        while (node != null) {
            if (node.element == n) {
                ++i;
            }
            node = node.next;
        }
        return i;
    }

    public boolean isLoopExists() {
        Node firstNode = root;
        Node secondNode = root;
        while (secondNode != null && secondNode.next != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next.next;
            if (firstNode == secondNode) {
                return true;
            }
        }
        return false;
    }

    public void setLoop() {
        tail.next = root.next.next.next;
    }

    public int getLoopLength() {
        int i = 0;
        Node firstNode = root;
        Node secondNode = root;
        while (secondNode != null && secondNode.next != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next.next;
            if (firstNode == secondNode) {
                break;
            }
        }
        firstNode = root;
        while (true) {
            ++i;
            firstNode = firstNode.next;
            if (firstNode == secondNode) {
                break;
            }
        }
        return i;
    }

    public void reverse() {
        Node node = root;
        if (node == null) return;
        while (node.next != null) {
            Node temp = node.next.next;
            node.next.next = root;
            root = node.next;
            node.next = temp;
        }
        tail = node;
    }

    public boolean isPalindrome() {
        //   boolean ispalindrome = true;
        Node mid = getMiddle();
        Node node = mid.next;
        Node start = mid.next;
        while (node.next != null) {
            Node temp = node.next.next;
            node.next.next = start;
            start = node.next;
            node.next = temp;
        }
        mid.next = start;
        Node first = root;
        while (start != null) {
            if (first.element == start.element) {
                start = start.next;
                first = first.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public void removeDuplicatesSorted() {
        Node node = root;
        while (node.next != null) {
            if (node.next.element == node.element) {
                node.next = node.next.next;
                --size;
            } else {
                node = node.next;
            }
        }
    }

    public void removeDuplicates() {
        Node node = root;
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(node.element);
        while (node.next != null) {
            if (hashSet.contains(node.next.element)) {
                node.next = node.next.next;
                --size;
            } else {
                hashSet.add(node.next.element);
                node = node.next;
            }
        }
    }

    public void pairwiseSwap() {
        Node node = root;
        Node prev = null;
        Node temp = null;
        while (node != null && node.next != null) {
            if (prev == null) {
                temp = node.next.next;
                root = node.next;
                root.next = node;
                root.next.next = temp;
                prev = root.next;
                node = temp;
            } else {
                temp = node.next.next;
                prev.next = node.next;
                node.next.next = node;
                node.next = temp;
                prev = node;
                node = temp;
            }
        }
    }

    public void printReverseCreator() {
        printReverse(root);
    }

    public void printReverse(Node node) {
        if (node == null) {
            return;
        }
        printReverse(node.next);
        System.out.println(node.element);
    }

    public void getIntersection(LinkedList a, LinkedList b) {
        Node first = a.root;
        Node second = b.root;
        LinkedList list = new LinkedList();
        list.root = null;
        Node node = null;
        while (first != null && second != null) {
            if (first.element == second.element) {
                if (list.root == null) {
                    list.root = new Node(first.element);
                    node = list.root;
                    list.size = 1;
                } else {
                    node.next = new Node(first.element);
                    node = node.next;
                    ++list.size;
                }
                first = first.next;
                second = second.next;
            } else if (first.element < second.element) {
                first = first.next;
            } else {
                second = second.next;
            }
        }
        traversal(list);
    }

    public void deleteAlternate() {
        Node node = root;
        while (node != null && node.next != null) {
            Node temp = node.next;
            node.next = temp.next;
            node = node.next;
            temp.next = null;
            --size;
        }
    }

    public void rotateKElemAlternate(int k) {
        Node node = root;
        Node prev = null;
        Node start = root;
        int i = 1;
        while (node != null && node.next != null) {
            Node temp = node.next;
            node.next = temp.next;
            temp.next = start;
            start = temp;
            if (prev != null) {
                prev.next = start;
            } else {
                root = temp;
            }
            ++i;
            if (i == k) {
                prev = node;
                start = node.next;
                node = start;
                i = 1;
            }
        }
    }

    public static Node getMiddle(Node node) {
        Node firstNode = node;
        Node secondNode = node;
        while (secondNode.next != null && secondNode.next.next != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next.next;
        }
        return firstNode;
    }

    public static LinkedList mergeUtil(LinkedList a) {
        LinkedList list = new LinkedList();
        list.root = mergeSort(a.root);
        list.size = a.size;
        return list;
    }

    public static Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node mid = getMiddle(node);
        Node midNext = mid.next;
        mid.next = null;
        return merge(mergeSort(node), mergeSort(midNext));
    }

    public static Node merge(Node start, Node end) {
        Node node = null;
        if (start == null) {
            return end;
        }
        if (end == null) {
            return start;
        }
        if (start.element < end.element) {
            node = start;
            node.next = merge(node.next, end);
        } else {
            node = end;
            node.next = merge(start, end.next);
        }
        return node;
    }


    public Node reverseMerge(LinkedList listA, LinkedList listB) {
        Node aStart = listA.root;
        Node bStart = listB.root;
        Node temp = null;
        Node node = null;
        while (aStart != null && bStart != null) {
            if (node == null) {
                if (aStart.element <= bStart.element) {
                    node = new Node(aStart.element);
                    aStart = aStart.next;
                } else {
                    node = new Node(bStart.element);
                    bStart = bStart.next;
                }
            } else {
                if (aStart.element < bStart.element) {
                    temp = new Node(aStart.element);
                    aStart = aStart.next;
                } else {
                    temp = new Node(bStart.element);
                    bStart = bStart.next;
                }
                temp.next = node;
                node = temp;
            }
        }
        if (aStart != null) {
            while (aStart != null) {
                temp = new Node(aStart.element);
                aStart = aStart.next;
                temp.next = node;
                node = temp;
            }
        } else {
            while (bStart != null) {
                temp = new Node(bStart.element);
                bStart = bStart.next;
                temp.next = node;
                node = temp;
            }
        }
        Node t = node;
        while (t != null) {
            System.out.println(t.element);
            t = t.next;
        }
        return node;
    }

    public LinkedList addition(LinkedList a, LinkedList b) {
        int sum = 0;
        int aDigit = 0;
        int bDigit = 0;
        Node aNode = a.root;
        while (aNode != null) {
            aDigit = aDigit * 10 + aNode.element;
            aNode = aNode.next;
        }
        Node bNode = b.root;
        while (bNode != null) {
            bDigit = bDigit * 10 + bNode.element;
            bNode = bNode.next;
        }
        sum = aDigit + bDigit;
        int carry = 0;
        LinkedList list = new LinkedList();
        Node node = null;
        Node temp = null;
        while (sum > 0) {
            carry = sum % 10;
            sum = sum / 10;
            if (node == null) {
                node = new Node(carry);
            } else {
                temp = new Node(carry);
                temp.next = node;
                node = temp;
            }
        }
        list.root = node;
        return list;
    }

    public LinkedList subtraction(LinkedList a, LinkedList b) {
        int sum = 0;
        int aDigit = 0;
        int bDigit = 0;
        Node aNode = a.root;
        while (aNode != null) {
            aDigit = aDigit * 10 + aNode.element;
            aNode = aNode.next;
        }
        Node bNode = b.root;
        while (bNode != null) {
            bDigit = bDigit * 10 + bNode.element;
            bNode = bNode.next;
        }
        sum = aDigit > bDigit ? aDigit - bDigit : bDigit - aDigit;
        int carry = 0;
        LinkedList list = new LinkedList();
        Node node = null;
        Node temp = null;
        while (sum > 0) {
            carry = sum % 10;
            sum = sum / 10;
            if (node == null) {
                node = new Node(carry);
            } else {
                temp = new Node(carry);
                temp.next = node;
                node = temp;
            }
        }
        list.root = node;
        return list;
    }
}
