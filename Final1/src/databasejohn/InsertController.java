/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasejohn;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import static java.util.Optional.of;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InsertController implements Initializable {

    ObservableList<String> position=FXCollections.observableArrayList("Manager","Chef","Waiter");
    
        
        
        
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private DatePicker date;
    @FXML
    private TextField nat;
    @FXML
    private Button Insert_method;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> pos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    pos.setItems(position);
    
        // TODO
    }    

    @FXML
    private void Insert_method(ActionEvent event) throws ClassNotFoundException {
        
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "maharaja", "123") ;

        Connection conn = HomePackage.MainClass.con;

            System.out.println(pos.getValue());
        if(pos.getValue()=="Manager"){
        int x=1;
        
        LocalDate localDate = date.getValue();        
        Date date2 = java.sql.Date.valueOf(localDate);
        
        String sql3="insert into EMPLOYEE values (?,?,?,?,?,?,?,?) ";
        PreparedStatement statement3 = conn.prepareStatement(sql3);
        statement3.setString(1,id.getText());
        statement3.setString(2,name.getText());
        statement3.setString(3,gender.getText());
        statement3.setString(4,phone.getText());
        statement3.setString(5,address.getText());
        statement3.setDate(6,date2);
        statement3.setString(7,nat.getText());
        statement3.setInt(8,x);
        statement3.executeUpdate();
        }
        else if(pos.getValue()=="Waiter"){
        int x=2;
        LocalDate localDate = date.getValue();        
        Date date2 = java.sql.Date.valueOf(localDate);
        
        String sql3="insert into EMPLOYEE values (?,?,?,?,?,?,?,?) ";
        PreparedStatement statement3 = conn.prepareStatement(sql3);
        statement3.setString(1,id.getText());
        statement3.setString(2,name.getText());
        statement3.setString(3,gender.getText());
        statement3.setString(4,phone.getText());
        statement3.setString(5,address.getText());
        statement3.setDate(6,date2);
        statement3.setString(7,nat.getText());
        statement3.setInt(8,x);
        statement3.executeUpdate();
        }
        
        else if(pos.getValue()=="Chef"){
        int x=3;
        LocalDate localDate = date.getValue();        
        Date date2 = java.sql.Date.valueOf(localDate);
        
        String sql3="insert into EMPLOYEE values (?,?,?,?,?,?,?,?) ";
        PreparedStatement statement3 = conn.prepareStatement(sql3);
        statement3.setString(1,id.getText());
        statement3.setString(2,name.getText());
        statement3.setString(3,gender.getText());
        statement3.setString(4,phone.getText());
        statement3.setString(5,address.getText());
        statement3.setDate(6,date2);
        statement3.setString(7,nat.getText());
        statement3.setInt(8,x);
        statement3.executeUpdate();
        }
        
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" Data Inserted Successfully");
            ar.setTitle("");
            ar.show();
              
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
        
    }
     @FXML
    private void back(ActionEvent event) throws ClassNotFoundException, IOException {
     Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("stage1.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
        
    
}
