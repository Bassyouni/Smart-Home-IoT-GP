//
//  User.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class User
{
    //MARK:- private varibales
    private var _id: String!
    private var _name: String!
    private var _birthDate: String!
    private var _homes: [Home]!
    
    
    //MARK:- constructors
    public init()
    {
        _id = ""
        _name = ""
        _birthDate = ""
        _homes = [Home]()
    }
    
    init(id: String, name: String, birthDate: String)
    {
        _id = id
        _name = name
        _birthDate = birthDate
        _homes = [Home]()
    }
    
    init(id: String, name: String, birthDate: String, homes: [Home])
    {
        _id = id
        _name = name
        _birthDate = birthDate
        _homes = homes
    }
    
    
    //MARK:- public functions
    public func addHome(home: Home)
    {
        _homes.append(home)
    }
    
    public func getAllHomes() -> [Home]
    {
        return _homes
    }
    
    
    //MARK:- public varibles
    
    var id: String {
        set {_id = newValue}
        get {return _id}
    }
    
    var name: String {
        set {_name = newValue}
        get {return _name}
    }
    
    var birthDate: String {
        set {_birthDate = newValue}
        get {return _birthDate}
    }
}
