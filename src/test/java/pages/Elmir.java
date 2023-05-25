package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import setup.BaseTest;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Elmir {

    WebDriverWait wait;
    WebDriver driver;

    @FindBy(xpath = "//input[@type='search']")
    WebElement search;
     @FindBy(xpath = "//div[@id='subscribe-close']")
     WebElement closeBtn;
     @FindBy(css = "li[class=\"vit-item\"]")
     List<WebElement> allItems;
    By itemName = By.xpath(".//*[@class=\"vit-name\"]");
    By itemPrice = By.xpath(".//*[@class=\"tovar-price\"]");
    By itemAvailability = By.xpath("//*[@id=\"vitrina-tovars\"]/li[4]/div/section[1]/div[5]");
    By itemCode = By.xpath(".//*[@class=\"vit-name\"]");

    public Elmir(WebDriver driver) {
        this.driver = driver;
        driver.get("https://elmir.ua/ua/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        BaseTest.LOG.info("Init page");
    }

    public void closeSuscribe()
    {
        wait.until(ExpectedConditions.visibilityOf(closeBtn));
        closeBtn.click();
        BaseTest.LOG.info("Close subscribe alert");

    }

    public void searchFind()
    {
        wait.until(ExpectedConditions.visibilityOf(search));
        search.sendKeys("Рюкзак");
        search.submit();
        BaseTest.LOG.info("Type in search field with submit");
        WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"page-title\"]/span"));
        String title = pageTitle.getText().toString();
        Assert.assertTrue(title.contains("Результати пошуку за запитом «Рюкзак»"), "Opened page should be 'Pюкзак'");
        BaseTest.LOG.info("Check that page is applied");
    }

    public Map<String, String> getReturnedItemsNameAndPrice() {
        Map<String, String> namePrice = new HashMap<>();
        for (WebElement el : allItems) {
            String name = null;
                name = el.findElement(itemName).getText();
                BaseTest.LOG.debug("Go through the elements name");
           Assert.assertTrue(name != null, "Items name is present" );

                String price = el.findElement(itemPrice).
                        getAttribute("outerText");
           Assert.assertTrue((!price.isEmpty()), "Price is incorrect");
            BaseTest.LOG.debug("Get price of the element");
                namePrice.put(name, price);
           // System.out.println(namePrice);
            }
        return namePrice;
    }

    public Map<String, String> getReturnedItemsNameAndAvailability() {
        Map<String, String> nameAvailability = new HashMap<>();
        for (WebElement el : allItems) {
            String name = null;
            name = el.findElement(itemName).getText();
            BaseTest.LOG.debug("Go through the elements name");
            Assert.assertTrue(name != null, "Items name is present" );

            String availability = el.findElement(itemAvailability).
                    getAttribute("outerText");
            Assert.assertTrue((!availability.isEmpty() && !availability.contains("Немає в наявності")), "Item is available");
            BaseTest.LOG.debug("Get availability of the element");
            nameAvailability.put(name, availability);
           // System.out.println(nameAvailability);
        }
        return nameAvailability;
    }

    public Map<String, String> getReturnedItemsNameAndCode() {
        Map<String, String> nameCode = new HashMap<>();
        for (WebElement el : allItems) {
            String name = null;
            name = el.findElement(itemName).getText();
            BaseTest.LOG.debug("Go through the elements name");
            Assert.assertTrue(name != null, "Items name is present" );

            String code = el.findElement(itemCode).
                    getAttribute("outerText");
            Assert.assertTrue((!code.isEmpty()), "Item code is available");
            BaseTest.LOG.debug("Get code of the element");
            nameCode.put(name, code);
            //System.out.println(nameCode);
        }
        return nameCode;
    }
}
