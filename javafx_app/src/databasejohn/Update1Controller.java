/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasejohn;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John
 */
public class Update1Controller implements Initializable {
    
    ObservableList<String> position=FXCollections.observableArrayList("ID","NAME","GENDER","PHONE","ADDRESS","DATE OF BIRTH","NATIONALITY","POSITION");
    
    @FXML
    private TextField id;
    @FXML
    private TextField data;
    @FXML
    private ComboBox<String> select;
    @FXML
    private Button update;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        select.setItems(position);
    }    

    @FXML
    private void update(ActionEvent event) throws ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "maharaja", "123") ;
        Connection conn = HomePackage.MainClass.con ;


        if (select.getValue()=="DATE OF BIRTH"){
                PreparedStatement insert = conn.prepareStatement("update employee set date_of_birth = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
            else if (select.getValue()=="ID"){
                PreparedStatement insert = conn.prepareStatement("update employee set employee_id = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
        else if (select.getValue()=="NAME"){
                PreparedStatement insert = conn.prepareStatement("update employee set NAME= '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
        else if (select.getValue()=="ADDRESS"){
                PreparedStatement insert = conn.prepareStatement("update employee set ADDRESS = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
        else if (select.getValue()=="GENDER"){
                PreparedStatement insert = conn.prepareStatement("update employee set GENDER = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
        else if (select.getValue()=="PHONE"){
                PreparedStatement insert = conn.prepareStatement("update employee set PHONE = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
        else if (select.getValue()=="NATIONALITY"){
                PreparedStatement insert = conn.prepareStatement("update employee set NATIONALITY = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
        else if (select.getValue()=="POSITION"){
                PreparedStatement insert = conn.prepareStatement("update employee set POSITION = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
            
           
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("stage1.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
