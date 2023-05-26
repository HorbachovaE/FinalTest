package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

    public List<WebElement> getItems()
    {
        driver.getCurrentUrl();
        List<WebElement> list = allItems.stream().toList();
        return list;

    }
}
