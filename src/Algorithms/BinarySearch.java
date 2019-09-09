package Algorithms;

/**
 * @author Harish T
 */
public class BinarySearch {

    public static int binarySearch(int key, int[] a) {
        if (key > a[a.length - 1] || key < a[0]) {
            return -1;
        }
        int i = 0;
        int n = a.length - 1;
        while(i<=n){
            int m=i+((n-i)/2);
            if(key==a[m]){
                return m;
            }
            else if(key<a[m]){
                n=m-1;
            }
            else if(key>a[m]){
                i=m+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 123, 2345, 665345, 1123432423};
        System.out.println(binarySearch(1, a));

    }
}
