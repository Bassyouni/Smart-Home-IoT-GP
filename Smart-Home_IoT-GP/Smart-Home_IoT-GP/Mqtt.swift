//
//  Mqtt.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation
import CocoaMQTT


class Mqtt
{
    //MARK:- variables
    private static var _mqtt: CocoaMQTT!
    private var _delegate: CocoaMQTTDelegate!
    
    
    //MARK:- Constructors & destructors
    private func constructor(url:String , port:UInt16)
    {
        if Mqtt._mqtt == nil
        {
            let clientID = "CocoaMQTT-" + String(ProcessInfo().processIdentifier)
            
            Mqtt._mqtt = CocoaMQTT(clientID: clientID, host: url, port: port)
//            Mqtt._mqtt.username = "ubunto"
//            Mqtt._mqtt.password = ""
            Mqtt._mqtt.willMessage = CocoaMQTTWill(topic: "/will", message: "dieout")
//            Mqtt._mqtt.allowUntrustCACertificate = true
            
            
        }
        

    }
    
    init()
    {
        let url = "35.167.95.42"
        constructor(url: url, port: 1883)
    }
    
    init(url:String)
    {
        constructor(url: url, port: 1883)
    }
    
    init(url:String, port:UInt16)
    {
        constructor(url: url, port: port)
    }
    
    deinit
    {
        Mqtt._mqtt.disconnect()
    }

    
    //MARK:- public functions
    public func connect()
    {
        Mqtt._mqtt.connect()
    }
    
    public func subscribe(toTopic:String)
    {

        Mqtt._mqtt.subscribe(toTopic)

    }
    
    public func unSuscribe(toTopic:String)
    {

        Mqtt._mqtt.unsubscribe(toTopic)

    }
    
    public func publish(deviceId:String, command: String , topic:String)
    {
        let message = "{\"deviceId\":\"\(deviceId)\", \"command\":\"\(command)\" , \"platform\":\"ios\", \"userId\":\"\(currentUser.id)\"}"
        print(message)

        Mqtt._mqtt.publish(topic, withString: message)
 
    }
    
    public func publish(message: String, topic: String)
    {
        Mqtt._mqtt.publish(topic, withString: message)
    }
    
    public func ping()
    {
        Mqtt._mqtt.ping()
    }
    
    public func disconnect()
    {
        Mqtt._mqtt.disconnect()
    }
    
    
    //MARK:- public Varibales
    
    var delegate: CocoaMQTTDelegate {
        set { Mqtt._mqtt.delegate = newValue }
        get { return Mqtt._mqtt.delegate! }
    }
    
    var keepAlive: UInt16 {
        set { Mqtt._mqtt.keepAlive = newValue }
        get { return Mqtt._mqtt.keepAlive }
    }
    
    var cleanSession: Bool {
        set { Mqtt._mqtt.cleanSession = newValue }
        get { return Mqtt._mqtt.cleanSession }
    }

}
