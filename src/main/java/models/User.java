package models;

import java.util.ArrayList;


public class User 
{
    private String id;
    private String name;
    private String birthDate;
    private ArrayList<Home> homes;
    
    public User(){
        this.id = null;
        this.birthDate = null;
        this.name = null;
        homes = new ArrayList<>();
    }

    public User(String name, String birthDate) {
        this.id = null;
        this.name = name;
        this.birthDate = birthDate;
        this.homes = new ArrayList<>();
    }
   
    public User(String id, String name, String birthDate)
    {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
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

   

    @Override
    public String toString() {
        return "User{" + "_id=" + id + ", name=" + name + ", birthDate=" + birthDate + '}';
    }
    
    
    
    
}
