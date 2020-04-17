/**
 * This class specifies the attribute and behaviour of the Customer class.
 *@chkUserList a check on entered login and password 
 *@author (Team93)
 * @version (10/19/19)
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Customer
{
    private ArrayList<User> userList;
   //private ArrayList<Quotation> quotations;
    private User user;
    
    public Customer()
    {
        // initialise instance variables
        userList = new ArrayList<User>();
        //quotations = new ArrayList<Quotation>();
    }

    
    public void setUserInfo(String userName, String password, String email, String accountType, boolean chkCitizen)
    {
        User newUser = new User(userName,password,email,accountType,chkCitizen);
        userList.add(newUser);
    }
    
    public void setUserInfo()
    {
        User normalUser = new User();
        normalUser.customerCredentials();
        userList.add(normalUser);
        User specialUser = new User("senior","password","senior@gmail.com","Customer",true);
        userList.add(specialUser);
        
    }
    
    public User chkUserList(String email,String password)
    {
        
        setUserInfo();
        for(int val = 0; val<userList.size();val++)
        {
            //int attempt = 0;
            String inputUsername,inputPassword;
           
            
            while  ((email.equalsIgnoreCase(userList.get(val).getEmail()) && (password.equals(userList.get(val).getPassword()))))
            {
                if(email.equalsIgnoreCase(userList.get(val).getEmail()) && password.equals(userList.get(val).getPassword()))
                {           
                   return userList.get(val);
                }
                else 
                {
                    System.out.println("Wrong entry!!!. try again");     
                } 
            }   
        }   
        
        return null;    
    } 
    
    
    public User getCustomer(int index)
    { 
        return userList.get(index); 
    }
    
    public void setCustomer(int index,User c)
    { 
        this.userList.add(index,c);
    }
    
    public User getUserEmail(String email)
    {
        for(int val = 0; val<userList.size(); val++)
        {
            if(email.equals(userList.get(val).getEmail()))
            {
                return userList.get(val); 
            }
        }
        return new User(); 
    }
    
}
