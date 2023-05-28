package setup;
import com.google.gson.Gson;
import pages.Goods;

public class Helper {
    private Helper() {
    }

    public static Goods initFromJsonItem(String data){
        Gson gson = new Gson();
        return (Goods)gson.fromJson(data, Goods.class);
    }
}
