package StorePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Shipment_stage_displayController implements Initializable {
    @FXML private TableView<shipment_tableview> tableView;
    @FXML private TableColumn<shipment_tableview,Integer> id;
    @FXML private TableColumn<shipment_tableview,String> date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        id.setCellValueFactory(new PropertyValueFactory<shipment_tableview,Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<shipment_tableview,String>("date"));
        tableView.setItems(getdata());
        }
        catch (Exception ex) {
            System.out.println(ex);;
        }
    } 
    
    public ObservableList<shipment_tableview> getdata() throws Exception{
          
        ObservableList<shipment_tableview> data=FXCollections.observableArrayList();
          
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        Statement stmt=con.createStatement(); 

        ResultSet rs=stmt.executeQuery("select * from SHIPMENT");  
            
        while(rs.next()) { 
            data.add(new shipment_tableview(rs.getInt(1),rs.getString(2)));
        }
        
        return data;
    }   
    
    
}
