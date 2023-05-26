package tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Elmir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElmirTest extends BaseTest {
    @Test
    public void ElmirGetItemsFromSearch() {
        SoftAssert softAssert = new SoftAssert();

        Elmir elmirPage = PageFactory.initElements(driver, Elmir.class);
        elmirPage.closeSuscribe();
        elmirPage.searchFind();

        Map<String, List<String>> getItemsData = new HashMap<>();
        List<String> params = new ArrayList<>();;
        for (WebElement el : elmirPage.getItems()) {

            String name = null;
            name = el.findElement(By.xpath(".//*[@class=\"vit-name\"]")).getText();
            BaseTest.LOG.debug("Go through the elements name");
            softAssert.assertTrue(name != null, "Items name is present");

            String price = el.findElement(By.xpath(".//*[@class=\"tovar-price\"]")).
                    getAttribute("outerText");
            softAssert.assertTrue((!price.isEmpty()), "Price is incorrect");
            BaseTest.LOG.debug("Get price of the element");

            String availability = el.findElement(By.xpath("//*[@id=\"vitrina-tovars\"]/li[4]/div/section[1]/div[5]")).
                    getAttribute("outerText");
            softAssert.assertTrue((!availability.isEmpty() && !availability.contains("Немає в наявності")), "Item is available");
            BaseTest.LOG.debug("Get availability of the element");

            String code = el.findElement(By.xpath(".//*[@class=\"vit-name\"]")).
                    getAttribute("outerText");
            softAssert.assertTrue((!code.isEmpty()), "Item code is available");
            BaseTest.LOG.debug("Get code of the element");

            params.add(price);
            params.add(availability);
            params.add(code);
            getItemsData.put(name, params);
        }
        System.out.println(getItemsData);
        softAssert.assertAll();
    }
}
