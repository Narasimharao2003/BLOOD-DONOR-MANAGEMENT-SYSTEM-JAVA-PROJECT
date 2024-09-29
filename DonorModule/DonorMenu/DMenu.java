package DonorModule.DonorMenu;
import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DMenu
{
    public static void ViewDetails(String id) throws ParseException {
        String temp=id;
        try {
                BufferedReader reader = new BufferedReader(new FileReader("donor_details.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String info[] = line.split(",");
                    String Name = info[0].trim();
                    if(temp.equals(Name))
                    {
                    try {
                        try (BufferedReader r = new BufferedReader(new FileReader("donor_LoginDetails.txt"))) {
                            String loginLine;
                            while ((loginLine = r.readLine()) != null) {
                                String b[] = loginLine.split(",");
                                String user_password = b[1];
                                String user_name = b[0];
                                if (id.equals(user_name)) {
                                    System.out.println("\nUser Name: " + id);
                                    System.out.println("Password: " + user_password);
                                    System.out.println("Name: " + info[0]);
                                    System.out.println("Gender: " + info[1]);
                                    System.out.println("Age: " + info[2]);
                                    System.out.println("Phone Number: " + info[3]);
                                    System.out.println("Blood Group: " + info[4]);
                                    System.out.println("Centre: " + info[5]);
                                    System.out.println("State: " + info[6]);
                                    System.out.println("Mode: " + info[7]);
                                    System.out.println("Adress: " + info[8]+","+info[9]);
                                    break;
                                }
                            }
                            Menu(temp);
                        } catch (IOException e) {
                            System.out.println("Error");
                        }
                        
                      Menu(temp);
                    } catch (IOException e) {
                        System.out.println("Error");
                    }
                 }
                }
            
        } catch (IOException e){
            System.out.println("Error occurred while reading donor details.");
        }
    }
    public static void UpdateDetails(String id) throws IOException, ParseException {
        String temp1 = id;
        String filePath = "donor_details.txt";
        File file = new File(filePath);
        ArrayList<String> dataList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                dataList.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dataList.size(); i++) {
            String info2[] = dataList.get(i).split(",");
            String DName = info2[0].trim();
            if (DName.equals(temp1)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n----- Update Details -----");
                System.out.println("1. Phone Number");
                System.out.println("2. Address");
                System.out.println("3. Go Back");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Enter Phone Number");
                    sc.nextLine();
                    String Updated_phonenumber = sc.nextLine();
                    dataList.set(i, DName + ", " + info2[1] + ", " + info2[2] + ", " + Updated_phonenumber + ", " + info2[4] + ", " + info2[5] + ", " + info2[6] + ", " + info2[7] + ", " + info2[8] + ", " + info2[9]);
                    // write updated data to file
                    FileWriter writer = new FileWriter(filePath);
                    for (String line : dataList) {
                        writer.write(line + "\n");
                    }
                    writer.close();
                    System.out.println("Details updated successfully.");
                    ViewDetails(id);
                } else if (choice == 2) {
                    System.out.println("Enter Address");
                    sc.nextLine();
                    String Updated_address = sc.nextLine();
                    System.out.println("Enter Pincode");
                    Scanner ip=new Scanner(System.in);
                    String Updated_pincode = ip.nextLine();
                    dataList.set(i, DName + ", " + info2[1] + ", " + info2[2] + ", " + info2[3] + ", " + info2[4] + ", " + info2[5]  + ", " + info2[6] + ", " + info2[7] + ", " + Updated_address + ", " + Updated_pincode );
                    // write updated data to file
                    FileWriter writer = new FileWriter(filePath);
                    for (String line : dataList) {
                        writer.write(line + "\n");
                    }
                    writer.close();
                    System.out.println("Details updated successfully.");
                    ViewDetails(id);
                }
                else if(choice==3)
                {
                    Menu(id);
                }
             
              else {
                    System.out.println("Invalid Choice");
                }
            }
        }
    }  

    public static void History(String id) throws IOException, ParseException {
        String Check_User = id;
        String filePath = "History_details.txt";
        File file = new File(filePath);
        ArrayList<String> HistoryList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                HistoryList.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean hasDonationHistory = false; // flag to check if the user has a donation history
        for (int i = 0; i < HistoryList.size(); i++) {
            String A[] = HistoryList.get(i).split(",");
            String HName = A[0].trim();
            if (HName.equals(Check_User)) {
                hasDonationHistory = true;
                String LastDonationDate = A[1].trim();
                System.out.println("Last Donation Date : " + LastDonationDate);
                LocalDate currentDate = LocalDate.now();
                LocalDate date1 = LocalDate.parse(LastDonationDate);
                LocalDate date2 = currentDate;
                long daysBetween = ChronoUnit.DAYS.between(date1, date2);
                if (daysBetween > 90) {
                    System.out.println("You Are Eligible for Donation now");
                    System.out.println("Are you ready to Donate Blood? (Y/N)");
                    Scanner in = new Scanner(System.in);
                    char chc = in.next().charAt(0);
                    if (chc == 'Y') {
                        System.out.println("Enter the Donation Date in Format (YYYY-MM-DD)");
                        String UpdatedDate = in.next();
                        HistoryList.set(i, HName + "," + UpdatedDate);
                        FileWriter writer = new FileWriter(filePath);
                        for (String line : HistoryList) {
                            writer.write(line + "\n");
                        }
                        writer.close();
                        System.out.println("Your Data Is Updated Successfully");
                        Menu(Check_User);
                    } else {
                        Menu(Check_User);
                    }
                } else {
                    System.out.println("You are Not Eligible for Donation");
                    System.out.println("You can Donate Blood Every 3 Months since Your Last Donation is within 3 months ");
                    Menu(Check_User);
                }
            }
        }
        if (!hasDonationHistory) { // if the user has no donation history
            System.out.println("No Donation History");
            System.out.println("Do you want to Donate Blood? (1-'YES'/0-'NO')");
            Scanner sc = new Scanner(System.in);
            int chc = sc.nextInt();
            if (chc == 1) {
                Scanner ip = new Scanner(System.in);
                System.out.println("Enter the Donation Date in Format (YYYY-MM-DD)");
                String UpdatedDate1 = ip.next();
                HistoryList.add(Check_User + "," + UpdatedDate1); // add the donation history with user's name
                FileWriter writer = new FileWriter(filePath);
                for (String line : HistoryList) {
                    writer.write(line + "\n");
                }
                writer.close();
                System.out.println("Your Data Is Updated Successfully");
                Menu(Check_User);
            } else {
                Menu(Check_User);
            }
        }
    }
    
    
 public static void Menu(String id) throws IOException, ParseException
   {
     String ID =id;
     System.out.println("\n\t\t\t**********************************************");
     System.out.println("\t\t\t****       Welcome To Donor Module        ****");
     System.out.println("\t\t\t**********************************************");
     System.out.println("1. View Details ");
     System.out.println("2. Update Details");
     System.out.println("3. Donation History ");
     System.out.println("4. Logout ");
     System.out.print("Enter Choice: ");
     Scanner i = new Scanner(System.in);
        int choice = i.nextInt();
        switch (choice) 
         {
            case 1:
                 ViewDetails(ID);
                 break;
            case 2:
                 UpdateDetails(ID);
                 break;
            case 3:
                History(ID);
                break;
            case 4:
                choice = 0;
                try {
                    StartModule.StartInterface.Start.menu();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                break;

            default:
                    System.out.println("Invalid Choice");
         }
   }

}
