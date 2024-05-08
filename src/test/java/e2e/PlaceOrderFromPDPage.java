package e2e;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUtils;

import static org.testng.AssertJUnit.assertEquals;


public class PlaceOrderFromPDPage extends BaseTest {
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
    @Description("Place order from Product Details Page")
    @Test
    public void placeOrderFromProductDetails() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        HomePage homePage = new HomePage();
        homePage.clickOnItem("1");
        ProductDetailsPage productDetailsPage= new ProductDetailsPage();
        productDetailsPage.addToBasketFromPdp("1");
        validateBasketCountPdp();
        Selenide.sleep(2000);
        homePage.clickOnBasketIcon();
        YourCartPage yourCartPage= new YourCartPage();
        yourCartPage.clickOnCheckoutButton();
        CheckoutInfoPage checkoutInfoPage=new CheckoutInfoPage();
        checkoutInfoPage.enterFirstName(first_name);
        checkoutInfoPage.enterLastName(last_name);
        checkoutInfoPage.enterPostCode(valid_address);
        checkoutInfoPage.clickOnContinue();
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        validateOrderPrice();
        validateCardDetails();
        validateShippingInfoDetails();
        checkoutOverviewPage.clickOnFinishButton();
        validateOrderConfirmationText();
        validateOrderDispatched();

    }

    @Step
    public void validateOrderConfirmationText() {
        OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage();
        String actualUiConfirmationText = orderConfirmationPage.getOrderConfirmationMessage();
        Assert.assertEquals(actualUiConfirmationText, expected_OrderConfirmationText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateOrderDispatched() {
        OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage();
        String actualUiDispatchedText = orderConfirmationPage.getOrderDispatchedMessage();
        Assert.assertEquals(actualUiDispatchedText, expected_OrderDispatchedText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateBasketCountPdp() {
        HomePage homePage= new HomePage();
        int actualItemCount = homePage.getItemsInBasketCount();
        Assert.assertEquals(actualItemCount, expected_ItemCount_Pdp,"Basket Count Mismatched for the user " +standard_username);
    }
    @Step
    public void validateOrderPrice() {
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        double orderPriceActual = checkoutOverviewPage.getOrderPriceDetailsText();
        Assert.assertEquals(orderPriceActual, expected_OrderPrice_Pdp,"Order Price Mismatched for the user " +standard_username);
    }
    @Step
    public void validateShippingInfoDetails() {
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        String shippingInfoDetailsActual = checkoutOverviewPage.getShippingInfoDetailsText();
        Assert.assertEquals(shippingInfoDetailsActual, expected_shippingInfoDetailsActual,"Shipping Info Details Mismatched for the user " +standard_username);
    }
    @Step
    public void validateCardDetails() {
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        String cardDetailsActual = checkoutOverviewPage.getCardDetailsText();
        Assert.assertEquals(cardDetailsActual, expected_CardDetails,"Card Details Mismatched for the user " +standard_username);
    }

}
