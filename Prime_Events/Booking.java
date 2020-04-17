
/**
 * This class specifies the attribute and behaviour of the Booking
 * @author (Team 93)
 * @version (19/10/19)
 */
public class Booking
{
    private int bookingId;
    private Quotation quotation;
   

    /**
     * Constructor for objects of class Booking
     */
    public Booking()
    {
        // initialise instance variables
        bookingId = 0;
        quotation = new Quotation();
    }

    public int getBookingId()
    {
        return bookingId;
    }
    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }
    public Quotation getQuotation()
    { 
        return quotation; 
    }
    
    public void setQuotation(Quotation q)
    {
        quotation = q ;
    }
 
}
