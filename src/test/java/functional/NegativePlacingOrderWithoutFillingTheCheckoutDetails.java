package functional;

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

public class NegativePlacingOrderWithoutFillingTheCheckoutDetails extends BaseTest {
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount = Integer.parseInt(TestUtils.getProperty("expected.ItemCount"));
    @Epic("Swag Labs")
    @Description("Place order without filling the checkout details")
    @Test
    public void placingOrderWithoutFillingTheCheckoutDetails() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username,valid_password);
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
        checkoutInfoPage.enterFirstName("");
        checkoutInfoPage.enterLastName("");
        checkoutInfoPage.enterPostCode("");
        checkoutInfoPage.clickOnContinue();
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
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
        assertEquals(actualItemCount, expected_ItemCount,"Basket Count Mismatched for the user " +standard_username);
    }
}
