package DataStructures.DS.Tree;

import DataStructures.DS.LinkedList.LinkedList;

import java.util.HashSet;

/**
 * @author Harish T
 */
public class BST {
    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public Node root;
    public int size = 0;

    public void insert(int data) {
        if (root == null)
            root = new Node(data);
        else {
            insert(root, data);
        }
        ++size;
    }

    public Node insert(Node node, int data) {
        if (node != null) {
            if (data > node.data) {
                node.right = insert(node.right, data);
            } else {
                node.left = insert(node.left, data);
            }
            return node;
        }
        return new Node(data);
    }

    public Node delete(int data) {
        if (root.data == data) {
            root = delete(root, data);
            return root;
        }
        return delete(root, data);
    }

    private Node delete(Node node, int data) {
        if (node == null) return null;
        if (data > node.data) {
            node.right = delete(node.right, data);
        } else if (data < node.data) {
            node.left = delete(node.left, data);
        } else {
            --size;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            if (node.right.left == null) {
                node.right.left = node.left;
                return node.right;
            }
            node.data = minValue(node);
        }
        return node;
    }


    private int minValue(Node node) {
        Node temp = node;
        int min = node.left.data;
        node = node.right;
        while (node.left != null) {
            min = node.left.data;
            temp = node;
            node = node.left;
        }
        temp.left = node.right;
        return min;
    }

    public boolean hasKey(int data) {
        return hasKey(root, data);
    }

    private boolean hasKey(Node node, int key) {
        if (node == null) return false;
        if (node.data == key) return true;
        if (key < node.data) {
            return hasKey(node.left, key);
        }
        return hasKey(node.right, key);
    }

    public Node preTraversalToBst(int[] pre, int start, int end) {
        if (start > end || end >= pre.length) return null;
        Node node = new Node(pre[start]);
        int offset = start + 1;
        while (offset <= end) {
            if (pre[offset] > pre[start]) {
                break;
            }
            ++offset;
        }
        node.left = preTraversalToBst(pre, start + 1, offset - 1);
        node.right = preTraversalToBst(pre, offset, end);
        return node;
    }

    public Node levelTraversalTpBst(int[] level, int start, int min, int max, int length) {
        if (start > length - 1) return null;
        Node node = null;
        int offset = start;
        while (offset < length) {
            if (level[offset] < max && level[offset] > min) {
                node = new Node(level[offset]);
                break;
            }
            ++offset;
        }
        if (node == null) return null;
        node.left = levelTraversalTpBst(level, offset + 1, min, level[offset], length);
        node.right = levelTraversalTpBst(level, offset + 1, level[offset], max, length);
        return node;
    }


    public Node lca(Node node, int a, int b) {
        if (node == null) return null;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        if (node.data == a || node.data == b || (node.data > min && node.data < max)) return node;
        if (node.data > max) return lca(node.left, a, b);
        return lca(node.right, a, b);
    }

    public boolean isBst(Node node, int min, int max) {
        if (node == null) return true;
        return (node.data < max && node.data > min && isBst(node.left, min, node.data) && isBst(node.right, node.data, max));
    }

    public Node listToBst(HashSet<LinkedList.Node> set, LinkedList.Node node) {
        if (node == null) return null;
        LinkedList.Node mid = LinkedList.getMiddle(node);
        if (set.contains(mid)) return null;
        LinkedList.Node next = mid.next;
        mid.next = null;
        Node temp = new Node(mid.element);
        set.add(mid);
        temp.left = listToBst(set, node);
        temp.right = listToBst(set, next);
        return temp;
    }

    public static LinkedList.Node getMiddle(LinkedList.Node node) {
        LinkedList.Node firstNode = node;
        LinkedList.Node secondNode = node;
        LinkedList.Node last = null;
        while (secondNode.next != null && secondNode.next.next != null) {
            last = firstNode;
            firstNode = firstNode.next;
            secondNode = secondNode.next.next;
        }
        if (last != null) last.next = null;
        return firstNode;
    }

    public Node arrToBst(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.left = arrToBst(arr, start, mid - 1);
        node.right = arrToBst(arr, mid + 1, end);
        return node;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        LinkedList list=new LinkedList();
        list.buildList(arr);
        Node node = bst.listToBst(new HashSet<LinkedList.Node>(),list.root);
        System.out.println(node.data);
    }
    //Inorder Problems
    //Kth smallest,bst to heap, in
}
