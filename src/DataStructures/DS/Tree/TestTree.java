package DataStructures.DS.Tree;

/**
 * @author Harish T
 */
public class TestTree {
    public static void main(String[] args) throws Exception {
        BTree tree = new BTree();
        int[] a = {3, 21, 5, 45, 8, 1, 90, 6};
        tree.buildTree(a);
        tree.helper();
    }
}
