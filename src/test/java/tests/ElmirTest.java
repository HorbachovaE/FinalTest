package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Elmir;
import pages.Goods;
import pages.Item;
import setup.Helper;

import java.util.List;

public class ElmirTest extends BaseTest {
    String searchchKey = "Видеокарта";
    @Test
    public void ElmirGetItemsFromSearch() {
        SoftAssert softAssert = new SoftAssert();
        String body= mockGoods();
        //String expectedResponseData = getAllItemsFromRequest();
        Goods goods = Helper.initFromJsonItem(body);
        List<Item> items = goods.getGoods();

        Elmir elmirPage = PageFactory.initElements(driver, Elmir.class);
        elmirPage.closeSuscribe();
        elmirPage.searchFind(searchchKey);
        elmirPage.getPageTitle();
        softAssert.assertTrue(elmirPage.getPageTitle().toString().contains(searchchKey));

        for (Item element : items) {
            WebElement itemElement = null;
            itemElement = elmirPage.getByName(element.getName());
            softAssert.assertTrue(itemElement != null, "Items is present");
            BaseTest.LOG.debug("Go through the elements by name");

            String name = elmirPage.getItemName(itemElement);
            softAssert.assertEquals(name, element.getName());

            String price = elmirPage.getItemPrice(itemElement);
            BaseTest.LOG.debug("Get price of the element");
            softAssert.assertEquals(price, element.getPrice(), "Price is incorrect");

            String availability = elmirPage.getItemAvailability(itemElement);
            BaseTest.LOG.debug("Get availability of the element");
            softAssert.assertEquals(availability, element.getAvailability(), "Missing availability");

            String code = elmirPage.getItemCode(itemElement);
            BaseTest.LOG.debug("Get code of the element");
            softAssert.assertEquals(code, element.getCode(), "Code is incorrect");
        }
        softAssert.assertAll();
    }
}
