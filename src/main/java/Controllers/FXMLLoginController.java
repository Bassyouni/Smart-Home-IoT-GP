/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.smarthomesiot.desktop.UsersService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    private JFXButton loginButton;
    
    @FXML
    private void login(ActionEvent event) throws IOException 
    {
        
        UsersService userService = UsersService.getInstance();
        HashMap<String, Object> response = userService.login(emailTextField.getText(), passwordTextField.getText());
        if(response.get("status").equals("failure"))
        {
            errorMessage.setText(response.get("error").toString());
            return;
        }
        System.out.print(response);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
