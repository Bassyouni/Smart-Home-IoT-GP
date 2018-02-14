package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import models.User;


public class SettingsController extends ParentController implements Initializable 
{
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private VBox centralContainer;
    
    @FXML
    private HBox hbox;
    
    @FXML
    private HBox hbox2;
    
    @FXML
    private Label settingsLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        inflateComponents();
        setupSideMenu(drawer, hamburger);
    }    
    
    @Override
    protected void inflateComponents()
    {
        
        double height = Screen.getPrimary().getVisualBounds().getHeight();
        double width = Screen.getPrimary().getVisualBounds().getWidth();
        hbox.setPrefWidth(width - 60);
        hbox2.setPrefWidth(width - 60);
        centralContainer.setPrefHeight(height);
        centralContainer.setPrefWidth(width - 60);
        settingsLabel.setPrefWidth(250);
    }
    
    
    @FXML
    private void changeHome(ActionEvent event) throws IOException 
    {
        goToHomesView(event);   
    }
    
}
