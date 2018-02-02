//
//  HomeVC.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/23/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class HomeVC: UIViewController {
    
    //MARK:- iboutlets
    @IBOutlet weak var tableView: UITableView!
    
    //MARK:- varibales
    var homes = [Home]()
    
    //MARK:- viewDidLoad
    override func viewDidLoad() {
        super.viewDidLoad()
        
        print(Date(timeIntervalSince1970: 1516912324000))
        print(Date(timeIntervalSinceNow: 1516912324000))
        print(Date(timeIntervalSinceReferenceDate: 1516912324000))
        
        tableView.delegate = self
        tableView.dataSource = self
        
        let user  = User()
        user.id = "5a6867fe90786547d40078e2"
        let home = Home(id: "1", name: "Malibue", address: "Maadi")
        home.addDevice(device: Device(id: "1", name: "Lamp", pinNumber: 1, description: "nothing"))
        homes.append(home)
        tableView.reloadData()
            
        user.addHome(home:home)
//        HomeServices.getAllHomes(for: user)
//        { (status, homes) in
//            
//            if status == "success"
//            {
//                self.homes = homes
//                self.tableView.reloadData()
//            }
//            else
//            {
//                let alert = UIAlertController(title: "Error", message: status, preferredStyle: .alert)
//                let action = UIAlertAction(title: "Dismiss", style: .cancel, handler: nil)
//                alert.addAction(action)
//                self.present(alert, animated: true, completion: nil)
//            }
//            
//        }
        
    }

}

extension HomeVC: UITableViewDelegate , UITableViewDataSource
{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return homes.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "HomeVCTableViewCell") as? HomeVCTableViewCell
        {
            cell.configureCell(home: homes[indexPath.row], imageNum: indexPath.row % 3)
            return cell
        }
        else
        {
            return UITableViewCell()
        }
        
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
    {
        if let devicesVC = storyboard?.instantiateViewController(withIdentifier: "DevicesVC") as? DevicesVC
        {
            devicesVC.title = "Devices"
            devicesVC.view.backgroundColor = UIColor.blue
            devicesVC.devices = homes[indexPath.row].getAllDevices()
            
            self.navigationController?.pushViewController(devicesVC, animated: true)
        }
        
    }
    
    
}





