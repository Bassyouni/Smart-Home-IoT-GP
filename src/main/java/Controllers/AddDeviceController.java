/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import models.Device;
import models.Home;
import services.HomeService;

/**
 * FXML Controller class
 *
 * @author cdc
 */
public class AddDeviceController extends ParentController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField pinNumber;
    
    @FXML
    private VBox centralContainer;
    @FXML
    private HBox hbox1;
    @FXML
    private HBox hbox2;
    @FXML
    private HBox hbox3;
    @FXML
    private HBox hbox4;
    @FXML
    private HBox hbox5;
    @FXML
    private HBox hbox6;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inflateComponents();
        setupSideMenu(drawer, hamburger);
    }    
    
    @Override
    protected void inflateComponents()
    {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        
        hbox1.setPrefWidth(width - 60);
        hbox2.setPrefWidth(width - 60);
        hbox3.setPrefWidth(width - 60);
        hbox4.setPrefWidth(width - 60);
        hbox5.setPrefWidth(width - 60);
        hbox6.setPrefWidth(width - 60);
        centralContainer.setPrefHeight(height);
        centralContainer.setPrefWidth(width - 60);
        
        name.setPrefWidth(PREF_WIDTH);
        description.setPrefWidth(PREF_WIDTH);
        type.setPrefWidth(PREF_WIDTH);
        pinNumber.setPrefWidth(PREF_WIDTH);
    }
    
    @FXML
    private void addDevice(ActionEvent e){
        Thread thread = new Thread(createAddingTask());
        thread.start();
    }
    
    private Task createAddingTask(){
        Task task = new Task<Void>(){
            @Override
            protected Void call(){
                HomeService.getInstance().addDeviceToHome(Home.getChosenHome().getId(), createDeviceFromInputs());
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    goToDevicesView((MouseEvent) event);
                } catch (IOException ex) {
                    Logger.getLogger(AddDeviceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return task;
    }
    
    private Device createDeviceFromInputs(){
        Device device = new Device();
        device.setName(name.getText());
        device.setType(type.getText());
        device.setDescription(description.getText());
        device.setPinNumber( Integer.parseInt( pinNumber.getText() ) );
        return device;
    }
}
