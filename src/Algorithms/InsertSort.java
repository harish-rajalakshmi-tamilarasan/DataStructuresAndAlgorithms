package Algorithms;


public class InsertSort {
    public static void main(String[] args) {
        int[] a = {3, 3, 4, 123,0,2, 342, 1};
        int k;
        int i;
        for(int j=1;j<a.length-1;j++){
            k=a[j];
            i=j-1;
            while(i>=0&&(k<a[i])){
                a[i+1]=a[i];
                i--;
            }
            a[i+1]=k;
        }
        for(int s=0;s<a.length-1;s++){
            System.out.println(a[s]);
        }

    }
}



