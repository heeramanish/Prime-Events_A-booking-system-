import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;    
/**
 * Acts as the controller class and integrate all classes together
 *@processLoginPage validation check for login credentials
 *@processCustomerHomeScreenOptions prompts the options that are available for the customer
 *@processListOfHalls gives the list of halls available
 *@processSearchHallsView gets the detal of hall based on hallID selected
 *@processRequestForQuotation sends the quatation for approval to the owner of the hall, based on the Event details filled 
 *@processListOfBookings gives the list of booking done, facilitates review for the same
 *@processDisplayReceipt generates the receipt as per the booking
 *@author (Team 93)
 *@version (19/10/19)
 */
public class PrimeEvents
{
    private ArrayList<Customer> customers;
    private Customer customer;
    private User user;
    private ArrayList<Quotation> quotations;
    private HallsList hallsList;
    private ArrayList<Booking> bookings;

    /**
     * Constructor for objects of class PrimeEvents
     */
    
    
    public PrimeEvents()
    {
        customers = new ArrayList<Customer>();
        customer= new Customer();
        user = new User();
        hallsList = new HallsList();
        hallsList.setHallInformation();
        quotations = new ArrayList<Quotation>();
        bookings = new ArrayList<Booking>();
    }

    private void clearScreen() {  
        
        System.out.print('\u000C'); 
    }  
    
    /**
     * Validating user credentials
     *
     * @param  email  email of user
     * @param password password of user
     * @return    valid user or not
     */
    public boolean processLoginPage(String email,String password)
    {
        user = customer.chkUserList(email, password);
        if(user != null)
           return true;
        return false;
    }
    
    /**
     * process the home screen options for customer
     */
    public void processCustomerHomeScreenOptions() throws ParseException
    {
        //clearScreen();
        UserInterface ui = new UserInterface();
        Scanner input = new Scanner(System.in);
        int hallNumber = 0;
        
        ui.displayOptions();
        System.out.print("\t \t Enter your choice: ");
        String option = input.nextLine();
        if(option.equals("1"))
            processListOfHalls();
        else if(option.equals("2"))
            processListOfQuotations();
        else if(option.equals("3"))            
            processListOfBookings();
        else if(option.equals("4"))    
        {
            //UserInterface ui = new UserInterface();
            ui.Start();
            clearScreen();
        }
        else if(option.equals("5"))
        {
            System.out.println("\n\n\t!!You are about to exit the system!!");
            System.out.println("\t\t\t\t!!BYE!!");
            System.exit(0);
        }
        else
        {
            System.out.println("\nInvalid data. Try again\n\n\t");
            processCustomerHomeScreenOptions();
        }              
        
    }
    
    /**
     * shows the list of halls
     */
    public void processListOfHalls() throws ParseException
    {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("\n \t \t View halls and book with us !");
        for(int val=0;val<hallsList.getSize();val++)
        {
            System.out.println("Hall ID: "+hallsList.getHall(val).getHallId() + "\n" + "Hall Name: "+hallsList.getHall(val).getHallName()+"\n" +"Address: "+ hallsList.getHall(val).getHallLocation() +"\n"+"Average Rating :"+ hallsList.getHall(val).getAvgRating()+"\n");
        }
        System.out.print("\n Enter Hall ID to view details : ");
        int hallNumber = Integer.parseInt(input.nextLine());
        processSearchHallsView(hallNumber);
        
    }
    
