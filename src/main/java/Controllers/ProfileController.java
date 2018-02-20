/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import models.User;

/**
 * FXML Controller class
 *
 * @author cdc
 */
public class ProfileController extends ParentController implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label birthDate;
    @FXML 
    private JFXButton editProfile;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupSideMenu(drawer, hamburger);
        inflateComponents();
        initeLabels();
    }

    @Override
    protected void inflateComponents()
    {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        name.setPrefWidth(PREF_WIDTH);
        email.setPrefWidth(PREF_WIDTH);
        birthDate.setPrefWidth(PREF_WIDTH);
    }

    private void initeLabels() {
        name.setText( User.getLoggedUser().getName() );
        email.setText( User.getLoggedUser().getEmail() );
        birthDate.setText( User.getLoggedUser().getBirthDate() );
    }
    
    @FXML
    private void goToEditProfile(ActionEvent e){
        goToPage(e, EDIT_PROFILE_URL);
    }
    
}
