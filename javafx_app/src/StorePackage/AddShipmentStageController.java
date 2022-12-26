package StorePackage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddShipmentStageController implements Initializable {

    @FXML
    private Button next;
    @FXML
//    private TextField ShipmentDateTf;
//    @FXML
    private DatePicker ShipmentDateTf;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void next_method(ActionEvent event) throws Exception {
        
         LocalDate localDate = ShipmentDateTf.getValue();
         String ShipmentDateTfstring =localDate.toString();
               
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Data");
        alert.setHeaderText("Are you sure to insert this date");
        alert.setContentText("date: " +ShipmentDateTfstring);
        
        Optional<ButtonType> x=alert.showAndWait();
        
        if (! x.isPresent()){} 
        
        else if (x.get()==ButtonType.OK){
            shipment s=new shipment(ShipmentDateTfstring); 
            s.insert();
            
        Stage stage =new Stage();      
        Parent root = FXMLLoader.load(getClass().getResource("addshipmentstage2.fxml"));    
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }
        
        else if (x.get()==ButtonType.CANCEL){}
        
        
        
        
    }

    @FXML
    private void initialize(ActionEvent event) {
    }
    
}
