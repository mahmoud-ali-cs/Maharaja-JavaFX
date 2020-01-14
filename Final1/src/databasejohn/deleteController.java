/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasejohn;

//import static databaseproj.DatabaseTest.connect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John
 */
public class deleteController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private Button delete;
    @FXML
    private TextField id;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    private void delete(ActionEvent event) throws ClassNotFoundException {
    try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MAHARAJA", "123") ;
        Connection conn = HomePackage.MainClass.con ;

         PreparedStatement insert = conn.prepareStatement("Delete from EMPLOYEE where employee_id='"+id.getText()+"' ");
        insert.executeUpdate();
        
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText("data deleted successfully");
              ar.setTitle("data deleted");
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
