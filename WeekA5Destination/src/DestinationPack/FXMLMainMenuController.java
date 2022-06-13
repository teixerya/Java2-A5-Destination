package DestinationPack;



/*
 * @author ryanteixeira
 *
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLMainMenuController implements Initializable {

    @FXML
    AnchorPane pane;

    @FXML
    Label lblmyDestination;

    @FXML
    MenuItem menuSignOut;

    /**
     * openApp a method to open the trip history log.
     * @param e executes openApp method if Open is clicked.
     * @throws IOException 
     */
    @FXML
    private void openApp(ActionEvent e) throws IOException {

        Parent root1 = FXMLLoader.load(getClass().getResource("DView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("My Trip History Log");
        stage.show();

    }

    /**
     * showAbout a method to prompt the user about the application page.
     * @param e executes showAbout method if about is clicked.
     */
    @FXML
    private void showAbout(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("About");
        String s = "The program is designed to store records of your trips.";

        alert.setContentText(s);
        alert.show();

    }

        /**
     * // * The initialize method is Overridden to populate FXML fields. // *
     * @param url is used to resolve relative paths for the root object, and //
     * * null if the location is unknown. // * @param rb is part of the
     * ResourceBundle passed from the FXML loader and // * can be used to
     * translate text and modify local fields. //
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
