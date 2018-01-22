//
//  Login.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import CocoaMQTT

class Login: UIViewController  {
    
    //MARK:- iboutlets

    @IBOutlet weak var passwordView: UIView!
    @IBOutlet weak var emailView: UIView!
    @IBOutlet weak var emailTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let x = Mqtt()
        
        x.delegate = self
        _ = x.connect()
        
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
    @IBAction func logijnPressed(_ sender: Any) {
    }

    
    @objc private func handleTap()
    {
       self.view.endEditing(true)
    }

}

extension Login : CocoaMQTTDelegate
{
    func mqtt(_ mqtt: CocoaMQTT, didConnect host: String, port: Int)
        {
    //        print("xxx: " ,"connected")
    //        myLabel.text = "Connected form did connect"
    
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didConnectAck ack: CocoaMQTTConnAck)
        {
    
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
    
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didReceiveMessage message: CocoaMQTTMessage, id: UInt16 )
        {
    //        let topic = message.topic
    //        if let message = message.string
    //        {
    //            print("xxx: " ,"message received from topic:\(topic) and message: \(message)")
    //            myLabel.text = "\(message).\nfrom topic: \(topic)"
    //        }
    
        }
    
        func mqtt(_ mqtt: CocoaMQTT, didSubscribeTopic topic: String)
        {
    //        print("xxx: " ,"subscribed")
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
    //        print("xxx: " ,"dissconnenected")
    //        print(err.debugDescription)
    //        myLabel.text = "disconnected\n\(err.debugDescription)"
    //        mqtt.connect()
            
        }

}

