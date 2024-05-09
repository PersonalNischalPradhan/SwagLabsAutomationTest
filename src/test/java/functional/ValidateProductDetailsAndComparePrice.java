package functional;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.KeywordManager;
import pages.LoginPage;
import pages.ProductDetailsPage;
import utils.TestUtils;

import java.awt.image.Kernel;

import static org.testng.AssertJUnit.assertEquals;

public class ValidateProductDetailsAndComparePrice extends BaseTest {
    KeywordManager keywordManager= new KeywordManager();
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String article_number_1 = TestUtils.getProperty("article.number_1");
    @Epic("Swag Labs")
    @Description("Validate navigation from product lister to product details page")
    @Test
    public void navigateToProductDetails() {
        keywordManager.loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        keywordManager.homePage.getTheItemPricePlp(article_number_1);
        keywordManager.homePage.clickOnItem(article_number_1);
        keywordManager.productDetailsPage.getTheItemPricePdp(article_number_1);

    }
    @Epic("Swag Labs")
    @Story("Compare price of the product details page with product lister page")
    @Test
    public void validateProductDetails() {
        keywordManager.loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        comparePriceInPDPAndPlpForAnArticle();

    }
    @Step
    public void comparePriceInPDPAndPlpForAnArticle() {

        String plpPrice=keywordManager.homePage.getTheItemPricePlp(article_number_1);
        keywordManager.homePage.clickOnItem(article_number_1);
        String pdpPrice=keywordManager.productDetailsPage.getTheItemPricePdp(article_number_1);
        assertEquals(pdpPrice,plpPrice);

    }
}
