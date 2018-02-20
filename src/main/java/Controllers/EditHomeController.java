/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ParentController.PREF_WIDTH;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.Screen;
import models.Home;
import services.HomeService;

/**
 * FXML Controller class
 *
 * @author cdc
 */
public class EditHomeController extends ParentController implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField address;
    @FXML
    private Label error;
    @FXML
    private JFXButton editButton;
    
    HashMap<String, Object> response;
    ActionEvent actionEvent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupSideMenu(drawer, hamburger);
        inflateComponents();
        populateFields();
    }
    
    @Override
    protected void inflateComponents()
    {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        name.setPrefWidth(PREF_WIDTH);
        address.setPrefWidth(PREF_WIDTH);
        error.setVisible(false);
        error.setPrefWidth(PREF_WIDTH);
    }

    private void populateFields() {
        name.setText( Home.getChosenHome().getName() );
        address.setText( Home.getChosenHome().getAddress() );
    }
    
    @FXML
    private void editHome(ActionEvent e){
        actionEvent = e;
        Thread thread = new Thread(createEditHomeTask());
        thread.start();
    }
    
    private Task createEditHomeTask(){
        Task task = new Task() {
            @Override
            protected Object call() {
                response = HomeService.getInstance().updateHome(createHomeFromInput());
                return null;
            };
        };
        
        task.setOnSucceeded(new EventHandler() {
            @Override
            public void handle(Event event) {
                consumeResponse();
            }
        });
        
        return task;
    }
    
    private Home createHomeFromInput(){
        Home home = new Home();
        home.setId(Home.getChosenHome().getId());
        home.setName(name.getText());
        home.setAddress(address.getText());
        return home;
    }
    
    private void consumeResponse() {
        if(callSucceded())
        {
            Home home = (Home) response.get(RESPONSE);
            Home.setChosenHome(home);
            goToPage(actionEvent, ALL_HOMES_URL);
        }
        else{
            displayErrorMessage((String) response.get(RESPONSE));
        }
    }
    
    private boolean callSucceded(){
        return !response.get(STATUS).equals(STATUS_FAIL);
    }

    private void displayErrorMessage(String errorMessage) {
        error.setText(errorMessage);
        error.setVisible(true);
    }
    
}
