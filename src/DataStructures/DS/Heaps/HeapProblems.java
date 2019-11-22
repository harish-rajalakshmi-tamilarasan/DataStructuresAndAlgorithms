package DataStructures.DS.Heaps;



/**
 * @author Harish T
 */
public class HeapProblems {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 6};
        smallestDearangement(7);
    }

    public static void sortKsorted(int[] arr, int k) {
        for (int i = 0; i < arr.length - 1; i++) {
            buildHeap(arr, i, k + i);
        }
    }

    public static void buildHeap(int[] arr, int start, int end) {
        if (end >= arr.length - 1) {
            int index = (start + arr.length - 1) / 2;
            downHeap(arr, start, arr.length - 1, index);
            return;
        }
        int index = (start + end) / 2;
        while (index >= start) {
            downHeap(arr, start, end, index);
            --index;
        }
    }

    public static void downHeap(int[] arr, int start, int end, int index) {
        int left = (2 * index + 1) - start;
        int right = (2 * index + 2) - start;
        int childVal;
        if (left <= end) {
            childVal = left;
            if (right <= end && arr[right] < arr[childVal]) {
                childVal = right;
            }
            if (arr[index] > arr[childVal]) {
                int temp = arr[index];
                arr[index] = arr[childVal];
                arr[childVal] = temp;
            }
            downHeap(arr, start, end, childVal);
        }
    }

    //Median of running Streams
    public static int medianVal;
    public static MaxHeap maxHeap = new MaxHeap(10);
    public static MinHeap minHeap = new MinHeap(10);
    public static int initialVal = -1;
    public static int secVal = -1;

    public static void median(int value) {
        if (maxHeap.getSize() == 0 && minHeap.getSize() == 0) {
            if (initialVal == -1) {
                initialVal = value;
                medianVal = initialVal;
            } else {
                secVal = value;
                if (initialVal > secVal) {
                    minHeap.insert(initialVal);
                    maxHeap.insert(secVal);
                } else {
                    maxHeap.insert(initialVal);
                    minHeap.insert(secVal);
                }
                medianVal = (initialVal + secVal) / 2;
            }
            return;
        }
        if (value > minHeap.getMin()) minHeap.insert(value);
        else maxHeap.insert(value);
        if (Math.abs(minHeap.getSize() - maxHeap.getSize()) == 2) {
            if (minHeap.getSize() > maxHeap.getSize()) {
                maxHeap.insert(minHeap.removeMin());
            } else {
                minHeap.insert(maxHeap.removeMax());
            }
        }
        if ((minHeap.getSize() + maxHeap.getSize()) % 2 == 0) {
            medianVal = (minHeap.getMin() + maxHeap.getMax()) / 2;
        } else {
            medianVal = minHeap.getSize() < maxHeap.getSize() ? maxHeap.getMax() : minHeap.getMin();
        }
    }


    public static int getKthSmallestElem(int[] arr, int k) {
        MinHeap minHeap = MinHeap.buildHeap(arr);
        while (k > 1) {
            minHeap.removeMin();
            --k;
        }
        return minHeap.removeMin();
    }

    public static int getKthLargestElem(int[] arr, int k) {
        MaxHeap maxHeap = MaxHeap.buildHeap(arr);
        while (k > 1) {
            maxHeap.removeMax();
            --k;
        }
        return maxHeap.removeMax();
    }

    public static void insertStream(MaxHeap maxHeap, int val, int k) {
        if (maxHeap.getSize() < k) {
            maxHeap.insert(val);
        } else {
            if (maxHeap.getMax() > val) {
                maxHeap.removeMax();
                maxHeap.insert(val);
            }
        }
    }

    public static void getKthSmallestElemInStream(int k) {
        MaxHeap maxHeap = new MaxHeap(3);
        int[] arr = {21, 3, 4, 132, 6, 0, 45, 2, 76, 10};
        for (int temp : arr) {
            insertStream(maxHeap, temp, k);
        }
        System.out.println(maxHeap.removeMax());
    }

    public static void connectNRopes(int[] arr) {
        MinHeap minHeap = MinHeap.buildHeap(arr);
        int sum = 0;
        int count = 0;
        while (minHeap.getSize() > 1) {
            sum = minHeap.removeMin() + minHeap.removeMin();
            minHeap.insert(sum);
            count += sum;
        }
        System.out.println(count);
    }

    public static void smallestDearangement(int n) {
        int limit = n;
        boolean isOdd = false;
        if (n % 2 == 1) {
            --limit;
            isOdd = true;
        }
        boolean flipFlop = true;
        int[] arr = new int[n];
        for (int i = 1; i <= limit; i++) {
            if (flipFlop) {
                arr[i - 1] = i + 1;
            } else {
                arr[i - 1] = i - 1;
            }
            flipFlop = !flipFlop;
        }
        if (isOdd) {
            arr[n-1] = arr[n - 2];
            arr[n - 2] = n;
        }
        for(int temp:arr){
            System.out.println(temp);
        }
    }

    public static int kMaxSumSubarray(int[] arr){
        return 0;
    }
}
