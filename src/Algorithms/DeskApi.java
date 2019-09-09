package Algorithms;

/**
 * @author Harish T
 */
public class DeskApi {

    public static void main(String[] args) {
        String ab = "Module,Owner,Open\n" +
                "Admin General Settings,Srihari,212\n" +
                "Admin General Settings,,212\n" +
                "Agent,Karunya,33\n" +
                "Agent,,33\n" +
                "Configurations,SivaShankar,50\n" +
                "Configurations,,50\n" +
                "Custom Groups,Srihari,16\n" +
                "Custom Groups,,16\n" +
                "DC Integration,Venkatesh,25\n" +
                "DC Integration,,25\n" +
                "DC Product Security,Mathan Karthik Rajan,34\n" +
                "DC Product Security,,34\n" +
                "DC-MSP,Siva Hari Ganesan,1\n" +
                "DC-MSP,,1\n" +
                "Home Page and Support Tab,Srihari,16\n" +
                "Home Page and Support Tab,,16\n" +
                "I18N,Yokeswaran,8\n" +
                "I18N,,8\n" +
                "Inventory Mgmt,Prabha,427\n" +
                "Inventory Mgmt,,427\n" +
                "Linux Support,Rangaraj,17\n" +
                "Linux Support,,17\n" +
                "MAC Support,Gokul Krishnan,57\n" +
                "MAC Support,,57\n" +
                "Me-Tracking,Test,1\n" +
                "Me-Tracking,,1\n" +
                "Mobile App,Poovamraj,6\n" +
                "Mobile App,,6\n" +
                "New Features,Krishna,42\n" +
                "New Features,,42\n" +
                "Notification Server,Yuvaraj,7\n" +
                "Notification Server,,7\n" +
                "Patch Crawler,Arun N,20\n" +
                "Patch Crawler,,20\n" +
                "Patch Mgmt,Pavan,8\n" +
                "Patch Mgmt,,8\n" +
                "Remote Control,Divakar,4\n" +
                "Remote Control,,4\n" +
                "Remote Office Mgmt,Yuvaraj,33\n" +
                "Remote Office Mgmt,,33\n" +
                "Reports,Siva Hari Ganesan,20\n" +
                "Reports,,20\n" +
                "SOM,Yuvaraj,163\n" +
                "SOM,,163\n" +
                "SW Deployment,Saranya,61\n" +
                "SW Deployment,,61\n" +
                "Server,Mathan Karthik Rajan,12\n" +
                "Server,,12\n" +
                "Server - Native,Sundar,7\n" +
                "Server - Native,,7\n" +
                "Software Crawler,Narun,28\n" +
                "Software Crawler,,28\n" +
                "Tools Mgmt,Sandeep,9\n" +
                "Tools Mgmt,,9\n" +
                "USB Mgmt,Aravinth JB,20\n" +
                "USB Mgmt,,20\n" +
                "User Management,Srihari,37\n" +
                "User Management,,37\n" +
                "Grand Summary:,,1374\n";
        String[] s = ab.split("\n");
        StringBuilder k = new StringBuilder();
        for (int i = 1; i < s.length; i = i + 2) {
            k.append(s[i]).append("\n");

        }
        ab = k.toString().replaceAll(",", "-");
        System.out.println(ab);

    }

    private static void getUsersList() {

    }
}
