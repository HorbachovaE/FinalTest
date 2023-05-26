package tests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import setup.Helper;
import pages.Item;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class MockingTest {
    private static final String HOST = "localhost";
    private static final int PORT = 8888;
    private static final String URL = String.format("http://%s:%d/%s", HOST, PORT, "%s");
    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(PORT);

    @BeforeTest
    public void setUpWireMockServer() {
        System.out.println("Start server");
        WIRE_MOCK_SERVER.start();
        WireMock.configureFor(HOST, PORT);
    }

    @AfterTest(alwaysRun = true)
    public void stopWireMockServer() {
        if (WIRE_MOCK_SERVER.isRunning()) {
            System.out.println("Shot Down");
            WIRE_MOCK_SERVER.stop();
        }
    }


    @Test
    public void GetAllItemsFromRequest() {
        SoftAssert softAssert = new SoftAssert();

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

        softAssert.assertEquals(responseGetMethodActual, responseGetMethodExpected);

    }
}


