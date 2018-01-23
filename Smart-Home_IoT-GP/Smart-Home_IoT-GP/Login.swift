//
//  Login.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import CocoaMQTT
import Alamofire

class Login: UIViewController  {
    
    //MARK:- variables
    var isEmailEditingFirstTime = true
    var isPasswordEditingFirstTime = true
    let mqtt = Mqtt()
    
    //MARK:- iboutlets
    @IBOutlet weak var passwordView: UIView!
    @IBOutlet weak var emailView: UIView!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    //MARK:- view methods
    override func viewDidLoad() {
        super.viewDidLoad()
        
        emailTextField.tag = 1
        passwordTextField.tag = 2
        
        
        
        mqtt.delegate = self
        _ = mqtt.connect()
        
        
        
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap))
        self.view.addGestureRecognizer(tap)
        
        
    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
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
        
    }
    
    
    //MARK:- ibactions
    @IBAction func loginPressed(_ sender: Any) {
        //test\
        
        if let message = emailTextField.text
        {
            mqtt.publish(message: message, topic: "bass")
            return
        }
        
        //end of test
        
        
        if emailTextField.text == "" || passwordTextField.text == ""
        {
            let alert = UIAlertController(title: "Error", message: "please Fill Both Fields", preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
            alert.addAction(cancelAction)
            self.present(alert, animated: true, completion: nil)
        }
        else if let email = emailTextField.text , let password = passwordTextField.text
        {
            let url = URL(string: "")
            
            let parameters = ["email" : email , "password" : password]
            
            Alamofire.request(url!, method: .post, parameters: parameters).responseJSON { response in
                if let dic = response.result.value as? Dictionary<String ,AnyObject>
                {
                    if let sucsess = dic["sucsess"] as? Bool , sucsess == true
                    {
                        print("woohooo")
                    }
                    else
                    {
                        print("damn braaaah")
                    }
                }
            }
        }
        
        
    }

    
    //MARK:- functions
    @objc private func handleTap()
    {
       self.view.endEditing(true)
    }

    @IBAction func goAwayPressed(_ sender: Any) {
        mqtt.disconnect()
    }
}

//MARK:- mqtt delegate
extension Login : CocoaMQTTDelegate
{
    func mqtt(_ mqtt: CocoaMQTT, didConnect host: String, port: Int)
        {
            print("xxx: " ,"connected")
    //        myLabel.text = "Connected form did connect"
    
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didConnectAck ack: CocoaMQTTConnAck)
        {
            //subscribe after acknwledgment
            print("connection Acknloedged")
            print(mqtt.subscribe("bass"))
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didPublishMessage message: CocoaMQTTMessage, id: UInt16)
        {
    //        let topic = message.topic
    //        if let message = message.string
    //        {
    //            print("xxx: " ,"message Published: \(message)")
    //            myLabel.text = "message Published: \(message)\nin topic \(topic)"
    //
    //        }
    
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didPublishAck id: UInt16)
        {
            print("publish Acknloedged")
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didReceiveMessage message: CocoaMQTTMessage, id: UInt16 )
        {
            let topic = message.topic
            if let message = message.string
            {
                print("xxx: " ,"message received from topic:\(topic) and message: \(message)")
    //            myLabel.text = "\(message).\nfrom topic: \(topic)"
            }
    
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didSubscribeTopic topic: String)
        {
            print("xxx: " ,"subscribed")
    //        myLabel.text = "subscribed to \(topic)"
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didUnsubscribeTopic topic: String)
        {
    
        }
    
        func mqttDidPing(_ mqtt: CocoaMQTT)
        {
    
        }
    
        func mqttDidReceivePong(_ mqtt: CocoaMQTT)
        {
    
        }
    
        func mqttDidDisconnect(_ mqtt: CocoaMQTT, withError err: Error?)
        {
            print("xxx: " ,"dissconnenected")
            print(err.debugDescription)
    //        myLabel.text = "disconnected\n\(err.debugDescription)"
            mqtt.connect()
            
        }

}


//MARK:- keyboard delegates
extension Login: UITextFieldDelegate
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
        
        if textField.tag == 2 && isPasswordEditingFirstTime
        {
            textField.text = ""
            isPasswordEditingFirstTime = false
        }
        
    }
}

