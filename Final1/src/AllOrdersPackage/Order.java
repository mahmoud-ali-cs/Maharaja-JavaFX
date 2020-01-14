package AllOrdersPackage;

import ViewMenu.*;
import java.util.* ;

public class Order {
    
    private int id;
    private Date date;
    private String order_type;
    private int table_id;

    
    
    public Order(int id, Date date, String order_type, int table_id) {
        this.id = id;
        this.date = date;
        this.order_type = order_type;
        this.table_id = table_id;
    }

 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

 
    
    
}
