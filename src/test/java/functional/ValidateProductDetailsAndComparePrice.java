package functional;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import utils.TestUtils;

import static org.testng.AssertJUnit.assertEquals;

public class ValidateProductDetailsAndComparePrice extends BaseTest {
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    @Epic("Swag Labs")
    @Description("Validate navigation from product lister to product details page")
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
    @Epic("Swag Labs")
    @Story("Compare price of the product details page with product lister page")
    @Test
    public void validateProductDetails() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        comparePriceInPDPAndPlpForAnArticle("1");

    }
    @Step
    public void comparePriceInPDPAndPlpForAnArticle(String articleNumber) {

        HomePage homePage = new HomePage();
        String plpPrice=homePage.getTheItemPricePlp("1");
        homePage.clickOnItem("1");
        ProductDetailsPage productDetailsPage= new ProductDetailsPage();
        String pdpPrice=productDetailsPage.getTheItemPricePdp("1");
        assertEquals(pdpPrice,plpPrice);

    }
}
