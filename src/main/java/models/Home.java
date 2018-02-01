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
    String id;
    String homeName;
    String homeAdress;
    String topic;
    ArrayList<Device> devices;
    
    public Home(){
        this.id = null;
        this.homeName = null;
        this.homeAdress = null;
        this.topic = null;
        this.devices = new ArrayList<>();
    }

    public Home(String homeName, String homeAdress, String topic) {
        this.id = null;
        this.homeName = homeName;
        this.homeAdress = homeAdress;
        this.topic = topic;
        devices = new ArrayList<>();
    }

    
    public Home(String id, String homeName, String homeAdress, String topic) {
        this.id = id;
        this.homeName = homeName;
        this.homeAdress = homeAdress;
        this.topic = topic;
        this.devices = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    
}
