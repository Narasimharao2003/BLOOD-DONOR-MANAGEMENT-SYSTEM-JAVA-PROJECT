package UserModule.UserMenu;
import java.util.*;
import java.io.*;

public class UMenu
{
    
    public static void input(long phn_no) throws InterruptedException, IOException {
        Scanner ip = new Scanner(System.in);
        System.out.println("Enter blood Group: ");
        String Blood_group = ip.nextLine();
        Blood_group=Blood_group.toUpperCase();
        System.out.println("Enter your Name:");
        String User_Name = ip.nextLine();
        System.out.println("Enter Centre Name:");
        String Center_name = ip.nextLine();
        System.out.println("Your Details are Saved!! Your Request Was Sent ");
        System.out.println("Your Donors Will Contact you soon..");
        System.out.println("Your Details are \n" + "Name: " + User_Name + "\tPhone Number:" + phn_no + "\tBlood Group:" + Blood_group + "\tCentre Name: " + Center_name);
        StartModule.StartInterface.Start.menu();
        
    }
    
    public static void DashBoard(long phnnum) throws InterruptedException, IOException 
    {
        long phn_no = phnnum;
        System.out.println("\nAvailable Blood Donor for Each blood group");
        AdminModule.AdminLogin.ALogin.CountBloodGroups();
        System.out.println("\n\033[31m Public Donor List:\033[0m ");
        try {
            try (BufferedReader rd = new BufferedReader(new FileReader("donor_details.txt"))) {
                String line;
                boolean found = false;
                while ((line = rd.readLine()) != null) {
                    String info[] = line.split(",");
                    String mode = info[7].trim();
                    if (mode.equals("Public") || mode.equals("public") ) {
                        found = true;
                        System.out.print("Name: " + info[0]);
                        System.out.print("\tGender: " + info[1]);
                        System.out.print("\tAge: " + info[2]);
                        System.out.print("\tPhone Number: " + info[3]);
                        System.out.print("\tBlood Group: " + info[4]);
                        System.out.print("\tCentre: " + info[5]);
                        System.out.print("\tState: " + info[6]);
                        System.out.println("\tAddress: " + info[7] + "," + info[8]);
                    }
                }
                if (!found) {
                    System.out.println("No Donor List");
                }
            }
        } catch(IOException e) {
            System.out.println("Error");
        }
        System.out.println("\nDo you need more information (Y - Yes/N - No):");
        Scanner ip = new Scanner(System.in);
        String chc = ip.nextLine();
        boolean needMoreInfo = chc.equalsIgnoreCase("Y");
        if (needMoreInfo) {
           input(phn_no);
        } 
        else
        {
        System.out.println("1. Go back to Main menu");
        System.out.println("2. Exit Application");
        int Chc = ip.nextInt();
        if (Chc == 1) {
        StartModule.StartInterface.Start.menu();
         } else {
        System.out.println("Program is Terminated.....");
        System.exit(0);
        }
        }
    }
}

