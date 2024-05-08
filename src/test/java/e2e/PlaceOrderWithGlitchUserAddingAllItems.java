package e2e;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUtils;

import static org.testng.Assert.assertEquals;

public class PlaceOrderWithGlitchUserAddingAllItems extends BaseTest {
    private final String performance_glitch_user = TestUtils.getProperty("performance.glitch.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount = Integer.parseInt(TestUtils.getProperty("expected.ItemCount"));
    private final double expected_OrderPrice = Double.parseDouble(TestUtils.getProperty("expected.orderPrice"));
    private final String expected_CardDetails = TestUtils.getProperty("expected.cardDetails");
    private final String expected_shippingInfoDetailsActual = TestUtils.getProperty("expected.shippingDetails");
    private final String first_name = TestUtils.getProperty("first.name");
    private final String last_name = TestUtils.getProperty("last.name");
    private final String valid_address = TestUtils.getProperty("valid.address");
    @Epic("Swag Labs")
    @Description("Place order with all the items present with a glitch user")
    @Test
    public void addProductsToBasketGlitchUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(performance_glitch_user,valid_password);
        Selenide.sleep(4000);
        HomePage homePage=new HomePage();
        homePage.addToBasket("6");
        homePage.addToBasket("5");
        homePage.addToBasket("4");
        homePage.addToBasket("3");
        homePage.addToBasket("2");
        homePage.addToBasket("1");
        validateBasketCount();
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
        assertEquals(actualUiConfirmationText, expected_OrderConfirmationText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateOrderDispatched() {
        OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage();
        String actualUiDispatchedText = orderConfirmationPage.getOrderDispatchedMessage();
        assertEquals(actualUiDispatchedText, expected_OrderDispatchedText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateBasketCount() {
        HomePage homePage= new HomePage();
        int actualItemCount = homePage.getItemsInBasketCount();
        assertEquals(actualItemCount, expected_ItemCount,"Basket Count Mismatched for the user " +performance_glitch_user);
    }
    @Step
    public void validateOrderPrice() {
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        double orderPriceActual = checkoutOverviewPage.getOrderPriceDetailsText();
        assertEquals(orderPriceActual, expected_OrderPrice,"Order Price Mismatched for the user " +performance_glitch_user);
    }
    @Step
    public void validateShippingInfoDetails() {
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        String shippingInfoDetailsActual = checkoutOverviewPage.getShippingInfoDetailsText();
        assertEquals(shippingInfoDetailsActual, expected_shippingInfoDetailsActual,"Shipping Info Details Mismatched for the user " +performance_glitch_user);
    }
    @Step
    public void validateCardDetails() {
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        String cardDetailsActual = checkoutOverviewPage.getCardDetailsText();
        assertEquals(cardDetailsActual, expected_CardDetails,"Card Details Mismatched for the user " +performance_glitch_user);
    }
}
