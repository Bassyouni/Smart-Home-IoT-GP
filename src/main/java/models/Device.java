/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author cdc
 */
public class Device {
    String _id;
    String name;
    String description;
    String type;
    @SerializedName("pin_number")
    int pinNumber;
    transient ArrayList<Log> logs;

    @Override
    public String toString() {
        return this.name;
    }

    public Device() {
        this._id = null;
        this.name = null;
        this.description = null;
        this.type = null;
        this.pinNumber = -1;
        this.logs = new ArrayList<>();
    }

    public Device(String name, String description, String type, int pinNumber) {
        this._id = null;
        this.name = name;
        this.description = description;
        this.type = type;
        this.pinNumber = pinNumber;
        this.logs = new ArrayList<>();
    }

    public Device(String id, String name, String description, String type, int pinNumber) {
        this._id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.pinNumber = pinNumber;
        this.logs = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this._id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.pinNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Device other = (Device) obj;
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.pinNumber, other.pinNumber)) {
            return false;
        }
        return true;
    }

    
    
    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
}
