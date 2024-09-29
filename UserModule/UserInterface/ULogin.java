package UserModule.UserInterface;
import java.io.IOException;
import java.util.*;
public class ULogin
{
    public  static long  phnnum;
    public static  void show() throws InterruptedException, IOException
    {
        System.out.println("\n\t\t\t**********************************************");
        System.out.println("\t\t\t****\033[31m        Welcome To User Module\033[0m         ****");
        System.out.println("\t\t\t**********************************************");
        System.out.println("Enter the Phone Number:");
        Scanner ip = new Scanner(System.in);
        phnnum = ip.nextLong();
        UserModule.UserMenu.UMenu.DashBoard(phnnum);
        ip.close();
    }
}
