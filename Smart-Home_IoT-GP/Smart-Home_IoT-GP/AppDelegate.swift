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
        
        self.window?.rootViewController = initialVC
        self.window?.makeKeyAndVisible()

        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }


}

