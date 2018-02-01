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
public class Device {
    String id;
    String name;
    String description;
    String type;
    String pinNumber;
    ArrayList<Log> logs;

    public Device() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.type = null;
        this.pinNumber = null;
        this.logs = new ArrayList<>();
    }

    public Device(String name, String description, String type, String pinNumber) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.type = type;
        this.pinNumber = pinNumber;
        this.logs = new ArrayList<>();
    }

    public Device(String id, String name, String description, String type, String pinNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.pinNumber = pinNumber;
        this.logs = new ArrayList<>();
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }
}
