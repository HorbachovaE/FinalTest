package tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    public static final Logger LOG = LogManager.getLogger(BaseTest.class.getName());
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static final String URL = String.format("http://%s:%d/%s", HOST, PORT, "%s");
    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(PORT);

    @BeforeTest
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));
        action = new Actions(driver);
    }
    @BeforeTest
    public void setUpWireMockServer() {
        System.out.println("Start server");
        WIRE_MOCK_SERVER.start();
        WireMock.configureFor(HOST, PORT);
    }
    @AfterTest(alwaysRun = true)
    public void closeUp() {
        if (driver != null)
            driver.quit();
    }
    @AfterTest(alwaysRun = true)
    public void stopWireMockServer() {
        if (WIRE_MOCK_SERVER.isRunning()) {
            System.out.println("Shot Down");
            WIRE_MOCK_SERVER.stop();
        }
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    protected void mouseClick(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        action.moveToElement(element).click().perform();
    }

    protected void switchToWindow(int index) {
        index--;
        String windowKey = (String) driver.getWindowHandles().toArray()[index];
        driver.switchTo().window(windowKey);
        LOG.info("Switch to window");
        LOG.debug("Switch to window by key: " + windowKey);
    }

    protected String getAllItemsFromRequest() {
        String responseGetMethodExpected = "{\"goods\":[\n    {\n      \"name\":\"Видеокарта Inno3D NVIDIA CMP 90-HX MINING CARD (ACMP-90HX-3FS, 288-9N612-101KT)\",\n      \"price\":\"79 999 грн\",\n      \"availability\":\"Под заказ\",\n      \"code\": \"Код:63918\"\n    },\n    {\n      \"name\":\"МФУ Kyocera Ecosys M2040dn (1102S33NL0)\",\n      \"price\":\"14 060 грн\",\n      \"availability\":\"Доступен\",\n      \"code\": \"Код: 1122990\"\n    },\n    {\n      \"name\":\"Bluetooth-адаптер Baseus Bluetooth Qiyin AUX Black (WXQY-01)\",\n      \"price\":\"277 грн\",\n      \"availability\":\"В наличии\",\n      \"code\": \"Код: 1205257\"\n    },\n    {\n      \"name\":\"Штатив VELBON EX-323 Mini\",\n      \"price\":\"1 004 грн\",\n      \"availability\":\"Доступен\",\n      \"code\": \"Код: 24567\"\n    }\n  ]}";
        stubFor(get(urlEqualTo("/api/goods"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseGetMethodExpected)));

        String apiURL = String.format(URL, "api/goods");
        String responseGetMethodActual = RestAssured.given().log().all()
                .get(apiURL)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .assertThat()
                .header("Content-Type", ("application/json"))
                .extract()
                .body()
                .asString();
        return responseGetMethodActual;
    }

    protected String mockGoods(){
        return  "{\"goods\":[\n    {\n      \"name\":\"Видеокарта MSI PCI-E GeForce GTX1660 Super 6GB DDR6 (GTX 1660 SUPER VENTUS XS OC)\",\n      \"price\":\"10 370 грн\",\n      \"availability\":\"есть в наличии\",\n      \"code\": \"Код:1006124\"\n    }]}";
    }
}