    /**
     * Get details of the hall whose number is chosen by customer
     *
     * @param  hallNumber  hall number
     */
    public void processSearchHallsView(int hallNumber) throws ParseException
    {
        Scanner input = new Scanner(System.in);
        Hall requestedHall = new Hall();
        int flag = 0;
        for(int val=0;val<hallsList.getSize();val++)
        {
            if(hallsList.getHall(val).getHallId() == hallNumber)
            {
                hallsList.getHall(val).displayHall();
                requestedHall=hallsList.getHall(val);
                flag = 1;
                break;
            }
        }      
        
        String choice = "0";
        if(flag == 1)
        {  
            do
            {
                System.out.println("\n1. Request for Quotation");
                System.out.println("2. Go to main menu");
                System.out.print("Enter choice : ");
                choice = input.nextLine();
            
                if(choice.equals("1"))
                    processRequestForQuotation(requestedHall);
                else if(choice.equals("2"))
                    processCustomerHomeScreenOptions();
                else 
                {
                    choice = "0";
                    System.out.println("Invalid. Enter 1 or 2");  
                }
            }
            while(choice.equals("0"));
        }
        else
        {
            System.out.println("\n Hall ID not found. ");
            System.out.println("\n Press Enter to go back to list of halls screen");
            input.nextLine();
            processListOfHalls();
        }
    }
    
