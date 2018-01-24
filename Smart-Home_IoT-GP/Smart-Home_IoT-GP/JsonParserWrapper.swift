//
//  JsonParserWrapper.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

protocol JsonParserWarapper {
    static func getArrayOfObjects<T>(dictionary: Dictionary<String,AnyObject>) -> [T]
    static func getOneObject<T>(dictionary: Dictionary<String,AnyObject>) -> T
}
