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
    public static func login(downloadCompleted: @escaping DownloadCompleted)
    {
        let url = URL(string: "x")
        Alamofire.request(url!).responseJSON   {response in
            
        }
    }
    
    public static func signUp(name: String, email: String, password: String, confirmPassword: String, dateOfBirth:String,downloadCompleated: @escaping DownloadCompleted)
    {
        let url = URL(string: "(domain name)/api/users/auth/signup")!

        let parameters: Dictionary<String ,String> = ["name": name , "email": email ,"birthDate": dateOfBirth , "password": password , "confirmPassword": confirmPassword ]
        var user = User()
        
        Alamofire.request(url, method: .post, parameters: parameters).responseJSON { response in
            var returnStatment = "success"
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
                }
                else
                {
                    if let returnedDataDic = dic["response"] as? Dictionary<String, AnyObject>
                    {
                        let x: User = UserParser.getOneObject(dictionary: returnedDataDic)
                        user = x

                    }
                }
                
            }
            
            downloadCompleated(returnStatment, user)
        }
        
    }
}
