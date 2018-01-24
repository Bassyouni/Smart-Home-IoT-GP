//
//  Device.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class Device
{
    //MARK:- enum for type
    public enum DeviceType: String
    {
        case On_Off  = "on/off"
        
    }
    
    
    //MARK:- private variables
    private var _id: String!
    private var _name: String!
    private var _pinNumber: Int!
    private var _description: String!
    private var _type :DeviceType!
    private var _home: Home!
    private var _logs: [Log]!
    
    
    //MARK:- Constructors
    init()
    {
        _id = ""
        _name = ""
        _pinNumber = 0
        _description = ""
        _type = .On_Off
        _home = Home()
        _logs = [Log]()
    }
    
    init(id: String, name: String, pinNumber: Int, description: String)
    {
        _id = id
        _name = name
        _pinNumber = pinNumber
        _description = description
        _type = .On_Off
        _home = Home()
        _logs = [Log]()
    }
    
    init(id: String, name: String, pinNumber: Int, description: String, type: DeviceType)
    {
        _id = id
        _name = name
        _pinNumber = pinNumber
        _description = description
        _type = type
        _home = Home()
        _logs = [Log]()
    }

    init(id: String, name: String, pinNumber: Int, description: String, type: DeviceType, home: Home)
    {
        _id = id
        _name = name
        _pinNumber = pinNumber
        _description = description
        _type = type
        _home = home
        _logs = [Log]()
    }
    
    init(id: String, name: String, pinNumber: Int, description: String, type: DeviceType, home: Home, logs: [Log])
    {
        _id = id
        _name = name
        _pinNumber = pinNumber
        _description = description
        _type = type
        _home = home
        _logs = logs
    }
    
    
    //MARK:- public functions
    public func addLog(log: Log)
    {
        _logs.append(log)
    }
    
    public func getAllLogs() -> [Log]
    {
        return _logs
    }
    
    
    //MARK:- public varibales
    public var id: String {
        set {_id = newValue}
        get {return _id}
    }
    
    public var name: String {
        set {_name = newValue}
        get {return _name}
    }
    
    public var pinNumber: Int {
        set {_pinNumber = newValue}
        get {return _pinNumber}
    }
    
    public var description: String {
        set {_description = newValue}
        get {return _description}
    }
    
    public var type: DeviceType {
        set {_type = newValue}
        get {return _type}
    }
    
    public var home: Home {
        set {_home = newValue}
        get {return _home}
    }
    
}

