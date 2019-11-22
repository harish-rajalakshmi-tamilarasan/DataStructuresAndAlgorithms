package DataStructures.DS.Tree;

import java.util.*;

/**
 * @author Harish T
 */
public class BTree {
    public class Node {
        Node left;
        Node right;
        int data;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public int size = 0;
    public Node root;

    public void buildTree(int[] arr) {
        for (int temp : arr) {
            insert(temp);
        }
    }

    public void addRoot(int data) throws Exception {
        if (size == 0) {
            throw new Exception("Root already added");
        }
        root = new Node(data);
        ++size;
    }

    public void insert(int data) {
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            root = new Node(data);
            ++size;
            return;
        }
        queue.offer(root);
        Node node = null;
        ++size;
        while (!queue.isEmpty()) {
            node = queue.remove();
            if (node.left == null) {
                node.left = new Node(data);
                return;
            }
            if (node.right == null) {
                node.right = new Node(data);
                return;
            }
            queue.add(node.left);
            queue.add(node.right);

        }
    }

    public int maxNode(Node root) {
        int max = Integer.MIN_VALUE;
        if (root != null) {
            int leftMax = maxNode(root.left);
            int rightMax = maxNode(root.right);
            max = leftMax > rightMax ? leftMax : rightMax;
            return max > root.data ? max : root.data;
        }
        return max;
    }

    Node searchNode = null;

    public void search(Node node, int data) {
        if (node.data == data) {
            searchNode = node;
            return;
        }
        if (node.left != null) search(node.left, data);
        if (node.right != null) search(node.right, data);
    }

    public int getSize(Node node) {
        if (node == null) return 0;
        return 1 + getSize(node.left) + getSize(node.right);
    }

    public int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int heightIter() {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        int counter = 0;
        Node node = null;
        while (!queue.isEmpty()) {
            counter = queue.size();
            ++height;
            while (counter > 0) {
                node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                --counter;
            }
        }
        return height;
    }

    public int minDepth() {
        return minDepth(root);
    }

    public int minDepth(Node node) {
        if (node == null) return 0;
        return 1 + Math.min(minDepth(node.left), minDepth(node.right));
    }

    int leaves;

    public void noOfLeaves() {
        noOfLeaves(root);
        System.out.println(leaves);
    }

    public void noOfLeaves(Node node) {
        if (node.left == null && node.right == null) {
            ++leaves;
            return;
        }
        if (node.left != null) noOfLeaves(node.left);
        if (node.right != null) noOfLeaves(node.right);
    }


    int halfNodes;

    public void halfNodes(Node node) {
        if ((node.right != null && node.left == null) || (node.left != null && node.right == null)) {
            halfNodes++;
        }
        if (node.left != null) halfNodes(node.left);
        if (node.right != null) halfNodes(node.right);
    }

    int fullNodes;

    public void fullNodes(Node node) {
        if (node.right != null && node.left != null) {
            fullNodes++;
        }
        if (node.left != null) fullNodes(node.left);
        if (node.right != null) fullNodes(node.right);
    }

    public void rootToLeafPaths(Node node, Deque<Integer> deque) {
        if (node == null) return;
        deque.add(node.data);
        if (node.left == null && node.right == null) {
            System.out.println(deque);
            deque.removeLast();
        } else {
            rootToLeafPaths(node.left, deque);
            rootToLeafPaths(node.right, deque);
        }
    }

    public int diameter(Node node) {
        if (node == null) return 0;
        return Math.max(Math.max(height(node.left) + height(node.right) + 1, diameter(node.left)), diameter(node.right));
    }

    public boolean printPath(Node node, int sum) {
        if (node == null) return false;
        if (node.left == null && node.right == null && node.data == sum) {
            return true;
        }
        return printPath(node.left, sum - node.data) || printPath(node.right, sum - node.data);
    }

