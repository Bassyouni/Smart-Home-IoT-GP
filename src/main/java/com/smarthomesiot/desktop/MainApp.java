package com.smarthomesiot.desktop;

import database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
    
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setScene(scene);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        
        
        stage.show();
        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
        () {

              @Override
              public void handle(KeyEvent t) {
                if(t.getCode()==KeyCode.ESCAPE)
                {
                    try {
                       System.exit(0);
                    } catch (Exception ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }
          });
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //System.out.println(DatabaseManager.connect());
        //DatabaseManager.constructDatabase();
        /*ResultSet rs = DatabaseManager.executeQuery("select * from User");
        try {
            rs.next();
            System.out.println(rs.getString("name"));
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DatabaseManager.closeConnection();
*/
    }

}