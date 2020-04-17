
/**
 * This class specifies the attribute and behaviour of the Customer class.
 *
 * @author (Team 93)
 * @version (19/10/10)
 */
public class Review
{
    // instance variables - replace the example below with your own
    private int rating;
    private String feedback;
    
    public Review()
    {
        // initialise instance variables
        rating = 0;
        feedback = "";
    }
    
    public Review(int rating,String feedback)
    {
        // initialise instance variables
        this.rating = rating;
        this.feedback = feedback;
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public String getFeedback() {
        int maxLength = 2000;
        if (feedback.length() > maxLength)
            feedback = feedback.substring(0, maxLength);
        return feedback;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "rating=" + rating + ", feedback=" + feedback;
    }
    
}
