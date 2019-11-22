package DataStructures.DS;

/**
 * @author Harish T
 */
public class CircularLinkedList {
    private static class Node {
        Node next;
        int data;
    }

    public Node root;
    public Node tail;
    public int size;

    public void addRoot(int data) {
        if (size == 0) {
            root = new Node();
            root.data = data;
            root.next = root;
            tail = root;
            ++size;
            return;
        }
        Node node = new Node();
        node.data = data;
        node.next = root;
        root = node;
        tail.next = root;
        ++size;
    }

    public Node getRootNode() {
        return root;
    }

    public void addTail(int data) {
        if (size == 0) {
            root = new Node();
            root.data = data;
            root.next = root;
            tail = root;
            ++size;
            return;
        }
        Node node = new Node();
        node.data = data;
        node.next = root;
        tail.next = node;
        tail = node;
        ++size;
    }

    public int removeRoot() {
        if (root == null) {
            return -1;
        }
        int element = root.data;
        if (root.next == root) {
            root = tail = null;
            size = 0;
            return element;
        }
        tail.next = root.next;
        root = root.next;
        --size;
        return element;
    }

    public int removeTail() {
        if (root == null) {
            return -1;
        }
        Node node = root;
        int element = tail.data;
        if (root == tail) {
            root = tail = null;
        }
        while (node.next != tail) {
            node = node.next;
        }
        node.next = root;
        tail = node;
        --size;
        return element;
    }

    public void removeNode(int n) {
        Node node = root;
        if (root.data == n) {
            root = root.next;
            tail.next = root;
            --size;
            return;
        }
        Node temp = node;
        node = node.next;
        while (node != root) {
            if (node.data == n) {
                temp.next = node.next;
                if (node == tail) tail = temp;
                break;
            }
            temp = temp.next;
            node = node.next;
        }
        node = root;
        do {
            System.out.println(node.data);
            node = node.next;
        } while (node != root);
    }

    public int josephCicle(int n) {
        Node node = root;
        Node temp = null;
        while (node.next != node) {
            for (int i = 1; i < n; i++) {
                temp = node;
                node = node.next;
            }
            if (temp == null) {
                root = node.next;
                tail.next = root;
            } else {
                temp.next = node.next;
                if (node == tail) {
                    tail = temp;
                }
            }
            if (temp != null) {
                node = temp.next;
            } else node = root;

        }
        root = tail = node;
        node = root;
        do {
            System.out.println(node.data);
            node = node.next;
        } while (node != root);
        return node.data;
    }

    public int length() {
        int n = 0;
        if (root == null) return n;
        Node node = root;
        do {
            ++n;
            node = node.next;
        } while (node != root);
        return n;
    }

    public Node[] split() {
        Node[] listArr = new Node[2];
        Node slow = root;
        Node fast = root;
        while (fast.next != root && fast.next.next != root) {
            slow = slow.next;
            fast = fast.next;
        }
        Node temp = slow.next;
        slow.next = root;
        tail.next = temp;
        listArr[0] = root;
        listArr[1] = temp;
        return listArr;
    }

    public void callSplit() {
        Node[] listA = split();
        //    Node node = listA[0];
        for (Node node : listA) {
            Node temp = node;
            do {
                System.out.println(temp.data);
                temp = temp.next;
            } while (temp != node);
        }
    }

    public Node nthNode(int n) {
        Node node = root;
        n = n % size;
        for (int i = 1; i < n; i++) {
            node = node.next;
        }
        return node;
    }

    public void splitAtN(int n) {
        Node nodeToSplit = nthNode(n);
        tail.next = nodeToSplit.next;
        nodeToSplit.next = root;
    }

    public void exchangeEnds() {
        Node node = root;
        Node second = root.next;
        while (node.next != tail) {
            node = node.next;
        }
        Node prev = node;
        prev.next = root;
        root.next = tail;
        tail.next = second;
        root = prev.next.next;
        tail = prev.next;
        System.out.println(root.data);
        System.out.println(tail.data);
        System.out.println(root.next.data + " " + root.next.next.data);
        System.out.println(root.next.next.next.next.data);
    }

    public void deleteOdd() {
        Node temp = root.next;
        Node prev = null;
        while (temp.next != root && temp != root) {
            temp.next = temp.next.next;
            prev = temp;
            temp = temp.next;
        }
        if (temp.next == root) {
            temp.next = root.next;
            root = temp.next;
        } else {
            prev.next = temp.next;
            root = prev.next;
        }
    }

    public void deleteEven() {
        if (root == null || root.next == null) {
            return;
        }
        Node node = root.next.next;
        while (node != root && node.next != root) {
            node.next = node.next.next;
        }
        if (node == root) {
            node.next = node.next.next;
        }
        else{
            root.next=root.next.next;
        }
    }
}
