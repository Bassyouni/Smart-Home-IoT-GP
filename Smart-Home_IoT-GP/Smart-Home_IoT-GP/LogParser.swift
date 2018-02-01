//
//  LogParser.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class LogParser : JsonParserWarapper
{
    static func getOneObject<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let user = User()
        
        return user as! T
        
    }
    
    public static func getArrayOfObjects<T>(array: [AnyObject]) -> T {
        var returnedArray = [Log]()
        
        for object in array
        {
            var log = Log()
            
            if let dictionary = object as? Dictionary<String , AnyObject>
            {
                if let command = dictionary["command"] as? String
                {
                    log.command = command
                }
            }
            
            returnedArray.append(log)
        }
        
        return returnedArray as! T
    }
}
