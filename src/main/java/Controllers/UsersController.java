/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.Home;
import models.User;
import services.HomeService;

/**
 * FXML Controller class
 *
 * @author Mahmoud Mokhtar
 */
public class UsersController extends ParentController implements Initializable 
{
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private JFXListView<User> usersList;
    
    @FXML
    private VBox centralContainer;
    
    @FXML
    private HBox hbox;
    
    @FXML
    private Label myUsersLabel;
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        inflateComponents();
        setupSideMenu(drawer, hamburger);
        fetchComponentsData();
        setupListView();
    }   
    
    @Override
    protected void inflateComponents()
    {
        
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();


        usersList.setPrefWidth(250);
        hbox.setPrefWidth(width - 60);
        centralContainer.setPrefHeight(height);
        centralContainer.setPrefWidth(width - 60);
        myUsersLabel.setPrefWidth(250);
        usersList.setExpanded(false);
        usersList.setDepth(4);
    }
    
    private void fetchComponentsData()
    {
        Task task = new Task<Void>() 
        {
            @Override 
            public Void call() 
            {
                if(Home.isHomeChosen())
                {
                    HomeService homeService = HomeService.getInstance();
                    HashMap<String, Object> response = homeService.getUsersAttachedToHome(Home.getChosenHome().getId());
                    if(response.get("status").equals("failure"))
                    {
                        return null;
                    }      
                    ArrayList<User> users = (ArrayList<User>) response.get("response");     
                    usersList.getItems().addAll(users);
                }
                return null;
            } 
        };    
       Thread thread = new Thread(task);
       thread.start();   
       
    }
    
    
    
    private void setupListView()
    {
        usersList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println(usersList.getSelectionModel().getSelectedItem());

                
               
            }
        });
        
        usersList.setOnKeyPressed(new EventHandler<KeyEvent>(){
            
            @Override
            public void handle(KeyEvent event)
            {
                if(event.getCode() == KeyCode.SPACE)
                {
                     System.out.println(usersList.getSelectionModel().getSelectedItem());
                
                   
                }
               
            }
        });
        
        
    }
    
    
    
    @FXML
    private void addUser(ActionEvent event) throws IOException 
    {
        goToAddUserView(event);   
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
