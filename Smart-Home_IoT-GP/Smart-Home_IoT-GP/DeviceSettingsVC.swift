//
//  DeviceSettingsVC.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/4/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class DeviceSettingsVC: UIViewController , UITextFieldDelegate , UINavigationControllerDelegate {

    //MARK:- iboutlets
    @IBOutlet weak var nameTextField: UITextField!
    
    //MARK:- varibles
    var device: Device!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        navigationController?.delegate = self
        if let topItem = self.navigationController?.navigationBar.topItem
        {
            topItem.backBarButtonItem = UIBarButtonItem(title: "Back" , style: UIBarButtonItemStyle.plain, target: nil, action: nil)
        }
        nameTextField.text = device.name
        nameTextField.delegate = self
        nameTextField.returnKeyType = .done
        nameTextField.becomeFirstResponder()

    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.navigationController?.popViewController(animated: true)
        return true
    }
    
    func navigationController(_ navigationController: UINavigationController, willShow viewController: UIViewController, animated: Bool)
    {
        (viewController as? DevicesVC)?.devices?.forEach({ (editedDevice) in
            if let name = nameTextField.text, name != ""
            {
                if editedDevice.id == device.id
                {
                    editedDevice.name = name.capitalized
                }
            }
            
        })
        
    }

}
