//
//  ProfileVC.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/4/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class ProfileVC: ParentViewController {

    //MARK:- iboutlets
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var datePicker: UIDatePicker!
    
 
    @IBOutlet weak var changePasswordTopConstraint: NSLayoutConstraint!
    
    //MARK:- view functions
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.rightBarButtonItem = self.editButtonItem
        nameTextField.isHidden = true
        datePicker.isHidden = true
        
        nameLabel.text = currentUser.name
        nameTextField.text = currentUser.name
        dateLabel.text = currentUser.birthDate
        
        datePicker.maximumDate = Date()
        datePicker.datePickerMode = .date
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat =  "dd-MM-yyyy"
        
        if let date = dateFormatter.date(from: currentUser.birthDate)
        {
            datePicker.date = date
        }
        
        
        
    changePasswordTopConstraint.constant = -150

        nameTextField.delegate = self
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap))
        self.view.addGestureRecognizer(tap)
        
    }
    
    //MARK:- functions
    override func setEditing(_ editing: Bool, animated: Bool)
    {
        super.setEditing(editing, animated: animated)
        if editing
        {
            nameLabel.isHidden = true
            dateLabel.isHidden = true
            datePicker.isHidden = false
            nameTextField.isHidden = false
            
            changePasswordTopConstraint.constant = 20
            
        }
        else
        {
            
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "dd-MM-yyyy"
            let selectedDate = dateFormatter.string(from: datePicker.date)
            
            if selectedDate == currentUser.birthDate && nameTextField.text == currentUser.name
            {
                
            }
            else if nameTextField.text == ""
            {
                self.present(showAlert(message: "please Fill your name"), animated: true, completion: nil)
            }
            else
            {
                showLoading()
                let user = currentUser
                user.name = nameTextField.text!
                user.birthDate = selectedDate
                
                UserServices.updateUser(user: user, password: nil, confirmPassword: nil, downloadCompleated: { (status, returnedUser) in
                    
                    self.hideLoading()
                    
                    if status == "success"
                    {
                        currentUser = returnedUser
                        self.present(showAlert(message: "Success"), animated: true, completion: nil)
                    }
                    else
                    {
                        self.present(showAlert(message: status), animated: true, completion: nil)
                    }
                })
            }
            
            self.nameLabel.isHidden = false
            self.dateLabel.isHidden = false
            self.datePicker.isHidden = true
            self.nameTextField.isHidden = true
            changePasswordTopConstraint.constant = -150
        }
    }
    
    func handleTap()
    {
        self.view.endEditing(true)
    }

    //MARK:- ibactions
    @IBAction func menuBtnPressed(_ sender: Any)
    {
        self.menuContainerViewController.toggleLeftSideMenuCompletion(nil)
    }


    @IBAction func changePasswordBtnPressed(_ sender: Any)
    {
        //the main alert
        let alert = UIAlertController(title: "Change Password",
                                      message: "Insert Password & Confirm Passowrd",
                                      preferredStyle: .alert)
        
        //password textField
        alert.addTextField { (textField: UITextField) in
            textField.keyboardType = .default
            textField.autocorrectionType = .default
            textField.placeholder = "Password"
            textField.isSecureTextEntry = true
        }
        
        //confirmPassword textField
        alert.addTextField { (textField: UITextField) in
            textField.keyboardType = .default
            textField.autocorrectionType = .default
            textField.placeholder = "Confirm Password"
            textField.isSecureTextEntry = true
        }
        
        //logic for change password
        let changePasswordAction = UIAlertAction(title: "Change", style: .default, handler: { (action) -> Void in
            // Get TextFields text
            let password = alert.textFields![0]
            let confirmPassword = alert.textFields![1]
            
            // if empty show again
            if password.text == "" || confirmPassword.text == ""
            {
                self.present(alert, animated: true, completion: nil)
            }
                // if less than eight char's show error and show main alert again
            else if (password.text?.characters.count)! < 8 || (confirmPassword.text?.characters.count)! < 8
            {
                let errorAlert = UIAlertController(title: "Error", message: "Password is less than 8 characters", preferredStyle: .alert)
                
                let action = UIAlertAction(title: "cancel", style: .cancel, handler: { (_) in
                    self.present(alert, animated: true, completion: nil)
                })
                
                errorAlert.addAction(action)
                self.present(errorAlert, animated: true, completion: nil)
            }
                // if not equal make text color = red
            else if password.text != confirmPassword.text
            {
                alert.textFields?.forEach({ (textField) in
                    textField.textColor = UIColor.red
                })
                self.present(alert, animated: true, completion: nil)
            }
            else
            {
                //validated and call webservice
                UserServices.updateUser(user: currentUser, password: password.text!, confirmPassword: confirmPassword.text!, downloadCompleated: { (status, user) in
                    
                    if status == "success"
                    {
                        currentUser = user
                        let successAlert = UIAlertController(title: "Success", message: "Password has changed", preferredStyle: .alert)
                        let action = UIAlertAction(title: "Dismiss", style: .cancel, handler: nil)
                        successAlert.addAction(action)
                        self.present(successAlert, animated: true, completion: nil)
                    }
                    else
                    {
                        //Error happend, show Error
                        self.present(showAlert(message: status), animated: true, completion: nil)
                    }
                })
            }
        
        })
        
        // Cancel button
        let cancel = UIAlertAction(title: "Cancel", style: .destructive, handler: nil)
        
        alert.addAction(changePasswordAction)
        alert.addAction(cancel)
        
        self.present(alert, animated: true, completion: nil)
        
    }
    
}

extension ProfileVC: UITextFieldDelegate
{
    func textFieldShouldReturn(_ textField: UITextField) -> Bool
    {
        self.view.endEditing(true)
        return true
    }
}
