
package StorePackage;



import java.sql.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class shipment  {
    
    String date;
    int id;

    public shipment() {//for delete_all method     
    }
          
    public shipment(String date) {//for insert method -- for delete method
        this.date=date;      
    }
      
    public shipment(int id,String date) {//for update method
        this.id=id;
        this.date=date;      
    }
    
    public void insert() throws Exception {

        
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        Statement stmt=con.createStatement(); 

        int max=-1;
        ResultSet rs = stmt.executeQuery("select MAX(SHIPMENT_ID) as maxLevel from shipment");
        if (rs.next()) { max = rs.getInt("maxLevel");}
        max =max+1;

        String sql="INSERT INTO shipment VALUES (?,?)";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, max);
        statement.setString(2, this.date);
        statement.executeUpdate();

        
        }
    
    
    public void update() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        String sql="Update shipment Set SHIPMENT_DATE=? where SHIPMENT_ID =?";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, this.date);
        statement.setInt(2,this.id );
        statement.executeUpdate();
        
        }

    
    public void delete() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        
        String sql="DELETE FROM shipment where SHIPMENT_DATE =?";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, this.date);
        statement.executeUpdate();
        
        }

    public void delete_all() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        
        String sql="DELETE FROM shipment";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.executeUpdate();
        
        }
    
    
    
    // table view after
    public void select() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        Statement stmt=con.createStatement(); 

        ResultSet rs=stmt.executeQuery("select * from shipment");  
        while(rs.next())  
        System.out.println(rs.getInt(1)+"  "+rs.getString(2));

        }
    
}

