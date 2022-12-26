
package AddMenuItemPackage;

import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


public class MenuController implements Initializable {
    
    Connection con = null;
    PreparedStatement pstmt = null;
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox itemClass;

    @FXML
    private TextField itemId;

    @FXML
    private TextField itemName1;

    @FXML
    private TextField price;
    
    @FXML
    void DoneItem(ActionEvent event) throws SQLException, IOException {
        
//        con = DriverManager.getConnection(
//                "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
       
        con = HomePackage.MainClass.con;

        String sql = "INSERT INTO MENU_ITEMS VALUES (?,?,?,?)"; 
        pstmt = con.prepareStatement(sql);
        
        System.out.println(itemId.getText());
        int id = Integer.parseInt(itemId.getText());
        pstmt.setInt(1, id);
        
        pstmt.setString(2, itemName1.getText());
        System.out.println(itemName1.getText());
        
        float price1 = Float.parseFloat(price.getText());
        System.out.println(price.getText());
        pstmt.setFloat(4, price1);
        
        System.out.println(itemClass.getValue());
        Statement stmt = con.createStatement();
        ResultSet r =  stmt.executeQuery("SELECT Menu_classes_ID FROM MENU_CLASSES WHERE CLASS_NAME = "+ "'" + itemClass.getValue() +"'");
        
        int id2 = 0;
        while(r.next()){
            id2 = r.getInt("Menu_classes_ID");
        }
        
        System.out.println(id2);
        pstmt.setInt(3, id2);
        pstmt.executeUpdate();
        stmt.close();
        r.close();


        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
          
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            con = HomePackage.MainClass.con ;
            
            PreparedStatement pstmt1 = con.prepareStatement("select * from MENU_Classes");
            ResultSet r1 = pstmt1.executeQuery();
        
            ObservableList<String> itemClassList = FXCollections
                .observableArrayList();
            
            while(r1.next()){
                itemClassList.add(r1.getString("CLASS_NAME"));
            }
        
            itemClass.setItems(itemClassList);
        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
}
