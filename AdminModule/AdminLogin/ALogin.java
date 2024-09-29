package AdminModule.AdminLogin;
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

public  class ALogin 
{
    private static String id = "admin";
    private static String pw = "admin";

    public static void AddDonor() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Donor Details:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Gender: ");
        String gender = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Blood Group: ");
        String bloodGroup = sc.nextLine();
        bloodGroup = bloodGroup.toUpperCase();        
        System.out.print("Centre: ");
        String centre = sc.nextLine();
        System.out.print("State: ");
        String state = sc.nextLine();
        System.out.print("Mode (Public/Private): ");
        String mode = sc.nextLine();
        System.out.print("Address: ");
        String add = sc.nextLine();
        System.out.print("Pincode: ");
        String pincode = sc.nextLine();
        try {
            FileWriter fw = new FileWriter("donor_details.txt", true);
            fw.write(name + "," + gender + "," + age + "," + phoneNumber + "," + bloodGroup + "," + centre + "," + state + "," + mode +  "," + add + "," + pincode + "\n");
            fw.close();
            System.out.println("Donor Details Added Successfully\n");
             } catch (IOException e) {
            System.out.println("Error occurred while adding donor details.");
        }
    }


    public static void ListDonor() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("donor_details.txt"));
            String line;
            System.out.println("\n---\033[31m  Donor Details\033[0m  ---");
            while ((line = reader.readLine()) != null) {
                String[] donorInfo = line.split(",");
                System.out.println("Name: " + donorInfo[0]);
                System.out.println("Gender: " + donorInfo[1]);
                System.out.println("Age: " + donorInfo[2]);
                System.out.println("Phone Number: " + donorInfo[3]);
                System.out.println("Blood Group: " + donorInfo[4]);
                System.out.println("Centre: " + donorInfo[5]);
                System.out.println("State: " + donorInfo[6]);
                System.out.println("Mode: " + donorInfo[7]);
                System.out.println("Address: " + donorInfo[8]+" "+ donorInfo[9]);
                System.out.println("---------------------");
            }
            reader.close();
            AdminModule.AdminLogin.ALogin.Amenu();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occurred while reading donor details.");
        }
    }
    

    public  static void CountBloodGroups() throws InterruptedException, IOException   
    {
        try {

            BufferedReader reader = new BufferedReader(new FileReader("donor_details.txt"));
            String line;
            int countAPos = 0, countOPos = 0, countBPos = 0, countABPos = 0;
            int countANeg = 0, countONeg = 0, countBNeg = 0, countABNeg = 0;

            while ((line = reader.readLine()) != null) {
                String[] donorInfo = line.split(",");
                String bloodGroup = donorInfo[4].trim();

                switch (bloodGroup) {
                    case "A+":
                        countAPos++;
                        break;
                    case "O+":
                        countOPos++;
                        break;
                    case "B+":
                        countBPos++;
                        break;
                    case "AB+":
                        countABPos++;
                        break;
                    case "A-":
                        countANeg++;
                        break;
                    case "O-":
                        countONeg++;
                        break;
                    case "B-":
                        countBNeg++;
                        break;
                    case "AB-":
                        countABNeg++;
                        break;
                    default:
                        break;
                }
            }
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("--- Blood Group Donor Counts ---");
            System.out.println("A+: " + countAPos);
            System.out.println("O+: " + countOPos);
            System.out.println("B+: " + countBPos);
            System.out.println("AB+: " + countABPos);
            System.out.println("A-: " + countANeg);
            System.out.println("O-: " + countONeg);
            System.out.println("B-: " + countBNeg);
            System.out.println("AB-: " + countABNeg);
            reader.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occurred while reading donor details.");
        }
    }
    
    public static void ViewData() throws InterruptedException, IOException {
    System.out.println("\n--- View Data ---");
           CountBloodGroups();      
    }
    public static void Amenu() throws InterruptedException, IOException 
    {
        int a=1;
        while (a!=0)
        {
            try{
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----- \033[31mWELCOME\033[0m -----");
        System.out.println("1. Add donor");
        System.out.println("2. List donor");
        System.out.println("3. View data");
        System.out.println("4. Logout");
        System.out.print("Enter Choice: ");
        int choice = sc.nextInt();
        switch (choice) 
        {
            case 1:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                AddDonor();
                Amenu();
                break;
            case 2:
                 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                ListDonor();
                break;
            case 3:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                ViewData();
                Amenu();
                break;
            case 4:
                choice=0;
                StartModule.StartInterface.Start.menu();
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }
    catch(InputMismatchException e)
    {
        System.out.println("Invalid Input");
        Amenu();
    }
        }
    }


public static void Login() throws  IOException, InterruptedException 
{

    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    Scanner ip = new Scanner(System.in);
    System.out.println("\n\t\t\t**********************************************");
    System.out.println("\t\t\t****\033[31m       Welcome To Admin Module \033[0m       ****");
    System.out.println("\t\t\t**********************************************");
    String tempid, temppw;
    boolean valid = false;
    while (!valid) 
    {
        System.out.println("Enter the Id:");
        tempid = ip.nextLine();
        Console console = System.console();
                    if (console == null) {
                        System.out.println("Couldn't get Console instance");
                        System.exit(0);
                    }
            char[] passwordArray = console.readPassword("Enter your secret password: \033[31m (It won't show due to security reasons)\033[0m  \n");
            temppw = new String(passwordArray);
        if (temppw.equals(pw) && tempid.equals(id)) 
        {
            valid = true;
            System.out.println("Login Successfully");
        } else 
        {
            System.out.println("Invalid Details");
        }
    }
    try {
        Amenu();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    ip.close();
}

}
