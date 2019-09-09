package Algorithms;

/**
 * @author Harish T
 */
public class StrngFnd {
    public static void main(String[] args) {

    }
    static void findNo(String str,String sub){
        int count=0;
        for(int i=0;i<(str.length()/sub.length());i++){
           str= str.replaceAll(sub," ");

        }

    }
}
