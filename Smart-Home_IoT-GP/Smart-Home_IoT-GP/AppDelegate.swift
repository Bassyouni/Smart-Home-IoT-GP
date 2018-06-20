//
//  AppDelegate.swift
//  Smart-Home_IoT-GP
//
//  Created by Bassyouni on 1/18/18.
//  Copyright © 2018 Bassyouni. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
//        test data
//        currentUser.id = "5a6867fe90786547d40078e2"
//        currentUser.name = "Omar Bassyouni"
//        currentUser.birthDate = "23-12-2011"
//        
//        UserDefaults.standard.set(currentUser.id, forKey: userId)
//
//        UserDefaults.standard.set(currentUser.name, forKey: userName)
//
//        UserDefaults.standard.set(currentUser.birthDate, forKey: userBirthDate)
        
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let initialVC: UIViewController!
        
        if UserDefaults.standard.object(forKey: userId) != nil
        {
            currentUser.name = UserDefaults.standard.string(forKey: userName)!
            currentUser.id = UserDefaults.standard.string(forKey: userId)!
            currentUser.birthDate = UserDefaults.standard.string(forKey: userBirthDate)!
            
            let homeVC = storyboard.instantiateViewController(withIdentifier: "HomeVC")
            let sideMenuVC = storyboard.instantiateViewController(withIdentifier: "SideVC")
            
            let containerVC = MFSideMenuContainerViewController.container(withCenter: homeVC , leftMenuViewController: sideMenuVC, rightMenuViewController: nil)
            
            initialVC = containerVC
        }
        else
        {
            initialVC = storyboard.instantiateViewController(withIdentifier: "LoginVC")
        }
        
        //TODO: remove test code
//        self.window = UIWindow(frame: UIScreen.main.bounds)
//        self.window!.backgroundColor = UIColor.white
//        initialVC = StatisticsVC()
        //end of test code
        
        self.window?.rootViewController = initialVC
        self.window?.makeKeyAndVisible()

        return true
    }
    
    func application(_ application: UIApplication, supportedInterfaceOrientationsFor window: UIWindow?) -> UIInterfaceOrientationMask
    {
        if let rootViewController = self.topViewControllerWithRootViewController(rootViewController: window?.rootViewController)
        {
            if (rootViewController.responds(to: Selector(("canRotate"))))
            {
                // Unlock landscape view orientations for this view controller
                return .landscape ;
            }
        }
        
        // Only allow portrait (standard behaviour)
        return .portrait;
    }
    
    private func topViewControllerWithRootViewController(rootViewController: UIViewController!) -> UIViewController?
    {

        if (rootViewController == nil) { return nil }

        if let vc =  rootViewController as? MFSideMenuContainerViewController
        {
           return topViewControllerWithRootViewController(rootViewController: vc.centerViewController as! UIViewController)
        }
        if (rootViewController.isKind(of: (UITabBarController).self))
        {
            return topViewControllerWithRootViewController(rootViewController: (rootViewController as! UITabBarController).selectedViewController)
        } else if (rootViewController.isKind(of:(UINavigationController).self))
        {
            return topViewControllerWithRootViewController(rootViewController: (rootViewController as! UINavigationController).visibleViewController)
        } else if (rootViewController.presentedViewController != nil)
        {
            return topViewControllerWithRootViewController(rootViewController: rootViewController.presentedViewController)
        }
        return rootViewController
    }


}

