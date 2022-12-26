package BillPackage;

public class FinalBill {

    private String item_name ;
    private int quantity ;
    private float price ;
    private float total ;

    public FinalBill(String item_name, int quantity, float price, float total) {
        this.item_name = item_name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    
    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    
}
