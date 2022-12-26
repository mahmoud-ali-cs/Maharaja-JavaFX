
package AllReservationsPackage;

import java.sql.Timestamp;


public class reserv {
    
    private int table_id;
    private String name;
    private Timestamp time;

    public reserv(int table_id, String name, Timestamp time) {
        this.table_id = table_id;
        this.name = name;
        this.time = time;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    
    
}
