package DataStructures.DS.Tree;

import DataStructures.DS.LinkedList.LinkedList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Harish T
 */
public class BinarySearchTree {
    public class Node {
        public int data;
        public Node left;
        public Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this.data = data;
        }
    }

    public int size;
    public Node root;

    public void insert(int data) {
        ++size;
        Node node = insertToTree(root, data);
        if (root == null) root = node;
    }

    private Node insertToTree(Node node, int data) {
        if (node == null) {
            return new Node(data, null, null);
        }
        if (node.data > data) {
            node.left = insertToTree(node.left, data);
        } else {
            node.right = insertToTree(node.right, data);
        }
        return node;
    }

    public boolean hasKey(int data) {
        return hasKey(root, data);
    }

    private boolean hasKey(Node node, int data) {
        if (node == null) return false;
        if (node.data == data) return true;
        if (node.data > data) return hasKey(node.left, data);
        return hasKey(node.right, data);
    }

    public Node deleteKey(Node node, int data) {
        if (node == null) return null;
        if (data < node.data) {
            node.left = deleteKey(node.left, data);
        } else if (data > node.data) {
            node.right = deleteKey(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.data = minValue(node);
        }
        return node;
    }

    public int minValue(Node node) {
        Node right = node;
        Node temp = node;
        node = node.right;
        int min = temp.left.data;
        while (node.left != null) {
            min = node.left.data;
            temp = node;
            node = node.left;
        }
        if (temp != right) temp.left = node.right;
        else temp.left = null;
        return min;
    }

    public Node lca(Node node, int a, int b) {
        if (node == null) return null;
        if (node.data == a || node.data == b) return node;
        Node left = null, right = null;
        if (node.data > Math.min(a, b)) {
            left = lca(node.left, a, b);
        }
        if (node.data < Math.max(a, b)) {
            right = lca(node.right, a, b);
        }
        if (right != null && left != null) return node;
        if (right == null) return left;
        return right;
    }

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.data);
            inorder(node.right);
        }
    }

    public boolean isBst(Node node, int min, int max) {
        if (node == null) return true;
        return (node.data > min && node.data < max) && isBst(node.left, min, node.data) && isBst(node.right, node.data, max);
    }

    public Node bstConstruct(int[] pre, int start, int end) {
        if (start > end) {
            return null;
        }
        Node node = new Node(pre[start]);
        if (start == end) return new Node(pre[start]);
        int i = start;
        while (i < end) {
            if (pre[i] > pre[start]) {
                break;
            }
            ++i;
        }
        node.left = bstConstruct(pre, start + 1, i - 1);
        node.right = bstConstruct(pre, i, end);
        return node;
    }

    public void inorder(Node node, IntVal i, int[] trav) {
        if (node != null) {
            inorder(node.left, i, trav);
            node.data = trav[i.value++];
            inorder(node.right, i, trav);
        }
    }

    private class IntVal {
        int value = 0;
    }

    public void listToBST() {
        int[] a = {1, 2, 3, 4, 5, 6};
        root = constructBSTFromArray(a, 0, 5);
        System.out.println(root.data);
    }

    HashSet<LinkedList.Node> nodeHashSet = new HashSet<>();

    public Node constructBstFromList(LinkedList.Node lNode) {
        if (lNode == null) return null;
        if (lNode.next == null) {
            if (nodeHashSet.contains(lNode)) return null;
            return new Node(lNode.element);
        }
        LinkedList.Node mid = LinkedList.getMiddle(lNode);
        LinkedList.Node sec = mid.next;
        mid.next = null;
        Node temp = new Node(mid.element);
        nodeHashSet.add(mid);
        temp.left = constructBstFromList(lNode);
        temp.right = constructBstFromList(sec);
        return temp;
    }

    public Node constructBSTFromArray(int[] arr, int start, int end) {
        if (start > end || start < 0 || end < 0) return null;
        if (start == end) return new Node(arr[start]);
        int temp = start + end;
        int mid = (start + end) / 2;
        if (temp % 2 != 0) mid++;
        Node node = new Node(arr[mid]);
        node.left = constructBSTFromArray(arr, start, mid - 1);
        node.right = constructBSTFromArray(arr, mid + 1, end);
        return node;
    }


    public void inorderArr(Node node, ArrayList<Integer> arr) {
        if (node != null) {
            inorderArr(node.left, arr);
            arr.add(node.data);
            inorderArr(node.right, arr);
        }
    }

    public void inorderToSumTree(Node node, IntVal sum) {
        if (node != null) {
            inorderToSumTree(node.left, sum);
            int temp = node.data;
            node.data = sum.value;
            sum.value -= temp;
            inorderToSumTree(node.right, sum);
        }
    }


    public void bstToHeap(int[] inorder, BinarySearchTree tree) {
        Queue<Node> queue = new java.util.LinkedList<>();
        int index = 0;
        queue.offer(tree.root);
        Node temp;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            temp.data = inorder[index];
            ++index;
            if (temp.left != null) queue.offer(temp.left);
            if (temp.right != null) queue.offer(temp.right);
        }
    }


    public Node levelOrderToBST(int min, int max, int start, int[] arr) {
        if (start >= arr.length) return null;
        Node node = null;
        int iter = start;
        while (iter < arr.length) {
            if (arr[iter] > min && arr[iter] < max) {
                node = new Node(arr[iter]);
                break;
            }
            ++iter;
        }
        if (node == null) return null;
        node.left = levelOrderToBST(min, arr[iter], iter + 1, arr);
        node.right = levelOrderToBST(arr[iter], max, iter + 1, arr);
        return node;
    }

    int kthSmallest = Integer.MIN_VALUE;
    int index = 0;

    public void kthSmallest(Node node, int k) {
        if (node != null) {
            kthSmallest(node.left, k);
            ++index;
            if (index == k) kthSmallest = node.data;
            kthSmallest(node.right, k);
        }
    }

    boolean flag = false;
    int iter = 0;
    boolean isSeqInBST = true;

    public void sortedSubSeqInBST(Node node, int[] seq) {
        if (node != null) {
            sortedSubSeqInBST(node.left, seq);
            if (flag) {
                if (iter < seq.length) {
                    if (seq[iter] != node.data) {
                        isSeqInBST = false;
                    }
                    ++iter;
                }
            } else if (iter == 0 && seq[iter] == node.data) {
                flag = true;
                iter++;
            }
            sortedSubSeqInBST(node.right, seq);
        }
    }

    public void printKeysBetweenRange(Node node, int min, int max) {
        if (node == null) return;
        if (node.data < max && node.data > min) {
            System.out.println(node.data);
            printKeysBetweenRange(node.left, min, node.data);
            printKeysBetweenRange(node.right, node.data, max);
            return;
        }

        if (node.data < max && node.data <= min) {
            if (node.data == min) {
                System.out.println(node.data);
            }
            printKeysBetweenRange(node.right, min, max);
            return;
        }

        if (node.data >= max && node.data > min) {
            if (node.data == max) {
                System.out.println(node.data);
            }
            printKeysBetweenRange(node.left, min, max);
        }

    }


    public Node removeLeaf(Node node) {
        if (node == null || (node.left == null && node.right == null)) return null;
        node.left = removeLeaf(node.left);
        node.right = removeLeaf(node.right);
        return node;
    }

    public int maxBetweenTwoNodes(int a, int b) {
        Node node = lca(root, a, b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        if (node.data == max) {
            if (node.left.data == min) return -1;
            return node.left.data;
        }
        int val = node.data;
        while (node.right.data != max) {
            val = node.right.data;
            node = node.right;
        }
        return val;
    }

    int pred = Integer.MIN_VALUE;
    int succ = Integer.MAX_VALUE;

    public void inorderSuccessor(Node node, int key) {
        if (node != null) {
            inorderSuccessor(node.left, key);
            if (node.data < key) {
                pred = node.data;
            } else if (node.data > key) {
                if (succ > node.data) succ = node.data;
            }
            inorderSuccessor(node.right, key);
        }
    }

    public boolean hasDeadEnd(Node node, int min, int max) {
        if (node == null) return false;
        if (node.data == min || node.data == max) return true;
        return hasDeadEnd(node.left, min, node.data - 1) || hasDeadEnd(node.right, node.data + 1, max);

    }

    public boolean isAllNodeSingleChild(int[] arr, Node node) {
        if (arr.length <= 2) return true;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        int increasingSet = 0;
        if (arr[0] > arr[1]) {
            max = arr[0];
        } else {
            min = arr[0];
            increasingSet = 1;
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > max || arr[i] < min) return false;
            if (arr[i] > arr[i - 1]) {
                if (increasingSet == 1) {
                    min = arr[i - 1];
                } else {
                    max = arr[i - 1];
                }
            } else {
                if (increasingSet == 0) {
                    min = arr[i - 1];
                } else {
                    max = arr[i - 1];
                }
            }
        }
        return false;
    }

    public boolean isPreOrderBst(int[] pre) {
        Stack<Integer> stack = new Stack<>();
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < pre.length; i++) {
            if (pre[i] < maxSoFar) return false;
            while (!stack.isEmpty() && stack.peek() < pre[i]) {
                maxSoFar = stack.pop();
            }
            stack.push(pre[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        BinarySearchTree a = new BinarySearchTree();
        a.construct();
    }

    public void construct() {
        BinarySearchTree a = new BinarySearchTree();
        a.root = new Node(8, null, null);
        a.root.left = new Node(6, null, null);
        /** a.root.left.right = new Node(5);
         a.root.left.right.right = new Node(6);
         a.root.left.right.right.right = new Node(7);**/
        a.root.right = new Node(15, null, null);
        a.root.left.left = new Node(4, null, null);
        a.root.left.right = new Node(7, null, null);
        a.root.right.left = new Node(12, null, null);
        a.root.right.right = new Node(24, null, null);
        maxBSTSubTree(a.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(maxSubTree);
    }

    int bstSubTreeCount = 0;

    public boolean noOfTrees(Node node, int min, int max) {
        if (node == null) return true;
        boolean l = noOfTrees(node.left, min, node.data);
        boolean r = noOfTrees(node.right, node.data, max);
        if (l && r) {
            ++bstSubTreeCount;
            return node.data > min && node.data < max;
        }
        return false;
    }

    public class NodeBST {
        boolean isBst = false;
        int bstSize = 0;
        Node node = null;

        public NodeBST(boolean isBst, int size, Node node) {
            this.isBst = isBst;
            this.bstSize = size;
            this.node = node;
        }

        public NodeBST() {
        }
    }

    int maxSubTree = 0;

    public NodeBST maxBSTSubTree(Node node, int min, int max) {
        if (node == null) return new NodeBST(true, 0, null);
        NodeBST l = maxBSTSubTree(node.left, min, node.data);
        NodeBST r = maxBSTSubTree(node.right, node.data, max);
        NodeBST nodeBST = new NodeBST();
        if (l.isBst && r.isBst) {
            nodeBST.bstSize = 1 + l.bstSize + r.bstSize;
            if (maxSubTree < nodeBST.bstSize) maxSubTree = nodeBST.bstSize;
            if (node.data > min && node.data < max) {
                nodeBST.isBst = true;
            }
        }
        return nodeBST;
    }
}
