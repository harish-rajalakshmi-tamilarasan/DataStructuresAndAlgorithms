package DataStructures.DS;


/**
 * @author Harish T
 */
public class TestDS {
    public static void main(String[] args) throws Exception {
        // System.out.println(StackDynArr.intToPostfix("a+b*(c^d-e)^(f+g*h)-i"));
        int[] array={5,4,3,2,5};
        System.out.println(StackDynArr.prodSum(array));
    }

    void expand() {

    }

    public static void test() {
        MultiLevelSinglyLinkedList list = new MultiLevelSinglyLinkedList();
        int[] a = {10, 5, 12, 7, 11};
        list.addElemList(a);
        MultiLevelSinglyLinkedList listb = new MultiLevelSinglyLinkedList();
        int[] b = {4, 20, 13, 16, 3};
        listb.addElemList(b);
        MultiLevelSinglyLinkedList listc = new MultiLevelSinglyLinkedList();
        int[] c = {2};
        listc.addElemList(c);
        MultiLevelSinglyLinkedList listd = new MultiLevelSinglyLinkedList();
        int[] d = {17, 6};
        listd.addElemList(d);
        MultiLevelSinglyLinkedList liste = new MultiLevelSinglyLinkedList();
        int[] e = {9, 8};
        liste.addElemList(e);
        MultiLevelSinglyLinkedList listf = new MultiLevelSinglyLinkedList();
        int[] f = {19, 15};
        listf.addElemList(f);
        MultiLevelSinglyLinkedList listg = new MultiLevelSinglyLinkedList();
        MultiLevelSinglyLinkedList.setDown(list.root, listb);
        MultiLevelSinglyLinkedList.setDown(list.root.next.next.next, listd);
        MultiLevelSinglyLinkedList.setDown(listb.root.next, listc);
        MultiLevelSinglyLinkedList.setDown(listd.root, liste);
        MultiLevelSinglyLinkedList.setDown(liste.root, listf);
        MultiLevelSinglyLinkedList newList = new MultiLevelSinglyLinkedList();
        newList = list;

        flatten(list.root, newList);
        MultiLevelSinglyLinkedList.Node temp = newList.root;
        while (temp != null) {
            System.out.println(temp.elem);
            temp = temp.next;
        }
    }

    public static void flatten(MultiLevelSinglyLinkedList.Node top, MultiLevelSinglyLinkedList list) {
        MultiLevelSinglyLinkedList.Node temp = top;
        MultiLevelSinglyLinkedList.Node end = list.tail;
        boolean isSet = false;
        while (temp != null) {
            if (temp.down != null) {
                if (!isSet) {
                    top = temp.down.root;
                    isSet = true;
                }
                list.tail.setNext(temp.down.root);
                list.tail = temp.down.tail;
            }
            if (temp == end) {
                temp = null;
            } else {
                temp = temp.next;
            }
        }
        if (!isSet) {
            return;
        }
        flatten(top, list);
    }
}