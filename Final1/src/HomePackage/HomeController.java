package HomePackage;

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

public class HomeController implements Initializable {

    @FXML
    void goToEmployees(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/databasejohn/stage1.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MenuPackage/Menu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToOrders(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/OrdersPackage/Orders.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToStore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/StorePackage/Stage1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToTables(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/TablesPackage/Tables.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
