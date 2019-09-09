package DataStructures;

import java.util.*;

/**
 * @author Harish T
 */
public class UpHeap {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(12, 3, 31, 4232, 12123, 1233, 41, 1, 0));
        buildHeap(array, (array.size() - 1) / 2);
        ArrayList<Integer> newArray=new ArrayList<>();
        heapSort(array,newArray);
        for (int temp : newArray) {
            System.out.println(temp);
        }
    }

    public static boolean hasLeft(ArrayList<Integer> list, int j) {
        return list.size() > (left(j));
    }

    public static boolean hasRight(ArrayList<Integer> list, int j) {
        return list.size() > (right(j));
    }

    protected static int parent(int j) {
        return (j - 1) / 2;
    }

    protected static int left(int j) {
        return j * 2 + 1;
    }

    protected static int right(int j) {
        return j * 2 + 2;
    }

    protected static void swap(ArrayList<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public static void upHeap(ArrayList<Integer> list, int j) {
        if (j > 0) {
            if (list.get(parent(j)) > list.get(j)) {
                swap(list, parent(j), j);
                upHeap(list, parent(j));
            }
        }
    }

    public static void downHeap(ArrayList<Integer> list, int j) {
        if (hasLeft(list, j)) {
            int smallIndex = left(j);
            if (hasRight(list, j)) {
                if (list.get(left(j)) > list.get(right(j))) {
                    smallIndex = right(j);
                }
            }
            if (list.get(j) > list.get(smallIndex)) {
                swap(list, smallIndex, j);
                downHeap(list, smallIndex);
            }
        }
    }

    public static void buildHeap(ArrayList<Integer> list, int j) {
        if (j >= 0) {
            downHeap(list, j);
            buildHeap(list, j - 1);
        }
    }

    public static void heapSort(ArrayList<Integer> heapArray, ArrayList<Integer> sortedArray) {
        if (heapArray.size() == 1) {
            sortedArray.add(heapArray.get(0));
        } else {
            sortedArray.add(heapArray.get(0));
            int l = heapArray.size() - 1;
            heapArray.set(0, heapArray.get(l));
            heapArray.remove(l);
            downHeap(heapArray, 0);
            heapSort(heapArray, sortedArray);

        }
    }
}
