
package StorePackage;
import java.sql.*;

public class store_items {
   int id;
   int status;
   String name;
   int amount;
   String store_items_date;
   

public store_items(int id) {
       
this.id=id;
        
}

public store_items(int id,int amount,String store_items_date ) {
        
        this.id=id;
        this.amount=amount;
        this.store_items_date=store_items_date;
        
    }

public store_items(String name,int amount,String store_items_date,int status ) {
       
        this.status=status;
        this.name=name;
        this.amount=amount;
        this.store_items_date=store_items_date;
        
    }
   
  
    public store_items(int id,String name,int amount,String store_items_date,int status ) {
        
        this.id=id;
        this.status=status;
        this.name=name;
        this.amount=amount;
        this.store_items_date=store_items_date;
        
    }
    
    
    
    
    
    public void insert() throws Exception {

        
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        Statement stmt=con.createStatement(); 
        int max=-1;
        ResultSet rs = stmt.executeQuery("select MAX(ITEMS_ID) as maxLevel from store_items");
        if (rs.next()) { max = rs.getInt("maxLevel");}
        max =max+1;
        
        String sql="INSERT INTO store_items VALUES (?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, max);
        statement.setString(2, this.name);
        statement.setInt(3, this.amount);
        statement.setString(4, this.store_items_date);
        statement.setInt(5, this.status);
        
        statement.executeUpdate();

        }
    
   
    public void update_amount() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;
        
        String sql="Update store_items Set AMOUNT=? where ITEMS_ID=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, this.amount);
        statement.setInt(2,this.id );
        statement.executeUpdate();
        
        
        String sql1="Update store_items Set STORE_ITEMS_DATES=? where ITEMS_ID=?";
        PreparedStatement statement1 = con.prepareStatement(sql1);
        statement1.setString(1, this.store_items_date);
        statement1.setInt(2,this.id );
        statement1.executeUpdate();
        
        
        if(this.amount>9){
        String sql4="Update store_items SET item_status=? where ITEMS_ID=?";
        PreparedStatement statement4 = con.prepareStatement(sql4);

        statement4.setInt(1,1);
        statement4.setInt(2,this.id);
        statement4.executeUpdate();
        }
        
        
        else{
        String sql4="Update store_items SET item_status=? where ITEMS_ID=?";
        PreparedStatement statement4 = con.prepareStatement(sql4);

        statement4.setInt(1,0);
        statement4.setInt(2,this.id);
        statement4.executeUpdate();    
        }
        
        
        }

    
    
    
    
    
    public void delete() throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","maharaja","123");
        Connection con = HomePackage.MainClass.con ;

        String sql="DELETE FROM store_items where ITEMS_ID=?";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, this.id);
        statement.executeUpdate();
        }
    

     
}

    
    


