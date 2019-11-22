package Revision.Heap;

/**
 * @author Harish T
 */
public class MinHeap {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 1};
        /** MinHeap heap = new MinHeap(6);
         for (int i = 0; i < arr.length; i++) {
         heap.insert(arr[0]);
         }**/
        heapSort(arr);
        for (int temp : arr) {
            System.out.println(temp);
        }
    }

    private int size = 0;
    private int[] heapArr;

    public MinHeap(int capacity) {
        heapArr = new int[capacity];
    }

    public int size() {
        return size;
    }

    public int getMin() {
        return heapArr[0];
    }

    public int removeMin() {
        int value = heapArr[0];
        heapArr[0] = heapArr[size - 1];
        --size;
        downHeap(0);
        return value;
    }

    public boolean hasRight(int position) {
        return (size > 2 * position + 2);
    }

    public boolean hasLeft(int position) {
        return (size > 2 * position + 1);
    }

    public int getParent(int position) {
        if (position <= 0 || position > size - 1) return -1;
        return (position - 1) / 2;
    }

    public int getRight(int position) {
        return position * 2 + 2;
    }

    public int getLeft(int position) {
        return position * 2 + 1;
    }

    public void swap(int a, int b) {
        int temp = heapArr[a];
        heapArr[a] = heapArr[b];
        heapArr[b] = temp;
    }

    public void insert(int element) {
        heapArr[size++] = element;
        upHeap(size - 1);
    }

    public void upHeap(int position) {
        int parent = getParent(position);
        if (parent != -1) {
            if (heapArr[parent] > heapArr[position]) {
                swap(position, parent);
                upHeap(parent);
            }
        }
    }

    public void downHeap(int position) {
        if (hasLeft(position)) {
            int value = getLeft(position);
            if (hasRight(position)) {
                int right = getRight(position);
                if (heapArr[right] < heapArr[value]) {
                    value = right;
                }
            }
            if (heapArr[position] > heapArr[value]) {
                swap(value, position);
                downHeap(value);
            }
        }
    }

    public static void heapSort(int[] arr) {
        int position = arr.length / 2 - 1;
        while (position >= 0) {
            heapify(arr, position,0);
            --position;
        }
        for (int i = arr.length - 1,j=1; i >= 0; i--,j++) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, 0,j);
        }
    }

    public static void heapify(int[] arr, int position, int offset) {
        int value = -1;
        int limit = arr.length - offset;
        if (position * 2 + 1 < limit) {
            value = 2 * position + 1;
            if (position * 2 + 2 < limit) {
                if (arr[value] < arr[position * 2 + 2]) {
                    value = 2 * position + 2;
                }
            }
            if (arr[value] > arr[position]) {
                int temp = arr[value];
                arr[value] = arr[position];
                arr[position] = temp;
                heapify(arr, value,offset);
            }

        }


    }
}
