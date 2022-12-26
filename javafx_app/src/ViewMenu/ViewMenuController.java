package ViewMenu;

import static HomePackage.MainClass.stage;
import OLDBillPackage.FinalBill;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


public class ViewMenuController implements Initializable {
    
    @FXML
    private TableView<Menu> tableView;
    
    @FXML
    private TableColumn<Menu, Integer> ID;
    
    @FXML
    private TableColumn<Menu, String> Item;
    
    @FXML
    private TableColumn<Menu, String> Class;
    
    @FXML
    private TableColumn<Menu, Float> Price;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM MENU_ITEMS");
            
            int count = 0 ;
            while(r.next()){
                count++ ;
            } 
            
            
            r = stmt.executeQuery("SELECT * FROM MENU_ITEMS");
            Menu [] allItem = new Menu[count];
            
            count = 0;
            while(r.next()){
            
                int id = r.getInt("MENU_ITEMS_ID");
                
                
                String item_name = r.getString("ITEMS");
                float item_price = r.getFloat("PRICE");
                
                Statement stmt2 = con.createStatement();
                ResultSet r2 = stmt2.executeQuery("SELECT CLASS_NAME FROM MENU_CLASSES WHERE MENU_CLASSES_ID = " + r.getInt("MENU_CLASSES_ID"));
                String item_class = "";
                while(r2.next()){
                    item_class = r2.getString("CLASS_NAME");
                }
                
                allItem[count] = new Menu(id,item_name,item_class,item_price);
                count++;
                
                System.out.println(id);
                System.out.println(item_name);
                System.out.println(item_class);
                System.out.println(item_price);
                
            }
            
            ID.setCellValueFactory(new PropertyValueFactory<Menu,Integer>("id"));
            Item.setCellValueFactory(new PropertyValueFactory<Menu,String>("Item"));
            Class.setCellValueFactory(new PropertyValueFactory<Menu,String>("item_class"));
            Price.setCellValueFactory(new PropertyValueFactory<Menu,Float>("Price"));
            
            ObservableList<Menu> menuList = FXCollections.observableArrayList();
            
            for(int i = 0 ; i < allItem.length ; i++){
                menuList.add(allItem[i]) ;
            }
            
            
            
            tableView.setItems(menuList);
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }    
        
    }    
    
    
        @FXML
    void goToBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MenuPackage/Menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void goToEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditMenuItem/editMenuItem.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    
}
