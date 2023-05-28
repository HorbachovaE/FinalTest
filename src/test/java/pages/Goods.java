package pages;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;
@XStreamAlias("goods")
public class Goods {
  public List<Item> goods;
    public List<Item> getGoods() {
        return goods;
    }

}
