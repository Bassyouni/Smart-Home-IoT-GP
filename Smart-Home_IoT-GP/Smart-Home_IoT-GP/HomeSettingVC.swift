//
//  HomeSettingVC.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/15/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

protocol UpdateHomeVCDelegate: class {
    func updateHomeVC(_ controller: HomeSettingVC , didFinishAdding home: Home)
}

class HomeSettingVC: UITableViewController, UITextFieldDelegate {

    @IBOutlet weak var doneBtn: UIBarButtonItem!
    
    @IBOutlet weak var nameTextField: UITextField!
    
    @IBOutlet weak var addressTextField: UITextField!
    
    var home: Home?
    
    weak var delegate: UpdateHomeVCDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        nameTextField.delegate = self
        addressTextField.delegate = self
        
        nameTextField.tag = 1
        addressTextField.tag = 2
        
        if let home = home
        {
            nameTextField.text = home.name
            addressTextField.text = home.address
        }
        
        let tap = UITapGestureRecognizer(target: self, action: #selector(handleTap))
        self.view.addGestureRecognizer(tap)
        
        

    }
    
    override func viewWillAppear(_ animated: Bool) {
        nameTextField.becomeFirstResponder()
    }
    
    @objc func handleTap()
    {
        self.view.endEditing(true)
    }
    
    @IBAction func DoneBtnPressed(_ sender: Any) {
        
        self.navigationController?.popViewController(animated: true)
        
        if nameTextField.text == home?.name && addressTextField.text == home?.address
        {
            return
        }
        
        if let name = nameTextField.text , name != ""
        {
            home?.name = name
        }
        
        if let address = addressTextField.text , address != ""
        {
            home?.address = address
        }
        
        if let home = home
        {
            delegate?.updateHomeVC(self, didFinishAdding: home)
        }
        
    }
    
    //Table
   override func tableView(_ tableView: UITableView, willSelectRowAt indexPath: IndexPath) -> IndexPath? {
        return nil
    }
    
    
    
    

}
