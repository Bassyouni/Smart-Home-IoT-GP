package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import models.Home;
import models.User;
import services.HomeService;


public class AddUserController extends ParentController implements Initializable 
{
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    
    @FXML
    private TextField emailTextField;
    
    private VBox centralContainer;
    
    @FXML
    private HBox hbox;
    
    @FXML
    private Label addUserLabel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setupSideMenu(drawer, hamburger);
    }    
    
    
    @Override
    protected void inflateComponents()
    {
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        emailTextField.setPrefWidth(250);
        hbox.setPrefWidth(width - 60);
        centralContainer.setPrefHeight(height);
        centralContainer.setPrefWidth(width - 60);
        addUserLabel.setPrefWidth(250);
    }
    
    @FXML
    private void addUser(ActionEvent event)
    {
        Task task = new Task<Void>() 
        {
            @Override 
            public Void call() 
            {
                HomeService homeService = HomeService.getInstance();
                if(Home.isHomeChosen())
                {
                    homeService.addUserToHomeByEmail(Home.getChosenHome().getId(), emailTextField.getText());
                }
                return null;
            } 
        };    
       Thread thread = new Thread(task);
       thread.start();    
    }
    
    
    
    
    
    
}
