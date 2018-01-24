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
        
        if let id = dictionary["id"] as? String
        {
            user.id = id
        }
        
        if let birthDate = dictionary["birthDate"] as? String
        {
            user.birthDate = birthDate
        }
        
        return user as! T
        
    }
    
    public static func getArrayOfObjects<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let array = [User]()
        return array as! T
    }
}
