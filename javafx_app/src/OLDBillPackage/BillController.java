package OLDBillPackage;

import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.text.Text;



public class BillController implements Initializable {

    public static int [] orderID ;
    public static double orderDiscount = 0;
    public static double taxesValue = 0 ;
    
    @FXML
    private TableView<FinalBill> tableView;

    @FXML
    private TableColumn<FinalBill, String> colItem;

    @FXML
    private TableColumn<FinalBill, Float> colPrice;

    @FXML
    private TableColumn<FinalBill, Integer> colQuantity;

    @FXML
    private TableColumn<FinalBill, Float> colTotal;

    
    @FXML
    private Text discount;

    @FXML
    private Text finalAmount;

    @FXML
    private Text taxes;

    @FXML
    private Text total;


    @FXML
    void DoneBill(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        we need from previous page (tableOrder / Takeaway ) --> (order_id, discount) .
        System.out.println("start initialize : orderID : " + orderID.length);
        
        try {
            Connection con = HomePackage.MainClass.con ;

            float sum = 0 ;
            ObservableList<FinalBill>  finalBills = FXCollections.observableArrayList(
    //                    new FinalBill("meat",2,30,60)
                );
 
            for(int i = 0 ; i < orderID.length ; i++){
                
                Statement stmt = con.createStatement();

                ResultSet r = stmt.executeQuery("SELECT Order_details.menu_item_id, Order_details.quantity  \n" +
                    "FROM Orders\n" +
                    "INNER JOIN order_details ON Orders.ORDERS_ID=Order_details.order_id where Orders.ORDERS_ID="+orderID[i]+" ");
                int count = 0 ;
                while(r.next()){
                    count++ ;
                    System.out.println("count : " + count);
                }            

                r = stmt.executeQuery("SELECT Order_details.menu_item_id, Order_details.quantity  \n" +
                    "FROM Orders\n" +
                    "INNER JOIN order_details ON Orders.ORDERS_ID=Order_details.order_id where Orders.ORDERS_ID="+orderID[i]+" ");

                FinalBill [] billList = new FinalBill[count];

                count = 0 ;
                while(r.next()){
                    int item = r.getInt("menu_item_id");

                    Statement stmt2 = con.createStatement();
                    ResultSet r2 = stmt2.executeQuery("SELECT ITEMS,price from menu_items where MENU_ITEMS_ID='"+item+"' ");
                    String item_name = "" ;
                    float item_price = 0 ;
                    while(r2.next()){
                        item_name = r2.getString("ITEMS");
                        item_price = r2.getFloat("price");
                    }

                    item_name = "("+item+") " + item_name ;

                    int quan = r.getInt("quantity");

                    float totalForItem = quan * item_price ;

                    billList[count] = new FinalBill(item_name, quan, item_price, totalForItem);
                    count++ ;
                }

    //            System.out.println(billList.toString());

                colItem.setCellValueFactory(new PropertyValueFactory<FinalBill,String>("item_name"));
                colPrice.setCellValueFactory(new PropertyValueFactory<FinalBill,Float>("price"));
                colQuantity.setCellValueFactory(new PropertyValueFactory<FinalBill,Integer>("quantity"));
                colTotal.setCellValueFactory(new PropertyValueFactory<FinalBill,Float>("total"));

            
                sum = 0 ;
                for(int j = 0 ; j < billList.length ; j++){
                    finalBills.add(billList[j]) ;
                    sum += billList[j].getTotal() ;
                }
            
            }
            
            tableView.setItems(finalBills);
            total.setText( sum + " lE" );
            taxes.setText(taxesValue*100 + "%" );
            discount.setText(orderDiscount*100 + "%");
            String finalAmountText =  sum + (sum * taxesValue) - (sum * orderDiscount) + "" ;
            finalAmount.setText(finalAmountText);
            
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }




    }    
    
}
