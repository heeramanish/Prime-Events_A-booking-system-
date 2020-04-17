import java.util.*;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Write a description of class Hall here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hall
{
    private int hallId;
    private String hallName;
    private String hallLocation;
    private String hallDescription;
    private int hallCapacity;
    private double pricePerPerson;
    private double hallSize;
    private ArrayList<Review> hallReview;
    private int[][] availability;
    //private double avgRating;
    
    public Hall()
    {
        // initialise instance variables
        hallId = 0;
        hallName = "";
        hallLocation = "";
        hallDescription ="";
        hallCapacity=0;
        pricePerPerson=0.0;
        hallSize=0.0;
        availability= new int[14][3];
        hallReview = new ArrayList<Review>();     
        //avgRating = 0.0;
    }
    
    public Hall(int hallId, String hallName, String hallLocation, String hallDescription, int hallCapacity, double pricePerPerson, double hallSize) 
    {
        this.hallId = hallId;
        this.hallName = hallName;
        this.hallLocation = hallLocation;
        this.hallDescription = hallDescription;
        
        this.hallCapacity = hallCapacity;
        this.pricePerPerson = pricePerPerson;
        this.hallSize = hallSize;
        this.hallReview = new ArrayList<Review>();
        availability= new int[14][3];
    }
    
    public int getHallId() {
        return hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public String getHallLocation() {
        return hallLocation;
    }

    public String getHallDescription() {
        return hallDescription;
    }

    public double getHallSize() {
        return hallSize;
    }

    public ArrayList<Review> getHallReview() {
        return hallReview;
    }

    public int getHallCapacity() {
        return hallCapacity;
    }
    
    public double getPrice() {
        return pricePerPerson;
    }

    public int[][] getAvailability() {
        return availability;
    }

    public double getAvgRating()
    {
        double avgRating = 0.0;
        for(int val=0;val<hallReview.size();val++)
        {
            avgRating = hallReview.get(val).getRating() + avgRating;  
        }
        return avgRating/hallReview.size();
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public void setHallLocation(String hallLocation) {
        this.hallLocation = hallLocation;
    }

    public void setHallDescription(String hallDescription) {
        this.hallDescription = hallDescription;
    }

    public void setHallSize(double hallSize) {
        this.hallSize = hallSize;
    }

    public void setHallReview(ArrayList<Review> hallReview) {
        this.hallReview = hallReview;
    }

    public void setHallCapacity(int hallCapacity) {
        this.hallCapacity = hallCapacity;
    }
    
     public void setPrice(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public void setAvailability(int[][] availability) {
        this.availability = availability;
    }
    public void addReview(int rating, String feedback)
    {
        Review newReview = new Review(rating,feedback);
        hallReview.add(newReview);
    }
    
    public void displayHall()
    {
        System.out.println("\n \t \t Hall Details !");
        System.out.println("\nHall number : " + getHallId());
        System.out.println("Name of the hall : " + getHallName());
        System.out.println("Location : " + getHallLocation());
        System.out.println("Hall size : " + getHallSize());
        System.out.println("Maximum Capacity : " + getHallCapacity());
        System.out.println("Estimated Price Per Person : " + getPrice());
        System.out.println("Available dates :\n " + checkAvailability());
        System.out.println("Description : " + getHallDescription());
        System.out.println("Average Rating : " + getAvgRating());
        System.out.println("Reviews : ");
        for(Review review:getHallReview())
            System.out.println(review);
        
    }
    public void updateAvailability(Date date, String time) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Date checkDate = new Date();
        int i ,j=4;
        double difference = date.getTime() - checkDate.getTime();
        i=(int) Math.ceil(difference / (24 * 60 * 60 * 1000));
        //System.out.println("i="+i);
        if(time.equals("Morning"))
            j=0;
        if(time.equals("Afternoon"))
            j=1;
        if(time.equals("Evening"))
            j=2;
        availability[i][j]=1;
    }

    public boolean checkAvailability(Date date, String time) throws ParseException 
    {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date checkDate = new Date();
        int i ,j=4;
        double difference = date.getTime() - checkDate.getTime();
        i=(int) Math.ceil(difference / (24 * 60 * 60 * 1000));
        //System.out.println("i="+i);
        if(time.equals("Morning"))
            j=0;
        if(time.equals("Afternoon"))
            j=1;
        if(time.equals("Evening"))
            j=2;

        if(availability[i][j]==0)
            return true;
        else return false;
    }

    public String checkAvailability()
    {
        
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(13);

        List<LocalDate> dateList = new ArrayList<>();
        int j=0;
        if (startDate.isAfter(endDate)) {
            throw new IllegalStateException("starting date must be before or equal to end date");
        }
        String session = "\n                Morning        Afternoon      Evening";
        while (!startDate.isAfter(endDate)) 
        {
            session = session + "\n" + startDate.toString();
            for(int i = 0;i<3;i++)
            {
                if(availability[j][i]==0)
                    session = session + "      Available";
                else
                    session = session + "       Booked";
            }
            dateList.add(startDate);
            startDate = startDate.plusDays(1);
            j++;
        }

        return session;
    }


}
