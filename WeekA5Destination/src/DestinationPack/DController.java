
package DestinationPack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class that Accepts 5 textfield values, 
 * can update the trip history (userRecordList) list view and 
 * provides the logic to List action buttons. 
 * The logic to following buttons are in the controller: 
 * btnOpen, btn Delete, btnClose, btnLast, btnPrev, btnEdit 
 * and any other buttons on the trip history scene.
 *
 * @author Ryan Teixeria
 */
public class DController implements Initializable {

    @FXML
    TextField txtDestName, txtDuration, txtAirline, txtYear, txtComment;

    @FXML
    ListView userRecordList;

    @FXML
    Button btnOpen, btnDelete, btnClose, btnLast, btnPrev, btnEdit;

    private static int count = 0;
    private static int ctr = 0;

    ArrayList<Destination> destinationList = new ArrayList<>();
    ArrayList<Destination> recordList = new ArrayList<>();
    File file;
    FileOutputStream fo;
    FileInputStream fi;
    ObjectInputStream oi;
    ObjectOutputStream os;

    Destination p;

    /**
     * btnAdd takes the textfields value and adds them to the GUI list.
     * @param event will run the method after the Add to list button is clicked.
     * exception is an object that wraps an error event that occurred within a method 
     * and contains: Information about the error including its type. 
     */
    @FXML
    private void btnAdd(ActionEvent event) throws Exception {
        p = new Destination();
        p.setDestName(txtDestName.getText().trim());
        p.setDuration(txtDuration.getText().trim());
        p.setAirline((txtAirline.getText().trim()));

        p.setYear((txtYear.getText().trim()));

        p.setComment((txtComment.getText().trim()));

        userRecordList.getItems().add(p);
        destinationList.add(p);
    }

    /**
     * removeItem takes the selected item off the list.
     * @param event will run the removeItem method after the Remove item button is clicked.
     */
    @FXML
    void removeItem(ActionEvent event) {
        Object t = userRecordList.getSelectionModel().getSelectedItem();
        userRecordList.getItems().remove(t);
    }

    /**
     * btnClear will clear the GUI list.
     * @param event will run the btnClear method after the clear cart is clicked.
     */
    @FXML
    private void btnClear(ActionEvent event) {

        userRecordList.getItems().clear();
    }

    /**
     * btnSave will save the current GUI list to the file test2.dat
     * @param event will run the btnSave method after the Save to file button is clicked.
     * @throws IOException occurs when an IO operation fails.
     */
    @FXML
    private void btnSave(ActionEvent event) throws IOException {
        System.out.println(destinationList.size());

        fo = new FileOutputStream("test2.dat");
        os = new ObjectOutputStream(fo);//write object to a file
        os.writeObject(destinationList);
    }

    /**
     * btnEdit method allows the user to update the current selected item from the list and
     * update the record. The application will need to be closed and reopen to see the changes in the record.
     * @param event will run the btnEdit method after the Edit Record button is clicked.
     * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnEdit(ActionEvent event) throws IOException, FileNotFoundException,
            ClassNotFoundException, Exception {



        int i = userRecordList.getSelectionModel().getSelectedIndex();

        Destination editProduct = new Destination();

        editProduct.setDestName(txtDestName.getText().trim());
        editProduct.setDuration(txtDuration.getText().trim());
        editProduct.setAirline((txtAirline.getText().trim()));
        editProduct.setYear((txtYear.getText().trim()));
        editProduct.setComment((txtComment.getText().trim()));

        userRecordList.getItems().set(i, editProduct);

        destinationList.set(i, editProduct);
        

        oi.close();
        fi.close();

        fo = new FileOutputStream("test2.dat");
        os = new ObjectOutputStream(fo);
        os.writeObject(destinationList);

        os.close();
        fo.close();

    }

    /**
     * btnOpen method allows the user to open a file.
     * @param event will run the btnOpen method after Open File is clicked.
     * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnOpen(ActionEvent event) throws IOException, ClassNotFoundException {
        FileChooser filechooser = new FileChooser();
        file = filechooser.showOpenDialog(btnOpen.getScene().getWindow());
        fi = new FileInputStream(file.getName());
        oi = new ObjectInputStream(fi);
//        System.out.println("in if");
        recordList = (ArrayList) oi.readObject();

        if (!recordList.isEmpty()) {
//        System.out.println(productNew.size());
            Destination e = recordList.get(0);
            txtDestName.setText(e.getDestName());
            txtDuration.setText("" + e.getDuration());
            txtAirline.setText("" + e.getAirline());
            txtYear.setText("" + e.getYear());
            txtComment.setText("" + e.getComment());

            count++;
        }
    }

    /**
     * btnDelete method allows the user to delete a record from the file.
     * @param event will run the btnDelete method after the Delete record button is clicked.
      * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnDelete(ActionEvent event) throws FileNotFoundException,
            IOException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Record Delete");
        alert.setHeaderText("Permanent File Loss Alert");
        String w = "You are about to delete the record, do you want to continue? ";

        alert.setContentText(w);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("pinter here");
            String s;
            for (int i = 0; i < recordList.size(); i++) {
                s = recordList.get(i).getDestName();
                if (s.equals(txtDestName.getText())) {
                    recordList.remove(i);
                }
            }
            count--;
            txtDestName.clear();
            txtDuration.clear();
            txtAirline.clear();
            txtYear.clear();
            txtComment.clear();

            int i = userRecordList.getSelectionModel().getSelectedIndex();

            userRecordList.getItems().remove(i);

            oi.close();
            fi.close();

            fo = new FileOutputStream("test2.dat");
            os = new ObjectOutputStream(fo);
            os.writeObject(recordList);

            os.close();
            fo.close();
        }
    }

    
    /**
     * btnAbout method will prompt the user with an about page.
     * @param event if About us is clicked the btnAbout method will run.
     */
    @FXML
    private void btnAbout(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("About");
        String s = "Welcome to your Trip History";

        alert.setContentText(s);
        alert.show();

    }

