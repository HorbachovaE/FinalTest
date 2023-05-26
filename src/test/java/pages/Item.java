package pages;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("item")
public class Item {

    private String itemName;
    private String itemAvailabylity;
    private String itemCode;
    private String itemPrice;

    public Item(String itemName, String itemPrice, String itemAvailabylity, String itemCode) {
        this.itemName = itemName;
        this.itemPrice = this.itemPrice;
        this.itemAvailabylity = this.itemAvailabylity;
        this.itemCode = this.itemCode;

    }

    @Override
    public String toString() {
        return "Item{" +
                "name= " + itemName  + ", price = " + itemPrice
                + ", availability = " + itemAvailabylity + ", code= "+itemCode+
                '}';
    }

}

