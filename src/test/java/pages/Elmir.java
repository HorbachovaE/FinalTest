package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;


public class Elmir {
    WebDriverWait wait;
    WebDriver driver;
    @FindBy(xpath = "//input[@type='search']")
    WebElement search;
     @FindBy(xpath = "//div[@id='subscribe-close']")
     WebElement closeBtn;
     @FindBy(css = "li[class=\"vit-item\"]")
     List<WebElement> allItems;
     @FindBy(xpath = "//*[@id=\"page-title\"]/span")
     WebElement pageTitle;


     String itemNameXPath = ".//*[@class=\"vit-name\"]";
     String itemPriceXPath = ".//*[@class=\"tovar-price\"]";
     String itemAvailabilityXPath = "//*[@id=\"vitrina-tovars\"]/li[4]/div/section[1]/div[5]";
     String itemCodeXPath = "//*[@id=\"vitrina-tovars\"]/li[26]/div/section[1]/div[4]";


    public Elmir(WebDriver driver) {
        this.driver = driver;
        driver.get("https://elmir.ua/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        BaseTest.LOG.info("Init page");
    }
    public void closeSuscribe()
    {
        wait.until(ExpectedConditions.visibilityOf(closeBtn));
        closeBtn.click();
        BaseTest.LOG.info("Close subscribe alert");
    }
    public void searchFind(String keyFound)
    {
        wait.until(ExpectedConditions.visibilityOf(search));
        search.sendKeys(keyFound);
        search.submit();
        BaseTest.LOG.info("Type in search field with submit");
    }
    public String getPageTitle()
    {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        String title = pageTitle.getText().toString();
        return title;
    }
    public WebElement getByName(String name)
    {
        var element = allItems.stream().filter(el ->
                el.findElement(By.xpath(itemNameXPath)).getText().equals(name)).findFirst().orElse(null);
        BaseTest.LOG.info("Get items by name");
        return element;
    }
    public String getItemName(WebElement itemWebElement){
        BaseTest.LOG.info("Get items name");
        return itemWebElement.findElement(By.xpath(itemNameXPath)).getText();
    }
    public String getItemPrice(WebElement itemWebElement){
        BaseTest.LOG.info("Get items price");
        return itemWebElement.findElement(By.xpath(itemPriceXPath)).getText();
    }
    public String getItemAvailability(WebElement itemWebElement){
        BaseTest.LOG.info("Get items availability");
        return itemWebElement.findElement(By.xpath(itemAvailabilityXPath)).getText();
    }
    public String getItemCode(WebElement itemWebElement){
        BaseTest.LOG.info("Get items code");
        return itemWebElement.findElement(By.xpath(itemCodeXPath)).getText();
    }
}
