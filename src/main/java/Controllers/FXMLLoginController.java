/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import services.UsersService;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.User;

/**
 * FXML Controller class
 *
 * @author Mahmoud Mokhtar
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private JFXTextField emailTextField;
    @FXML
    private JFXPasswordField passwordTextField;
    @FXML
    private Label errorMessage;
    @FXML
    private VBox vBox;
    
    @FXML
    private ImageView imageView;
    
    private  boolean canLogin = false;
    
    @FXML
    private void login(final ActionEvent event) throws IOException 
    {
       
        Task task = new Task<Void>() 
        {
            @Override 
            public Void call() 
            {
                executeLogin();
                return null;
            }
            
            
            
        };
        
        task.setOnSucceeded(new EventHandler(){
            @Override
            public void handle(Event e) 
            {
                if(canLogin)
                {
                    try {
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        
                        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllHomes.fxml"));
                        
                        Scene scene = new Scene(root);
                        
                        stage.setScene(scene);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        
        
        });
        
       Thread thread = new Thread(task);
       thread.start();
       
        
        
        
    }
    
    
    private void executeLogin()
    {
        
            UsersService userService = UsersService.getInstance();
            HashMap<String, Object> response = userService.login(emailTextField.getText(), passwordTextField.getText());
            if(response.get("status").equals("failure"))
            {
                errorMessage.setText(response.get("error").toString());
                return;
            }
            User.setLoggedUser((User)response.get("response"));
            System.out.print(User.getLoggedUser());
            canLogin = true;
           
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageView.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
        imageView.setFitHeight(Screen.getPrimary().getVisualBounds().getHeight());
        vBox.setPrefSize(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
    }    
    
}
