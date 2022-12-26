package AddMenuClassPackage;

import AddTablePackage.addTableController;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class classController implements Initializable  {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField className;


    @FXML
    void DoneClass(ActionEvent event) throws IOException {
        
        try {
            Connection con = HomePackage.MainClass.con ;
            
            int count = 0 ;
            PreparedStatement pstmt1 = con.prepareStatement("select count(*) from MENU_Classes");
            ResultSet r1 = pstmt1.executeQuery();
            while(r1.next()){
                System.out.println("Count : " + r1.getInt(1));
                count = r1.getInt(1) ;
            }
            
            Statement stmt = con.createStatement();
            count++;
            System.out.println(count);
            System.out.println(className.getText());
 
            stmt.execute("INSERT INTO MENU_Classes VALUES ("+ count +","+"'" +className.getText()+ "'" +")");
            
            stmt.close();

        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
            
        } catch (SQLException ex) {
            Logger.getLogger(addTableController.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
