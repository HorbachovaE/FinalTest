package setup;
import com.google.gson.Gson;
import pages.Item;

public class Helper {
    private Helper() {
    }

    public static Item initFromJsonItem(String data){
        Gson gson = new Gson();
        return (Item)gson.fromJson(data, Item.class);
    }
}
