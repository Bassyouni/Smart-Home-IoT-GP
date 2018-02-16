//
//  DeviceServices.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation
import Alamofire

class DeviceServices: ServicesWrapper
{
    public static func addLogTo(deviceId: String, homeId: String, command: String, downloadCompleted: @escaping DownloadCompletedForDevice )
    {
        let url = URL(string: "\(baseURL)/api/homes/add-log-to-device/\(homeId)/\(deviceId)/command")!
        
        Alamofire.request(url, method: .get).responseJSON { response in
            var returnStatment = "failure"
            
            if let dic = response.result.value as? Dictionary<String , AnyObject>
            {
                if let status =  dic["status"] as? String
                {
                    if status == "failure"
                    {
                        if let error = dic["error"] as? String
                        {
                            returnStatment = error
                        }
                        
                    }
                    else
                    {
                        returnStatment = "success"
                    }
                    
                }
            }
            else
            {
                print(response.error.debugDescription)
            }
            
            downloadCompleted(returnStatment)
        }
    }
}
