package e2e;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.bouncycastle.jcajce.provider.symmetric.Serpent;
import org.checkerframework.checker.units.qual.K;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUtils;

import static org.testng.AssertJUnit.assertEquals;


public class PlaceOrderFromPDPage extends BaseTest {
    KeywordManager keywordManager= new KeywordManager();
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount_Pdp = Integer.parseInt(TestUtils.getProperty("expected.ItemCount.Pdp"));
    private final double expected_OrderPrice_Pdp = Double.parseDouble(TestUtils.getProperty("expected.orderPrice.pdp"));
    private final String expected_CardDetails = TestUtils.getProperty("expected.cardDetails");
    private final String expected_shippingInfoDetailsActual = TestUtils.getProperty("expected.shippingDetails");
    private final String first_name = TestUtils.getProperty("first.name");
    private final String last_name = TestUtils.getProperty("last.name");
    private final String valid_address = TestUtils.getProperty("valid.address");
    private final String article_number_1 = TestUtils.getProperty("article.number_1");
    @Description("Place order from Product Details Page with standard user")
    @Test
    public void placeOrderFromProductDetails() {

        keywordManager.loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        keywordManager.homePage = new HomePage();
        keywordManager.homePage.clickOnItem(article_number_1);
        keywordManager.productDetailsPage.addToBasketFromPdp(article_number_1);
        validateBasketCountPdp();
        Selenide.sleep(2000);
        keywordManager.homePage.clickOnBasketIcon();
        keywordManager.yourCartPage.clickOnCheckoutButton();
        keywordManager.checkoutInfoPage.enterFirstName(first_name);
        keywordManager.checkoutInfoPage.enterLastName(last_name);
        keywordManager.checkoutInfoPage.enterPostCode(valid_address);
        keywordManager.checkoutInfoPage.clickOnContinue();
        validateOrderPrice();
        validateCardDetails();
        validateShippingInfoDetails();
        keywordManager.checkoutOverviewPage.clickOnFinishButton();
        validateOrderConfirmationText();
        validateOrderDispatched();

    }

    @Step
    public void validateOrderConfirmationText() {
        String actualUiConfirmationText = keywordManager.orderConfirmationPage.getOrderConfirmationMessage();
        Assert.assertEquals(actualUiConfirmationText, expected_OrderConfirmationText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateOrderDispatched() {
        String actualUiDispatchedText = keywordManager.orderConfirmationPage.getOrderDispatchedMessage();
        Assert.assertEquals(actualUiDispatchedText, expected_OrderDispatchedText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateBasketCountPdp() {
        int actualItemCount = keywordManager.homePage.getItemsInBasketCount();
        Assert.assertEquals(actualItemCount, expected_ItemCount_Pdp,"Basket Count Mismatched for the user " +standard_username);
    }
    @Step
    public void validateOrderPrice() {
        double orderPriceActual = keywordManager.checkoutOverviewPage.getOrderPriceDetailsText();
        Assert.assertEquals(orderPriceActual, expected_OrderPrice_Pdp,"Order Price Mismatched for the user " +standard_username);
    }
    @Step
    public void validateShippingInfoDetails() {
        String shippingInfoDetailsActual = keywordManager.checkoutOverviewPage.getShippingInfoDetailsText();
        Assert.assertEquals(shippingInfoDetailsActual, expected_shippingInfoDetailsActual,"Shipping Info Details Mismatched for the user " +standard_username);
    }
    @Step
    public void validateCardDetails() {
        String cardDetailsActual = keywordManager.checkoutOverviewPage.getCardDetailsText();
        Assert.assertEquals(cardDetailsActual, expected_CardDetails,"Card Details Mismatched for the user " +standard_username);
    }

}
