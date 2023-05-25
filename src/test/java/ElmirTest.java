import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.Elmir;
import setup.BaseTest;

public class ElmirTest extends BaseTest {
    @Test
    public void testCaseOne() {
       Elmir elmirPage = PageFactory.initElements(driver, Elmir.class);

       elmirPage.closeSuscribe();
       elmirPage.searchFind();
       elmirPage.getReturnedItemsNameAndPrice();
       elmirPage.getReturnedItemsNameAndAvailability();
       elmirPage.getReturnedItemsNameAndCode();




    }
}
