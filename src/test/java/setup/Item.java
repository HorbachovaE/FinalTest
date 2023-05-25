package setup;
import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("item")
public class Item {

    private String itemName;
    private String itemAvailabylity;
    private int itemCode;
    private double itemPrice;

    public Item(String itemName, String itemAvailabylity,int itemCode, double itemPrice) {
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

