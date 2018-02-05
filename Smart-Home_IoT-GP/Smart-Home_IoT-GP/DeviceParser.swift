//
//  DeviceParser.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class DeviceParser : JsonParserWarapper
{
    static func getOneObject<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let device = Device()
        
        if let name = dictionary["name"] as? String
        {
            device.name = name
        }
        if let pinNumber = dictionary["pin_number"] as? Int
        {
            device.pinNumber = pinNumber
        }
        if let type = dictionary["type"] as? String
        {
            //TODO:
//            device.type = Device.DeviceType(rawValue: type)!
        }
        if let description = dictionary["description"] as? String
        {
            device.description = description
        }
        if let id = dictionary["_id"] as? String
        {
            device.id = id
        }
        
        return device as! T
        
    }
    
    public static func getArrayOfObjects<T>(array: [AnyObject]) -> T {
        let array = [User]()
        return array as! T
    }
}