    /**
     * Process request for quotation
     *
     * @param  requestedHall  hall object which is selected by customer
     */
    public void processRequestForQuotation(Hall requestedHall) throws ParseException
    {
        Scanner input = new Scanner(System.in);
        Quotation quotation = new Quotation();
        quotation.setHall(requestedHall);
        System.out.println("\n \t \t Request for Quotation !");
        String type = "0";
        do
        {
            System.out.println("Enter the event type:");
            System.out.println("1.Wedding ceremony\n2.Wedding reception\n3.Birthday\n4.Anniversary");
            System.out.print("Enter your choice: ");
            type = input.nextLine();
            if(type.equals("1"))
                quotation.setEventType("Wedding Ceremony");
            else if(type.equals("2"))
                quotation.setEventType("Wedding Reception");
            else if(type.equals("3"))
                quotation.setEventType("Birthday");
            else if(type.equals("4"))
                quotation.setEventType("Anniversary");
            else
            {
                type = "0";
                System.out.println("Invalid. Enter numbers from 1-4"); 
            }
        }
        while(type.equals("0"));
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate;
        int i;
        String bookingDate;
        do
        {
            do
            {
                System.out.print("Enter booking Date:(dd/MM/YYYY)");
                bookingDate = input.nextLine();
                newDate = simpleDateFormat.parse(bookingDate);
                Date compareDate = new Date();
                double difference = newDate.getTime() - compareDate.getTime();
                i=(int) Math.ceil(difference / (24 * 60 * 60 * 1000));
                if(i>14||i<0)
                    System.out.println("Please Enter correct date within 2 weeks of range");
                    
            }while(i>14||i<0);
        
        
            quotation.setEventBookingDate(newDate);
            String choice = "0";
            do
            {
                System.out.println("Enter the event time");
                System.out.println("\t \t 1. Morning 2. Afternoon 3. Evening");
                System.out.print("Enter your choice: ");
                choice = input.nextLine();
                if(choice.equals("1"))
                quotation.setEventTime("Morning");
                else if(choice.equals("2"))
                    quotation.setEventTime("Afternoon");
                else if(choice.equals("3"))
                    quotation.setEventTime("Evening");
                else
                {
                    choice = "0";
                    System.out.println("Invalid. Enter 1,2 or 3"); 
                }
                
            }
            while(choice.equals("0"));
            if(!hallsList.getHall(requestedHall.getHallId()-1).checkAvailability(quotation.getEventBookingDate(), quotation.getEventTime()))
            {

                System.out.println("The hall is already booked for the given time.");
                input.nextLine();
                choice ="0";
            }
        }while(!hallsList.getHall(requestedHall.getHallId()-1).checkAvailability(quotation.getEventBookingDate(), quotation.getEventTime()));
            
        System.out.print("Enter the number of people attending the event : ");
        int count = input.nextInt();
        if(requestedHall.getHallCapacity() >= count)
            quotation.setMaxCapacity(count);
        else
        {
            System.out.println("Hall capacity is lesser than given count");
            System.out.println("Press enter to go to main menu");
            input.nextLine(); input.nextLine();
            processCustomerHomeScreenOptions();
        } 
        System.out.println("\n \t \t ****Additional 20$ per person for catering****");
        input.nextLine();
        String catering;
        do
        {
            System.out.print("Should catering be included (y/n) : ");
            catering = input.nextLine();
            if( catering.equals("y") || catering.equals("Y"))
                quotation.setCatering(true);
            else if(catering.equals("n") || catering.equals("N"))
                quotation.setCatering(false); 
            else 
            {
                catering = "x";
                System.out.println("Invalid. Enter y/n");
            }
        }
        while(catering.equals("x"));
        double discount = 0.0;
        String chkDiscount;
        if(!user.isCitizen())
        {
            do
            {
                System.out.print("Do you have Discount coupon? (y/n): ");
                chkDiscount = input.nextLine();
                if(chkDiscount.equals("y") || chkDiscount.equals("Y"))
                {
                    do{
                        System.out.print("Enter the discount code to redeem your discount : ");
                        String code = input.nextLine();
                        discount = quotation.checkCoupon(code);
                        if(discount == 0.0)
                        {
                            System.out.println("Wrong code.Try again");
                        }
                    }while(discount == 0.0);
                }
                else if (chkDiscount.equals("n") || chkDiscount.equals("N"))
                {
                    System.out.println("Discount Not Applied ");  
                    calculatePrice(quotation.getMaxCapacity(),requestedHall.getPrice(),discount,quotation.isCatering());
                }
                else
                {
                    chkDiscount ="x";
                    System.out.println("Invalid. Enter y/n");
                }
            }
            while(chkDiscount.equals("x"));
        }
        else 
            discount = 0.2;
        double t_amount = calculatePrice(quotation.getMaxCapacity(),requestedHall.getPrice(),discount,quotation.isCatering());
        double d_amount = t_amount/2;
        System.out.println("\nTotal amount to be paid after discount is : " + t_amount);
        quotation.setTotalAmount(t_amount);
        System.out.println("Deposit amount to be paid is : " + d_amount);
        quotation.setDepositAmount(d_amount);
        quotation.setUser(user);
        quotations.add(quotation);
        System.out.println("Press enter to request for quotation");
        input.nextLine();
        System.out.println("Quotation requested successfully. Press enter to go back to main menu");
        input.nextLine();
        processCustomerHomeScreenOptions();
        
    }
    /**
     * process list of quotation and proceeds with booking
     */
    public void processListOfQuotations() throws ParseException
    {  
       Scanner input = new Scanner(System.in);
       String catering = "";
       System.out.println("List of Quotations"); 
       if(quotations.size()>0)
       {
           int quotationId=0;
           for(Quotation quotation:quotations)
           {           
               Booking booking = new Booking();
               booking.setBookingId(quotationId + 1);
               booking.setQuotation(quotation);
               hallsList.getHall((quotation.getHall().getHallId())-1).updateAvailability(quotation.getEventBookingDate(),quotation.getEventTime());
               bookings.add(booking);
               if(quotation.isCatering())
                    catering = "yes";
               else 
                    catering = "no";
               System.out.println("Quotation ID:"+(quotationId+1)+"\n"+"Event Type: "+quotation.getEventType() + "\n" +"Booking Date: "+quotation.getEventBookingDate() + "\n" +"Time: "+quotation.getEventTime() + "\n" +"Catering: "+ catering + "\n" + "Total Amount: "+quotation.getTotalAmount()+"\n" +"Deposit: "+ quotation.getDepositAmount() +"\n");
               quotationId++;
           }
           System.out.print("Enter quotation id to proceed with booking: ");
           int bookingId = Integer.parseInt(input.nextLine());
           System.out.print("Confirm booking ? (y/n) : ");
           String choice;   
           do
           {
               choice = input.nextLine(); 
               if(choice.equals("Y") || choice.equals("y"))
               {
                   processDisplayBooking(bookingId);
                   System.out.print("Can we proceed with payment ? (y/n) : ");           
                   String option ;
                   do
                   {
                       option = input.nextLine();
                       if(option.equals("Y") || option.equals("y"))
                       {
                            System.out.println("\t\tPAYMENT HAS BEEN DONE. Press Enter to see the receipt. ");
                            input.nextLine();
                            processDisplayReceipt(bookingId);
                       }
                       else if(option.equals("N") || option.equals("n"))
                       {
                            System.out.println("\n You have not opted to proceed with payment.");
                            System.out.println("\n Press enter to view halls. ");
                            input.nextLine();
                            processListOfHalls();
                       }
            
                       else           
                       {
                           option = "x";
                           System.out.println("Invalid entry. Enter y/n for payment");
                       }
                    }
                    while(option.equals("x"));                       
               }
        
               else if(choice.equals("N") || choice.equals("n"))
               {
                    System.out.println("\n You have not opted to proceed with booking.");
                    System.out.println("\n Press enter to view halls. ");
                    input.nextLine();
                    processListOfHalls();
               }
               else
               {
                    choice = "x";
                    System.out.println("\n Invalid entry. Enter y/n for booking");
               }
           }
           while(choice.equals("x"));
       }
       else
       {
           System.out.println("No Quotations Requested yet. ");
           System.out.println("Press enter to main menu");
           input.nextLine();
           processCustomerHomeScreenOptions();
       }
    }
    /**
     * displays booking details
     */
    public void processListOfBookings() throws ParseException
    {  
       Scanner input = new Scanner(System.in);
       System.out.println("List of your Bookings"); 
       int b_id=0;
       if(bookings.size()>0)
       {
           for(Booking booking : bookings)
           {
                b_id=booking.getBookingId();
                System.out.println("Booking Id : " + booking.getBookingId()); 
                System.out.println("Hall Name : " + booking.getQuotation().getHall().getHallName());
                System.out.println("Location : " + booking.getQuotation().getHall().getHallLocation());
                System.out.println("Type of event : " + booking.getQuotation().getEventType());
                System.out.println("Date of event : " + booking.getQuotation().getEventBookingDate());
                System.out.println("Time of event : " + booking.getQuotation().getEventTime());
                System.out.println("Total amount to be paid : " + booking.getQuotation().getTotalAmount());
                System.out.println("Deposit amount to be paid : " + booking.getQuotation().getDepositAmount());
                System.out.println("Average Rating : " + booking.getQuotation().getHall().getAvgRating());
                System.out.println("Reviews : ");
                for(Review review:booking.getQuotation().getHall().getHallReview())
                    System.out.println(review);
                b_id++;    
           }
           System.out.println("\nWould you like to give a review for your booking?(y/n)"); 
           String option = input.nextLine();
           int flag=0;
           do
           {
               if(option.equals("Y") || option.equals("y"))
               {
                    System.out.println("\n Enter a booking ID for which you want to write a review. ");
                    int id = Integer.parseInt(input.nextLine());
                    
                    for(Booking b : bookings)
                    {
                        //System.out.println("getBookindId() and id: "+b.getBookingId()+" "+id);
                        if(b.getBookingId() == id)
                        flag = 1;
                        break;
                    }
                    id--;
                    if(flag==1)
                    {
                        int rating;
                        String feedback;
                        System.out.println("\n Rate your experience:");
                        rating = Integer.parseInt(input.nextLine());
                        System.out.println("\n Write review for your stay:");
                        feedback = input.nextLine();
                        bookings.get(id).getQuotation().getHall().addReview(rating,feedback);
                        System.out.println("\n Thank you for sharing your experience. ");
                        System.out.println("\n Press enter to main menu. ");
                        input.nextLine();
                        processCustomerHomeScreenOptions();                       
                    }
                    else
                    {
                        System.out.println("\n Booking Id not found");
                        System.out.println("\n Press enter to main menu. ");
                        input.nextLine();
                        processCustomerHomeScreenOptions();
                    }
                }
                else if(option.equals("N") || option.equals("n"))
                {
                    System.out.println("\n You have not opted to write a review.");
                    System.out.println("\n Press enter to main menu. ");
                    input.nextLine();
                    processCustomerHomeScreenOptions();
                }
                else           
                {
                    option = "x";
                    System.out.println("Invalid. Enter y/n");
                }
           }while(option.equals("x"));
         
        }
        else
        {
           System.out.println("\n In order to write a review, you need to make atleast one bookings"); 
           System.out.println("Press enter to main menu");
           input.nextLine();
           processCustomerHomeScreenOptions();
        }
    }
    
