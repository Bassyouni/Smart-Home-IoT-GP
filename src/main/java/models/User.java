package models;


public class User 
{
    private String _id;
    private String name;
    private String birthDate;
    
   
    public User(String id, String name, String birthDate)
    {
        this._id = id;
        this.birthDate = birthDate;
        this.name = name;
    }
    
    public User(){};

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

   

    @Override
    public String toString() {
        return "User{" + "_id=" + _id + ", name=" + name + ", birthDate=" + birthDate + '}';
    }
    
    
    
    
}
