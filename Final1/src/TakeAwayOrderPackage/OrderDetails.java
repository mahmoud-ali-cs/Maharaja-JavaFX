package TakeAwayOrderPackage;

//import SelectOrderPackage.*;

public class OrderDetails {
    
    private int orderDetailsID ;
    private int menuID ;
    private String menuItem ;
    private int quantity ;

    
    
    public OrderDetails(int orderDetailsID, int menuID, String menuItem, int quantity) {
        this.orderDetailsID = orderDetailsID;
        this.menuID = menuID;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    
    
    public int getOrderDetailsID() {
        return orderDetailsID;
    }

    public void setOrderDetailsID(int orderDetailsID) {
        this.orderDetailsID = orderDetailsID;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
  
    
    
}
