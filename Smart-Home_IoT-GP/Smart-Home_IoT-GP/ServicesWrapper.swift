//
//  ServicesWrapper.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

class ServicesWrapper
{
    public static let baseURL = "http://192.168.1.8:8000"
    
    public typealias DownloadCompletedForUser = (String , User) -> ()
    public typealias DownloadCompletedForHome = (String , Home) -> ()
    public typealias DownloadCompletedForHomes = (String , [Home]) -> ()
}
