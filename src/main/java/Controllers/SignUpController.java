/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import models.User;
import services.UsersService;

/**
 * FXML Controller class
 *
 * @author cdc
 */
public class SignUpController extends ParentController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField passwordConfirmation;
    @FXML
    private TextField birthDate;
    
    
     
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
    @FXML
    private HBox hbox7;
    
    
    
    private boolean success;
    
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
        hbox7.setPrefWidth(width - 60);
        centralContainer.setPrefHeight(height);
        centralContainer.setPrefWidth(width - 60);
        
        name.setPrefWidth(PREF_WIDTH);
        email.setPrefWidth(PREF_WIDTH);
        password.setPrefWidth(PREF_WIDTH);
        passwordConfirmation.setPrefWidth(PREF_WIDTH);
        birthDate.setPrefWidth(PREF_WIDTH);
    }
    
    private Task createSignUpTask(){
        Task task = new Task<Void>(){
            @Override
            protected Void call() {
                callSignUpService();
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler(){
            @Override
            public void handle(Event event) {
                try {
                    goToHomesView(event);
                } catch (IOException ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        return task;
    }
    
    private void callSignUpService(){
        HashMap<String, Object> response = UsersService.getInstance().signUp(
                        name.getText(),
                        email.getText(),
                        birthDate.getText(),
                        password.getText(),
                        passwordConfirmation.getText()
                );
        checkResponse(response);
        if(success)
            saveUser(response);
    }
    
    private void saveUser(HashMap<String, Object> response){
        User.setLoggedUser((User)response.get("response"));
    }
    
    private void checkResponse(HashMap<String, Object> response){
        success = !response.get(STATUS).equals(STATUS_FAIL);
    }
    
    @FXML
    private void signUp(ActionEvent e){
        Thread thread = new Thread(createSignUpTask());
        thread.start();
    }
    
}
