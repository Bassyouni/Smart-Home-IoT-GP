/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author cdc
 */
public class LoginController implements Initializable{
    
    @FXML private static JFXButton loginButton;
    @FXML private static JFXTextField email;
    @FXML private static JFXPasswordField password;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controllers.LoginController.initialize()");
    }
    
    public static void login(){
        System.out.println(email.getText().toString());
    }
    
    public static void goToSignUpPage(){
        System.out.println("Controllers.LoginController.goToSignUpPage()");
    }
}
