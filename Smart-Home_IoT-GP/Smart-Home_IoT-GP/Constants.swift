//
//  Constants.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/2/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation


var currentUser = User()

func showAlert(message: String) -> UIAlertController
{
    let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
    let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.cancel, handler: nil)
    alert.addAction(action)
    return alert
}
