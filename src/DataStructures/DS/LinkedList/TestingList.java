package DataStructures.DS.LinkedList;

/**
 * @author Harish T
 */
public class TestingList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        LinkedList list=new LinkedList();
        //    int[] arr = {12};
        // int[] arr = {12, 123,65, 123, 456, 9,456, 999,65, 999, 999, 999, 1000,12,908};
        // int[] arr = {1, 2, 3, 4, 5, 6, 10,13,8,-6};
//        linkedList.buildList(arr);
        int[] arr = {1, 3, 5};
        int[] ar = {2, 4, 6, 8};
        for (int temp : arr) {
            linkedList.insert(temp);
        }
        for (int temp : ar) {
            list.insert(temp);
        }
        linkedList.subtraction(linkedList,list);

    }
}
