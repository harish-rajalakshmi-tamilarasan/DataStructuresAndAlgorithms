package Revision.Heap;


import java.util.PriorityQueue;

/**
 * @author Harish T
 */
public class KeyIndexHeap {
    private static class Key {
        int index;
        int key;

        Key(int key, int index) {
            this.key = key;
            this.index = index;
        }
    }

    private int size;
    private Key[] heapArr;

    public KeyIndexHeap(int capacity) {
        heapArr = new Key[capacity];
    }

    public int size() {
        return size;
    }

    public boolean hasRight(int index) {
        return (2 * index + 2) < size;
    }

    public boolean hasLeft(int index) {
        return (2 * index + 1) < size;
    }

    public int parent(int index) {
        if (index == 0) return -1;
        return (index - 1) / 2;
    }

    public int left(int index) {
        return 2 * index + 1;
    }

    public int right(int index) {
        return 2 * index + 2;
    }

    public Key getMin() {
        Key arr = heapArr[0];
        return arr;
    }

    public Key removeMin() {
        Key arr = heapArr[0];
        heapArr[0] = heapArr[size - 1];
        --size;
        downHeap(0);
        return arr;
    }

    public void swap(int a, int b) {
        Key temp = heapArr[a];
        heapArr[a] = heapArr[b];
        heapArr[b] = temp;
    }

    public void insert(Key key) {
        heapArr[size++] = key;
        upHeap(size - 1);
    }

    public void downHeap(int position) {
        if (hasLeft(position)) {
            int value = left(position);
            if (hasRight(position)) {
                if (heapArr[value].key > heapArr[right(position)].key) {
                    value = right(position);
                }
            }
            if (heapArr[position].key > heapArr[value].key) {
                swap(position, value);
                downHeap(value);
            }

        }
    }

    public void upHeap(int position) {
        int parent = parent(position);
        if (parent != -1) {
            if (heapArr[position].key < heapArr[parent].key) {
                swap(position, parent);
                upHeap(parent);
            }
        }
    }


    public static int[] sortKArrays(int[][] a) {
        int k = a.length;
        int size = 0;
        KeyIndexHeap heap = new KeyIndexHeap(k);
        int[] index = new int[k];
        for (int i = 0; i < k; i++) {
            size += a[i].length;
            heap.insert(new Key(a[i][0], i));
        }
        int offset = 0;
        int[] output = new int[size];
        while (heap.size() != 0) {
            Key temp = heap.removeMin();
            int ind = temp.index;
            int val = temp.key;
            index[ind]++;
            output[offset++] = val;
            if (index[ind] < a[ind].length) {
                heap.insert(new Key(a[ind][index[ind]], ind));
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int[][] a={{1,2,15,98},{2,13,14,16,98},{5,6}};
        for(int temp:sortKArrays(a)){
            System.out.println(temp);
        }
    }
}
