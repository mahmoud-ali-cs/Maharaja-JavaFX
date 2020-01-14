package DemoViewReservation;

import ViewMenu.Menu;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class oldCOntroller implements Initializable {
    
    @FXML
    private TableView<reserv> tableView;
    
    @FXML
    private TableColumn<reserv, Integer> tableId;
    
    @FXML
    private TableColumn<reserv, String> name;
    
    @FXML
    private TableColumn<reserv, Timestamp> date;
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM RESERVATION");
            
            int count = 0 ;
            while(r.next()){
                count++ ;
                System.out.println(count);
            }
            
            r = stmt.executeQuery("SELECT * FROM RESERVATION");
            reserv [] reservList = new reserv[count];
            
            count = 0;
            while(r.next()){
                Timestamp time = r.getTimestamp("reservation_date");
                int tableId = r.getInt("TABLE_ID");
                
                System.out.println(time);
                System.out.println(tableId);
                
                Statement stmt2 = con.createStatement();
                ResultSet r2 = stmt2.executeQuery("SELECT NAME FROM CUSTOMER WHERE ID =" + r.getInt("CUSTOMER_ID"));
                String cust_name = "";
                while(r2.next()){
                    cust_name = r2.getString("NAME");
                }
                
                reservList[count] = new reserv(tableId, cust_name, time);
                count++;
                
                System.out.println(tableId);
                System.out.println(cust_name);
                System.out.println(time);
                
            }
            
            tableId.setCellValueFactory(new PropertyValueFactory<reserv,Integer>("table_id"));
            name.setCellValueFactory(new PropertyValueFactory<reserv,String>("name"));
            date.setCellValueFactory(new PropertyValueFactory<reserv,Timestamp>("time"));
            
            ObservableList<reserv> reserveList = FXCollections.observableArrayList();
            
            for(int i = 0 ; i < reservList.length ; i++){
                reserveList.add(reservList[i]) ;
            }
                                 
            tableView.setItems(reserveList);
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }    
    
}
