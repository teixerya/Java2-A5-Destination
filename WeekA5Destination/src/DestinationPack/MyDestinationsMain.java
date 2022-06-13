
package DestinationPack;
/*

 * @author ryanteixeira
 *
*/


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * MyDestinationsMain is the main class that will launch FXML file "FXMLMainMenu.fxml" and link to
 * the CSS file "CSSMainMenu.css". The start method will generate a new scene from the
 * FXML file "FXMLMainMenu.fxml" with the CSS styled by the file "CSSMainMenu.css".
 * @author ryanteixeira
 */
public class MyDestinationsMain extends Application {


    /**
     * The start method is used to create a user window by creating a stage
     * and loading the "View" file.
     * @param stage is the graphic interface that contain the FXML generated
     * GUI.
     */
    @Override
    public void start(Stage stage)throws Exception{

        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));

        stage.setTitle("Assignment 5 & 6");
        stage.setScene(new Scene(root));
        root.setId("pane");
        
        stage.setMaximized(true);
        stage.show();
        
        root.setFocusTraversable(true);
        

    }
    
    
    public static void main(String[] args) {
       
        launch(args);    
        
    }

}
