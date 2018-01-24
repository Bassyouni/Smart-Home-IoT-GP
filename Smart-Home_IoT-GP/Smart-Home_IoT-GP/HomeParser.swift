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
    
    public static func getArrayOfObjects<T>(dictionary: Dictionary<String, AnyObject>) -> T {
        let array = [User]()
        return array as! T
    }
}
