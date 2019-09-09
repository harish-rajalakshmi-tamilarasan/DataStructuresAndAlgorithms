package Algorithms;

/**
 * @author Harish T
 */
public class LinearSearch {

    public static int linearSearch(int[] a, int noToSearch) {
        int i=0;
        while (i<a.length) {
            if(a[i]==noToSearch){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a={12,3,4242,1231233,1,13434,12234,43};
        System.out.println(linearSearch(a,3));
    }
}
