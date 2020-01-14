package databasejohn;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateController implements Initializable {
    @FXML
    private Button update;
    @FXML
    private TextField id;
    @FXML
    private Button back;
    @FXML
    private ComboBox select;
    @FXML
    private TextField data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }
    
        @FXML
    private void update(ActionEvent event) throws ClassNotFoundException {
        
        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1033") ;
            Connection conn = HomePackage.MainClass.con ;

        if (select.getValue().toString()=="date of birth"){
                PreparedStatement insert = conn.prepareStatement("update employee set date_of_birth = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
            else if (select.getValue().toString()=="id"){
                PreparedStatement insert = conn.prepareStatement("update employee set employee_id = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
             insert.executeUpdate();
            Alert ar= new Alert(Alert.AlertType.INFORMATION);
            ar.setContentText(" data updated successfully");
              ar.setTitle("data updated");
              ar.show();
            }
            else{
        PreparedStatement insert = conn.prepareStatement("update employee set '"+select.getValue().toString()+"' = '"+data.getText()+"' where employee_id='"+id.getText()+"' ");
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
    private void back(ActionEvent event) throws ClassNotFoundException, IOException {
     Stage stage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("stage1.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
}
