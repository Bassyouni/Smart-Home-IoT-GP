//
//  Constants.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/2/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation

//5a687a9a9078655e8c004962
//omarmok@live.com
//hello123

//35.167.95.42

//User Defaults constants
let userId = "id"
let userBirthDate = "birthDate"
let userName = "name"

var currentUser = User()


func showAlert(message: String) -> UIAlertController
{
    let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
    let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.cancel, handler: nil)
    alert.addAction(action)
    return alert
}
