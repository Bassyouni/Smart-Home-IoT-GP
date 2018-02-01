//
//  HomeParser.swift
//  
//
//  Created by Bassyouni on 1/24/18.
//
//

import Foundation

class HomeParser : JsonParserWarapper
{
    static func getOneObject<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let user = User()
        
        return user as! T
        
    }
    
    public static func getArrayOfObjects<T>(array: [AnyObject]) -> T {
        var returnedArray = [Home]()
        
        for object in array
        {
            let home = Home()
            
            if let dictionary = object as? Dictionary<String , AnyObject>
            {
                if let id = dictionary["_id"] as? String
                {
                    home.id = id
                }
                if let name = dictionary["name"] as? String
                {
                    home.name = name
                }
                if let address = dictionary["address"] as? String
                {
                    home.address = address
                }
                if let topic  = dictionary["topic"] as? String
                {
                    home.topic = topic
                }
                if let users = dictionary["users"] as? [Dictionary<String , AnyObject>]
                {
                    for user in users
                    {
                        let returnedUser: User = UserParser.getOneObject(dictionary: user)
                        home.addUser(user: returnedUser)
                    }
                }
                if let devices = dictionary["devices"] as? [Dictionary<String , AnyObject>]
                {
                    for device in devices
                    {
                        let retrurnedDevice: Device = DeviceParser.getOneObject(dictionary: device)
                        home.addDevice(device: retrurnedDevice)
                    }
                }
                returnedArray.append(home)
            }
        }
        
        
        return returnedArray as! T
    }
}
