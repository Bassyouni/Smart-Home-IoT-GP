//
//  LogServices.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation
import Alamofire

class LogServices: ServicesWrapper
{
    public static func getDeviceLogs(homeId: String , deviceId: String, downloadCompleted: @escaping DownloadCompletedForLogs)
    {
        let url = URL(string: "\(baseURL)/api/homes/get-device-logs/\(homeId)/\(deviceId)")!
        print(url.absoluteString)
        var logs = [Log]()
        
        Alamofire.request(url, method: .get).responseJSON  { response in
            
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
                        if let returnedDataDic = dic["response"] as? [AnyObject]
                        {
                            let parsedLogs: [Log] = LogParser.getArrayOfObjects(array: returnedDataDic)
                            logs = parsedLogs
                        }
                    }
                    
                }
            }
            else
            {
                print(response.error.debugDescription)
            }
            
            downloadCompleted(returnStatment,logs)
        }
    }
}
