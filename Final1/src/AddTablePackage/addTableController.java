package AddTablePackage;

import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class addTableController implements Initializable {
       
    
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;

    @FXML
    private TextField chairsNumber;

    @FXML
    private TextField id;


    @FXML
    void DoneTable(ActionEvent event) throws SQLException, IOException {
            
        try {
            Connection con = HomePackage.MainClass.con ;
            
            Statement stmt = con.createStatement(); 
            int ID = Integer.parseInt(id.getText());
            System.out.println(ID);
            int chairNo = Integer.parseInt(chairsNumber.getText());
            System.out.println(chairNo);
            stmt.execute("INSERT INTO TABLES VALUES ("+ ID +","+ chairNo +", 0)");
            
            
            stmt.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException ex) {
            Logger.getLogger(addTableController.class.getName()).log(Level.SEVERE, null, ex);
        }  
            
        
    }

    
    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       
    }
    
    
}
