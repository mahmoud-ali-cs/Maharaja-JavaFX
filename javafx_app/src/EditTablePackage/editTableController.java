/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditTablePackage;

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

/**
 *
 * @author Marwan
 */
public class editTableController implements Initializable {
    
    private int id;
    ObservableList<Integer> itemClassList = FXCollections
                .observableArrayList();
    
    @FXML
    private TextField chairsNo;

    @FXML
    private ComboBox<Integer> tableId;


    @FXML
    void DeleteTable(ActionEvent event) {
        
        try{
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
            
            String sql = "delete from MAHRAJA_TABLES WHERE ID = " + Integer.toString(id);
            //PreparedStatement pstmt1 = con.prepareStatement("select * from MAHRAJA_TABLES");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
           // tableId.setItems(null);
            chairsNo.setText("");
            
            PreparedStatement pstmt1 = con.prepareStatement("select * from MAHRAJA_TABLES");
            ResultSet r1 = pstmt1.executeQuery();
            itemClassList.clear();
            while(r1.next()){
                itemClassList.add(r1.getInt("ID"));
                System.out.println(r1.getInt("ID"));
            }
        
            tableId.setItems(itemClassList);
            tableId.getSelectionModel().select(null);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    @FXML
    void DoneTable(ActionEvent event) {
        
        try{
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
            
            String sql = "UPDATE MAHRAJA_TABLES SET CHAIR_NUMBER = " + chairsNo.getText() + " WHERE ID = " + Integer.toString(id);
            //PreparedStatement pstmt1 = con.prepareStatement("select * from MAHRAJA_TABLES");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
            
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    @FXML
    void actionId(ActionEvent event)  {
        try{
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM");
            id = tableId.getValue();
            System.out.println(id);
            
            String sql1 = "select CHAIR_NUMBER from MAHRAJA_TABLES where ID = ?";
            PreparedStatement pstmt2 = con.prepareStatement(sql1);

            pstmt2.setInt(1, id);
            ResultSet r2 = pstmt2.executeQuery();
            
            int chairNOo = 0;
            while(r2.next()){
                chairNOo = r2.getInt("CHAIR_NUMBER");
            }
            System.out.println(chairNOo);
            
            chairsNo.setText(Integer.toString(chairNOo));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
       try (Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "SYSTEM")) {
        
           tableId.getSelectionModel().select(null);
           
            if (con != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            
            PreparedStatement pstmt1 = con.prepareStatement("select * from MAHRAJA_TABLES");
            ResultSet r1 = pstmt1.executeQuery();
        
//            ObservableList<Integer> itemClassList = FXCollections
//                .observableArrayList();
            
            
            while(r1.next()){
                itemClassList.add(r1.getInt("ID"));
                System.out.println(r1.getInt("ID"));
            }
        
            tableId.setItems(itemClassList);
            
        }catch (Exception ex) {
           System.out.println(ex);        }
    }
    
}
