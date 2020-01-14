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
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John
 */
public class ViewController implements Initializable {

    @FXML private TableView<employee> tableview;
    @FXML private TableColumn<employee,Integer> id;
    @FXML private TableColumn<employee,String> name;
    @FXML private TableColumn<employee,String> gender;
    @FXML private TableColumn<employee,String> phone;
    @FXML private TableColumn<employee,String> address;
    @FXML private TableColumn<employee,Date> dob;
    @FXML private TableColumn<employee,String> nationality;
    @FXML private TableColumn<employee,Integer> position_id;
    //@FXML private TableColumn<employee,Integer> position_id2;
    @FXML private TableColumn<employee,String> position;
    @FXML private TableColumn<employee,Double> salary;
    @FXML private ObservableList<employee> data;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "maharaja", "123") ;
            Connection conn = HomePackage.MainClass.con ;
            
            
            data=FXCollections.observableArrayList();
            ResultSet rs= conn.createStatement().executeQuery("select EMPLOYEE.EMPLOYEE_ID,EMPLOYEE.NAME,EMPLOYEE.GENDER,EMPLOYEE.PHONE,EMPLOYEE.ADDRESS,EMPLOYEE.DATE_OF_BIRTH,EMPLOYEE.NATIONALITY,EMPLOYEE.POSITION,\n" +
"POSITIONS.POSITION,POSITIONS.SALARY\n" +
"from employee inner join POSITIONS \n" +
"on employee.position=positions.positions_id");
            while (rs.next()){
            data.add(new employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getInt(8),rs.getString(9), rs.getInt(10)));
            }
        } catch (Exception ex) {
            System.out.println(ex);        }
        
        
        id.setCellValueFactory(new PropertyValueFactory<employee , Integer>("employee_id"));
        name.setCellValueFactory(new PropertyValueFactory<employee , String>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<employee , String>("gender"));
        phone.setCellValueFactory(new PropertyValueFactory<employee , String>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<employee , String>("address"));
        dob.setCellValueFactory(new PropertyValueFactory<employee , Date>("date_of_birth"));
        nationality.setCellValueFactory(new PropertyValueFactory<employee , String>("nationality"));
        position_id.setCellValueFactory(new PropertyValueFactory<employee , Integer>("position_id"));
        //position_id2.setCellValueFactory(new PropertyValueFactory<employee , Integer>("position_id2"));
        position.setCellValueFactory(new PropertyValueFactory<employee , String>("position"));
        salary.setCellValueFactory(new PropertyValueFactory<employee , Double>("salary"));


        
        tableview.setItems(data);
        
        
 //       tableview.setItems(getEmployee());
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
