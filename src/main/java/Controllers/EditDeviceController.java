/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.Device;

/**
 * FXML Controller class
 *
 * @author Mahmoud Mokhtar
 */
public class EditDeviceController extends ParentController implements Initializable 
{
    
     @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField pinNumber;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXButton saveButton;

    @FXML 
    private VBox vbox;
    
    @FXML 
    private HBox hbox;
    
    
    
    
    private Device selectedDevice;
    
    public EditDeviceController(Device selectedDevice)
    {
        this.selectedDevice = selectedDevice;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        System.out.println("EditDeviceController: " + this.selectedDevice);
        inflateComponents();
        setupSideMenu(drawer, hamburger);
    }    
    

    @Override
    protected void inflateComponents()
    {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        vbox.setPrefWidth(250);
        vbox.setPrefHeight(height);
        hbox.setPrefWidth(width - 40);
        hbox.setPrefHeight(height - 60);
        name.setText(selectedDevice.getName());
        description.setText(selectedDevice.getDescription());
        pinNumber.setText(String.valueOf(selectedDevice.getPinNumber())); 
    }
    
    



    
}
