//
//  Log.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class Log
{
    //MARK:- private variables
    private var _id: String!
    private var _timeStamp: String!
    private var _command: String!
    private var _device: Device!
    
    
    //MARK:- constructors
    init()
    {
        _id = ""
        _timeStamp = ""
        _command = ""
        _device = Device()
    }
    
    init(id: String, timesStamp: String, device: Device)
    {
        _id = id
        _timeStamp = timesStamp
        _device = device
    }
    
    //MARK:- public functions
    
    
    //MARK:- public variables
    var id: String {
        set { _id = newValue }
        get { return _id }
    }
    
    var timeStamp: String {
        set { _timeStamp = newValue }
        get { return _timeStamp }
    }
    
    var device: Device {
        set { _device = newValue }
        get { return _device }
    }
    
    var command: String {
        set { _command = newValue }
        get { return _command }
    }
}
