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
        }
        

    }
    
    init()
    {
        let url = "192.168.1.10"
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
    public func connect() -> Bool
    {
        return Mqtt._mqtt.connect()
    }
    
    public func subscribe(toTopic:String) -> Bool
    {
        if toTopic  != ""
        {
            Mqtt._mqtt.subscribe(toTopic)
            return true
        }
        else
        {
            return false
        }
    }
    
    public func unSuscribe(toTopic:String) -> Bool
    {
        if  toTopic != ""
        {
            Mqtt._mqtt.unsubscribe(toTopic)
            return true
        }
        else
        {
            return false
        }
    }
    
    public func publish(message:String , topic:String) -> Bool
    {
        if  message != "" && topic  != ""
        {
            Mqtt._mqtt.publish(topic, withString: message)
            return true
        }
        else
        {
            return false
        }
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

}
