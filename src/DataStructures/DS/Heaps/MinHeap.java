package DataStructures.DS.Heaps;

/**
 * @author Harish T
 */
public class MinHeap {
    public static void main(String[] args) {
        int[] arr = {12, 3, 54, 9, 7, 0, 123, 1, 43, 4, 1};
        heapSort(arr);
        for(int temp:arr){
            System.out.println(temp);
        }
    }

    private int size = 0;
    private int[] heapArray;

    public MinHeap(int capacity) {
        heapArray = new int[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getParent(int position) {
        if (position < 0 || position > size - 1) return -1;
        if (position == 0) return -1;
        return (position - 1) / 2;
    }

    private int getRight(int position) {
        return position * 2 + 2;
    }

    private int getLeft(int position) {
        return position * 2 + 1;
    }

    private boolean hasRight(int position) {
        return size > (2 * position + 2);
    }

    private boolean hasLeft(int position) {
        return size > (2 * position + 1);
    }

    private void swap(int i, int j) {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public int getMin() {
        return heapArray[0];
    }

    public int removeMin() {
        int minValue = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        --size;
        downHeap(0);
        return minValue;
    }

    public void downHeap(int i) {
        if (hasLeft(i)) {
            int value = getLeft(i);
            if (hasRight(i)) {
                value = heapArray[value] < heapArray[getRight(i)] ? value : getRight(i);
            }
            if (heapArray[i] > heapArray[value]) {
                swap(i, value);
                downHeap(value);
            }
        }
    }

    public void insert(int value) {
        heapArray[size++] = value;
        upHeap(size - 1);
    }

    public void upHeap(int position) {
        if (getParent(position) == -1) return;
        if (heapArray[getParent(position)] > heapArray[position]) {
            swap(position, getParent(position));
            upHeap(getParent(position));
        }
    }

    private void printArr() {
        for (int temp : heapArray) {
            System.out.println(temp);
        }
    }

    public static MinHeap buildHeap(int[] heapArr) {
        MinHeap heap = new MinHeap(heapArr.length);
        heap.size = heapArr.length;
        for (int i = (heapArr.length / 2) + 1; i < heapArr.length; i++) {
            heap.heapArray[i] = heapArr[i];
        }
        int index = heapArr.length / 2;
        while (index >= 0) {
            heap.heapArray[index] = heapArr[index];
            heap.downHeap(index);
            index--;
        }
        return heap;
    }

    public static void heapSort(int[] array) {
        MinHeap heap = buildHeap(array);
        int index = array.length;
        for (int i = 0; i < index; i++) {
            array[i] = heap.removeMin();
        }
    }
}
