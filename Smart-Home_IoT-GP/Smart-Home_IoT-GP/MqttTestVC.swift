//
//  MqttTestVC.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/2/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import CocoaMQTT
import SwiftyTimer

class MqttTestVC: UIViewController {

    var mqtt: Mqtt!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
        mqtt = Mqtt(url: "54.244.99.193", port: 1883)
        mqtt.keepAlive = 60
        mqtt.cleanSession = true
        mqtt.delegate = self
        mqtt.connect()

        
        
    }

    @IBAction func subscribeBtnPressed(_ sender: Any) {
        mqtt.subscribe(toTopic: "/fci")
    }

    @IBAction func publishBtnPressed(_ sender: Any) {
        mqtt.publish(message: "Hello", topic: "/fci")
    }
}

extension MqttTestVC: CocoaMQTTDelegate
{
    func mqtt(_ mqtt: CocoaMQTT, didConnect host: String, port: Int)
    {
        print("connected to host: \(host) on port: \(port)")
    }
   
    func mqtt(_ mqtt: CocoaMQTT, didConnectAck ack: CocoaMQTTConnAck)
    {
        print("connection ack")
    }
    func mqtt(_ mqtt: CocoaMQTT, didPublishMessage message: CocoaMQTTMessage, id: UInt16)
    {
        print("published: \(String(describing: message.string))")
    }
    func mqtt(_ mqtt: CocoaMQTT, didPublishAck id: UInt16)
    {
        print("publish ack")
    }
    func mqtt(_ mqtt: CocoaMQTT, didReceiveMessage message: CocoaMQTTMessage, id: UInt16 )
    {
        print("recived message: \(message.string! ) , topic: \(message.topic)")
    }
    func mqtt(_ mqtt: CocoaMQTT, didSubscribeTopic topic: String)
    {
        print("subscribed")
    }
    func mqtt(_ mqtt: CocoaMQTT, didUnsubscribeTopic topic: String)
    {
        print("unsuscribed")
    }
    func mqttDidPing(_ mqtt: CocoaMQTT)
    {
        print("ping")
    }
    func mqttDidReceivePong(_ mqtt: CocoaMQTT)
    {
        print("pong")
    }
    func mqttDidDisconnect(_ mqtt: CocoaMQTT, withError err: Error?)
    {
        print("disconnected: \(err.debugDescription)")
        Timer.after(5.seconds) {
            _ = self.mqtt.connect()
        }
    }
}
