
package EditCustomer;

import AddMenuItemPackage.MenuController;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditCustomerController implements Initializable {
    
    ObservableList<String> itemClassList = FXCollections
                .observableArrayList();
    
    private boolean delete;
    
    @FXML
    private TextField name;
    
    @FXML
    private ComboBox<String> phone;

    
    @FXML
    private void selectPhone(ActionEvent event) {
        
        
        try {
            Connection con = HomePackage.MainClass.con;
            
            if(delete == true){
                delete = false;
                return;
            }

            String ph = phone.getValue();
            System.out.println(ph);
            
            PreparedStatement pstmt3 = con.prepareStatement("select * from CUSTOMER where PHONE = ?");
            pstmt3.setString(1, ph);
            ResultSet r3 = pstmt3.executeQuery();
            
            String nam = "";
            while(r3.next()){
                nam = r3.getString("NAME");
            }
            
            name.setText(nam);
            
            
            
        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }

    @FXML
    private void DeleteCostumer(ActionEvent event) {
     
        delete = true;
        
        try {
            Connection con = HomePackage.MainClass.con;

            PreparedStatement p = con.prepareStatement("DELETE FROM CUSTOMER WHERE PHONE = ?");
            p.setString(1, phone.getValue());
            ResultSet resultSet = p.executeQuery();
            
            name.setText("");
            
            PreparedStatement pstmt1 = con.prepareStatement("select PHONE from CUSTOMER");
            ResultSet result1 = pstmt1.executeQuery();
            
            itemClassList.clear();
            
            while(result1.next()){
                itemClassList.add(result1.getString("PHONE"));
                System.out.println(result1.getString("PHONE"));
            }

            phone.setItems(itemClassList);


        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void DoneCostumer(ActionEvent event) {
        
         try {
             Connection con = HomePackage.MainClass.con;
            
            PreparedStatement pstm = con.prepareStatement("UPDATE CUSTOMER SET NAME = ? WHERE PHONE = ?");
            pstm.setString(1, name.getText());
            pstm.setString(2, phone.getValue());
            pstm.executeUpdate();
            
        }catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Connection con = HomePackage.MainClass.con;
            
            PreparedStatement pstmt1 = con.prepareStatement("select * from CUSTOMER");
            ResultSet r1 = pstmt1.executeQuery();
            
            while(r1.next()){
                itemClassList.add(r1.getString("PHONE"));
                System.out.println(r1.getString("PHONE"));
            }
        
            phone.setItems(itemClassList);
            
        }catch (Exception ex) {
           System.out.println(ex);        
        }
        
    }    
}
