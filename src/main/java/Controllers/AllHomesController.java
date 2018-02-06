package Controllers;



import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
public class AllHomesController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
     @FXML
    private HBox hbox;

    @FXML
    private JFXListView<Home> homesList;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        inflateComponents();
        setupSideMenu();
        fetchComponentsData();
        setupListView();
       
        
       
        
       
        
    }    
    
    private void fetchComponentsData()
    {
        if(User.isLoggedIn())
        {
            HomeService homeService = HomeService.getInstance();
            HashMap<String, Object> response = homeService.getAllHomesAttachedToUser(User.getLoggedUser().getId());
            if(response.get("status").equals("failure"))
            {
                return;
            }
            
            ArrayList<Home> homes = (ArrayList<Home>) response.get("response");

            
            homesList.getItems().addAll(homes);
            
            
            
        }
    }
    
    private void inflateComponents()
    {
        try 
        {
            double height = Screen.getPrimary().getVisualBounds().getHeight();
            double width = Screen.getPrimary().getVisualBounds().getWidth();
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
            box.setPrefHeight(height);
            homesList.setPrefWidth(200);
            hbox.setPrefWidth(width - 60);
            drawer.setPrefHeight(height);
            drawer.setSidePane(box);
            drawer.toFront();
            homesList.setExpanded(true);
            homesList.setDepth(1);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void setupListView()
    {
        homesList.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event)
            {
                System.out.println(homesList.getSelectionModel().getSelectedItem());
                Home.setChosenHome(homesList.getSelectionModel().getSelectedItem());
                
                try {
                    goToDevicesView(event);
                } catch (IOException ex) {
                    Logger.getLogger(AllHomesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        homesList.setOnKeyPressed(new EventHandler<KeyEvent>(){
            
            @Override
            public void handle(KeyEvent event)
            {
                if(event.getCode() == KeyCode.SPACE)
                {
                     System.out.println(homesList.getSelectionModel().getSelectedItem());
                     Home.setChosenHome(homesList.getSelectionModel().getSelectedItem());
                
                    try {
                        goToDevicesView(event);
                    } catch (IOException ex) {
                        Logger.getLogger(AllHomesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
            }
        });
        
        
    }
    
    private void setupSideMenu()
    {
         final HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask.setRate(-1);
        
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                               
            @Override
            public void handle(MouseEvent event)         
            {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
        
                if(drawer.isShown())
                {
                    drawer.close();
                }
                else
                {
                    drawer.open();
                }
            }    
        });
    }
    
    
    private void goToDevicesView(MouseEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllDevices.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    private void goToDevicesView(KeyEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AllDevices.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
    }
    
    
    
}
