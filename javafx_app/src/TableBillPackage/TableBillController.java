package TableBillPackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import static HomePackage.MainClass.stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableBillController implements Initializable {

    @FXML
    private ComboBox<Integer> tableID_CB;

    
    public void initialize(URL url, ResourceBundle rb) {
        try{
    
            PreparedStatement pstmt = HomePackage.MainClass.con.prepareStatement("select tables_id from tables where table_status=1 ");
            ResultSet r = pstmt.executeQuery();

            tableID_CB.getItems().clear();
            while(r.next()){
                tableID_CB.getItems().add( r.getInt(1) );
            }

        }catch(Exception e){
            System.out.println(e);
        }
        
    }    
    
    
    @FXML
    void goToBill(ActionEvent event) throws IOException {
        int tableID = tableID_CB.getSelectionModel().getSelectedItem();
        
        try{
            
            int count = 0 ;
            PreparedStatement pstmt = HomePackage.MainClass.con.prepareStatement("select count(*) from orders where table_id=? and bill_id is null ");
            pstmt.setInt(1, tableID);
            ResultSet r = pstmt.executeQuery();
            while(r.next()){
                count = r.getInt(1) ;
                System.out.println("count : " + count);
            }
                            
            pstmt = HomePackage.MainClass.con.prepareStatement("select orders_id from orders where table_id=? and bill_id is null ");
            pstmt.setInt(1, tableID);
            r = pstmt.executeQuery();

            int [] tablesList = new int[count];
            
            count = 0 ;
            while(r.next()){
                tablesList[count] = r.getInt(1);
                count++ ;
            }
            
            // tableList is READY !!
            System.out.println("Table Orders : ");
            for(int i = 0 ; i < tablesList.length ; i++){
                System.out.println(tablesList[i]);
            }
            
            OLDBillPackage.BillController.orderID = tablesList ;
            Parent root = FXMLLoader.load(getClass().getResource("/BillPackage/bill.fxml"));
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
    void goToEmployees(ActionEvent event) {
    }
    @FXML
    void goToHome(ActionEvent event) {
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
