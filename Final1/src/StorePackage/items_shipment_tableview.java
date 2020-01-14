package StorePackage;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class items_shipment_tableview {
    
    SimpleIntegerProperty shid;
    SimpleIntegerProperty itid;
    SimpleIntegerProperty amount1;
    
    public items_shipment_tableview(int shid, int itid, int amount1) {
        this.shid = new SimpleIntegerProperty(shid);
        this.itid =new SimpleIntegerProperty (itid);       
        this.amount1 =new SimpleIntegerProperty(amount1);

    }

    public int getShid() {
        return shid.get();
    }

    public void setShid(SimpleIntegerProperty shid) {
        this.shid = shid;
    }

    public int getItid() {
        return itid.get();
    }

    public void setItid(SimpleIntegerProperty itid) {
        this.itid = itid;
    }

    public int getAmount1() {
        return amount1.get();
    }

    public void setAmount1(SimpleIntegerProperty amount) {
        this.amount1 = amount;
    }
    
    
}
