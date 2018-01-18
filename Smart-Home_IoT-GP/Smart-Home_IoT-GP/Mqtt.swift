//
//  Mqtt.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import Foundation
import CocoaMQTT

//    let url = "192.168.1.17"
//
//    let clientID = "CocoaMQTT-" + String(ProcessInfo().processIdentifier)
//    mqtt = CocoaMQTT(clientID: clientID, host: url, port: 1883)
//    //        mqtt.username = "test"
//    //        mqtt.password = "public"
//    mqtt.willMessage = CocoaMQTTWill(topic: "fciGp", message: "dieout")
//    mqtt.keepAlive = 60
//    mqtt.delegate = self
//    mqtt.connect()
//
//
//
//    publishBtn.addTarget(self, action: #selector(self.publish), for: .touchUpInside)
//    subscribeBtn.addTarget(self, action: #selector(self.subscribe), for: .touchUpInside)
//
//
//}
//
//func publish()
//{
//    if let message = publishTextField.text, let topic = topicToPublishTextField.text, publishTextField.text != "" , topicToPublishTextField.text  != ""
//    {
//        mqtt.publish(topic, withString: message)
//        myLabel.text = "published"
//    }
//    else
//    {
//        myLabel.text = "text field is empty"
//    }
//
//}
//
//func subscribe()
//{
//    if let topic = subscribeTopicTextField.text , subscribeTopicTextField.text != ""
//    {
//        mqtt.subscribe(topic)
//    }
//
//
//}

class Mqtt
{
    private var mqtt: CocoaMQTT!
    
    


}


extension Mqtt: CocoaMQTTDelegate
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
