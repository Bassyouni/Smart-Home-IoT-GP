/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mahmoud Mokhtar
 */
public class Message 
{
    private String topic;
    private String command;
    private String platform;
    private String userId;
    private String DeviceId;

    public String getDeviceId() {
        return DeviceId;
    }

    @Override
    public String toString() {
        return "Message{" + "topic=" + topic + ", command=" + command + ", platform=" + platform + ", userId=" + userId + ", DeviceId=" + DeviceId + '}';
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
