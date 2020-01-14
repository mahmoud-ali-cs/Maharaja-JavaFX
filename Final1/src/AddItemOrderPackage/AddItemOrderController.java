package AddItemOrderPackage;

import static HomePackage.MainClass.stage;
import TableOrderPackage.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class AddItemOrderController implements Initializable {

    public static int orderID = -1 ;
    public static Stage myStage = new Stage() ;
    public static String lastLocation ;

    
    @FXML
    private ComboBox<Integer> quantity;

    @FXML
    private ComboBox<Integer> menu;


    String food, q;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = HomePackage.MainClass.con ;
        try{
              Statement st = conn.createStatement();

              ResultSet rec2 = st.executeQuery("SELECT MENU_ITEMS_ID FROM MENU_ITEMS");
              while(rec2.next()){
                  int e = rec2.getInt("MENU_ITEMS_ID");
                  menu.getItems().add(e);
                  System.out.println(rec2.getInt("MENU_ITEMS_ID"));
              }
              
              for(int i = 0; i < 20; ++i)
                  quantity.getItems().add(i + 1);
        }
        catch(Exception e){
            System.out.println(e);
        }
     }


    @FXML
    void doneBtn(ActionEvent event) throws SQLException {
        try{
        if(menu.getValue() != null && quantity.getValue() != null){
   
            Connection con = HomePackage.MainClass.con ;

            int count = 0 ;
            PreparedStatement pstmt1 = HomePackage.MainClass.con.prepareStatement("select count(*) from ORDER_DETAILS ");
            ResultSet r1 = pstmt1.executeQuery();
            while(r1.next()){
                System.out.println("Count : " + r1.getInt(1));
                count = r1.getInt(1) ;
            }            
            int orderDetailsId = count + 1 ;
            System.out.println("orderDetailsId : " + orderDetailsId);
            PreparedStatement insert2 = con.prepareStatement("INSERT INTO ORDER_DETAILS VALUES(?,?,?,?)"); // ORDER_DETAILS_ID.nextval, " + quantity.getValue() + ", " + orderID + ", " + menu.getValue() + "
            insert2.setInt(1, orderDetailsId );
            insert2.setInt(2, quantity.getValue());
            insert2.setInt(3, orderID);
            insert2.setInt(4, menu.getValue());
            insert2.executeUpdate();

            Alert a= new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Order");
            a.setContentText("Order has been reserved successfully");
            a.show();
            
        }
        else{

            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText("Make sure you have selected all follwing details!");
            a.show();
        }
        
        myStage.close();
        
        Parent root = FXMLLoader.load(getClass().getResource(lastLocation));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
