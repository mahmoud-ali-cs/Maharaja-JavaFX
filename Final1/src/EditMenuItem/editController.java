package EditMenuItem;

import AddMenuItemPackage.MenuController;
import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class editController implements Initializable{
    
    private boolean delete;
    private int id;
    private int class_id;
    private String name;
    private String class_name;
    private float cost;
    
    ObservableList<Integer> itemIDListt = FXCollections
                .observableArrayList();
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> itemClass;

    @FXML
    private ComboBox<Integer> itemId;

    @FXML
    private TextField itemName;

    @FXML
    private TextField price;

    @FXML
    void itemIdAction(ActionEvent event) {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            if(delete == true){
                delete = false;
                return;
            }

            id = itemId.getValue();
            System.out.println(id);
            
            PreparedStatement pstmt3 = con.prepareStatement("select * from MENU_ITEMS where MENU_ITEMS_ID = ?");
            pstmt3.setInt(1, id);
            ResultSet r3 = pstmt3.executeQuery();
            
            while(r3.next()){
                name = r3.getString("ITEMS");
                cost = r3.getFloat("PRICE");
                class_id = r3.getInt("MENU_CLASSES_ID");
            }
            
            itemName.setText(name);
            price.setText(Float.toString(cost));
            
            PreparedStatement pstmt4 = con.prepareStatement("select CLASS_NAME from MENU_CLASSES where MENU_CLASSES_ID = ?");
            pstmt4.setInt(1, class_id);
            ResultSet r4 = pstmt4.executeQuery();
            
            while(r4.next()){
                class_name = r4.getString("CLASS_NAME");
            }
            
            itemClass.setValue(class_name);
            
            
            
        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }

    @FXML
    void DoneItem(ActionEvent event) throws IOException {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            PreparedStatement pstm = con.prepareStatement("UPDATE MENU_ITEMS SET ITEMS = ?, PRICE = ?, MENU_CLASSES_ID = ? WHERE MENU_ITEMS_ID = ?");
            pstm.setString(1, itemName.getText());
            pstm.setFloat(2, Float.parseFloat(price.getText()));
            
            PreparedStatement pst = con.prepareStatement("select MENU_CLASSES_ID from MENU_CLASSES where CLASS_NAME = ?");
            pst.setString(1, itemClass.getValue());
            ResultSet rr = pst.executeQuery();
            int classID = 0;
            while(rr.next()){
                classID = rr.getInt("MENU_CLASSES_ID");
            }
            
            pstm.setInt(3, classID);
            pstm.setInt(4, itemId.getValue());
            pstm.executeUpdate();
            
            Parent root = FXMLLoader.load(getClass().getResource("/ViewMenu/viewMenu.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
            
        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void DeleteItem(ActionEvent event) {
        
        delete = true;
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            PreparedStatement p = con.prepareStatement("DELETE FROM MENU_ITEMS WHERE MENU_ITEMS_ID = ?");
            p.setInt(1, itemId.getValue());
            ResultSet resultSet = p.executeQuery();
            
            itemName.setText("");
            price.setText("");
            
            PreparedStatement pstmt1 = con.prepareStatement("select MENU_ITEMS_ID from MENU_ITEMS");
            ResultSet result1 = pstmt1.executeQuery();
            
            itemIDListt.clear();
            
            while(result1.next()){
                itemIDListt.add(result1.getInt("MENU_ITEMS_ID"));
                System.out.println(result1.getInt("MENU_ITEMS_ID"));
            }

            itemId.setItems(itemIDListt);

            itemClass.getSelectionModel().select(null);

        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
           Connection con = HomePackage.MainClass.con ;
            
            itemId.getSelectionModel().select(null);
            itemClass.getSelectionModel().select(null);
            
            PreparedStatement pstmt1 = con.prepareStatement("select * from MENU_Classes");
            ResultSet r1 = pstmt1.executeQuery();
        
            ObservableList<String> itemClassList = FXCollections
                .observableArrayList();
            
            while(r1.next()){
                itemClassList.add(r1.getString("CLASS_NAME"));
            }
        
            itemClass.setItems(itemClassList);
            
            
            PreparedStatement pstmt2 = con.prepareStatement("select * from MENU_ITEMS");
            ResultSet r2 = pstmt2.executeQuery();
            
            
            
            while(r2.next()){
                itemIDListt.add(r2.getInt("MENU_ITEMS_ID"));
            }
            itemId.setItems(itemIDListt);
            
            
            
            
        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
