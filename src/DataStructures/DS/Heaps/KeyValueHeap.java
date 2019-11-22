package DataStructures.DS.Heaps;

/**
 * @author Harish T
 */

public class KeyValueHeap {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4, 5,34}, {3, 4, 5, 6, 7}, {10, 11, 12, 13, 14}};
        for (int temp : sortKsortedArr(arr)) {
            System.out.println(temp);
        }
    }

    public static int[] sortKsortedArr(int[][] arr) {
        KeyValueHeap heap = new KeyValueHeap(arr.length);
        int[] indexArr = new int[arr.length];
        int[] finalArr = new int[arr.length * arr[1].length+1];
        int size = 0;
        int lastIndex;
        for (int i = 0; i < arr.length; i++) {
            heap.insert(i, arr[i][0]);
        }
        int[] keyValue = heap.removeMin();
        finalArr[size++] = keyValue[1];
        indexArr[keyValue[0]]++;
        lastIndex = keyValue[0];
        while (heap.size() != 0 && size <= finalArr.length) {
            if (indexArr[lastIndex] < arr[lastIndex].length) {
                heap.insert(lastIndex, arr[lastIndex][indexArr[lastIndex]]);
            }
            keyValue = heap.removeMin();
            finalArr[size++] = keyValue[1];
            indexArr[keyValue[0]]++;
            lastIndex = keyValue[0];
        }
        if (heap.size() == 0) {
            for (int i = indexArr[lastIndex]; i < arr[lastIndex].length; i++) {
                finalArr[size++] = arr[lastIndex][i];
            }
        }
        return finalArr;
    }

    private static class Key {
        int value;
        int index;

        Key(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    private Key[] array;
    private int size;

    KeyValueHeap(int capacity) {
        array = new Key[capacity];
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


    public int[] getMin() {
        int[] arr = {array[0].index, array[0].value};
        return arr;
    }

    public int[] removeMin() {
        int[] arr = {array[0].index, array[0].value};
        array[0] = array[size - 1];
        --size;
        downHeap(0);
        return arr;
    }

    public void downHeap(int index) {
        int offset;
        if (hasLeft(index)) {
            offset = left(index);
            if (array[right(index)].value < array[offset].value) {
                offset = right(index);
            }
            if (array[index].value > array[offset].value) {
                Key temp = array[index];
                array[index] = array[offset];
                array[offset] = temp;
                downHeap(offset);
            }
        }

    }

    public void insert(int index, int value) {
        array[size] = new Key(index, value);
        size++;
        upHeap(size - 1);
    }

    public void upHeap(int index) {
        if (parent(index) == -1) return;
        if (array[index].value < array[parent(index)].value) {
            Key temp = array[index];
            array[index] = array[parent(index)];
            array[parent(index)] = temp;
            upHeap(parent(index));
        }
    }


}

