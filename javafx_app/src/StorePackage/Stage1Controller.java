package StorePackage;

import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Stage1Controller implements Initializable {
    
    @FXML private TableView<store_items_tableview> tableView;
    @FXML private TableColumn<store_items_tableview,Integer> firstcolumn;
    @FXML private TableColumn<store_items_tableview,String> secondcolumn;
    @FXML private TableColumn<store_items_tableview,String> thirdcolumn;
    @FXML private TableColumn<store_items_tableview,Integer> forthtcolumn;
    @FXML private TableColumn<store_items_tableview,Integer> fifthtcolumn;
    
    @FXML private TableView<items_shipment_tableview> tableView2;
    @FXML private TableColumn<items_shipment_tableview,Integer> firstcolumn1;
    @FXML private TableColumn<items_shipment_tableview,Integer> secondcolumn2;
    @FXML private TableColumn<items_shipment_tableview,Integer> thirdcolumn3;

    @FXML
    private Button delete_shipmet;
    @FXML
    private Button update_shipmet;
    @FXML
    private Button add_shipmet;
    @FXML
    private Button add_item;
    @FXML
    private Button update_item;
    @FXML
    private Button delete_item;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            firstcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("id"));
            secondcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,String>("item"));
            forthtcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("date"));
            thirdcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,String>("amount"));
            fifthtcolumn.setCellValueFactory(new PropertyValueFactory<store_items_tableview,Integer>("status"));
            
            
            firstcolumn1.setCellValueFactory(new PropertyValueFactory<items_shipment_tableview,Integer>("shid"));
            secondcolumn2.setCellValueFactory(new PropertyValueFactory<items_shipment_tableview,Integer>("itid"));
            thirdcolumn3.setCellValueFactory(new PropertyValueFactory<items_shipment_tableview,Integer>("amount1"));
            
            
            tableView.setItems(getdata());
            tableView2.setItems(getdata2());
            
        } catch (Exception ex) {
            System.out.println(ex);;
        }
    }
    
    
    
    

    
      public ObservableList<store_items_tableview> getdata() throws Exception{
          
        ObservableList<store_items_tableview> data2=FXCollections.observableArrayList();

//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        Statement stmt=con.createStatement(); 

            int x1=0;
            String x2=null;
            String x3=null;
            int x4=0;
            int x5=0;

        ResultSet rs2=stmt.executeQuery("select * from STORE_ITEMS"); 
        while(rs2.next()) { 
             x1=rs2.getInt("ITEMS_ID");
             x2=rs2.getString("NAME");
             x4=rs2.getInt("AMOUNT");
             x3=rs2.getString("STORE_ITEMS_DATES");
             x5=rs2.getInt("ITEM_STATUS");

             data2.add(new store_items_tableview(x1,x2,x3,x4,x5));
            
        }

        return data2;
    }   
      
      
      
      
      
      
      
      
      
      
      
    public ObservableList<items_shipment_tableview> getdata2() throws Exception{
          
        ObservableList<items_shipment_tableview> data2=FXCollections.observableArrayList();
          
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        Statement stmt=con.createStatement(); 

        ResultSet rs=stmt.executeQuery("select * from ITEMS_SHIPMENT");  
            
        while(rs.next()) { 
            data2.add(new items_shipment_tableview(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
        }
        
        return data2;
    }   
     @FXML
     
    private void display_shipments_method (ActionEvent event) throws IOException { 
        
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("Shipment_stage_display.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void delete_shipment_method(ActionEvent event) throws IOException {
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("DeleteShipmentStage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void update_shipment(ActionEvent event) throws IOException {
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("UpdateShipmentStage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void add_shipment_method(ActionEvent event) throws IOException {
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("AddShipmentStage.fxml"));        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void add_item_method(ActionEvent event) throws IOException {
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("Add_items.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void update_item_method(ActionEvent event) throws IOException {
        
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("update_item.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void delete_item_method(ActionEvent event) throws IOException {
        
        Stage stage =new Stage();
        stage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("delete_item.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

}
