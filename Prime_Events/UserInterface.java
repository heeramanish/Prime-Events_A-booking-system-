import java.util.*;
import java.lang.*;
import java.text.ParseException;
/**
 * This class has direct interface with the control, acts as boundary
 * @Start Redirects to the welcome screen
 * @WelcomeScreen method is the screen that will be prompted to the customer when he opens up the Prime event system
 * @login has login credentials
 * @Author(Team 93)
 * @version (19/10/19)
 */
public class UserInterface 
{
    public PrimeEvents pe;
    
    public UserInterface()  throws ParseException
    {
        pe = new PrimeEvents();
        //Start();
    }
    
    /**
     * Start of the program
     */
    public void Start()  throws ParseException
    {
        String option = "0";
        do
        {
            option = welcomeScreen();
            if(option.equals("1"))
            {
                login();
                showCustomerHomeScreen();
            } 
            else
            {
                option ="0";
                System.out.println("\nIncorrect value. Try again\n");
            }
        }
        while(option.equals("0"));
    }
     
    /**
     * displays options for customer
     */
    public void displayOptions()
    {
        System.out.println("\t \t Please Choose an option to proceed.");
        System.out.println("\t \t 1. View halls and Request for quotation");
        System.out.println("\t \t 2. Bookings");
        System.out.println("\t \t 3. Write Review");
        System.out.println("\t \t 4. Log out");
        System.out.println("\t \t 5. Exit System");
    }
    /**
     * displays welcome screen
     */
    public String welcomeScreen() 
    {
        
        Scanner input = new Scanner(System.in);
        System.out.println("+===================================================================+");
        System.out.println("|                                                                   |");
        System.out.println("|         Welcome to the Prime Events  !!                           |");
        System.out.println("|Your directory to all community venues in Melbourne                |");
        System.out.println("+===================================================================+");
        System.out.print("Enter 1 to Login \t \t");
        return input.nextLine();   
    }
    
    public void showCustomerHomeScreen() throws ParseException {
        pe.processCustomerHomeScreenOptions();
    }
    /**
     * login method where customer enters email and password for verification
     */
    public void login()
    {
       boolean isValid = false;
       Scanner input = new Scanner(System.in);
       do
       {
           System.out.println("\n \t \t LOGIN !"); 
           System.out.println("\nEnter your email:\t");
           String email = input.nextLine();
           System.out.println("Enter your password:\t");
           String password = input.nextLine();
           isValid = pe.processLoginPage(email,password);
       }
       while(!isValid);
       System.out.println("\n Login Successful. Press enter to continue");
       input.nextLine();
    }

    public PrimeEvents getPrimeEvents()
    {
        return pe;
    }
     
    public void setPrimeEvenets(PrimeEvents pe)
    { 
        this.pe = pe;
    }
    
    
}
