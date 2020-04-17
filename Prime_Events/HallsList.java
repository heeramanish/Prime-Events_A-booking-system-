/**
*This class specifies the attribute and behaviour of the Halls
@setHallInformation sets the details regarding the Halls
*@Author(Team 93)
*@Version(19/10/19)
*/
import java.util.ArrayList;
public class HallsList
{
    private ArrayList<Hall> hallsList;
    
    /**
     * Constructor for objects of class HallsList
     */
    public HallsList()
    {
        // initialise instance variables
        hallsList = new ArrayList<Hall>();
    }
    
    public Hall getHall(int index)
    {
        return hallsList.get(index);
    }
    
    public void setHall(ArrayList<Hall> hallsList) {
        hallsList = hallsList;
    }
    
    public int getSize()
    {
        return hallsList.size();
    }
    

    /**
     * Initialise hall information for two halls
     */
    public void setHallInformation()
    {
        int id=0;       
        Hall hallInfo = new Hall(id+1,"Kensington Town Hall","30-34 Bellair St, Kensington VIC","Kensington Town Hall is a former municipal hall in Kensington, Victoria, Australia.",3000,100,5000);
        hallInfo.addReview(3,"Lovely venue for the Foods of CHILE event");
        hallInfo.addReview(4,"Amazing place for food festival");
        hallInfo.addReview(2,"The only thing we didnt like was the sound. The management should spend some money to fix the sound and acoustics.");
        hallsList.add(hallInfo);
        
        hallInfo = new Hall(id+2,"Temperance Hall","199 Napier St, South Melbourne VIC 3205","Performing arts theater in South Melbourne, Australia.",4000,100,7000);
        hallsList.add(hallInfo);   
    
    }
}
