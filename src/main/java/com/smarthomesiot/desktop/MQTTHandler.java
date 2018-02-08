/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthomesiot.desktop;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Mahmoud Mokhtar
 */
public class MQTTHandler 
{
    private static final String _broker = "tcp://35.167.95.42:1883";
    private static final MemoryPersistence _persistence = new MemoryPersistence();
    private static final int _qos = 2;
    private static final MqttConnectOptions _connectionOptions = new MqttConnectOptions();
    private static MqttClient _client;
    private static MQTTHandler _handler;
    private MQTTHandler()
    {
    }
    
    public static MQTTHandler getInstance() throws MqttException
    {
        if(_handler == null)
        {
            System.out.println("Client Initialized...");
            _client = new MqttClient(_broker, "RaspberryPi_" + (Math.random() * 1000) , _persistence);
            _connectionOptions.setCleanSession(true);
            _client.connect(_connectionOptions);
            _handler = new MQTTHandler();
            System.out.println("Client Connected...");
        }
        return _handler;
    }
    
    public void publish(String topic, String message) throws MqttException
    {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(_qos);
        _client.publish(topic, mqttMessage);
        System.out.println("Topic: " + topic + " - Message: " + message + " sent successfully");
    }
    
    public void subscribe(String topic, IMqttMessageListener listener) throws MqttException
    {
        _client.subscribe(topic, listener);
    }
    
    
    public void disconnect() throws MqttException
    {
        System.out.println("Client Disconnected...");
        _client.disconnect();
    }
    
    
    
    
}
