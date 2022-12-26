package StorePackage;
import java.sql.*;

public class items_shipment {
   
    int shipment_id;
    int item_id;
    int amount;
    
    public items_shipment(int item_id){    
        this.item_id=item_id;
 
    }
    
    
    public items_shipment(int shipment_id,int item_id){    
        this.item_id=item_id;
        this.shipment_id=shipment_id;
        
    }
    
    public items_shipment(int shipment_id,int item_id,int amount){    
        this.item_id=item_id;
        this.shipment_id=shipment_id;
        this.amount=amount;
    }

    public void insert() throws Exception {
     
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        String sql="INSERT INTO ITEMS_SHIPMENT VALUES (?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, this.shipment_id);
        statement.setInt(2, this.item_id);
        statement.setInt(3, this.amount);
        statement.executeUpdate();
        
        //change amount & date in store_items
        
        int new_amount = 0;
        Statement stmt=con.createStatement(); 
        ResultSet rs=stmt.executeQuery("select Amount from store_items where items_id="+this.item_id+" ");  
        while(rs.next()){  
        new_amount += rs.getInt(1);}        
        
        //change amount
        
        new_amount +=this.amount;
        
        
        
        //change date
        String new_date = null ;
        
        ResultSet rs2=stmt.executeQuery("select * from shipment where SHIPMENT_ID ="+this.shipment_id+"");  
        while(rs2.next()){  
        new_date=rs2.getString(2);
        }
        
        
        
        String sql2="Update store_items SET AMOUNT=?  where ITEMS_ID=?";
        PreparedStatement statement2 = con.prepareStatement(sql2);

        statement2.setInt(1,new_amount );
        statement2.setInt(2,this.item_id );
        statement2.executeUpdate();
        
        
        
        
        
        String sql3="Update store_items SET STORE_ITEMS_DATES=? where ITEMS_ID=?";
        PreparedStatement statement3 = con.prepareStatement(sql3);
        
        statement3.setString(1,new_date );
        statement3.setInt(2,this.item_id );
        statement3.executeUpdate();
        
        
        
        
        //change status if amount >9
        
        if(new_amount>9){
        String sql4="Update store_items SET item_status=? where ITEMS_ID=?";
        PreparedStatement statement4 = con.prepareStatement(sql4);

        statement4.setInt(1,1);
        statement4.setInt(2,this.item_id );
        statement4.executeUpdate();
        }
        
    }
    
    
    
    
    public void update() throws Exception { //table view SHIPMENT_ID AND ITEM_ID
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        
        int old_amount =0;
        Statement stmt=con.createStatement();   
        try{
        ResultSet rs1=stmt.executeQuery("select AMOUNT from ITEMS_SHIPMENT where SHIPMENT_ID='"+this.shipment_id+"' AND ITEM_ID='"+this.item_id+"' ");  
        
        while(rs1.next()){ 
             old_amount=rs1.getInt(1);}
        }
        catch(Exception ex){System.out.println(ex);;}
        
        
        
        
        int current_amount=this.amount-old_amount;

        Statement stmt2=con.createStatement(); 
        ResultSet rs=stmt2.executeQuery("select Amount from store_items where items_id="+this.item_id+" ");  
        while(rs.next()){  
        current_amount += rs.getInt(1);}
        
        
        String sql2="Update store_items SET AMOUNT=?  where ITEMS_ID=?";
        PreparedStatement statement2 = con.prepareStatement(sql2);

        statement2.setInt(1,current_amount );
        statement2.setInt(2,this.item_id );
        statement2.executeUpdate();
        

        
        String sql="Update ITEMS_SHIPMENT Set AMOUNT=? where SHIPMENT_ID =? AND ITEM_ID=?";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, this.amount);
        statement.setInt(2,this.shipment_id );
        statement.setInt(3,this.item_id );
        statement.executeUpdate();
        
        
        
        
        
        }
    
    
    public void delete() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        
        String sql="DELETE FROM ITEMS_SHIPMENT where ITEM_ID=? AND SHIPMENT_ID=?";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1,this.item_id );
        statement.setInt(2,this.shipment_id );
        statement.executeUpdate();
        
        }
    
    
    public void select() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        Statement stmt=con.createStatement(); 

        ResultSet rs=stmt.executeQuery("select * from ITEMS_SHIPMENT");  
        while(rs.next()){  
        System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+"  "+rs.getInt(3));}
        
        }  
    
}

