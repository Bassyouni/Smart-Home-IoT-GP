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
    
    public static func getArrayOfObjects<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let array = [User]()
        return array as! T
    }
}
