package TableCustomerPackage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import static HomePackage.MainClass.stage;

public class TableCustomerController implements Initializable {

    @FXML
    private ComboBox<Integer> numOfChairsCB;
    
    @FXML
    private ComboBox<Integer> tableID_CB;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numOfChairsCB.getItems().addAll(2,4,6);
    }  
    
    
    @FXML
    void tableSelection(ActionEvent event) {
        int selectedChairs = numOfChairsCB.getSelectionModel().getSelectedItem();

        try{
            PreparedStatement pstmt = HomePackage.MainClass.con.prepareStatement("select tables_id from tables where chair_number=? and table_status=0 ");
            pstmt.setInt(1, selectedChairs);
            ResultSet r = pstmt.executeQuery();

            tableID_CB.getItems().clear();
            while(r.next()){
                tableID_CB.getItems().add( r.getInt(1) );
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    
    @FXML
    void doneCustomer(ActionEvent event) throws IOException {
        int selectedTable = tableID_CB.getSelectionModel().getSelectedItem();
        
        try{
            PreparedStatement pstmt = HomePackage.MainClass.con.prepareStatement("update tables set table_status=1 where tables_id=? ");
            pstmt.setInt(1, selectedTable);
            pstmt.executeUpdate();

            Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
  
    @FXML
    void goToEmployees(ActionEvent event) {
    }
    @FXML
    void goToMenu(ActionEvent event) {
    }
    @FXML
    void goToOrders(ActionEvent event) {
    }
    @FXML
    void goToStore(ActionEvent event) {
    }
    @FXML
    void goToTables(ActionEvent event) {
    }
    
}
