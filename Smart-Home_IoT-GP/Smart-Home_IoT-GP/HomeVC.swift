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
    lazy var refreshControl: UIRefreshControl = {
        let refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(self.handleRefresh(refreshControl:)), for: UIControlEvents.valueChanged)
        
        return refreshControl
    }()
    
    //MARK:- viewDidLoad
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.rightBarButtonItem = self.editButtonItem
        tableView.delegate = self
        tableView.dataSource = self
        self.tableView.addSubview(refreshControl)
        
        let testDevice = Device(id: "123", name: "My Fucking Lamp", pinNumber: 12, description: "boom")
        
        
        let testHome = Home(id: "123", name: "Sa2r", address: "Sa2r el krosih", users: [], devices: [])
        
        testDevice.home = testHome
        testHome.addDevice(device: testDevice)
        self.homes.append(testHome)
        
//        callWebServiceForHomes()
        
        // tapRecognizer, placed in viewDidLoad
        let longPressRecognizer = UILongPressGestureRecognizer(target: self, action: #selector(longPress(longPressGestureRecognizer:)) )
        longPressRecognizer.minimumPressDuration = 1.0 // 1 second press
        longPressRecognizer.delegate = self as? UIGestureRecognizerDelegate
        self.tableView.addGestureRecognizer(longPressRecognizer)
        
        
    }
    
    fileprivate func callWebServiceForHomes() {
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
        }
    }
    
    //Called, when long press occurred
    @objc func longPress(longPressGestureRecognizer: UILongPressGestureRecognizer) {
        
        if longPressGestureRecognizer.state == UIGestureRecognizerState.began {
            
            let touchPoint = longPressGestureRecognizer.location(in: self.view)
            if let indexPath = tableView.indexPathForRow(at: touchPoint) {
                
                if let homeSettingVC = storyboard?.instantiateViewController(withIdentifier: "HomeSettingVC") as? HomeSettingVC
                {
                    homeSettingVC.title = "Home Setting"
                    homeSettingVC.home = homes[indexPath.row]
                    homeSettingVC.delegate = self
                    self.navigationController?.pushViewController(homeSettingVC, animated: true)
                }
            }
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destinationNavigationController = segue.destination as? UINavigationController
        {
            if let targetController = destinationNavigationController.topViewController as? HomeSettingVC
            {
                targetController.delegate = self
            }
        }
        
    }

    @IBAction func menuBtnPressed(_ sender: Any) {
        self.menuContainerViewController.toggleLeftSideMenuCompletion(nil)
    }
    
    @objc func handleRefresh(refreshControl: UIRefreshControl) {

        HomeServices.getAllHomes(for: currentUser)
        { (status, homes) in
            
            if status == "success"
            {
                self.homes = homes
                currentUser.addHomes(homes: homes)
            }
            else
            {
                let alert = UIAlertController(title: "Error", message: status, preferredStyle: .alert)
                let action = UIAlertAction(title: "Dismiss", style: .cancel, handler: nil)
                alert.addAction(action)
                self.present(alert, animated: true, completion: nil)
            }
            self.tableView.reloadData()
            refreshControl.endRefreshing()
        }

    }

}

//MARK:- Table
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
    
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCellEditingStyle
    {
        return .none
    }
    
    func tableView(_ tableView: UITableView, shouldIndentWhileEditingRowAt indexPath: IndexPath) -> Bool
    {
        return false
    }
    
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        return UIView()
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
    {
        //tableView.deselectRow(at: indexPath, animated: true)

        if tableView.isEditing
        {
            
            
        }
        else
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
    
    
}

extension HomeVC: UpdateHomeVCDelegate
{
    func updateHomeVC(_ controller: HomeSettingVC, didFinishAdding home: Home) {
        showLoading()
        HomeServices.updateHome(for: home) { (status, home) in
            
            if status == "success"
            {
                for (i,tempHome) in self.homes.enumerated()
                {
                    if home.id == tempHome.id
                    {
                        self.homes[i] = home
                        currentUser.updateHome(homeIndex: i, home: home)
                    }
                }
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
    
    
}





