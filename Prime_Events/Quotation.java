import java.util.*;
/**
 * This class specifies the attribute and behaviour of the Quotation class
 * @checkCoupon a validation for the coupon entered
 * @author (Team93)
 * @version (10/19/19)
 */
public class Quotation
{
    //private int quotationId;
    //private int ownerId;
    private Hall hall;
    private String eventType;
    private Date eventBookingDate;
    private String eventTime;
    private int maxCapacity;
    private boolean catering;
    private String paymentStatus;
    private double totalAmount;
    private double depositAmount;
    private User user;
    

    /**
     * Constructor for objects of class Quotation
     */
    public Quotation()
    {
        hall = new Hall();
        eventType = "";
        eventBookingDate = new Date();
        eventTime = "";
        maxCapacity = 0;
        catering = false;
        //this.paymentStatus = paymentStatus;
        totalAmount = 0.0;
        depositAmount = 0.0;
        
        
    }
    
    public Quotation(Hall hall, String eventType, Date eventBookingDate, String eventTime, int maxCapacity, boolean catering, double totalAmount, double depositAmount) 
    {
        
        this.hall = hall;
        this.eventType = eventType;
        this.eventBookingDate = eventBookingDate;
        this.eventTime = eventTime;
        this.maxCapacity = maxCapacity;
        this.catering = catering;
        //this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.depositAmount = depositAmount;
    }

    public User getUser()
    {

        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public Hall getHall() 
    {
        return hall;
    }

    public void setHall(Hall hall) 
    {
        this.hall = hall;
    }
  
    

    public String getEventType() 
    {
        return eventType;
    }

    public void setEventType(String eventType) 
    {
        this.eventType = eventType;
    }

    public Date getEventBookingDate() 
    {
        return eventBookingDate;
    }

    public void setEventBookingDate(Date eventBookingDate) 
    {
        this.eventBookingDate = eventBookingDate;
    }

    public String getEventTime() 
    {
        return eventTime;
    }

    public void setEventTime(String eventTime) 
    {
        this.eventTime = eventTime;
    }

    public int getMaxCapacity() 
    {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) 
    {
        this.maxCapacity = maxCapacity;
    }

    public boolean isCatering() 
    {
        return catering;
    }

    public void setCatering(boolean catering) 
    {
        this.catering = catering;
    }

    public String getPaymentStatus() 
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) 
    {
        this.paymentStatus = paymentStatus;
    }

    public double getTotalAmount() 
    {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public double getDepositAmount() 
    {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) 
    {
        this.depositAmount = depositAmount;
    }
    
    public double checkCoupon(String coupon)
    {
        double discount = 0.15;
        if(coupon.equals("PRIME") || coupon.equals("prime"))
            return discount;
        return 0.0;
    }
    
   
    
    public void displayQuotation()
    {
        System.out.println("\n \t \t Hall Name !"+hall.getHallName());
        System.out.println("Event Type : " + getEventType());
        System.out.println("Booking Date : " + getEventBookingDate());
        System.out.println("Time : " + getEventTime());
        System.out.println("Catering : " + isCatering());
        System.out.println("Max Capacity : " + getMaxCapacity());
        //System.out.println("Available dates : ");
        System.out.println("Total : " + getTotalAmount());
        System.out.println("Deposit : " + getDepositAmount());       
    }
}

