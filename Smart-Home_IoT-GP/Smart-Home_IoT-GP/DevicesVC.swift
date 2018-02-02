//
//  DevicesVC.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/2/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class DevicesVC: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    var devices: [Device]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self
        tableView.dataSource = self
    
    }
    
    func switchToggled(_ sender: UISwitch)
    {
        print("toggle tag: \(sender.tag)")
        if sender.isOn
        {
            print("baaam ON")
        }
        else
        {
            print("baaam OFF")
        }
    }
    

}

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
        if let cell = tableView.dequeueReusableCell(withIdentifier: "DeviceTableViewCell") as? DeviceTableViewCell
        {
            if let devices = devices
            {
                cell.toggleSwitch.tag = indexPath.row
                cell.toggleSwitch.addTarget(self, action: #selector(switchToggled(_:)), for: .valueChanged)
                cell.confireCell(device: devices[indexPath.row])
            }
            
            return cell
        }
        return UITableViewCell()
    }
}