    /**
     * Gets the booking details
     *
     * @param  bookingId  id of booking
     */
    public void processDisplayBooking(int bookingId)
    {
        Booking booking = new Booking();
        for(Booking b : bookings)
        {
            if(b.getBookingId() == bookingId)
                booking = b;
        }
        System.out.println("\n\t\tBooking Details !");
        System.out.println("Hall Name : " + booking.getQuotation().getHall().getHallName());
        System.out.println("Location : " + booking.getQuotation().getHall().getHallLocation());
        System.out.println("Type of event : " + booking.getQuotation().getEventType());
        System.out.println("Date of event : " + booking.getQuotation().getEventBookingDate());
        System.out.println("Time of event : " + booking.getQuotation().getEventTime());
        System.out.println("Number of people attended : " + booking.getQuotation().getMaxCapacity());
        System.out.println("Total amount: " + booking.getQuotation().getTotalAmount());
        System.out.println("Deposit amount: " + booking.getQuotation().getDepositAmount());
        
    }
    
    /**
     * Gets the receipt display
     *
     * @param  bookingId  id of booking
     */
    public void processDisplayReceipt(int bookingId) throws ParseException
    {
        Scanner input = new Scanner(System.in);
        Booking booking = new Booking();
        for(Booking b : bookings)
        {
            if(b.getBookingId() == bookingId)
                booking = b;
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("                          RECEIPT                                    ");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Customer Name : " + user.getUserName());
        System.out.println("Hall Name : " + booking.getQuotation().getHall().getHallName());
        System.out.println("Location : " + booking.getQuotation().getHall().getHallLocation());
        System.out.println("Type of event : " + booking.getQuotation().getEventType());
        System.out.println("Date of event : " + booking.getQuotation().getEventBookingDate());
        System.out.println("Time of event : " + booking.getQuotation().getEventTime());
        System.out.println("Number of people attending : " + booking.getQuotation().getMaxCapacity());
        System.out.println("Total amount to be paid : " + booking.getQuotation().getTotalAmount());
        System.out.println("Deposit amount paid : " + booking.getQuotation().getDepositAmount());
        System.out.println("Booking Status : BOOKED");
        System.out.println("----------------------------------------------------------------------");       
        System.out.println("Press enter to go to main menu");
        input.nextLine();
        processCustomerHomeScreenOptions();
    }
    /**
     * calculates total amount to be paid by the customer
     * @param noOfPeople number of people attending event
     * @param hallPrice price of hall per person
     * @param discount gives discount percent
     * @param catering shows whether catering is there or not
     * @return total total price
     */
    public double calculatePrice(int noOfPeople,double hallPrice,double discount,boolean catering)
    {
        double total = 0.0;
        double cateringCharge = 0.0;
        if(catering)
            cateringCharge = noOfPeople * 20;
        total = ((noOfPeople * hallPrice) + cateringCharge) - ((noOfPeople * hallPrice) + cateringCharge) * discount;
        return total;
    }
    
    public ArrayList<Customer> getCustomers()
    {
        return customers;
    }
    
    
    public void setCustomers(ArrayList<Customer> customers)
    {
        this.customers = customers;
    }
    
    public Quotation getQuotation(int index)
    {
        return quotations.get(index);
    }
    
    
    public void setQuotation(int index,Quotation quotation)
    {
        quotations.set(index,quotation);
    }
    
    public Customer getCustomer(int index)
    {
        return customers.get(index);
    }
    
    public void setCustomer(int index,Customer customer)
    {
        customers.add(index,customer);
    }
    
}