    public void printPath(Node node, Deque<Integer> deque, int sum) {
        if (node == null) return;
        deque.add(node.data);
        if (node.left == null && node.right == null && node.data == sum) {
            System.out.println(deque);
            deque.removeLast();
            return;
        } else if (node.left == null && node.right == null) {
            deque.removeLast();
        } else {
            printPath(node.left, deque, sum - node.data);
            printPath(node.right, deque, sum - node.data);
        }
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    int ans = 0;

    public void width(Node node, int depth, int pos) {
        if (node == null) return;
        map.computeIfAbsent(depth, x -> pos);
        ans = Math.max(ans, pos - map.get(depth) + 1);
        width(node.left, depth + 1, pos * 2);
        width(node.right, depth + 1, pos * 2 + 1);
    }

    public Node createMirror(Node node) {
        if (node == null) return null;
        Node left = createMirror(node.left);
        Node right = createMirror(node.right);
        if (left == null && right == null) return node;
        node.right = left;
        node.left = right;
        return node;
    }

    public Node commonAncestor(Node node, int a, int b) {
        if (node == null) return null;
        if (node.data == a || node.data == b) return node;
        Node left = commonAncestor(node.left, a, b);
        Node right = commonAncestor(node.right, a, b);
        if (left != null && right != null) return node;
        if (left != null) return left;
        return right;
    }

    public boolean printAllAncestors(Node node, int data) {
        if (node == null) return false;
        if (node.left.data == data || node.right.data == data || printAllAncestors(node.left, data) || printAllAncestors(node.right, data)) {
            System.out.println(node.data);
            return true;
        }
        return false;
    }

    public void verticalSum(HashMap<Integer, Integer> map, Node node, int pos) {
        if (node == null) return;
        if (map.containsKey(pos)) {
            map.replace(pos, map.get(pos) + node.data);
        } else {
            map.put(pos, node.data);
        }
        verticalSum(map, node.left, pos - 1);
        verticalSum(map, node.right, pos + 1);
    }


    public Node createTree(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend) {
        if (prestart > preend) return null;
        Node current = new Node(preorder[prestart]);
        int offset = 0;
        for (int i = instart; i <= inend; i++) {
            if (inorder[i] == preorder[prestart]) {
                offset = i;
            }
        }
        offset = offset - instart;
        current.left = createTree(preorder, prestart + 1, prestart + offset, inorder, instart, instart + offset - 1);
        current.right = createTree(preorder, prestart + offset + 1, preend, inorder, instart + offset + 1, inend);
        return current;
    }

    public Node createPostTree(int[] postorder, int poststart, int postend, int[] inorder, int instart, int inend) {
        if (poststart < postend) return null;
        Node current = new Node(postorder[poststart]);
        int offset = 0;
        for (int i = instart; i <= inend; i++) {
            if (inorder[i] == postorder[poststart]) {
                offset = i;
            }
        }
        offset = offset - instart;
        current.left = createPostTree(postorder, postend + offset - 1, postend, inorder, instart, instart + offset - 1);
        current.right = createPostTree(postorder, poststart - 1, postend + offset, inorder, instart + offset + 1, inend);
        return current;
    }

    public void inOrder() {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.add(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.println(curr.data);
            curr = curr.right;
        }
    }

    public void preOrder() {
        Stack<Node> stack = new Stack<>();
        Node curr = null;
        stack.add(root);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            System.out.println(curr.data);
            if (curr.right != null) stack.add(curr.right);
            if (curr.left != null) stack.add(curr.left);
        }
    }

