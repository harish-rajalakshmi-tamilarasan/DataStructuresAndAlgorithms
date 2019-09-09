package Algorithms;

/**
 * @author Harish T
 */
public class PowerRecursion {
    public static void main(String[] args) {
        System.out.println(powerOfNo(3,5));
    }
    public static int powerOfNo(int no,int power){
        if(power==0){
            return 1;
        }
        if(power==1){
            return no;
        }
        if(power==2){
            return no*no;
        }
        if(power%2==0){
            return powerOfNo(no*no,power/2);
        }
        else {
            return no*powerOfNo(no*no,power/2);
        }
    }
}
