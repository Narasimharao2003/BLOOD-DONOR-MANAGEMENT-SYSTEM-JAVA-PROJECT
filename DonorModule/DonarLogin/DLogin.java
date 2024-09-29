package DonorModule.DonarLogin;
import java.util.*;
import java.io.*;
import java.io.Console;
import java.text.ParseException;

public class DLogin {
  public static String userName;
  public static String uname;

  public  static void LoginMenu() throws IOException, InterruptedException{
  Scanner ip = new Scanner(System.in);
    System.out.println("\n\t\t\t**********************************************");
    System.out.println("\t\t\t****\033[31m        Welcome To Donor Module\033[0m         ****");
    System.out.println("\t\t\t**********************************************");
    System.out.println("1. New user");
    System.out.println("2. Existing User");
    System.out.println("3.Logout");
    System.out.print("Enter Choice: ");
    int choice = ip.nextInt();
    try{
    switch(choice)
    {
         case 1: 
            try {
                Sigup();
            } catch (ParseException e) {
                e.printStackTrace();
            }
                  break;
        case 2 :
                 Login();            
                 break;
        case 3: choice=0;
                 StartModule.StartInterface.Start.menu();
                break;
        default :
        System.out.println("Invalid Choice");
    }
}
    catch(InputMismatchException e)
				{
					System.out.println("Invalid Input");
                    LoginMenu();
				}
    ip.close();
}
    public static void Sigup() throws IOException, ParseException, InterruptedException
    {
        Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter Username:");
            String userName = sc.nextLine();
            System.out.print("Enter Password: ");
            Console console = System.console();
                    if (console == null) {
                        System.out.println("Couldn't get Console instance");
                        System.exit(0);
                    }
            char[] passwordArray = console.readPassword("Enter your secret password: (It won't show due to security reasons) \n");
                    String Password = new String(passwordArray);
            try {
                FileWriter fw = new FileWriter("donor_LoginDetails.txt", true);
                fw.write(userName + "," + Password + System.lineSeparator());
                fw.close();
                System.out.println("Donor Login Details Added Successfully\n");
            } catch (IOException e) {
                System.out.println("Error occurred while  SIGUP.");
            }
            AdminModule.AdminLogin.ALogin.AddDonor();
           
            String id=userName;
         DonorModule.DonorMenu.DMenu.Menu(id);
         sc.close();
    }
    public static void Login() throws FileNotFoundException
    {
        String id;
        Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter Username:");
            String userName = sc.nextLine();
            System.out.print("Enter Password: ");
            Console console = System.console();
                    if (console == null) {
                        System.out.println("Couldn't get Console instance");
                        System.exit(0);
                    }
            char[] passwordArray = console.readPassword("Enter your secret password: (It won't show due to security reasons) \n");
                    String Password = new String(passwordArray);
            BufferedReader reader = new BufferedReader(new FileReader("donor_LoginDetails.txt"));
              try {
                String line;
                  while((line=reader.readLine())!=null)
                  {
                    String info[]=line.split(",");
                    String uname=info[0].trim();
                    String pword=info[1].trim();
                    if(userName.equals(uname) && (Password.equals(pword)))
                    {
                        System.out.println("Login Successful");
                        id=userName;
                        DonorModule.DonorMenu.DMenu.Menu( id);
                        break;
                    }
                  }
                        System.out.println("Login unsuccessful, please enter the correct username and password");
                        Login();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sc.close();;
    }
}
