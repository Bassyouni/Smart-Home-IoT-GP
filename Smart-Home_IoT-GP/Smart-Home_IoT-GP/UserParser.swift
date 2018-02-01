//
//  UserParser.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//
//         (id, name, email, birthDate)
import Foundation

class UserParser : JsonParserWarapper
{

    static func getOneObject<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let user = User()
        
        if let name = dictionary["name"] as? String
        {
            user.name = name
        }
        if let id = dictionary["_id"] as? String
        {
            user.id = id
        }
        if let birthDate = dictionary["birthDate"] as? String
        {
            user.birthDate = birthDate
        }
        
        
        return user as! T
        
    }
    
    public static func getArrayOfObjects<T>(array: [AnyObject]) -> T {
        let array = [User]()
        return array as! T
    }
    
    public static func getHome(dictionary: Dictionary<String, AnyObject>) -> Home
    {
        let home = Home()
        
        if let name = dictionary["name"] as? String
        {
            home.name = name
        }
        if let address = dictionary["address"] as? String
        {
            home.address = address
        }
        if let id = dictionary["_id"] as? String
        {
            home.id = id
        }
        if let topic = dictionary["topic"] as? String
        {
            home.topic = topic
        }
        return home
    }
}
