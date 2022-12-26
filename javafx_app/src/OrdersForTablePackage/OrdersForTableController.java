package OrdersForTablePackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static HomePackage.MainClass.stage;

public class OrdersForTableController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    @FXML
    void goToTableOrder(ActionEvent event) throws IOException {
        
        System.out.println("go to table order");
        Parent root = FXMLLoader.load(getClass().getResource("/SelectTablePackage/SelectTable.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void goToTableCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/TableCustomerPackage/TableCustomer.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToTableBill(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/TableBillPackage/TableBill.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
    
    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
