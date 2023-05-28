package pages;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("item")
public class Item {
    private String name;
    private String price;
    private String availability;
    private String code;

    public Item(String name, String price, String availability, String code) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getAvailability() {
        return availability;
    }
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name= " + name  + ", price = " + price
                + ", availability = " + availability + ", code= "+code+
                '}';
    }
}

