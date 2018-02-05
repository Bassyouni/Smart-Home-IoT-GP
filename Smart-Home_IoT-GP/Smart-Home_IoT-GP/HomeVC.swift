//
//  HomeVC.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/23/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class HomeVC: ParentViewController {
    
    //MARK:- iboutlets
    @IBOutlet weak var tableView: UITableView!
    
    //MARK:- varibales
    var homes = [Home]()
    
    //MARK:- viewDidLoad
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.delegate = self
        tableView.dataSource = self
    
//        Test DATA
        
//        let home = Home(id: "1", name: "Malibue", address: "Maadi")
//        home.addDevice(device: Device(id: "1", name: "Lamp", pinNumber: 1, description: "nothing"))
//        home.addDevice(device: Device(id: "2", name: "Takif", pinNumber: 2, description: "nothing"))
//        homes.append(home)
//        tableView.reloadData()
        
//        currentUser.addHome(home:home)
        showLoading()
        HomeServices.getAllHomes(for: currentUser)
        { (status, homes) in
            
            if status == "success"
            {
                self.homes = homes
                currentUser.addHomes(homes: homes)
                self.tableView.reloadData()
            }
            else
            {
                let alert = UIAlertController(title: "Error", message: status, preferredStyle: .alert)
                let action = UIAlertAction(title: "Dismiss", style: .cancel, handler: nil)
                alert.addAction(action)
                self.present(alert, animated: true, completion: nil)
            }
            self.hideLoading()
            
        }
        
    }
    
    @IBAction func menuBtnPressed(_ sender: Any) {
        self.menuContainerViewController.toggleLeftSideMenuCompletion(nil)
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
            devicesVC.home = homes[indexPath.row]
            
            self.navigationController?.pushViewController(devicesVC, animated: true)
        }
        
    }
    
    
}





