/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.smarthomesiot.desktop.GPIOHandler;
import com.smarthomesiot.desktop.MQTTHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import models.Device;
import models.Home;
import models.Message;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import parsers.MessageParser;


public class ParentController {
    
    protected static final int PREF_WIDTH = 250;
    protected static final String STATUS = "status";
    protected static final String STATUS_FAIL = "failure";
    protected static final String RESPONSE = "response";
     
    protected static final String ALL_DEVICES_URL = "/fxml/AllDevices.fxml";
    protected static final String USERS_URL = "/fxml/Users.fxml";
    protected static final String ALL_HOMES_URL = "/fxml/AllHomes.fxml";
    protected static final String PROFILE_URL = "/fxml/Proile.fxml";
    protected static final String EDIT_PROFILE_URL = "/fxml/EditProfile.fxml";

     
     
    protected void goToPage(KeyEvent event, String pageUrl){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(pageUrl));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    protected void goToPage(MouseEvent event, String pageUrl){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(pageUrl));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void goToPage(ActionEvent event, String pageUrl){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(pageUrl));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    protected void goToUsersView(Object event) throws IOException
    {
        KeyEvent keyE;
        MouseEvent mouseE;
        Stage stage;
        ActionEvent actionE;
        if(event instanceof KeyEvent)
        {
            keyE = (KeyEvent) event;
            stage = (Stage) ((Node)keyE.getSource()).getScene().getWindow();
        }
        else if(event instanceof MouseEvent)
        {
            mouseE = (MouseEvent) event;
            stage = (Stage) ((Node)mouseE.getSource()).getScene().getWindow();
        }
        else
        {
            actionE = (ActionEvent) event;
            stage = (Stage) ((Node)actionE.getSource()).getScene().getWindow();
        }
 
 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Users.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    protected void goToHomesView(Object event) throws IOException
    {
        KeyEvent keyE;
        MouseEvent mouseE;
        ActionEvent actionE;
        Stage stage;
        if(event instanceof KeyEvent)
        {
            keyE = (KeyEvent) event;
            stage = (Stage) ((Node)keyE.getSource()).getScene().getWindow();
        }
        else if(event instanceof MouseEvent)
        {
            mouseE = (MouseEvent) event;
            stage = (Stage) ((Node)mouseE.getSource()).getScene().getWindow();
        }
        else
        {
            actionE = (ActionEvent) event;
            stage = (Stage) ((Node)actionE.getSource()).getScene().getWindow();
        }
 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllHomes.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    
    
    protected void goToAddUserView(Object event) throws IOException
    {
        KeyEvent keyE;
        MouseEvent mouseE;
        Stage stage;
        ActionEvent actionE;
        if(event instanceof KeyEvent)
        {
            keyE = (KeyEvent) event;
            stage = (Stage) ((Node)keyE.getSource()).getScene().getWindow();
        }
        else if(event instanceof MouseEvent)
        {
            mouseE = (MouseEvent) event;
            stage = (Stage) ((Node)mouseE.getSource()).getScene().getWindow();
        }
        else
        {
            actionE = (ActionEvent) event;
            stage = (Stage) ((Node)actionE.getSource()).getScene().getWindow();
        }
 
 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllDevices.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    
    protected void goToSettingsView(Object event) throws IOException
    {
        KeyEvent keyE;
        MouseEvent mouseE;
        Stage stage;
        if(event instanceof KeyEvent)
        {
            keyE = (KeyEvent) event;
            stage = (Stage) ((Node)keyE.getSource()).getScene().getWindow();
        }
        else
        {
            mouseE = (MouseEvent) event;
            stage = (Stage) ((Node)mouseE.getSource()).getScene().getWindow();
        }
 
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    
    protected void inflateComponents(){}
    
    
  
    
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
                                case "users":
                                {
                                    try 
                                    {
                                        goToUsersView(event);
                                        break;
                                    } catch (IOException ex) 
                                    {
                                        Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                case "settings":
                                {
                                  try 
                                    {
                                        goToSettingsView(event);
                                        break;
                                    } catch (IOException ex) 
                                    {
                                        Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
                                    }  
                                }
                                    
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
    
    
    protected void setupMqttSubscription()
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
                                //GPIOHandler gpio = GPIOHandler.getInstance();
                                device = Home.getChosenHome().searchForDevice(m.getDeviceId());
                               if(m.getCommand().equalsIgnoreCase("on"))
                               {
                                   // gpio.switchOn(gpio.getPinFromIndex(device.getPinNumber()), device.getId());
                                   System.out.println("GPIO HANDLER: Simulate ON");
                               }
                               else if(m.getCommand().equalsIgnoreCase("off"))
                               {
                                    //gpio.switchOff(gpio.getPinFromIndex(device.getPinNumber()), device.getId());
                                   System.out.println("GPIO HANDLER: Simulate OFF");
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
