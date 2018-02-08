/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class AllDevicesController implements Initializable {

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private HBox hbox;
    
    @FXML
    private Label debugLabel;

    @FXML
    private JFXListView<Device> devicesList;
    
    private GPIOHandler gpio; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gpio = GPIOHandler.getInstance();
        inflateComponents();
        setupSideMenu();
        fetchComponentsData();
        setupListView();
        setupMqttSubscription();
        
        
        

            

               
        
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
        if(User.isLoggedIn())
        {
            HomeService homeService = HomeService.getInstance();
            if(Home.isHomeChosen())
            {
                 HashMap<String, Object> response = homeService.getDevicesAttachedToHome(Home.getChosenHome().getId());
                if(response.get("status").equals("failure"))
                {
                    return;
                }

                ArrayList<Device> devices = (ArrayList<Device>) response.get("response");
                Home.getChosenHome().setDevices(devices);
                initializePins(devices);

                devicesList.getItems().addAll(devices);
            } 
            
        }
    }
    
    private void inflateComponents()
    {
        try 
        {
            double height = Screen.getPrimary().getVisualBounds().getHeight();
            double width = Screen.getPrimary().getVisualBounds().getWidth();
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
            box.setPrefHeight(height);
            devicesList.setPrefWidth(200);
            hbox.setPrefWidth(width - 60);
            drawer.setPrefHeight(height);
            drawer.setSidePane(box);
            drawer.toFront();
            devicesList.setExpanded(true);
            devicesList.setDepth(1);
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void setupListView()
    {
        devicesList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println(devicesList.getSelectionModel().getSelectedItem());
            }
        
        
        });
    }
    
    private void setupSideMenu()
    {
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
    
    private void setupMqttSubscription()
    {
        try {
            MQTTHandler handler = MQTTHandler.getInstance();
            if(Home.isHomeChosen())
            {
                handler.subscribe(Home.getChosenHome().getTopic(), new IMqttMessageListener() {
                    @Override
                    public void messageArrived(String topic, final MqttMessage message) throws Exception {
                        System.out.println(topic + "  ---  " + message.toString());
                         Message m = MessageParser.getInstance().getObject(message.toString()); 
                         
                        Device device = new Device();
                      
                        if(m.getDeviceId() != null)
                        {
                            try
                            {
                                device = Home.getChosenHome().searchForDevice(m.getDeviceId());
                               if(m.getCommand().equalsIgnoreCase("on"))
                               {
                                    //gpio.switchOn(gpio.getPinFromIndex(device.getPinNumber()), device.getId());
                               }
                               else if(m.getCommand().equalsIgnoreCase("off"))
                               {
                                    //gpio.switchOff(gpio.getPinFromIndex(device.getPinNumber()), device.getId());
                               }
                                
                               


                               
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }
                                



                        }
                        final String deviceName, commandName;
                        deviceName = device.getName();
                        commandName = m.getCommand();
                         Platform.runLater(new Runnable(){
                    
                            @Override
                            public void run() {
                               debugLabel.setText(deviceName + " - " + commandName);
                            }
                        });
                       
                        
                    }
                } );
            }
            
        } catch (MqttException ex) {
            Logger.getLogger(AllDevicesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
 
    
}
