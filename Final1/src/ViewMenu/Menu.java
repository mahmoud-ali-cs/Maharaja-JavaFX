package ViewMenu;


public class Menu {
    
    private int id;
    private String item;
    private String item_class;
    private float price;

    public Menu(int id, String item, String item_class, float price) {
        this.id = id;
        this.item = item;
        this.item_class = item_class;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_class() {
        return item_class;
    }

    public void setItem_class(String item_class) {
        this.item_class = item_class;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
