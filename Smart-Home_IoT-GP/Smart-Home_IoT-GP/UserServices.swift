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
    
    
    /// return a user
    ///
    /// - Parameters:
    ///   - email: user email
    ///   - password: user password
    ///   - downloadCompleted: closure with status and returned user
    public static func login(email: String, password: String,downloadCompleted: @escaping DownloadCompletedForUser)
    {
        let url = URL(string: "\(baseURL))/api/users/auth/login")!
        
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
    
    /// return a new user
    ///
    /// - Parameters:
    ///   - downloadCompleated: closure with status and returned user
    public static func signUp(name: String, email: String, password: String, confirmPassword: String, dateOfBirth:String,downloadCompleated: @escaping DownloadCompletedForUser)
    {
        let url = URL(string: "\(baseURL)/api/users/auth/signup")!
        
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
                            let returnedUser: User = UserParser.getOneObject(dictionary: returnedDataDic)
                            user = returnedUser
                            
                        }
                    }

                }
                
            }
            else
            {
                print(response.error.debugDescription)
                print(response.description)
            }
            
            downloadCompleated(returnStatment, user)
        }
        
    }
    
    /// return and updated user
    ///
    /// - Parameters:
    ///   - downloadCompleated: closure with status and updated user
    public static func updateUser(user:User,password: String?, confirmPassword: String?, downloadCompleated: @escaping DownloadCompletedForUser)
    {
        let url = URL(string: "\(baseURL)/api/users/update/\(user.id)")!
        //name, birthDate, password, confirmPassword
        var paramters = ["name": user.name , "birthDate": user.birthDate]
        
        if let password = password , let confirmPassword = confirmPassword
        {
            paramters["password"] = password
            paramters["confirmPassword"] = confirmPassword
        }
        
        var updatedUser = User()
        
        Alamofire.request(url, method: .post, parameters: paramters).responseJSON { response in
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
                            let returnedUser: User = UserParser.getOneObject(dictionary: returnedDataDic)
                            updatedUser = returnedUser
                            
                        }
                    }
                    
                }
                
            }
            else
            {
                print(response.error.debugDescription)
                print(response.description)
            }
            downloadCompleated(returnStatment, updatedUser)
        }
    }
    
    /// return user with added a new home
    ///
    /// - Parameters:
    ///   - downloadCompleated: closure with status and user with new home
    public static func addHome(user:User, homeAddress: String, homeName: String, downloadCompleated: @escaping DownloadCompletedForUser)
    {
        let url = URL(string: "\(baseURL)/api/users/add-home/\(user.id)")!
        //homeName, homeAddress
        let paramters = ["homeName": homeName , "homeAddress": homeAddress]

        let updatedUser = User()
        
        Alamofire.request(url, method: .post, parameters: paramters).responseJSON { response in
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
                            let returnedHome: Home = UserParser.getHome(dictionary: returnedDataDic)
                            updatedUser.addHome(home: returnedHome)
                            
                        }
                    }
                    
                }
                
            }
            else
            {
                print(response.error.debugDescription)
                print(response.description)
            }
            downloadCompleated(returnStatment, updatedUser)
        }
    }
}
