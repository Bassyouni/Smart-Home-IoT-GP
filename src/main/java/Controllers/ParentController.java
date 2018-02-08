/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class ParentController 
{
    
     
    
    protected void goToDevicesView(MouseEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllDevices.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    protected void goToDevicesView(KeyEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllDevices.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    
    protected void inflateComponents()
    {
       
    }
    
    
  
    
    protected void setupSideMenu(final JFXDrawer drawer, JFXHamburger hamburger)
    {
        try 
        {
            double height = Screen.getPrimary().getVisualBounds().getHeight();
            double width = Screen.getPrimary().getVisualBounds().getWidth();
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
            box.setPrefHeight(height);
            for(final Node node: box.getChildren())
            {
                if(node.getAccessibleText() != null)
                {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event) 
                        {
                            switch(node.getAccessibleText())
                            {
                                case "mydevices": 
                                {
                                    try 
                                    {
                                        goToDevicesView(event);
                                    } 
                                    catch (IOException ex) 
                                    {
                                        Logger.getLogger(AllHomesController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                }
                                case "users": break;
                                case "logout": break;
                            }
                        }
                    });
                }  
            }

            drawer.setPrefHeight(height);
            drawer.setSidePane(box);
            drawer.toFront();

        } 
        catch (IOException ex) 
        {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {                               
            @Override
            public void handle(MouseEvent event)         
            {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();        
                if(drawer.isShown())
                {
                    drawer.close();
                }
                else
                {
                    drawer.open();
                }
            }    
        });
    }
    
    
    
    
}
