
package StorePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Add_itemsController implements Initializable {

    @FXML private TextField add_item;
    @FXML private Button next;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void add_item_method(ActionEvent event) {
        
    }

    @FXML
    private void next_method(ActionEvent event) throws Exception {
  
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Data");
        alert.setHeaderText("Are you sure to insert this data");
        alert.setContentText("item: " +add_item.getText());
        
        Optional<ButtonType> x=alert.showAndWait();
        
        if (! x.isPresent()){} 
        else if (x.get()==ButtonType.CANCEL){}
        else if (x.get()==ButtonType.OK){
            

        store_items si=new store_items( add_item.getText(), 0,"", 0); 
        si.insert();
            
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        Statement stmt=con.createStatement();
            
        int max=0;
        ResultSet rs = stmt.executeQuery("select MAX(SHIPMENT_ID) as maxLevel from shipment");
        if (rs.next()) { max = rs.getInt("maxLevel");}

        Stage1Controller s1controller=new Stage1Controller();

        s1controller.getdata().add(new store_items_tableview(max,add_item.getText(),"",0,0));
            
        
        }

    } 
}
