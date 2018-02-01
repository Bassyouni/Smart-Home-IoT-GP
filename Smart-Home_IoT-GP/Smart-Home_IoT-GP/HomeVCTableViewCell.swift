//
//  HomeVCTableViewCell.swift
//  Smart-Home_IoT-GP
//
//  Created by MacBook Pro on 2/1/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

class HomeVCTableViewCell: UITableViewCell {
    @IBOutlet weak var homeNameLabel: UILabel!

    @IBOutlet weak var homeImage: UIImageView!

    public func configureCell(home: Home,imageNum : Int)
    {
        homeNameLabel.text = home.name.capitalized
        homeImage.image = UIImage(named: "\(imageNum)")
    }

}
