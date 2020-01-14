package EditOrderPackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


public class editOrderController implements Initializable  {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> menu;

    @FXML
    private ComboBox<Integer> quantity;

    @FXML
    private ComboBox<Integer> table;

    Connection con = null;
    int m, q, t;

    @FXML
    void DeleteOrder(ActionEvent event) throws SQLException {
        
        
        PreparedStatement ps = con.prepareStatement("SELECT ID FROM ORDERS WHERE TABLE_ID = " + t);
        ResultSet r = ps.executeQuery();
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        while(r.next()){
            ids.add(r.getInt("ID"));
        }
        for(int i = 0; i < ids.size(); ++i){
            System.out.println(ids.get(i));
            PreparedStatement ps3 = con.prepareStatement("DELETE FROM ORDERS WHERE ID = " + Integer.toString(ids.get(i)));
            ps3.execute();
            PreparedStatement ps2 = con.prepareStatement("DELETE FROM ORDER_DETAILS WHERE ORDER_ID = " + Integer.toString(ids.get(i)));
            ps2.execute();
        }
        
    }

    @FXML
    void EditOrder(ActionEvent event) {
        
    }

    @FXML
    void onActionMenu(ActionEvent event) {
        m = menu.getValue();
    }

    @FXML
    void onActionQuantity(ActionEvent event) {
        q = quantity.getValue();
    }

    @FXML
    void onActionTable(ActionEvent event) {
        t = table.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
            
            PreparedStatement pstmt1 = con.prepareStatement("select DISTINCT TABLE_ID from ORDERS");
            ResultSet r1 = pstmt1.executeQuery();
        
            ObservableList<Integer> itemClassList = FXCollections
                .observableArrayList();
            
            
            while(r1.next()){
                itemClassList.add(r1.getInt("TABLE_ID"));
            }
        
            table.setItems(itemClassList);
            
            PreparedStatement ps = con.prepareStatement("SELECT ID FROM ORDERS WHERE TABLE_ID = " + t);
            ResultSet r = ps.executeQuery();
            ObservableList<Integer> ids = FXCollections.observableArrayList();
            ObservableList<Integer> food = FXCollections.observableArrayList();
            while(r.next()){
                ids.add(r.getInt("ID"));
            }
            for(int i = 0; i < ids.size(); ++i){
                System.out.println(ids.get(i));
                PreparedStatement psFood = con.prepareStatement("SELECT MENU_ITEM_ID FROM ORDER_DETAILS WHERE ORDER_ID = " + Integer.toString(ids.get(i)));
                ResultSet rFood = psFood.executeQuery();
                while(rFood.next()){
                    food.add(rFood.getInt("MENU_ITEM_ID"));
                }
            }
            menu.setItems(food);
            for(int i = 1; i <= 20; ++i){
                quantity.getItems().add(i);
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    
    
}
