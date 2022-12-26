package SelectOrderPackage;

import AllOrdersPackage.Order;
import static HomePackage.MainClass.stage;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SelectOrderController implements Initializable {

    public static int orderID = -1 ;
    
    @FXML
    private TableView<OrderDetails> tableView;

    @FXML
    private TableColumn<OrderDetails, Integer> itemIDCol;

    @FXML
    private TableColumn<OrderDetails, String> itemNameCol;

    @FXML
    private TableColumn<OrderDetails, Integer> quantityCol;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            PreparedStatement pstmt = con.prepareStatement("SELECT quantity,menu_item_id FROM order_details where order_id=?");
            pstmt.setInt(1, orderID);
            ResultSet r = pstmt.executeQuery();
            
            int count = 0 ;
            while(r.next()){
                count++ ;
            } 
            System.out.println("count : " + count);
            
            pstmt = con.prepareStatement("SELECT ORDER_DETAILS_ID,quantity,menu_item_id FROM order_details where order_id=?");
            pstmt.setInt(1, orderID);
            r = pstmt.executeQuery();

            OrderDetails [] allItem = new OrderDetails[count];
            
            count = 0;
            while(r.next()){
            
                int orderDetailsID = r.getInt(1);
                int quantity = r.getInt(2);
                int menu_item_id = r.getInt(3);
                
                PreparedStatement pstmt2 = con.prepareStatement("SELECT items FROM menu_items where menu_items_id=?");
                pstmt2.setInt(1, menu_item_id);
                ResultSet r2 = pstmt2.executeQuery();
                String item_name = "" ;
                while(r2.next()){
                    item_name = r2.getString(1);
                    
                }
                
                allItem[count] = new OrderDetails(orderDetailsID, menu_item_id, item_name, quantity);
                count++;
                
                System.out.println(menu_item_id);
                System.out.println(item_name);
                System.out.println(quantity);
                
            }
            
            itemIDCol.setCellValueFactory(new PropertyValueFactory<>("menuID"));
            itemNameCol.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
            quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            
            ObservableList<OrderDetails> orderDetailsList = FXCollections.observableArrayList();
            
            for(int i = 0 ; i < allItem.length ; i++){
                orderDetailsList.add(allItem[i]) ;
            }
            
            
            tableView.setItems(orderDetailsList);
            System.out.println("orderlist done!!");
            
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
    void goToAddOrderDetails(ActionEvent event) throws IOException {
        AddItemOrderPackage.AddItemOrderController.orderID = orderID ;
        AddItemOrderPackage.AddItemOrderController.lastLocation = "/SelectOrderPackage/SelectOrder.fxml" ;

        
        Parent root = FXMLLoader.load(getClass().getResource("/AddItemOrderPackage/AddItemOrder.fxml"));
        Stage s = AddItemOrderPackage.AddItemOrderController.myStage ;
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();
    }
    
    @FXML
    void DeleteOrderDetails(ActionEvent event) throws IOException {
        OrderDetails orderDetails = tableView.getSelectionModel().getSelectedItem();
        int orderDetailsID = orderDetails.getOrderDetailsID();
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            PreparedStatement pstmt = con.prepareStatement("delete FROM order_details where ORDER_DETAILS_ID=?");
            pstmt.setInt(1, orderDetailsID);
            pstmt.executeUpdate();
            
            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("order Details");
            a.setContentText("orderDetails has been Deleted successfully");
            a.show();
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }   
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/SelectOrderPackage/SelectOrder.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void goToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AllOrdersPackage/AllOrders.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }   
}
