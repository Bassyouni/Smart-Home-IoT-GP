/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ParentController.PREF_WIDTH;
import static Controllers.ParentController.PROFILE_URL;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import static javax.management.Query.value;
import javax.security.auth.callback.ConfirmationCallback;
import models.User;
import services.UsersService;

/**
 * FXML Controller class
 *
 * @author cdc
 */
public class EditProfileController extends ParentController implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField passwordConfirmation;
    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private TextField birthDate;
    @FXML
    private Label error;
    
    HashMap<String, Object> response;
    boolean updated = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupSideMenu(drawer, hamburger);
        inflateComponents();
        PopulateFieldWithOriginalInfo();
    }    
    
    @Override
    protected void inflateComponents()
    {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        name.setPrefWidth(PREF_WIDTH);
        email.setPrefWidth(PREF_WIDTH);
        password.setPrefWidth(PREF_WIDTH);
        passwordConfirmation.setPrefWidth(PREF_WIDTH);
        newPassword.setPrefWidth(PREF_WIDTH);
        birthDate.setPrefWidth(PREF_WIDTH);
        error.setVisible(false);
        error.setPrefWidth(PREF_WIDTH);
    }
    
    private void PopulateFieldWithOriginalInfo(){
        name.setText( User.getLoggedUser().getName() );
        email.setText( User.getLoggedUser().getEmail() );
        birthDate.setText( User.getLoggedUser().getBirthDate());
    }
    
    @FXML
    private void editProfile(ActionEvent e){
        Thread thread = new Thread(createEditProfileTask(e));
        thread.start();
    } 
    
    private Task createEditProfileTask(final ActionEvent e){
        Task task = new Task<Void>() {
            @Override
            protected Void call(){
                callEditProfile();
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event event) {
                consumeResponse();
                if(updated)
                    goToPage(e, PROFILE_URL);
            }
        });
        return task;
    }
    
    private void callEditProfile() {
        UsersService usersService = UsersService.getInstance();
        if( isPasswordConfirmed() )
            response = usersService.updateUser( createUserFromInputs() );
        else{
            response = null;
            showPasswordError();
        }
    }

    private User createUserFromInputs() {
        User user = new User();
        user.setId(User.getLoggedUser().getId());
        user.setName( name.getText() );
        user.setEmail( email.getText() );
        user.setBirthDate( birthDate.getText() );
        user.setPassword( newPassword.getText() );
        return user;
    }

    private boolean isPasswordConfirmed() {
        return password.getText().equals(User.getLoggedUser().getPassword()) 
                &&
               password.getText().equals(passwordConfirmation.getText());
    }

    private void showPasswordError() {
        if(password.getText().equals(User.getLoggedUser().getPassword()))
            error.setText("Password is Incorrect!");
        else if(password.getText().equals(passwordConfirmation.getText()))
            error.setText("Passwords does not match!");
        error.setVisible(true);
    }
    
    private void consumeResponse(){
        if(response == null)
            return;
        else if(response.get(STATUS).equals(STATUS_FAIL))
            error.setText(response.get(RESPONSE).toString());
        else{
            User.setLoggedUser((User) response.get(RESPONSE));
            updated = true;
        }
    }
}
