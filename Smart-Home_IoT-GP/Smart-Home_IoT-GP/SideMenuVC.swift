//
//  SideMenuVC.swift
//  Marriage
//
//  Created by Bassyouni on 6/17/16.
//  Copyright © 2016 Bassyouni. All rights reserved.
//

import UIKit

class SideMenuVC: UIViewController , UITableViewDelegate , UITableViewDataSource
{
//
    //MARK: - iboutlets
    @IBOutlet var tableMenu : UITableView!
    @IBOutlet var lblWelcome : UILabel!
    @IBOutlet var lblName : UILabel!
//    @IBOutlet var imgProfile : AsyncImageView!
//
    //MARK: - variables

    var arrMenuTxt : [String] = ["Homes", "Profile" , "Help" , "Logout"]
    var indexSelected : Int? = 0
    
    //MARK: - view
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        // set name
        self.lblWelcome.text = NSLocalizedString("Welcome", comment: "")
        self.lblName.text = currentUser.name.capitalized
      
        
    }
    
    deinit {
        print("SideVC closed")
    }

    
    //MARK: - table
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return self.arrMenuTxt.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MenuCell") as! SideMenuTableViewCell
        
        cell.lblMenu.text = self.arrMenuTxt[indexPath.row]

        if (self.indexSelected == indexPath.row)
        {
            UIView.animate(withDuration: 0.5, animations: {
                cell.viewMenu.backgroundColor = customBlueColor
            })
        }
        else
        {
            UIView.animate(withDuration: 0.5, animations: {
                cell.viewMenu.backgroundColor = UIColor.white
            })
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.indexSelected = nil
        UIView.animate(withDuration: 0, animations: {
            self.tableMenu.reloadData()
        }, completion: {_ in
            self.indexSelected = indexPath.row
            self.tableMenu.reloadData()
        })
        
        if indexPath.row == 0
        {
            let homeVC = self.storyboard?.instantiateViewController(withIdentifier: "HomeVC")
            menuContainerViewController.centerViewController = homeVC
        }
        else if indexPath.row == 1
        {
            let profileVC = self.storyboard?.instantiateViewController(withIdentifier: "ProfileVC")
            menuContainerViewController.centerViewController = profileVC
        }
        else if indexPath.row == 2
        {
            self.present(showAlert(message: "Call 911"), animated: true, completion: nil)
            
//            let helpVC = self.storyboard?.instantiateViewController(withIdentifier: "HelpVC")
//            menuContainerViewController.centerViewController = helpVC
        }
        else if indexPath.row == 3
        {
            //TODO: Logout options
            UserDefaults.standard.removeObject(forKey: userId)
            UserDefaults.standard.removeObject(forKey: userName)
            UserDefaults.standard.removeObject(forKey: userBirthDate)
            

            let loginVC = self.storyboard?.instantiateViewController(withIdentifier: "LoginVC")
//            delegate?.window?.rootViewController = loginVC
            self.present(loginVC!, animated: true, completion: nil)
        }
        
        self.menuContainerViewController.toggleLeftSideMenuCompletion(nil)
        
    }



}
