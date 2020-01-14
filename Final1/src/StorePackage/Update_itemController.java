package StorePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Update_itemController implements Initializable {

    @FXML private TableView<store_items_tableview> tableView;
    @FXML private TableColumn<store_items_tableview,Integer> firstcolumn;
    @FXML private TableColumn<store_items_tableview,String> secondcolumn;
    @FXML private TableColumn<store_items_tableview,String> thirdcolumn;
    @FXML private TableColumn<store_items_tableview,Integer> forthtcolumn;
    @FXML private TableColumn<store_items_tableview,Integer> fifthtcolumn;

    
    @FXML
    private TextField ITEM_ID;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            firstcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("id"));
            secondcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,String>("item"));
            forthtcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("date"));
            thirdcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,String>("amount"));
            fifthtcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("status"));

            
            tableView.setItems(getdata());
        } catch (Exception ex) {
            Logger.getLogger(Stage1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
             x4=rs2.getInt("AMOUNT");
             x3=rs2.getString("STORE_ITEMS_DATES");
             x5=rs2.getInt("ITEM_STATUS");

             data2.add(new store_items_tableview(x1,x2,x3,x4,x5));
            
        }

        return data2;
    }   

    @FXML
    private void Update_method(ActionEvent event) throws Exception {
        LocalDate localDate = date.getValue();
         String datestring =localDate.toString();
         
        store_items si=new store_items(Integer.parseInt(ITEM_ID.getText()), Integer.parseInt(amount.getText()),datestring);
        si.update_amount();
    }
    
}
