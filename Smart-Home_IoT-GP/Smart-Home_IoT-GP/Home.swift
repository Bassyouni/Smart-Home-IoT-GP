//
//  Home.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class Home
{
    //MARK:- private varibales
    private var _id: String!
    private var _name: String!
    private var _address: String!
    private var _users: [User]!
    private var _devices: [Device]!
 
    
    //MARK:- Constructors
    init()
    {
        _id = ""
        _name = ""
        _address = ""
        _users = [User]()
        _devices = [Device]()
    }
    
    init(id: String, name: String, address: String)
    {
        _id = id
        _name = name
        _address = address
        _users = [User]()
        _devices = [Device]()
    }
    
    init(id: String, name: String, address: String, users: [User], devices: [Device])
    {
        _id = id
        _name = name
        _address = address
        _users = users
        _devices = devices
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
    
    public var address: String {
        set {_address = newValue}
        get {return _address}
    }
    
    
    //MARK:- public functions
    public func addDevice(device: Device)
    {
        _devices.append(device)
    }
    
    public func addUser(user: User)
    {
        _users.append(user)
    }
    
    public func getAllDevices() -> [Device]
    {
        return _devices
    }
    
    public func getAllUsers() -> [User]
    {
        return _users
    }

    
}
