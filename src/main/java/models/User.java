package models;

import java.util.ArrayList;


public class User 
{
    private String id;
    private String name;
    private String email;
    private String birthDate;
    private String password;
    private ArrayList<Home> homes;
    
    public User(){
        this.id = null;
        this.birthDate = null;
        this.name = null;
        this.password = null;
        this.email = null;
        homes = new ArrayList<>();
    }

    public User(String name, String birthDate, String email, String password) {
        this.id = null;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.homes = new ArrayList<>();
    }
   
    public User(String id, String name, String birthDate, String email, String password)
    {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.email = email;
        this.password = password;
        homes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
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

    public ArrayList<Home> getHomes() {
        return homes;
    }

    public void setHomes(ArrayList<Home> homes) {
        this.homes = homes;
    }

   

    @Override
    public String toString() {
        return "User{" + "_id=" + id + ", name=" + name + ", birthDate=" + birthDate + '}';
    }
    
    
    
    
}
