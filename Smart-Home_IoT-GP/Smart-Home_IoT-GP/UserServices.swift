//
//  UserServices.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation
import Alamofire

class UserServices: ServicesWrapper
{
    public func login(downloadCompleted: @escaping DownloadCompleted)
    {
        let url = URL(string: "x")
        Alamofire.request(url!).responseJSON   {response in
            downloadCompleted()
        }
    }
}
