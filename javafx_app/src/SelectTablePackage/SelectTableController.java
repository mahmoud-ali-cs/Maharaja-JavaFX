package SelectTablePackage;

import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

public class SelectTableController implements Initializable {

    
    @FXML
    private ComboBox<Integer> tableCB;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = HomePackage.MainClass.con ;
        try{
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT DISTINCT TABLES_ID FROM TABLES ORDER BY TABLES_ID");
            ObservableList <Integer> tables = FXCollections.observableArrayList();
            while (rec.next()){
                tables.add(rec.getInt("TABLES_ID"));
            }
            tableCB.setItems(tables);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }    
    
    @FXML
    void goToTableOrder(ActionEvent event) {
        int tableID = tableCB.getSelectionModel().getSelectedItem();
        
        try{
            Connection con = HomePackage.MainClass.con ;
            
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM orders ");
            ResultSet r = pstmt.executeQuery();
            
            int count = 0 ;
            while(r.next()){
                count++ ;
            } 
            
            pstmt = con.prepareStatement("insert into orders (orders_id, order_date, order_type, table_id) values (?,?,?,?)");
            pstmt.setInt(1, count+1 );
            
            java.sql.Date d =  java.sql.Date.valueOf( LocalDate.now() );
            pstmt.setDate(2, d);
            pstmt.setInt(3, 0);
            pstmt.setInt(4, tableID);
            r = pstmt.executeQuery();
            
            TableOrderPackage.TableOrderController.orderID = count+1 ;

            Parent root = FXMLLoader.load(getClass().getResource("/TableOrderPackage/TableOrder.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }     
        
    }
    
}
