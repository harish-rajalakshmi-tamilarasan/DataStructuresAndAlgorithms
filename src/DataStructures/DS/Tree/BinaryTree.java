package DataStructures.DS.Tree;

import java.util.*;

/**
 * @author Harish T
 */
public class BinaryTree {
    public static class BinaryNode {
        public int data;
        public BinaryNode left;
        public BinaryNode right;

        public BinaryNode(int element) {
            data = element;
        }

        BinaryNode(int element, BinaryNode left, BinaryNode right) {
            data = element;
            this.left = left;
            this.right = right;
        }
    }

    private int size;
    public BinaryNode root;

    public void addRoot(int data) throws Exception {
        if (size > 0) {
            throw new Exception("Already added Root");
        }
        BinaryNode node = new BinaryNode(data, null, null);
        root = node;
        ++size;
    }

    public void insert(int data) {
        insert(root, data);
        ++size;
    }

    private void insert(BinaryNode root, int data) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode child = queue.remove();
            if (child.left == null) {
                child.left = new BinaryNode(data);
                break;
            }
            if (child.right == null) {
                child.right = new BinaryNode(data);
                break;
            }
            queue.add(child.left);
            queue.add(child.right);
        }
    }


    public boolean remove(int data) {
        return remove(root, data);
    }

    private boolean remove(BinaryNode root, int data) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode temp = null;
        BinaryNode nodeToDel = null;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (temp.data == data) {
                nodeToDel = temp;
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        if (nodeToDel == null) {
            return false;
        }
        nodeToDel.data = temp.data;
        removeDeepest(temp);
        return true;
    }

    private void removeDeepest(BinaryNode deepest) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        if (root == deepest) {
            root = null;
            return;
        }
        BinaryNode child;
        while (!queue.isEmpty()) {
            child = queue.remove();
            if (child.left != null) {
                if (child.left == deepest) {
                    child.left = null;
                    return;
                } else {
                    queue.add(child.left);
                }
            }
            if (child.right != null) {
                if (child.right == deepest) {
                    child.right = null;
                    return;
                } else {
                    queue.add(child.right);
                }
            }
        }
    }

    public void breadthFirstSearch() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode node;
        while (!queue.isEmpty()) {
            node = queue.remove();
            System.out.println(node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void inorder() {
        inorderTraversal(root);
    }

    public void preorder() {
        preorderTraversal(root);
    }

    public void postorder() {
        postorderTraversal(root);
    }

    private void preorderTraversal(BinaryNode node) {
        if (node != null) {
            System.out.println(node.data);
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    private void postorderTraversal(BinaryNode node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.println(node.data);
        }
    }

    private void inorderTraversal(BinaryNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.data);
            inorderTraversal(root.right);
        }
    }

    public static void inorderIter(BinaryTree tree) {
        BinaryNode curr = tree.root;
        Stack<BinaryNode> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.println(curr.data);
            curr = curr.right;
        }
    }

    public static void preorderIter(BinaryTree tree) {
        BinaryNode curr = tree.root;
        Stack<BinaryNode> stack = new Stack<>();
        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            System.out.println(curr.data);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    public static void postorderIter(BinaryTree tree) {
        BinaryNode curr = tree.root;
        BinaryNode temp = null;
        Stack<BinaryNode> stack = new Stack<>();
        stack.push(curr);
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
                System.out.println(curr.data);
                stack.pop();
            }
            temp = curr;
        }
    }

    private int height(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int getHeightRecur(BinaryTree tree) {
        return getHeightRecur(tree.root);
    }

    private static int getHeightRecur(BinaryNode node) {
        if (node == null) return 0;
        return Math.max(getHeightRecur(node.left), getHeightRecur(node.right)) + 1;
    }

    public int getHeight() {
        return height(root);
    }

    public static int getHeight(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        if (tree.root == null) return 0;
        BinaryNode node;
        int nodecount = 0;
        queue.offer(tree.root);
        int height = 0;
        while (!queue.isEmpty()) {
            nodecount = queue.size();
            ++height;
            while (nodecount > 0) {
                node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                --nodecount;
            }
        }
        return height;
    }

    public static int getMinimumDepth(BinaryTree tree) {
        return getMinimumDepth(tree.root);
    }

    public static int getMinimumDepth(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {
            return getMinimumDepth(node.right) + 1;
        }
        if (node.right == null) {
            return getMinimumDepth(node.left) + 1;
        }
        return Math.min(getMinimumDepth(node.right), getMinimumDepth(node.left)) + 1;
    }

    public static int getMinimumDepthIter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        if (tree.root == null) return -1;
        BinaryNode node;
        int nodeCount = 0;
        queue.offer(tree.root);
        int depth = 0;
        while (!queue.isEmpty()) {
            nodeCount = queue.size();
            ++depth;
            while (nodeCount > 0) {
                node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                --nodeCount;
            }
        }
        return depth;
    }


    public int getSize() {
        return size;
    }

    /**
     * Find no of nodes or size
     *
     * @param tree
     * @return
     */
    public static int findNodesCount(BinaryTree tree) {
        //   Integer count = new Integer(0);
        return findNodes(tree.root);
        //   return count;
    }

    public static int findNodes(BinaryNode node) {
        return 1 + (node.left == null ? 0 : findNodes(node.left)) + (node.right == null ? 0 : findNodes(node.right));
    }

    public static int findNodesCountIter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        int count = 0;
        queue.offer(tree.root);
        BinaryNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            ++count;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return count;
    }

    public static boolean isContinuous(BinaryTree tree) {
        if (tree.root == null || (tree.root.left == null && tree.root.right == null)) {
            return true;
        }
        return isContinuous(tree.root);
    }

    private static boolean isContinuous(BinaryNode node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                return true;
            }
            if (node.left == null) {
                return (Math.abs(node.data - node.right.data) == 1 && isContinuous(node.right));
            }
            if (node.right == null) {
                return (Math.abs(node.data - node.left.data) == 1 && isContinuous(node.left));
            }
            return (Math.abs(node.data - node.right.data) == 1 && Math.abs(node.data - node.left.data) == 1 && isContinuous(node.right) && isContinuous(node.left));
        }
        return true;
    }

    public static boolean isFoldable(BinaryTree tree) {
        if (tree.root == null || (tree.root.left == null && tree.root.right == null)) return true;
        return isFoldable(tree.root.left, tree.root.right);
    }

    private static boolean isFoldable(BinaryNode nodeA, BinaryNode nodeB) {
        if (nodeA == null && nodeB == null) return true;
        if (nodeA == null || nodeB == null) return false;
        return isFoldable(nodeA.left, nodeB.right) && isFoldable(nodeA.right, nodeB.left);
    }

    public static boolean isMirror(BinaryTree tree) {
        if (tree.root == null || (tree.root.left == null && tree.root.right == null)) return true;
        return isMirror(tree.root.left, tree.root.right);
    }

    private static boolean isMirror(BinaryNode nodeA, BinaryNode nodeB) {
        if (nodeA == null && nodeB == null) return true;
        if ((nodeA == null || nodeB == null) || nodeA.data != nodeB.data) return false;
        return isMirror(nodeA.left, nodeB.right) && isMirror(nodeA.right, nodeB.left);
    }


    /**
     * Find maximum of a tree
     *
     * @param tree
     * @return
     */
    public static int maximum(BinaryTree tree) {
        return maximum(tree.root);
    }

    private static int maximum(BinaryNode root) {
        if (root != null) {
            return Math.max(root.data, Math.max(maximum(root.left), maximum(root.right)));
        }
        return Integer.MIN_VALUE;
    }

    public static int maximumIter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.offer(tree.root);
        BinaryNode node = null;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            node = queue.poll();
            max = Math.max(max, node.data);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return max;
    }

    /**
     * @throws Exception
     */
    public static boolean search(BinaryTree tree, int data) {
        return search(tree.root, data);
    }

    public static boolean search(BinaryNode root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }
        return search(root.left, data) || search(root.right, data);
    }

    public static boolean searchIter(BinaryTree tree, int data) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.offer(tree.root);
        BinaryNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.data == data) return true;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    /**
     * @throws Exception
     */
    public static void printReverse(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        Stack<BinaryNode> stack = new Stack<>();
        queue.offer(tree.root);
        BinaryNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            stack.add(node);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().data);
        }
    }

    //count No of leaves
    public static int leavesCountIter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        int count = 0;
        BinaryNode temp;
        if (tree.root == null) return 0;
        queue.offer(tree.root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.left == null && temp.right == null) {
                ++count;
            } else {
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return count;
    }

    public static int leavesCount(BinaryTree tree) {
        if (tree.root == null) return 0;
        return leavesCount(tree.root);
    }

    public static int leavesCount(BinaryNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {
            return leavesCount(node.right);
        }
        if (node.right == null) {
            return leavesCount(node.left);
        }
        return leavesCount(node.right) + leavesCount(node.left);
    }

    /**
     * Find half nodes in tree
     *
     * @throws Exception
     */
    public static int halfNodesCountIter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        int count = 0;
        BinaryNode temp;
        if (tree.root == null) return 0;
        queue.offer(tree.root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.left == null && temp.right == null) {

            } else {
                if (temp.left == null || temp.right == null) {
                    ++count;
                    //   queue.offer(temp.left);
                }
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return count;
    }

    public static int halfNodesCount(BinaryTree tree) {
        return halfNodesCount(tree.root);
    }

    public static int halfNodesCount(BinaryNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return 0;
        }
        if (node.left == null || node.right == null) {
            return 1 + halfNodesCount(node.left) + halfNodesCount(node.right);
        }
        return halfNodesCount(node.left) + halfNodesCount(node.right);
    }

    public static int completeNodesIter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        int count = 0;
        BinaryNode temp;
        if (tree.root == null) return 0;
        queue.offer(tree.root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.left != null && temp.right != null) {
                ++count;
            }
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return count;
    }

    public static int completeNodes(BinaryTree tree) {
        return completeNodes(tree.root);
    }

    public static int completeNodes(BinaryNode node) {
        if (node == null) return 0;
        if (node.left != null && node.right != null) {
            return 1 + completeNodes(node.left) + completeNodes(node.right);
        }
        return completeNodes(node.left) + completeNodes(node.right);
    }

    public static int diameter(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.offer(tree.root);
        return getHeightRecur(tree.root.left) + getHeightRecur(tree.root.right) + 1;
    }

    public static void printAllPaths(BinaryTree tree) {
        Deque<Integer> path = new ArrayDeque<>();
        printAllPaths(tree.root, path);
    }

    public static void printAllPaths(BinaryNode node, Deque<Integer> path) {
        if (node == null) return;
        path.addLast(node.data);
        if (node.left == null && node.right == null) {
            for (int temp : path) {
                System.out.println(temp);
            }
        } else {
            printAllPaths(node.left, path);
            printAllPaths(node.right, path);
        }
        path.removeLast();
    }

    public static void maxSumOfPaths(BinaryTree tree) {
        maxSumOfPaths(tree.root, 0);
    }

    private static int max;

    public static void maxSumOfPaths(BinaryNode node, int sum) {
        if (node == null) {
            return;
        }
        sum += node.data;
        if (node.left == null && node.right == null) {
            if (max < sum) max = sum;
        } else {
            maxSumOfPaths(node.left, sum);
            maxSumOfPaths(node.right, sum);
        }
        sum -= node.data;
    }

    public static void createMirror(BinaryTree tree, BinaryTree mirror) {
        mirror.root = new BinaryNode(tree.root.data);
        createMirror(tree.root, mirror.root);
    }

    public static void createMirror(BinaryNode node, BinaryNode mirror) {
        if (node.left == null) {
            mirror.right = null;
        } else {
            mirror.right = new BinaryNode(node.left.data);
            createMirror(node.left, mirror.right);
        }
        if (node.right == null) {
            mirror.left = null;
        } else {
            mirror.left = new BinaryNode(node.right.data);
            createMirror(node.right, mirror.left);

        }

    }

    public static BinaryNode createTree(int[] preorder, int[] inorder) {
        if ((inorder.length == 0 && preorder.length == 0) || (inorder.length != preorder.length)) {
            return null;
        }
        return createNode(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static BinaryNode createNode(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        BinaryNode curr = new BinaryNode(preorder[preStart]);
        if (preStart == preEnd) {
            return curr;
        }
        int start = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (preorder[preStart] == inorder[i]) {
                start = i;
                break;
            }
        }
        start = start - inStart;
        curr.left = createNode(preorder, preStart + 1, preStart + start, inorder, inStart, start - inStart - 1);
        curr.right = createNode(preorder, preStart + start + 1, preEnd, inorder, start - inStart + 1, inEnd);
        return curr;
    }

    private static int postindex = 0;

    public static BinaryNode commonAncestor(BinaryNode node, int a, int b) {
        if (node == null) {
            return null;
        }
        if (node.data == a || node.data == b) {
            return node;
        }
        BinaryNode left = commonAncestor(node.left, a, b);
        BinaryNode right = commonAncestor(node.right, a, b);
        if (left != null && right != null) {
            //   System.out.println(node);
            return node;
        }
        if (left != null) return left;
        return right;
    }

    public static boolean printAncestors(BinaryNode root, BinaryNode node) {
        if (root == null) {
            return false;
        }
        if (root.left == node || root.right == node || printAncestors(root.left, node) || printAncestors(root.right, node)) {
            System.out.println(root.data);
            return true;
        }
        return false;
    }

    public static void zigZagTraversal(BinaryTree tree, ArrayList<Integer> array) {
        Deque<BinaryNode> deque = new ArrayDeque<>();
        deque.add(tree.root);
        //     array.add(tree.root.data);
        boolean left = true;
        BinaryNode node;
        int a = 2;
        int levelCount = 0;
        while (!deque.isEmpty()) {
            levelCount = deque.size();
            while (levelCount > 0) {
                if (left) {
                    node = deque.removeLast();
                    array.add(node.data);
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                } else {
                    node = deque.removeFirst();
                    array.add(node.data);
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                }
                levelCount--;
            }
            a--;
            if (a == 0) {
                left = !left;
                a = 2;
            }
        }
    }

    public static BinaryNode createTreePost(int[] postorder, int[] inorder) {
        if ((inorder.length == 0 && postorder.length == 0) || (inorder.length != postorder.length)) {
            return null;
        }
        return createTreePost(postorder, postorder.length - 1, 0, inorder, inorder.length - 1, 0);

    }

    public static BinaryNode createTreePost(int[] postorder, int poststart, int postend, int[] inorder, int instart, int inend) {
        if (postend > poststart || postend < 0 || poststart < 0) {
            return null;
        }

        BinaryNode curr = new BinaryNode(postorder[poststart]);
        if (poststart == postend) {
            return curr;
        }
        int start = 0;
        for (int i = instart; i >= inend; i--) {
            if (postorder[poststart] == inorder[i]) {
                start = i;
                break;
            }
        }
        start = instart - start;
        curr.right = createTreePost(postorder, poststart - 1, poststart - start, inorder, instart, instart - start + 1);
        curr.left = createTreePost(postorder, poststart - start - 1, postend, inorder, instart - start - 1, inend);
        return curr;
    }

    public static void addNodeData(HashMap<Integer, Integer> hashmap, BinaryNode node, int i) {
        if (node == null) return;
        int data = node.data;
        if (!hashmap.containsKey(i)) {
            hashmap.put(i, data);
        } else {
            hashmap.replace(i, data + hashmap.get(i));
        }
        addNodeData(hashmap, node.left, i - 1);
        addNodeData(hashmap, node.right, i + 1);
    }


    public static void createPost(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, int[] postorder) {
        if (preStart <= preEnd) {
            int curr = preorder[preStart];
            postorder[postindex--] = curr;
            int start = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (preorder[preStart] == inorder[i]) {
                    start = i;
                    break;
                }
            }
            start = start - inStart;
            createPost(preorder, preStart + start + 1, preEnd, inorder, start + inStart + 1, inEnd, postorder);
            createPost(preorder, preStart + 1, preStart + start, inorder, inStart, start + inStart - 1, postorder);
            //  return curr;
        }
    }

    public static void test() throws Exception {
        BinaryTree tree = new BinaryTree();
        tree.addRoot(1);
        tree.root.left = new BinaryNode(2);
        tree.root.right = new BinaryNode(3);
        tree.root.left.left = new BinaryNode(4);
        tree.root.left.right = new BinaryNode(5);
        tree.root.right.left = new BinaryNode(6);
        tree.root.right.right = new BinaryNode(7);
        tree.root.left.left.right = new BinaryNode(9);
        tree.root.left.left.left = new BinaryNode(8);
        ArrayList<Integer> array = new ArrayList<>();
        zigZagTraversal(tree, array);
        for (int temp : array) {
            System.out.println(temp);
        }
    }

    /**
     * Assign successor of inorder traversal
     * Node temp=null;
     * public static void assignInorderSucc(node){
     * assignInorder(node.right);
     * node.next=temp;
     * temp=node;
     * assignInorder(node.left);
     * }
     */

    public static void breadthFirstSearchAlternateReverse(BinaryTree tree) {
        Queue<BinaryNode> queue = new LinkedList<>();
        BinaryNode node;
        BinaryNode temp;
        int nodecount = 0;
        queue.offer(tree.root);
        Queue<BinaryNode> tempQueue = new LinkedList<>();
        Stack<BinaryNode> stack = new Stack<>();
        boolean needFlip = true;
        while (!queue.isEmpty()) {
            nodecount = queue.size();
            if (needFlip) {
                while (nodecount > 0) {
                    node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                        stack.add(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                        stack.add(node.right);
                    }
                    --nodecount;
                    tempQueue.offer(node);
                }
                while (!stack.isEmpty()) {
                    temp = tempQueue.poll();
                    temp.left = stack.pop();
                    temp.right = stack.pop();
                }
                needFlip = !needFlip;
            } else {
                while (nodecount > 0) {
                    node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    --nodecount;
                }
                needFlip = !needFlip;
            }
        }
    }

    public static boolean isHeap(BinaryNode node, int index, int totalCount) {
        if (node == null) return true;
        if (index >= totalCount) return false;
        if ((node.right != null && node.right.data < node.data) || (node.left != null && node.left.data < node.data)) {
            return false;
        }
        return isHeap(node.left, index * 2 + 1, totalCount) && isHeap(node.left, index * 2 + 2, totalCount);
    }

}