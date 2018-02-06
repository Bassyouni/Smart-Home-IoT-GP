/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author cdc
 */
public class Home {
    String _id;
    String name;
    String address;
    String topic;
    transient ArrayList<Device> devices;
    
    private static Home chosenHome;
    
    public static boolean isHomeChosen()
    {
        return chosenHome != null;
    }
    
    public static void setChosenHome(Home home)
    {
        chosenHome = home;
    }
    public static Home getChosenHome()
    {
        return chosenHome;
    }
    
 
    
    public Device searchForDevice(Device searchedFor)
    {
        int index = this.devices.indexOf(searchedFor);
        if(index == -1)
        {
            return null;
        }
        return this.devices.get(index);
    }
    
    public Device searchForDevice(String searchedForId)
    {
        
        for(int i = 0; i < this.devices.size(); i++)
        {
            if(this.devices.get(i).getId().equals(searchedForId))
            {
                
                return this.devices.get(i);
            }
        }
        return null;
    }
         
            

    @Override
    public String toString() {
        return name;
    }
    
    public Home(){
        this._id = null;
        this.name = null;
        this.address = null;
        this.topic = null;
        this.devices = new ArrayList<>();
    }

    public Home(String homeName, String homeAdress, String topic) {
        this._id = null;
        this.name = homeName;
        this.address = homeAdress;
        this.topic = topic;
        devices = new ArrayList<>();
    }

    
    public Home(String id, String homeName, String homeAdress, String topic) {
        this._id = id;
        this.name = homeName;
        this.address = homeAdress;
        this.topic = topic;
        this.devices = new ArrayList<>();
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public void setId(String id) {
        this._id = id;
    }

   
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    
}
