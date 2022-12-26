package HomePackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MainClass extends Application {
    
    public static Stage stage = new Stage() ;
    public static Connection con ;

//    public static void goTo(String s) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource(s));
//
//        Scene scene = new Scene(root, 700, 400);
//        stage.setScene(scene);
//        stage.show();
//
//    }
    
    public void startConnect(){
        try{
            MainClass.con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "youssef", "123");

            if (con != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }            
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) { //wtf
            System.out.println(e);
        }
    }
    
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        startConnect();
        
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    
}
    

