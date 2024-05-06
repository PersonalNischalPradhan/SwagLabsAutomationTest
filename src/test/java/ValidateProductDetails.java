import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import utils.TestUtils;

import static org.testng.AssertJUnit.assertEquals;

public class ValidateProductDetails extends BaseTest {
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");

    @Test
    public void navigateToProductDetails() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        HomePage homePage = new HomePage();
        homePage.getTheItemPricePlp("1");
        homePage.clickOnItem("1");
        ProductDetailsPage productDetailsPage= new ProductDetailsPage();
        productDetailsPage.getTheItemPricePdp("1");

    }

    @Test
    public void validateProductDetails() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        comparePriceInPDPAndPlpForAnArticle("1");

    }
    public void comparePriceInPDPAndPlpForAnArticle(String articleNumber) {

        HomePage homePage = new HomePage();
        String plpPrice=homePage.getTheItemPricePlp("1");
        homePage.clickOnItem("1");
        ProductDetailsPage productDetailsPage= new ProductDetailsPage();
        String pdpPrice=productDetailsPage.getTheItemPricePdp("1");
        assertEquals(pdpPrice,plpPrice);

    }
}
