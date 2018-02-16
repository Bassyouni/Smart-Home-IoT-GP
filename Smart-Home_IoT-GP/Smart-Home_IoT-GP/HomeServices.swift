//
//  HomeServices.swift
//  
//
//  Created by Bassyouni on 1/24/18.
//
//

import Foundation
import Alamofire

class HomeServices: ServicesWrapper
{
    /// return all homes for a user
    ///
    /// - Parameters:
    ///   - user: user
    ///   - downloadCompleted: closure with status and array of Homes
    public static func getAllHomes(for user: User , downloadCompleted: @escaping DownloadCompletedForHomes)
    {
        let url = URL(string: "\(baseURL)/api/homes/get/\(user.id)")!
        var homes = [Home]()
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
                        if let returnedDataDic = dic["response"] as? [AnyObject]
                        {
                            let parsedHomes: [Home] = HomeParser.getArrayOfObjects(array: returnedDataDic)
                            homes = parsedHomes
                        }
                    }
                    
                }
            }
            else
            {
                print(response.error.debugDescription)
            }
            
            downloadCompleted(returnStatment,homes)
        }
    }

    public static func updateHome(for home: Home, downloadCompleted: @escaping DownloadCompletedForHome)
    {
        let url = URL(string: "\(baseURL)/api/homes/update/\(home.id)")
        let paramters = ["homeName": home.name , "homeAddress": home.address]
        
        var home = Home()
        
        Alamofire.request(url!, method: .post , parameters: paramters).responseJSON { response in
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
                        if let returnedDataDic = dic["response"] as? Dictionary<String, AnyObject>
                        {
                            let parsedHome: Home = HomeParser.getOneObject(dictionary: returnedDataDic)
                            home = parsedHome
                        }
                    }
                    
                }
            }
            else
            {
                print(response.error.debugDescription)
            }
            
            downloadCompleted(returnStatment,home)
        }
        
    }
}
