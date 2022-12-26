package AddMenuItemPackage;

public class MenuItem {
    
    private int id;
    private String Item_name;
    private float price;
    private int MenuClass_id;

    public MenuItem(int id, String Item_name, float price, int MenuClass_id) {
        this.id = id;
        this.Item_name = Item_name;
        this.price = price;
        this.MenuClass_id = MenuClass_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String Item_name) {
        this.Item_name = Item_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMenuClass_id() {
        return MenuClass_id;
    }

    public void setMenuClass_id(int MenuClass_id) {
        this.MenuClass_id = MenuClass_id;
    }
    
    
  
    
}
