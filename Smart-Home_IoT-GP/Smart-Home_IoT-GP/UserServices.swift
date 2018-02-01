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
    public static func login(email: String, password: String,downloadCompleted: @escaping DownloadCompleted)
    {
        let url = URL(string: "http://192.168.1.7:8000/api/users/auth/login")!
        
        let parameters: Dictionary<String ,String> = ["email": email, "password": password ]
        var user = User()
        
        Alamofire.request(url, method: .post, parameters: parameters).responseJSON { response in
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
                            let parsedUser: User = UserParser.getOneObject(dictionary: returnedDataDic)
                            user = parsedUser
                            
                        }
                    }
                    
                }
            }
            else
            {
                print(response.error.debugDescription)
            }
        
            downloadCompleted(returnStatment, user)
        }
        
    }
    
    public static func signUp(name: String, email: String, password: String, confirmPassword: String, dateOfBirth:String,downloadCompleated: @escaping DownloadCompleted)
    {
        let url = URL(string: "http://192.168.1.7:8000/api/users/auth/signup")!
        
        let parameters: Dictionary<String ,String> = ["name": name , "email": email ,"birthDate": dateOfBirth , "password": password , "confirmPassword": confirmPassword ]
        var user = User()
        
        Alamofire.request(url, method: .post, parameters: parameters).responseJSON { response in
            var returnStatment = "failure"
            if let dic =  response.result.value as? Dictionary<String ,AnyObject>
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
                            let x: User = UserParser.getOneObject(dictionary: returnedDataDic)
                            user = x
                            
                        }
                    }

                }
                
            }
            else
            {
                print(response.error.debugDescription)
                print(response.description)
                print(response.result.value)
            }
            
            downloadCompleated(returnStatment, user)
        }
        
    }
}
