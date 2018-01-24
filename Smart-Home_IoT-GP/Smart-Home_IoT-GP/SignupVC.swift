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
    @IBOutlet weak var doneBtn: UIButton!
    
    @IBOutlet weak var datePicker: UIDatePicker!

    @IBOutlet weak var dateOfBirthLabel: UILabel!
    
    @IBOutlet weak var nameView: UIView!
    @IBOutlet weak var emailView: UIView!
    @IBOutlet weak var passwordView: UIView!
    @IBOutlet weak var confrimPasswordView: UIView!
    @IBOutlet weak var dateView: UIView!
    
    //MARK:- variables
    var isNameEditingFirstTime = true
    var isEmailEditingFirstTime = true
    var isPasswordEditingFirstTime = true
    var isConfirmPasswordEditingFirstTime = true

    override func viewDidLoad() {
        super.viewDidLoad()
        nameTextField.tag = 1
        nameTextField.delegate = self
        
        emailTextField.tag = 2
        emailTextField.delegate = self
        
        passwordTextField.tag = 3
        passwordTextField.delegate = self
        
        confirmPasswordTextField.tag = 4
        confirmPasswordTextField.delegate = self

        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap))
        self.view.addGestureRecognizer(tap)
       
    }

    
    //MARK:- ibactions
    @IBAction func dateBtnPressed(_ sender: Any)
    {
        doneBtn.isHidden = false
        datePicker.isHidden = false
        
        dateBtn.isHidden = true
        dateOfBirthLabel.isHidden = true
        SignUpBtn.isHidden = true
    }
    
    
    @IBAction func SignUpBtnPressed(_ sender: Any)
    {
        if !isInputsValidated()
        {
            return
        }
        
        let name = nameTextField.text!
        let email = emailTextField.text!
        let password = passwordTextField.text!
        let confrimPassword = confirmPasswordTextField.text!
        let dateofBirth = dateBtn.titleLabel?.text
        
        UserServices.signUp(name: name, email: email, password: password, confirmPassword: confrimPassword, dateOfBirth: dateofBirth!){ status,user in
            //Todo if sucsessful go somewere
            //Todo else show error
        
        }
    }
    
    @IBAction func doneBtnPressed(_ sender: Any)
    {
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd MM yyyy"
        let selectedDate = dateFormatter.string(from: datePicker.date)
        print(selectedDate)
        
        dateBtn.setTitle(selectedDate , for: .normal)
        print(dateBtn.titleLabel?.text ?? "shit")
        
        doneBtn.isHidden = true
        datePicker.isHidden = true
        
        dateBtn.isHidden = false
        dateOfBirthLabel.isHidden = false
        SignUpBtn.isHidden = false
    }
    
    @IBAction func backBtnPressed(_ sender: Any)
    {
        self.dismiss(animated: true, completion: nil)
    }
    
    //MARK:- functions
    @objc private func handleTap()
    {
        self.view.endEditing(true)
    }
    
    private func showAlert(message: String)
    {
        let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
        let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.cancel, handler: nil)
        alert.addAction(action)
        self.present(alert, animated: true, completion: nil)
    }
    
    
    //MARK:- input validations
    private func isInputsValidated() -> Bool
    {
        if isNameEditingFirstTime && isEmailEditingFirstTime && isPasswordEditingFirstTime && isConfirmPasswordEditingFirstTime
        {
            showAlert(message: "please fill in your information")
            return false
        }
        if nameTextField.text == ""
        {
            showAlert(message: "please fill in your name")
            return false
        }
        if emailTextField.text == ""
        {
            showAlert(message: "please fill in your email")
            return false
        }
        if passwordTextField.text == ""
        {
            showAlert(message: "please fill in your password")
            return false
        }
        if confirmPasswordTextField.text == ""
        {
            showAlert(message: "please fill in confrim password")
            return false
        }
        if !isValidEmail(testStr: emailTextField.text!)
        {
            showAlert(message: "email is not valid")
            return false
        }
        if !isvalidPassword(value: passwordTextField.text!)
        {
            showAlert(message: "please make sure the password is more than 7 characters")
            return false
        }
        if !isPasswordSame(password: passwordTextField.text!, confirmPassword: confirmPasswordTextField.text!)
        {
            showAlert(message: "Passwords don't match")
            return false
        }
        
        return true
        
        
    }
    
    private func isValidEmail(testStr:String) -> Bool
    {
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
        
        let emailTest = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return emailTest.evaluate(with: testStr)
    }
    
    private func isvalidPassword(value: String) -> Bool
    {
        if value.characters.count >= 8
        {
            return true
        }
        return false
    }
    
    private func isPasswordSame(password: String , confirmPassword : String) -> Bool
    {
        if password == confirmPassword{
            return true
        }else{
            return false
        }
    }

}


extension SignupVC: UITextFieldDelegate
{
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return true
    }
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if textField.tag == 1 && isNameEditingFirstTime
        {
            textField.text = ""
            isNameEditingFirstTime = false
        }
        else if textField.tag == 2 && isEmailEditingFirstTime
        {
            textField.text = ""
            isEmailEditingFirstTime = false
        }
        else if textField.tag == 3 && isPasswordEditingFirstTime
        {
            textField.text = ""
            isPasswordEditingFirstTime = false
        }
        else if textField.tag == 4 && isConfirmPasswordEditingFirstTime
        {
            textField.text = ""
            isConfirmPasswordEditingFirstTime = false
        }
        
    }
}


