    public void postOrder() {
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        Node temp = null;
        Node curr = null;
        while (!stack.isEmpty()) {
            curr = stack.peek();
            if (temp == null || temp.left == curr || temp.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == temp) {
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else {
                System.out.println(stack.pop().data);
            }
            temp = curr;
        }
    }

    int parentSum = 0;

    public void sumOfParentHavingChildWithGivenValue(Node node, int dataToCheck) {
        if (node == null) return;
        if (node.left.data == dataToCheck || node.right.data == dataToCheck) {
            parentSum += node.data;
        }
        sumOfParentHavingChildWithGivenValue(node.left, dataToCheck);
        sumOfParentHavingChildWithGivenValue(node.right, dataToCheck);
    }

    int leftLeaves = 0;


    public void addLeftLeaves(Node node, int pos) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                if (pos == 0) leftLeaves += node.data;
                return;
            }
            addLeftLeaves(node.left, 0);
            addLeftLeaves(node.right, 1);
        }
    }

    int level = 0;

    public void findLevel(Node node) {
        if (node != null) {
            ++level;
            findLevel(node.left);
        }
    }

    public int addAllNodesLeaf1ToN() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int iter = level;
        int sum = 0;
        Node node = null;
        while (iter > 1 && !queue.isEmpty()) {
            int queueSize = queue.size();
            while (queueSize > 0) {
                node = queue.remove();
                sum += node.data;
                queue.offer(node.left);
                queue.offer(node.right);
                --queueSize;
            }
            --iter;
        }
        int noOfLeaves = (int) Math.pow(2, level - 1);
        int leavesSum = noOfLeaves * (noOfLeaves + 1) / 2;
        return sum + leavesSum;
    }

    int weight = Integer.MAX_VALUE;

    public void helper() {
        //  findLevel(root);
        weightestPaths(root, 0);
        System.out.println(weight);
    }

    public void weightestPaths(Node node, int sumSoFar) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                weight = weight < sumSoFar + node.data ? weight : (sumSoFar + node.data);
            } else {
                weightestPaths(node.left, sumSoFar + node.data);
                weightestPaths(node.right, sumSoFar + node.data);
            }
        }
    }

    int[] a = {1, 2, 3, 4, 5};

    public boolean ifArrayPathExists(Node node, int i) {
        if (node == null) {
            return true;
        }
        if (i >= a.length) return false;
        if (node.data != a[i]) {
            return false;
        }
        return ifArrayPathExists(node.left, i + 1) || ifArrayPathExists(node.right, i + 1);
    }

    public Node leastCommonAncestor(Node node, int a, int b) {
        if (node == null) return null;
        if (node.data == a || node.data == b) return node;
        Node left = leastCommonAncestor(node.left, a, b);
        Node right = leastCommonAncestor(node.right, a, b);
        if (left != null && right != null) return node;
        if (left != node) return left;
        return right;
    }

    public void printDistanceBetweenTwoNodes(int a, int b) {
        Node lca = leastCommonAncestor(root, a, b);
        System.out.println(printFromRootToNode(lca, a, b));
    }

    public int printFromRootToNode(Node node, int a, int b) {
        int aDis = -1;
        int bDis = -1;
        int level = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        Node root = null;
        while (!queue.isEmpty() || (aDis < 0 && bDis < 0)) {
            ++level;
            int length = queue.size();
            while (length > 0) {
                root = queue.poll();
                if (root.data == a) {
                    aDis = level - 1;
                } else if (root.data == b) {
                    bDis = level - 1;
                }
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
                --length;
            }
        }
        return aDis + bDis;
    }

    public void printKFromNode(HashSet<Node> set, Node node, int a) {
        if (node == null) return;
        if (a == 0 && !set.contains(node)) {
            System.out.println(node.data);
            set.add(node);
            return; 
        }
        printKFromNode(set, node.left, a - 1);
        printKFromNode(set, node.right, a - 1);
    }

    public int printKFromNode(HashSet<Node> set, Node node, int data, int k) {
        if (node == null) return -1;
        if (node.data == data) {
            printKFromNode(set, node, k);
            return 0;
        }
        int left = printKFromNode(set, node.left, data, k);
        if (left == 0) {
            printKFromNode(set, node, k - 1);
            return 0;
        }
        int right = printKFromNode(set, node.right, data, k);
        if (right == 0) {
            printKFromNode(set, node, k - 1);
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        BTree tree = new BTree();
        tree.buildTree(b);
        tree.printDistanceBetweenTwoNodes(3, 8);
    }
}
