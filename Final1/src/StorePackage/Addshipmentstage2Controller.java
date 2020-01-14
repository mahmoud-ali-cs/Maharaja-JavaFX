package StorePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Addshipmentstage2Controller implements Initializable {

    @FXML
    private Button add;
    @FXML
    private TextField item_id;
    @FXML
    private TextField amount;
    
    @FXML private TableView<store_items_tableview> tableView;
    @FXML private TableColumn<store_items_tableview,Integer> firstcolumn;
    @FXML private TableColumn<store_items_tableview,String> secondcolumn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            
            firstcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("id"));
            secondcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,String>("item"));
           
            tableView.setItems(getdata());

            
        } catch (Exception ex) {
            System.out.println(ex);;
        }
    }    
    
        public ObservableList<store_items_tableview> getdata() throws Exception{
          
        ObservableList<store_items_tableview> data2=FXCollections.observableArrayList();

//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        Statement stmt=con.createStatement(); 

            int x1=0;
            String x2=null;
            String x3=null;
            int x4=0;
            int x5=0;

        ResultSet rs2=stmt.executeQuery("select * from STORE_ITEMS"); 
        while(rs2.next()) { 
             x1=rs2.getInt("ITEMS_ID");
             x2=rs2.getString("NAME");

             data2.add(new store_items_tableview(x1,x2,x3,x4,x5));
            
        }

        return data2;
    }  

          
          
          
          
    @FXML
    private void add_method(ActionEvent event) throws Exception {
        
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        Statement stmt=con.createStatement();

        int max=0;
        ResultSet rs = stmt.executeQuery("select MAX(SHIPMENT_ID) as maxLevel from shipment");
        if (rs.next()) { max = rs.getInt("maxLevel");}

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Data");
        alert.setHeaderText("Are you sure to insert this data");
        alert.setContentText("Item ID: " +item_id.getText()+ "\nAmount: "+amount.getText());
        
        Optional<ButtonType> x=alert.showAndWait();
        
        
        if (! x.isPresent()){} 
        else if (x.get()==ButtonType.CANCEL){}
        
        else if (x.get()==ButtonType.OK){
            
            int Itemid=Integer. parseInt (item_id.getText());
            int Amount1=Integer. parseInt (amount.getText());
            
            items_shipment is=new items_shipment(max,Itemid,Amount1);
            is.insert();  
        }
        
        
    }
}
    

