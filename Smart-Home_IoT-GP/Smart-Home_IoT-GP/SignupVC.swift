//
//  SignupVC.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/24/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class SignupVC: UIViewController {
    
    //MARK:- iboutles
    
    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var confirmPasswordTextField: UITextField!
    @IBOutlet weak var dateBtn: UIButton!
    @IBOutlet weak var SignUpBtn: UIButton!
    @IBOutlet weak var datePicker: UIDatePicker!
    @IBOutlet weak var doneBtn: UIButton!
    @IBOutlet weak var dateOfBirthLabel: UILabel!
    
    @IBOutlet weak var nameView: UIView!
    @IBOutlet weak var emailView: UIView!
    @IBOutlet weak var passwordView: UIView!
    @IBOutlet weak var confrimPasswordView: UIView!
    @IBOutlet weak var dateView: UIView!

    override func viewDidLoad() {
        super.viewDidLoad()


       
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        //coming from top and bottom
        nameView.center.y -= view.bounds.height
        dateView.center.y += view.bounds.height
        
        //coming from the sides
        emailView.center.x -= view.bounds.width
        passwordView.center.x += view.bounds.width
        confrimPasswordView.center.x -= view.bounds.width
        
        nameTextField.center.x += view.bounds.width
        emailTextField.center.x += view.bounds.width
        passwordView.center.x += view.bounds.width
        confrimPasswordView.center.x += view.bounds.width
        dateBtn.center.x += view.bounds.width
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        UIView.animate(withDuration: 1.0) { 
            //coming from top and bottom
            self.nameView.center.y += self.view.bounds.height
            self.dateView.center.y -= self.view.bounds.height
            
            //coming from the sides
            self.emailView.center.x += self.view.bounds.width
            self.passwordView.center.x -= self.view.bounds.width
            self.confrimPasswordView.center.x += self.view.bounds.width
        }
        
        UIView.animate(withDuration: 2.0, delay: 0.5, options: [], animations: { 
            self.nameTextField.center.x -= self.view.bounds.width
            self.emailTextField.center.x -= self.view.bounds.width
            self.passwordView.center.x -= self.view.bounds.width
            self.confrimPasswordView.center.x -= self.view.bounds.width
            self.dateBtn.center.x -= self.view.bounds.width
        }, completion: nil)
        
    }

    @IBAction func dateBtnPressed(_ sender: Any) {
    }
    
    
    @IBAction func SignUpBtnPressed(_ sender: Any) {
    }
    
    @IBAction func doneBtnPressed(_ sender: Any) {
    }
    
    @IBAction func backBtnPressed(_ sender: Any) {
    }

}
