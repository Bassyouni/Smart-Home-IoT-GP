//
//  DevicesVC.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/2/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit
import CocoaMQTT

class DevicesVC: UIViewController {

    //MARK:- iboutlets
    @IBOutlet weak var tableView: UITableView!
    
    //MARK:- variables
    var devices: [Device]?
    var home: Home?
    var mqtt: Mqtt!
    var isConnected = false
    
    let impact = UIImpactFeedbackGenerator()
    
    //MARK:- view functions
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self
        tableView.dataSource = self
        tableView.tableFooterView = UIView()
        self.navigationItem.rightBarButtonItem = self.editButtonItem
        self.editButtonItem.title = "Reorder"
        
//        mqtt = Mqtt(url: "broker.hivemq.com")
        mqtt = Mqtt()
        mqtt.keepAlive = 60
        mqtt.cleanSession = true
        mqtt.delegate = self
        mqtt.connect()
        
        
        let longPressRecognizer = UILongPressGestureRecognizer(target: self, action: #selector(longPress(longPressGestureRecognizer:)) )
        longPressRecognizer.minimumPressDuration = 1.0 // 1 second press
        longPressRecognizer.delegate = self as? UIGestureRecognizerDelegate
        self.tableView.addGestureRecognizer(longPressRecognizer)
        
    
    }
    
    override func viewWillAppear(_ animated: Bool)
    {
        super.viewWillAppear(animated)
        tableView.reloadData()
    }
    
    //MARK:- functions

    
    override func setEditing(_ editing: Bool, animated: Bool)
    {
        super.setEditing(editing, animated: animated)
        if editing
        {
            tableView.setEditing(editing, animated: true)
        }
        else
        {
            tableView.setEditing(editing, animated: true)
            self.editButtonItem.title = "Reorder"
        }
    }
    
    //Called, when long press occurred
    @objc func longPress(longPressGestureRecognizer: UILongPressGestureRecognizer) {
        
        if longPressGestureRecognizer.state == UIGestureRecognizerState.began {
            
            let touchPoint = longPressGestureRecognizer.location(in: self.view)
            if let indexPath = tableView.indexPathForRow(at: touchPoint)
            {
                if let devices = devices
                {
                    if let deviceSettingVC = storyboard?.instantiateViewController(withIdentifier: "DeviceSettingsVC") as? DeviceSettingsVC
                    {
                        deviceSettingVC.title = "Name"
                        deviceSettingVC.device = devices[indexPath.row]
                        self.navigationController?.pushViewController(deviceSettingVC, animated: true)
                        impact.impactOccurred()
                    }
                }
            }
        }
    }

    
    @objc func switchToggled(_ sender: UISwitch)
    {
        print("toggle tag: \(sender.tag)")
       if let device = devices?[sender.tag]
       {
            if let home = home
            {
                let command: String!
                if sender.isOn
                {
                    command = "on"
                    mqtt.publish(deviceId:device.id , command: command, topic: home.topic)
                }
                else
                {
                    command = "off"
                    mqtt.publish(deviceId:device.id , command: command, topic: home.topic)
                }
                impact.impactOccurred()
                
                
                DeviceServices.addLogTo(deviceId: device.id, homeId: home.id, command: command, downloadCompleted: { (status) in
                    print(status)
                })
                
            }
        }
        
    }
    

}

//MARK:- Table
extension DevicesVC: UITableViewDelegate , UITableViewDataSource
{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        if let devices = devices
        {
            return devices.count
        }
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "DeviceTableViewCell" ,for: indexPath ) as? DeviceTableViewCell
        {
            if let devices = devices
            {
                cell.toggleSwitch.tag = indexPath.row
                cell.toggleSwitch.addTarget(self, action: #selector(switchToggled(_:)), for: .valueChanged)
                cell.confireCell(device: devices[indexPath.row], isConnected: isConnected)
            }
            
            return cell
        }
        return UITableViewCell()
    }
    
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool
    {
        return true
    }
    

    
    func tableView(_ tableView: UITableView, moveRowAt sourceIndexPath: IndexPath, to destinationIndexPath: IndexPath)
    {
        if var devices = devices
        {
            let device = devices[sourceIndexPath.row]
            devices.remove(at: sourceIndexPath.row)
            devices.insert(device, at: destinationIndexPath.row)
        }
        
    }

    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCellEditingStyle
    {
        return .none
    }
    
    func tableView(_ tableView: UITableView, shouldIndentWhileEditingRowAt indexPath: IndexPath) -> Bool
    {
        return false
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        if let devices = devices
        {
            let statVC = StatisticsVC()
            statVC.device = devices[indexPath.row]
            statVC.view.backgroundColor = UIColor.white
            self.navigationController?.pushViewController(statVC, animated: true)
            
            // rotate device to landscape
            UIDevice.current.setValue(Int(UIInterfaceOrientation.landscapeLeft.rawValue), forKey: "orientation")
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        if devices?.count == 0
        {
            return "\u{274C} No Devices Registered"
        }
        if isConnected
        {
            return "\u{1F535} Connected Devices"
        }
        return "\u{1F534} Disconnected Devices"

    }
    
}

//MARK:- MQTT
extension DevicesVC: CocoaMQTTDelegate
{
    func mqtt(_ mqtt: CocoaMQTT, didConnect host: String, port: Int)
    {
        print("connected to host: \(host) on port: \(port)")
        isConnected = true
        tableView.reloadData()
    }
    
    func mqtt(_ mqtt: CocoaMQTT, didConnectAck ack: CocoaMQTTConnAck)
    {
        print("connection ack")
        isConnected = true
        tableView.reloadData()
//        self.mqtt.subscribe(toTopic: (home?.topic)!)
    }
    func mqtt(_ mqtt: CocoaMQTT, didPublishMessage message: CocoaMQTTMessage, id: UInt16)
    {
        print("published: \( message.string!)")
    }
    func mqtt(_ mqtt: CocoaMQTT, didPublishAck id: UInt16)
    {
        print("publish ack")
    }
    func mqtt(_ mqtt: CocoaMQTT, didReceiveMessage message: CocoaMQTTMessage, id: UInt16 )
    {
        print("recived message: \(message.string ?? message.topic)")

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
        isConnected = false
        tableView.reloadData()
        Timer.after(5.seconds) {
            _ = self.mqtt.connect()
        }
    }

}








