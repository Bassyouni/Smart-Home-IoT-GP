//
//  HomeVC.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/23/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import CocoaMQTT

class HomeVC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        let mqtt = Mqtt()
        mqtt.delegate = self
        mqtt.connect()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

extension HomeVC : CocoaMQTTDelegate
{
    func mqtt(_ mqtt: CocoaMQTT, didConnect host: String, port: Int)
    {
        print("yyy: " ,"connected")
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
            print("yyy: " ,"message received from topic:\(topic) and message: \(message)")
            //            myLabel.text = "\(message).\nfrom topic: \(topic)"
        }
        
    }
    
    func mqtt(_ mqtt: CocoaMQTT, didSubscribeTopic topic: String)
    {
        print("yyy: " ,"subscribed")
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
        print("yyy: " ,"dissconnenected")
        print(err.debugDescription)
        //        myLabel.text = "disconnected\n\(err.debugDescription)"
        mqtt.connect()
        
    }
    
}





