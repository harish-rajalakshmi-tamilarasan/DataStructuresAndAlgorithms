package DataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Harish T
 */
public class Tree<E> {
    private int size;
    Node<E> root;


    static class Node<E> {
        private Node<E> parent;
        private E data;
        private Node<E> left;
        private Node<E> right;

        private Node(E data, Node<E> left, Node<E> right, Node<E> parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        private Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E e) {
            data = e;
        }

        public Node<E> getRight() {
            return right;
        }

        private Node<E> getLeft() {
            return left;
        }

        private Node<E> getParent() {
            return parent;
        }

        private void setParent(Node<E> p) {
            this.parent = p;
        }

        private void setLeft(Node<E> p) {
            this.left = p;
        }

        private void setRight(Node<E> p) {
            this.right = p;
        }
    }

    private Node<E> createNode(E e) {
        return new Node<>(e);
    }

    public int size() {
        return size;
    }

    public void addRoot(E e) {
        if (size > 0) {
            throw new IllegalStateException("Already root added");
        }
        Node<E> rootNode = createNode(e);
        root = rootNode;
        rootNode.left = null;
        rootNode.right = null;
        rootNode.parent = null;
        size++;
    }

    public Node<E> getRoot() {
        return root;
    }

    public boolean isRoot(Node<E> node) {
        return getRoot().equals(node);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int numChildren(Node<E> e) {
        int child = 0;
        if (e.left != null) {
            child++;
        }
        if (e.right != null) {
            child++;
        }
        return child;
    }


    boolean isInternal(Node<E> e) {
        return numChildren(e) != 0;
    }

    boolean isExternal(Node<E> e) {
        return numChildren(e) == 0;
    }

    public int depth(Node<E> node) {
        if (isRoot(node))
            return 0;
        else return 1 + depth(getParent(node));
    }

    public int height(Node<E> node) {
        int h = 0;
        for (Node<E> e : children(node)) {
            h = Math.max(h, height(e));
        }
        return h;
    }

    public Iterable<Node<E>> children(Node<E> node) {
        List<Node<E>> snapshot = new ArrayList<>();
        if (getLeft(node) != null) {
            snapshot.add(node.left);
        }
        if (getRight(node) != null) {
            snapshot.add(node.right);
        }
        return snapshot;
    }


    public Node<E> getParent(Node<E> node) {
        if (node.equals(getRoot())) {
            throw new IllegalStateException("Node Is Root");
        }
        return node.parent;
    }

    public Node<E> getLeft(Node<E> node) {
        if (node.left != null) {
            return node.left;
        }
        throw new IllegalStateException("Left is Empty");
    }

    public Node<E> getRight(Node<E> node) {
        if (node.right != null) {
            return node.right;
        }
        throw new IllegalStateException("Right is Empty");
    }

    public Node<E> sibling(Node<E> node) {
        if (isRoot(node)) throw new IllegalStateException("Node is root");
        Node<E> pnode = node.parent;
        if (node.equals(pnode.left)) return pnode.right;
        else return pnode.left;
    }

    public void addLeft(Node<E> toAdd, Node<E> parent) {
        if (parent.left != null) {
            parent.setLeft(toAdd);
            size++;
        } else throw new IllegalStateException("Left already there");
    }

    public void addRight(Node<E> toAdd, Node<E> parent) {
        if (parent.right != null) {
            parent.setRight(toAdd);
            size++;
        } else throw new IllegalStateException("Right already there");
    }

    public E set(Node<E> node, E e) {
        E temp = node.getData();
        node.setData(e);
        return temp;
    }

    public void remove(Node<E> node) {
        if (numChildren(node) == 2) throw new IllegalStateException("Cannot remove Root");
        Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();
        if (isRoot(node)) {
            root = child;
        }
        if (child != null) {
            child.setParent(node.getParent());
            if (node.parent.left.equals(node)) {
                node.left = child;
            } else {
                node.right = child;
            }

        } else {
            if (node.parent.left.equals(node)) {
                node.left = null;
            } else {
                node.right = null;
            }
        }
        size--;
        node.setParent(node);
    }

    /*    public void attach(Node<E> node, Tree<E> l1, Tree<E> l2) {
            if (isInternal(node)) {
                throw new IllegalStateException("No leaf");
            }
            if (!l1.isEmpty()) {
                node.setLeft(l1.root);
                l1.getRoot().setParent(node);
                l1.root = null;
            }
            if (!l2.isEmpty()) {
                node.setRight(l2.root);
                l2.getRoot().setParent(node);
                l2.root = null;
            }
            size += l1.size() + l2.size();
        }
    */
    public void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getData());
        }
    }
    public void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getData());
            inOrder(node.getRight());
        }
    }
    public void preOrder(Node<E> node) {
        if (node != null) {
            System.out.println(node.getData());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
    public void breadthFS(Queue<Node<E>> queue) {
        Queue<Node<E>> queueNew = new SynchronousQueue<Tree.Node<E>>();
        if (queue.size() == 0) return;
        for (int i = 0; i < queue.size(); i++) {
            Node<E> n = queue.remove();
            System.out.println(n.getData());
            for (Node<E> nodes : children(n)) {
                queueNew.add(nodes);
            }
        }
        breadthFS(queueNew);
    }
}
