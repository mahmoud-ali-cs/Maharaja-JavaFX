
package StorePackage;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class shipment_tableview {
    SimpleIntegerProperty id;
    SimpleStringProperty date;

    public shipment_tableview(int id,String date) {
        
        this.id = new SimpleIntegerProperty(id);
        this.date =new SimpleStringProperty( date);

    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }


}


