//
//  DeviceTableViewCell.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/2/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class DeviceTableViewCell: UITableViewCell {


    
    @IBOutlet weak var toggleSwitch: UISwitch!
    
    @IBOutlet weak var deviceNameLabel: UILabel!

    

    
    func confireCell(device: Device, isConnected: Bool)
    {
        deviceNameLabel.text = device.name
        toggleSwitch.isHidden = !isConnected
    }

}
