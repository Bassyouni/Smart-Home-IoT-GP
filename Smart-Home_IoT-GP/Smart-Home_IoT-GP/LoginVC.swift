//
//  LoginVC.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import Alamofire
import SkyFloatingLabelTextField
import IHKeyboardAvoiding

class LoginVC: ParentViewController  {
    
    //MARK:- variables
    
    
    //MARK:- iboutlets

    @IBOutlet weak var emailTextField: SkyFloatingLabelTextField!
    @IBOutlet weak var passwordTextField: SkyFloatingLabelTextField!
    @IBOutlet weak var loginView: LoginView!
    
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
        
        KeyboardAvoiding.avoidingView = loginView
    }
    
    
//    override func viewDidAppear(_ animated: Bool) {
//        super.viewDidAppear(animated)
//        if isFirstTime
//        {
//            emailView.center.y -= view.bounds.height
//            passwordView.center.y += view.bounds.height
//            
//            emailTextField.center.x += view.bounds.width
//            passwordTextField.center.x += view.bounds.width
//            
//
//            
//            UIView.animate(withDuration: 1.0) {
//                
//                self.emailView.center.y += self.view.bounds.height
//                self.passwordView.center.y -= self.view.bounds.height
//                
//            }
//            
//            UIView.animate(withDuration: 2.0, delay: 0.5, options: [], animations: {
//                
//                self.emailTextField.center.x -= self.view.bounds.width
//                self.passwordTextField.center.x -= self.view.bounds.width
//                
//            }, completion: nil)
//            isFirstTime = false
//        }
//        
//        
//    }
    
    


    //MARK:- ibactions
    @IBAction func loginPressed(_ sender: Any)
    {
        showLoading()
//        goToHomeVC()
        
        emailTextField.errorMessage = ""
        passwordTextField.errorMessage = ""
        
        if emailTextField.text == ""
        {
            emailTextField.errorMessage = "Please Enter Email"
            hideLoading()
            return
        }
        if passwordTextField.text == ""
        {
            passwordTextField.errorMessage = "Please Enter Password"
        }
        else if let email = emailTextField.text , let password = passwordTextField.text
        {
            UserServices.login(email: email, password: password, downloadCompleted: { (status, user) in
                if status == "success"
                {
                    currentUser = user
                    
                    //Saving seassion
                    UserDefaults.standard.set(user.id, forKey: userId)
                    
                    UserDefaults.standard.set(user.name, forKey: userName)
                    
                    UserDefaults.standard.set(user.birthDate, forKey: userBirthDate)
                    
                    
                    self.goToHomeVC()
                }
                else
                {
                    self.present(showAlert(message: status), animated: true, completion: nil)
                }
            })
            
        }
        
        hideLoading()
    }

    
    //MARK:- functions
    @objc private func handleTap()
    {
       self.view.endEditing(true)
    }
    
    private func goToHomeVC()
    {
        view.endEditing(true)
        
        let mainVCNav = storyboard?.instantiateViewController(withIdentifier: "HomeVC")
        let sideMenuVC = storyboard?.instantiateViewController(withIdentifier: "SideVC")
        
        let containerVC = MFSideMenuContainerViewController.container(withCenter: mainVCNav , leftMenuViewController: sideMenuVC, rightMenuViewController: nil)
        
        self.present(containerVC!, animated: true, completion: nil)
        

    }

}


//MARK:- keyboard delegates
extension LoginVC: UITextFieldDelegate
{
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return true
    }
}