    /**
     * btnFirst method navigates to the first record of the list.
     * @param event will run the btnFirst method after First Record is clicked.
     * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnFirst(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        Destination e = new Destination();
        e = recordList.get(0);
        txtDestName.setText(e.getDestName());
        txtDuration.setText("" + e.getDuration());
        txtAirline.setText("" + e.getAirline());
        txtYear.setText("" + e.getYear());
        txtComment.setText("" + e.getComment());

        count = 1;
    }

    /**
     * btnLast navigated to the last record of the list.
     * @param event will run the btnLast method after Last Record is clicked.
     * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnLast(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        Destination f = new Destination();
        f = recordList.get(recordList.size() - 1);
        txtDestName.setText(f.getDestName());
        txtDuration.setText("" + f.getDuration());
        txtAirline.setText("" + f.getAirline());
        txtYear.setText("" + f.getYear());
        txtComment.setText("" + f.getComment());

        count = recordList.size();
    }

    
    /**
     * btnNext method navigates to the next record of the list.
     * @param event will run the btnNext method after Next Record is clicked.
     * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnNext(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (count < recordList.size()) {
            Destination e = new Destination();
            e = recordList.get(count);
            txtDestName.setText(e.getDestName());
            txtDuration.setText("" + e.getDuration());
            txtAirline.setText("" + e.getAirline());
            txtYear.setText("" + e.getYear());
            txtComment.setText("" + e.getComment());

            count++;
        }
    }

    /**
     * btnPrev will select the record above your selected record.
     * @param event will run the btnPrev method after the Previous record is clicked.
     * @throws FileNotFoundException extends IOException and will be thrown by FileInputStream, 
     * FileOutputStream and RandomAccessFile constructors if a file with a specified name does not exist.
     * @throws IOException occurs when an IO operation fails.
     * @throws ClassNotFoundException is thrown when the Java Virtual Machine (JVM) tries to load a 
     * particular class and the specified class cannot be found in the classpath.
     */
    @FXML
    private void btnPrev(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (count > 1) {

            Destination g = new Destination();
            g = recordList.get(count - 2);
            txtDestName.setText("" + g.getDestName());
            txtDuration.setText("" + g.getDuration());
            txtAirline.setText("" + g.getAirline());
            txtYear.setText("" + g.getYear());
            txtComment.setText("" + g.getComment());

            count--;
        }
    }

    /**
     * handleButtonClose will close the trip history list window.
     * @param event will execute the handleButtonClose if the close button is clicked.
     */
    @FXML
    private void handleButtonClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialize the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        userRecordList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Object s = userRecordList.getSelectionModel().getSelectedItem();

                //object type into Product type
                //i will explicit type cast.
                txtDestName.setText(((Destination) s).getDestName());
                txtDuration.setText("" + ((Destination) s).getDuration());
                txtAirline.setText("" + ((Destination) s).getAirline());
                txtYear.setText("" + ((Destination) s).getYear());
                txtComment.setText("" + ((Destination) s).getComment());

                System.out.println("Mouse Event triggered");

            }
        });
    }

}
