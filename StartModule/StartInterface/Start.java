package StartModule.StartInterface;
import java.io.*;
import java.util.*;
public class Start
{
	public  static void menu() throws  IOException, InterruptedException
	{
	  //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//it thrown an interrupt exception 
     System.out.println("\n\t\t\t**********************************************************");
		System.out.println("\t\t\t*********       \033[31m BLOOD DONOR MANAGEMENT \033[0m       ***********");
		System.out.println("\t\t\t**********************************************************");
	int a=1;
	while(a!=0)
	{
	        Scanner sc = new Scanner(System.in);
				System.out.println("\n-----\033[31m WELCOME\033[0m  -----");
				System.out.println("1. Login as Admin ");
				System.out.println("2. Login as Donor ");
				System.out.println("3. Login as User ");
				System.out.println("4. Exit ");
				System.out.print("Enter Choice : ");
				int choice =sc.nextInt();
				try{
                switch(choice)
                {
				case 1:
				        AdminModule.AdminLogin.ALogin.Login();	              
						 break;
				case 2: try {
						DonorModule.DonarLogin.DLogin.LoginMenu();
					} catch (IOException e) {
						e.printStackTrace();
					}
				break;
				case 3: UserModule.UserInterface.ULogin.show();
				break;
				case 4: a=0;
				System.out.println("Program is Terminated.....");
				System.exit(0);
				default: 
				System.out.println("Invalid Choice ");
               }
			sc.close();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Invalid Input");
                    menu();
				}
	}
}
	public static void main(String[] args) throws  IOException
	{
	  try {
		menu();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}	
	}
}

