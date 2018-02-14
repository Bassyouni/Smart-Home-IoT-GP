package models;

import java.util.ArrayList;
import java.util.List;


public class User 
{
    private String _id;
    private String name;
    private String email;
    private String birthDate;
    private String password;
    private String updated_at;
    private String created_at;
    
    private static User loggedUser;
    
    public static User getLoggedUser()
    {
        return loggedUser;
    }
    
    public static void setLoggedUser(User user)
    {
        loggedUser = user;
    }
    
    public static boolean isLoggedIn()
    {
        return loggedUser != null;
    }
    
    public User(){
        this._id = null;
        this.birthDate = null;
        this.name = null;
        this.password = null;
        this.email = null;
    }

    public User(String name, String birthDate, String email, String password) {
        this._id = null;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }
   
    public User(String id, String name, String birthDate, String email, String password)
    {
        this._id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  

   

    @Override
    public String toString() {
        return this.name;
    }
    
    
    
    
}
