package OrdersPackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import static HomePackage.MainClass.stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class OrdersController implements Initializable {

    
    @FXML
    private Button order;

    @FXML
    private Button menu;

    @FXML
    private Button tables;

    @FXML
    private Button store;

    @FXML
    private Button employee;

    
    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
        
    @FXML
    void goToEditReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EditReservationPackage/EditReservation.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void goToOrderForTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/OrdersForTablePackage/OrdersForTable.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void goToReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ReservationPackage/Reservation.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void goToAllReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AllReservationsPackage/AllReservations.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void goToAllOrders(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AllOrdersPackage/AllOrders.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void goToTakeAwayOrder(ActionEvent event) throws IOException {
        try{
            Connection con = HomePackage.MainClass.con ;
            
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM orders ");
            ResultSet r = pstmt.executeQuery();
            
            int count = 0 ;
            while(r.next()){
                count++ ;
            } 
            
            pstmt = con.prepareStatement("insert into orders (orders_id, order_date, order_type) values (?,?,?)");
            pstmt.setInt(1, count+1 );
            
            java.sql.Date d =  java.sql.Date.valueOf( LocalDate.now() );
            pstmt.setDate(2, d);
            pstmt.setInt(3, 0);
            r = pstmt.executeQuery();
            
            TakeAwayOrderPackage.TakeAwayOrderController.orderID = count+1 ;

            Parent root = FXMLLoader.load(getClass().getResource("/TakeAwayOrderPackage/TakeAwayOrder.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }     
          
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
