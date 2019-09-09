package Algorithms;

/**
 * @author Harish T
 */
class Dogs extends Animals{
   public void inherited(){
       System.out.println("adsdsdas");

   }
    public static void test(){
        System.out.println("Toplevelsdsd");
    }
}

public class Animals {
    public static void test() {
        System.out.println("Toplevel");
    }

    public void seocnd() {
        System.out.println("Tp level");
    }
}


