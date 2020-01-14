
package databasejohn;

import java.sql.Date;
import java.text.SimpleDateFormat;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class employee {
    private SimpleIntegerProperty employee_id;
    private SimpleStringProperty name;
    private SimpleStringProperty gender;
    private SimpleStringProperty phone;
    private SimpleStringProperty address;
    private Date date_of_birth;
    private SimpleStringProperty nationality;
    private SimpleIntegerProperty position_id;
 //   private SimpleIntegerProperty position_id2;
   private SimpleStringProperty position;
   private SimpleIntegerProperty salary;

    public employee(int employee_id, String name, String gender, String phone, String address, Date date_of_birth, String nationality, int position_id,String position,int salary) {
        this.employee_id = new SimpleIntegerProperty(employee_id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.date_of_birth = date_of_birth;
        this.nationality = new SimpleStringProperty(nationality);
        this.position_id = new SimpleIntegerProperty(position_id );
       // this.position_id2 = new SimpleIntegerProperty(position_id2 );
      this.position = new SimpleStringProperty(position);
       this.salary = new SimpleIntegerProperty(salary);
    }

//    public int getPosition_id2() {
//        return position_id2.get();
//    }
//
//    public void setPosition_id2(SimpleIntegerProperty position_id2) {
//        this.position_id2 = position_id2;
//    }

    public int getEmployee_id() {
        return employee_id.get();
    }

    public void setEmployee_id(SimpleIntegerProperty employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(SimpleStringProperty gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(SimpleStringProperty phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(SimpleStringProperty address) {
        this.address = address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNationality() {
        return nationality.get();
    }

    public void setNationality(SimpleStringProperty nationality) {
        this.nationality = nationality;
    }

    public int getPosition_id() {
        return position_id.get();
    }

    public void setPosition_id(SimpleIntegerProperty position_id) {
        this.position_id = position_id;
    }

    public String getPosition() {
        return position.get();
    }

    public void setPosition(SimpleStringProperty position) {
        this.position = position;
    }

    public int getSalary() {
        return salary.get();
    }

    public void setSalary(SimpleIntegerProperty salary) {
        this.salary = salary;
    }


    
    
    
    
}
