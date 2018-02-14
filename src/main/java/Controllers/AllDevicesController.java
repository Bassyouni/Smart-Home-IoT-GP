/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.smarthomesiot.desktop.GPIOHandler;
import com.smarthomesiot.desktop.MQTTHandler;
import com.smarthomesiot.desktop.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.Device;
import models.Home;
import models.Message;
import models.User;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import parsers.MessageParser;
import services.HomeService;

/**
 * FXML Controller class
 *
 * @author Mahmoud Mokhtar
 */
public class AllDevicesController extends ParentController implements Initializable  {

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private HBox hbox;
    
    @FXML
    private VBox centralContainer;
    
    @FXML
    private Label myDevicesLabel;
    
    @FXML
    private JFXButton editDeviceButton;
    
    @FXML
    private JFXListView<Device> devicesList;

    
    private GPIOHandler gpio; 
    
    private Device selectedDevice;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            //gpio = GPIOHandler.getInstance();
        }
        catch(Exception e)
        {
            System.err.println("Not Raspberry Pi");
        }

        inflateComponents();
        setupSideMenu(drawer, hamburger);
        fetchComponentsData();
        setupListView();

    }    
    
    
    private void initializePins(ArrayList<Device> devices)
    {
        System.out.println(devices);
        
        for(int i = 0; i < devices.size(); i++)
        {
            //System.out.println(gpio.getPinFromIndex(devices.get(i).getPinNumber()));
            //gpio.setPinOutputMode(gpio.getPinFromIndex(devices.get(i).getPinNumber()), devices.get(i).getId());
        }
    }
    
    private void fetchComponentsData()
    {
        Task task = new Task<Void>() 
        {
            @Override 
            public Void call() 
            {
                if(User.isLoggedIn())
                {
                    HomeService homeService = HomeService.getInstance();
                    if(Home.isHomeChosen())
                    {
                         HashMap<String, Object> response = homeService.getDevicesAttachedToHome(Home.getChosenHome().getId());
                        if(response.get("status").equals("failure"))
                        {
                            return null;
                        }

                        ArrayList<Device> devices = (ArrayList<Device>) response.get("response");
                        Home.getChosenHome().setDevices(devices);
                        //initializePins(devices);

                        devicesList.getItems().addAll(devices);
                    } 

                }
                
                setupMqttSubscription();
                
                return null;
            }
  
        };
       Thread thread = new Thread(task);
       thread.start();
        
    }
    
    @Override
    protected void inflateComponents()
    {

            double height = Screen.getPrimary().getVisualBounds().getHeight();
            double width = Screen.getPrimary().getVisualBounds().getWidth();
            
            devicesList.setPrefWidth(250);
            hbox.setPrefWidth(width - 60);
            centralContainer.setPrefHeight(height);
            centralContainer.setPrefWidth(width - 60);
            myDevicesLabel.setPrefWidth(250);
            editDeviceButton.setPrefWidth(250);
            devicesList.setExpanded(false);
            devicesList.setDepth(4);
            

    }
    
    
    private void setupListView()
    {
        devicesList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println(devicesList.getSelectionModel().getSelectedItem());
                selectedDevice = devicesList.getSelectionModel().getSelectedItem();
            }
        
        
        });
    }
    
  
    

    
    
    @FXML
    private void editDevice(ActionEvent event) throws IOException 
    {
        
      
        EditDeviceController controller = new EditDeviceController(selectedDevice);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditDevice.fxml"));
        
        loader.setController(controller);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
       Parent root =  (Parent) loader.load();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        
    }
    
    
    
    
    
 
    
}
