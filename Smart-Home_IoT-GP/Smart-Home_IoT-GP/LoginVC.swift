//
//  LoginVC.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import Alamofire

class LoginVC: UIViewController  {
    
    //MARK:- variables
    var isEmailEditingFirstTime = true
    var isPasswordEditingFirstTime = true
    
    //MARK:- iboutlets
    @IBOutlet weak var passwordView: UIView!
    @IBOutlet weak var emailView: UIView!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    //MARK:- variables
    var isFirstTime: Bool = true
    
    //MARK:- view methods
    override func viewDidLoad() {
        super.viewDidLoad()
        
        emailTextField.delegate = self
        passwordTextField.delegate = self
        emailTextField.tag = 1
        passwordTextField.tag = 2
  
        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap))
        self.view.addGestureRecognizer(tap)
        
        
    }
    
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if isFirstTime
        {
            emailView.center.y -= view.bounds.height
            passwordView.center.y += view.bounds.height
            
            emailTextField.center.x += view.bounds.width
            passwordTextField.center.x += view.bounds.width
            

            
            UIView.animate(withDuration: 1.0) {
                
                self.emailView.center.y += self.view.bounds.height
                self.passwordView.center.y -= self.view.bounds.height
                
            }
            
            UIView.animate(withDuration: 2.0, delay: 0.5, options: [], animations: {
                
                self.emailTextField.center.x -= self.view.bounds.width
                self.passwordTextField.center.x -= self.view.bounds.width
                
            }, completion: nil)
            isFirstTime = false
        }
        
        
    }
    
    


    //MARK:- ibactions
    @IBAction func loginPressed(_ sender: Any)
    {
        
        goToHomeVC()
        
        if emailTextField.text == "" || passwordTextField.text == ""
        {
            let alert = UIAlertController(title: "Error", message: "please Fill Both Fields", preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
            alert.addAction(cancelAction)
            self.present(alert, animated: true, completion: nil)
        }
        else if let email = emailTextField.text , let password = passwordTextField.text
        {
            UserServices.login(email: email, password: password, downloadCompleted: { (status, user) in
                if status == "success"
                {
                    currentUser = user
                    self.goToHomeVC()
                }
                else
                {
                    self.present(showAlert(message: status), animated: true, completion: nil)
                }
            })
            
        }
        
        
    }

    
    //MARK:- functions
    @objc private func handleTap()
    {
       self.view.endEditing(true)
    }
    
    private func goToHomeVC()
    {
        view.endEditing(true)
        let delegate = UIApplication.shared.delegate as? AppDelegate
        let mainVCNav = storyboard?.instantiateViewController(withIdentifier: "HomeVC")
        let sideMenuVC = storyboard?.instantiateViewController(withIdentifier: "SideVC")
        
        let containerVC = MFSideMenuContainerViewController.container(withCenter: mainVCNav , leftMenuViewController: sideMenuVC, rightMenuViewController: nil)
        
        delegate?.window?.rootViewController = containerVC
    }

}


//MARK:- keyboard delegates
extension LoginVC: UITextFieldDelegate
{
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return true
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if textField.tag == 1 && isEmailEditingFirstTime
        {
            textField.text = ""
            isEmailEditingFirstTime = false
        }
        else if textField.tag == 2 && isPasswordEditingFirstTime
        {
            textField.text = ""
            isPasswordEditingFirstTime = false
        }
        
    }
}

