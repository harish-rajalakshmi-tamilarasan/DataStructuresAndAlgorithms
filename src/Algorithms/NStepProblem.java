package Algorithms;

/**
 * @author Harish T
 */
public class NStepProblem {
    public static int getNoOfWays(int noOfSteps,int a,int b,int c){
     /*   if((a>noOfSteps&&b>noOfSteps)||((noOfSteps%a!=0)&&(noOfSteps%b!=0))){
            return 0;
        }*/
        int n=0;
        for(int x=0;x<=noOfSteps/a;x++){
            if((noOfSteps-(x*a))%b==0){
                n++;
            }
        }
        return n*2;
    }

    public static void main(String[] args) {
        System.out.println(getNoOfWays(10,7,1,1));
    }
}
