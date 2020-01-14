package EditReservationPackage;

import static HomePackage.MainClass.stage;
import ReservationPackage.ReservationController;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditReservationController implements Initializable {
    public static int reservartionID = 1 ;
    
    private boolean isOldCustomer = true ;

    @FXML
    private TextField customerPhoneTF;

    @FXML
    private TextField customerNameTF;

    @FXML
    private DatePicker dateDP;

    @FXML
    private ComboBox<Integer> hoursCB;

    @FXML
    private ComboBox<Integer> minutesCB;

    @FXML
    private ComboBox<Integer> numOfChairsCB;
    
    @FXML
    private ComboBox<Integer> tableID_CB;

    @FXML
    private Label searchResultLabel;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        System.out.println("start initialize");
        for(int i = 1 ; i <= 12 ; i++){
            hoursCB.getItems().add(i);
        }
        for(int i = 0 ; i <= 55 ; i+=5){
            minutesCB.getItems().add(i);
        }
        numOfChairsCB.getItems().addAll(2,4,6);
        
        try {  
            int table_id = -1 ;
            int customer_id = -1 ;
            Timestamp reservation_date = new Timestamp(System.currentTimeMillis()) ;
            int chair_number = -1 ;


            PreparedStatement pstmt = HomePackage.MainClass.con.prepareStatement("select table_id,customer_id,reservation_date from reservation where reservation_id=? ");
            pstmt.setInt(1, reservartionID);
            ResultSet r = pstmt.executeQuery();

            while(r.next()){
                table_id = r.getInt(1);
                customer_id = r.getInt(2);
                reservation_date = r.getTimestamp(3);
                
                System.out.println("table_id : " + table_id);
                System.out.println("customer_id : " + customer_id);
                System.out.println("date : " + reservation_date.toString());
            }

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(reservation_date.getTime());
            int hours = c.get(Calendar.HOUR_OF_DAY);
            int minutes = c.get(Calendar.MINUTE);
            java.util.Date date = c.getTime();
            
            
//            hoursCB.setSelectionModel(value);
            hoursCB.setValue( hoursCB.getItems().get( hoursCB.getItems().indexOf(hours) ) );
            minutesCB.setValue( minutesCB.getItems().get( minutesCB.getItems().indexOf(minutes) ) );
            dateDP.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            
            
            pstmt = HomePackage.MainClass.con.prepareStatement("select chair_number from tables where tables_id=? ");
            pstmt.setInt(1, table_id);
            r = pstmt.executeQuery();

            while(r.next()){
               chair_number = r.getInt(1);
               
                System.out.println("chair number : " + chair_number);
            }
            
            numOfChairsCB.setValue( numOfChairsCB.getItems().get( numOfChairsCB.getItems().indexOf(chair_number) ) );
            tableID_CB.getItems().add(table_id);
            tableID_CB.setValue( tableID_CB.getItems().get( tableID_CB.getItems().indexOf(table_id) ) );

            
            Statement stmt = HomePackage.MainClass.con.createStatement();
            
            r = stmt.executeQuery("select name,phone from customer where id='"+ customer_id +"' ");
            if(r.next()){
                System.out.println(r.getString("name"));
                System.out.println(r.getString("phone"));
                
                customerNameTF.setText(r.getString("name"));
                customerPhoneTF.setText(r.getString("phone"));
            }
                
                java.util.Date fullDate = date ;

                java.util.Date afterTwo = addHoursMinutesToJavaUtilDate(fullDate, 2, 0) ;
                java.util.Date beforeTwo = addHoursMinutesToJavaUtilDate(fullDate, -2, 0) ;

                System.out.println("afterTwo : " + afterTwo);
                System.out.println("beforeTwo : " + beforeTwo);

                Timestamp tsUpper = new Timestamp( afterTwo.getTime() );
                Timestamp tsLower = new Timestamp( beforeTwo.getTime() );
            
            
                int count = 0 ;
    //            Statement stmt = con.createStatement();
                PreparedStatement pstmt1 = HomePackage.MainClass.con.prepareStatement("select count(*) from reservation where reservation_date >= ? and reservation_date <= ? ");
                pstmt1.setTimestamp(1, tsLower);
                pstmt1.setTimestamp(2, tsUpper);
                ResultSet r1 = pstmt1.executeQuery();
    //            ResultSet r = stmt.executeQuery("select table_id from reservation where reservation_date >= "+tsLower+" and reservation_date <= 22 ");
                while(r1.next()){
                    System.out.println("Count : " + r1.getInt(1));
                    count = r1.getInt(1) ;
                }
                
                PreparedStatement pstmt2 = HomePackage.MainClass.con.prepareStatement("select table_id from reservation where reservation_date >= ? and reservation_date <= ? ");
                pstmt2.setTimestamp(1, tsLower);
                pstmt2.setTimestamp(2, tsUpper);
                ResultSet r2 = pstmt2.executeQuery();
                
                int [] busyTableList = new int [count] ;
                
                count = 0 ;
                while(r2.next()){
                    System.out.println(r2.getString("table_id"));
                    busyTableList[ count ] = r2.getInt(1);
                    count++ ;
                }
                
                count = 0 ;
                PreparedStatement pstmt3 = HomePackage.MainClass.con.prepareStatement("select count(*) from tables where chair_number=? ");
                pstmt3.setInt(1, chair_number);
                ResultSet r3 = pstmt3.executeQuery();
                while(r3.next()){
//                    System.out.println("Count : " + r3.getInt(1));
                    count = r3.getInt(1) ;
                }
                
                PreparedStatement pstmt4 = HomePackage.MainClass.con.prepareStatement("select tables_id from tables where chair_number=? ");
                pstmt4.setInt(1, chair_number);
                ResultSet r4 = pstmt4.executeQuery();
                
                int [] allTableList = new int [count] ;
                
                count = 0 ;
                while(r4.next()){
//                    System.out.println(r.getString("table_id"));
                    allTableList[ count ] = r4.getInt(1);
                    count++ ;
                }    

//          Both lists are READY!!
                for(int i = 0 ; i < allTableList.length ; i++){
                    for(int j = 0 ; j < busyTableList.length ; j++){
                        if( allTableList[i] == busyTableList[j] ){
                            allTableList[i] = -1 ;
                        }
                    }
                }
                
                for(int i = 0 ; i < allTableList.length ; i++){
                    if(allTableList[i] != -1){
                        tableID_CB.getItems().add( allTableList[i] );
                    }
                }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    
    @FXML
    void SearchForOldCustomer(ActionEvent event){
        try {
            String phone = customerPhoneTF.getText();
            
            
            Statement stmt = HomePackage.MainClass.con.createStatement();
            
            ResultSet r = stmt.executeQuery("select name from customer where phone='"+ phone +"' ");
            if(r.next()){
                System.out.println(r.getString("name"));
                searchResultLabel.setText("Old Customer Found");
                isOldCustomer = true ;
            }else{
                searchResultLabel.setText("New Phone Number");
                isOldCustomer = false ;
            }
            
            customerNameTF.setText(r.getString("name"));
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public java.util.Date addHoursMinutesToJavaUtilDate(java.util.Date date, int hours, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    } 
    
    @FXML
    void tableSelection(ActionEvent event) {
        System.out.println("table!!");
        if(dateDP.getValue() != null &&
                numOfChairsCB.getSelectionModel().getSelectedItem() != null &&
                hoursCB.getSelectionModel().getSelectedItem() != null &&
                minutesCB.getSelectionModel().getSelectedItem() != null ){
            
            int selectedChairs = numOfChairsCB.getSelectionModel().getSelectedItem();
            int selectedHour = hoursCB.getSelectionModel().getSelectedItem();
            int selectedMinute = minutesCB.getSelectionModel().getSelectedItem();
    //        int selectedDate = LocalDate.(date.getValue())

            LocalDate d = LocalDate.MAX ;
    
    //        System.out.println("tableSelection start!");
            java.util.Date selectedDate = 
                java.util.Date.from( dateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant() );
//            System.out.println( selectedDate.toString() );
            java.util.Date fullDate = addHoursMinutesToJavaUtilDate(selectedDate, selectedHour, selectedMinute) ;
            
            java.util.Date afterTwo = addHoursMinutesToJavaUtilDate(fullDate, 2, 0) ;
            java.util.Date beforeTwo = addHoursMinutesToJavaUtilDate(fullDate, -2, 0) ;
            
            System.out.println("afterTwo : " + afterTwo);
            System.out.println("beforeTwo : " + beforeTwo);
            
            Timestamp tsUpper = new Timestamp( afterTwo.getTime() );
            Timestamp tsLower = new Timestamp( beforeTwo.getTime() );
            
//            System.out.println("Timestamp of fullDate : " + ts);        
                    
//            System.out.println( fullDate.toString() );
//            java.sql.Date sqlDate = new java.sql.Date(fullDate.getTime());
//            
//            System.out.println(sqlDate.toString());
    //        pst.setDate(5, sqlDate);

            try{

                
                int count = 0 ;
    //            Statement stmt = con.createStatement();
                PreparedStatement pstmt1 = HomePackage.MainClass.con.prepareStatement("select count(*) from reservation where reservation_date >= ? and reservation_date <= ? ");
                pstmt1.setTimestamp(1, tsLower);
                pstmt1.setTimestamp(2, tsUpper);
                ResultSet r1 = pstmt1.executeQuery();
    //            ResultSet r = stmt.executeQuery("select table_id from reservation where reservation_date >= "+tsLower+" and reservation_date <= 22 ");
                while(r1.next()){
                    System.out.println("Count : " + r1.getInt(1));
                    count = r1.getInt(1) ;
                }
                
                PreparedStatement pstmt2 = HomePackage.MainClass.con.prepareStatement("select table_id from reservation where reservation_date >= ? and reservation_date <= ? ");
                pstmt2.setTimestamp(1, tsLower);
                pstmt2.setTimestamp(2, tsUpper);
                ResultSet r2 = pstmt2.executeQuery();
                
                int [] busyTableList = new int [count] ;
                
                count = 0 ;
                while(r2.next()){
                    System.out.println(r2.getString("table_id"));
                    busyTableList[ count ] = r2.getInt(1);
                    count++ ;
                }
                
                count = 0 ;
                PreparedStatement pstmt3 = HomePackage.MainClass.con.prepareStatement("select count(*) from tables where chair_number=? ");
                pstmt3.setInt(1, selectedChairs);
                ResultSet r3 = pstmt3.executeQuery();
                while(r3.next()){
//                    System.out.println("Count : " + r3.getInt(1));
                    count = r3.getInt(1) ;
                }
                
                PreparedStatement pstmt4 = HomePackage.MainClass.con.prepareStatement("select tables_id from tables where chair_number=? ");
                pstmt4.setInt(1, selectedChairs);
                ResultSet r4 = pstmt4.executeQuery();
                
                int [] allTableList = new int [count] ;
                
                count = 0 ;
                while(r4.next()){
//                    System.out.println(r.getString("table_id"));
                    allTableList[ count ] = r4.getInt(1);
                    count++ ;
                }    

//          Both lists are READY!!
                for(int i = 0 ; i < allTableList.length ; i++){
                    for(int j = 0 ; j < busyTableList.length ; j++){
                        if( allTableList[i] == busyTableList[j] ){
                            allTableList[i] = -1 ;
                        }
                    }
                }
                
                tableID_CB.getItems().clear();
                for(int i = 0 ; i < allTableList.length ; i++){
                    if(allTableList[i] != -1){
                        tableID_CB.getItems().add( allTableList[i] );
                    }
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
            
        
    }
    
    
    @FXML
    void updateReservation(ActionEvent event) {
        
            
        int customerID = -1 ;
        String customerPhone = customerPhoneTF.getText();
        String customerName = customerNameTF.getText();
//        int numOfChairs = numOfChairsCB.getSelectionModel().getSelectedItem();
        int tableID = tableID_CB.getSelectionModel().getSelectedItem();
        int hours = hoursCB.getSelectionModel().getSelectedItem();
        int minutes = minutesCB.getSelectionModel().getSelectedItem();

        java.util.Date selectedDate = 
            java.util.Date.from( dateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant() );
//            System.out.println( selectedDate.toString() );
        java.util.Date fullDate = addHoursMinutesToJavaUtilDate(selectedDate, hours, minutes) ;

        Timestamp reservationTimestamp = new Timestamp( fullDate.getTime() );
        try{
                PreparedStatement pstmt = HomePackage.MainClass.con.prepareStatement("select ID from customer where phone=?");
                pstmt.setString(1, customerPhone);
                ResultSet r = pstmt.executeQuery();
                isOldCustomer = false ;
                while(r.next()){
                    isOldCustomer = true ; 
                }
            
            
            
            if(isOldCustomer){
                System.out.println("if --> old customer");
                pstmt = HomePackage.MainClass.con.prepareStatement("select ID from customer where phone=?");
                pstmt.setString(1, customerPhone);
                r = pstmt.executeQuery();
                while(r.next()){
                    System.out.println("ID : " + r.getInt(1));
                    customerID = r.getInt(1) ;
                }
                
            }else{
                System.out.println("else --> new customer");
                int numOfCustomers = 0 ;
                pstmt = HomePackage.MainClass.con.prepareStatement("select count(*) from customer ");
                r = pstmt.executeQuery();
                while(r.next()){
                    System.out.println("numOfCustomers : " + r.getInt(1));
                    numOfCustomers = r.getInt(1) ;
                }
                customerID = numOfCustomers + 1 ;
                
                pstmt = HomePackage.MainClass.con.prepareStatement("insert into customer values(?,?,?)");
                pstmt.setInt(1, customerID );
                pstmt.setString(2, customerName);
                pstmt.setString(3, customerPhone);
                pstmt.executeUpdate(); 
                
            }
               
            // Create Reservation Record 
            int numOfReservation = 0 ;
            pstmt = HomePackage.MainClass.con.prepareStatement("select count(*) from reservation ");
            r = pstmt.executeQuery();
            while(r.next()){
                System.out.println("numOfReservation : " + r.getInt(1));
                numOfReservation = r.getInt(1) ;
            }
//            int reservationID = numOfReservation + 1 ;
            
            pstmt = HomePackage.MainClass.con.prepareStatement("update reservation set reservation_date=? , customer_id=? , table_id=? where reservation_id=?");
            pstmt.setTimestamp(1, reservationTimestamp );
            pstmt.setInt(2, customerID );
            pstmt.setInt(3, tableID );
            pstmt.setInt(4, reservartionID );
            pstmt.executeUpdate();            
            System.out.println("table id : " + tableID);
            
            
            Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
            stage.show();

        }catch(Exception e){
            System.out.println(e);
        }
                        

    }
    
    
    
    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    void goToBack(ActionEvent event) {

    }
    


    
}
