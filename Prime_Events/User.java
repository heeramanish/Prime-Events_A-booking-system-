
/**
 * This class specifies the attribute and behaviour of the User class
 * @author (Team 93)
 * @version (19/10/19)
 */
public class User
{
    // instance variables - replace the example below with your own
    private String userName;
    private String password;
    private String email;
    private String accountType;
    private boolean chkCitizen;

    public User()
    {
        userName = "";
        password = "";
        email = "";
        accountType = "";
        chkCitizen = false;
        
    }
    
    public User(String userName, String password,String email, String accountType, boolean chkCitizen)
    {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
        this.chkCitizen = chkCitizen;
    }
    
    public void customerCredentials()
    {
         userName="customer";
         password="password";
         email="customer@gmail.com";
         accountType = "Customer";
         chkCitizen= false;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public boolean isCitizen() {
        return chkCitizen;
    }

    public void setCitizen(boolean chkCitizen) {
        this.chkCitizen = chkCitizen;
    }
}
