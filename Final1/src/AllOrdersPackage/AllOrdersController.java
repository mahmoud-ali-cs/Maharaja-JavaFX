package AllOrdersPackage;

import static HomePackage.MainClass.stage;
import ViewMenu.Menu;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllOrdersController implements Initializable {

    
    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, Integer> orderID_Col;

    @FXML
    private TableColumn<Order, Date> orderDateCol;

    @FXML
    private TableColumn<Order, String> orderTypeCol;

    @FXML
    private TableColumn<Order, Integer> tableID_Col;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM orders");
            
            int count = 0 ;
            while(r.next()){
                count++ ;
            } 
            
            
            r = stmt.executeQuery("SELECT * FROM orders");
            Order [] allItem = new Order[count];
            
            count = 0;
            while(r.next()){
            
                int order_id = r.getInt(1);
                java.sql.Date order_date = r.getDate(2);
                String order_type = r.getString(3);
                int table_id = r.getInt(3);
                
                allItem[count] = new Order(order_id, order_date, order_type, table_id);
                count++;
                
                System.out.println(order_id);
                System.out.println(order_date);
                System.out.println(order_type);
                System.out.println(table_id);
                
            }
            
            orderID_Col.setCellValueFactory(new PropertyValueFactory<>("id"));
            orderDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
            orderTypeCol.setCellValueFactory(new PropertyValueFactory<>("order_type"));
            tableID_Col.setCellValueFactory(new PropertyValueFactory<>("table_id"));
            
            ObservableList<Order> orderList = FXCollections.observableArrayList();
            
            for(int i = 0 ; i < allItem.length ; i++){
                orderList.add(allItem[i]) ;
            }
            
            
            tableView.setItems(orderList);
            System.out.println("orderlist done!!");
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }           
    }    
    
    @FXML
    void goToEdit(ActionEvent event) throws IOException {
        Order order = tableView.getSelectionModel().getSelectedItem();
        SelectOrderPackage.SelectOrderController.orderID = order.getId();
        
        Parent root = FXMLLoader.load(getClass().getResource("/SelectOrderPackage/SelectOrder.fxml"));
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
    
    @FXML
    void goToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/OrdersPackage/Orders.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }    
}
