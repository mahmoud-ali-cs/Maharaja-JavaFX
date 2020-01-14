package StorePackage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class store_items_tableview {
    
    SimpleIntegerProperty id;
    SimpleStringProperty item;
    SimpleStringProperty date;
    SimpleIntegerProperty amount;
    SimpleIntegerProperty status;

    public store_items_tableview(int id, String item,String date, int amount, int status) {
        this.id = new SimpleIntegerProperty(id);
        this.item =new SimpleStringProperty (item);
        this.date =new SimpleStringProperty( date);
        this.amount =new SimpleIntegerProperty(amount);
        this.status =new SimpleIntegerProperty( status);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getItem() {
        return item.get();
    }

    public void setItem(String item) {
        this.item.set(item);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getStatus() {
        return status.get();
    }

    public void setStatus(int status) {
        this.status.set(status);
    }
    



}
