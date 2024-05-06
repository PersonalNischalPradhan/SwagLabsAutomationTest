import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUtils;

import static org.testng.Assert.assertEquals;

public class PlaceOrderWithProblemUserAddingAllItems extends BaseTest{
    private final String problem_user = TestUtils.getProperty("problem.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount = Integer.parseInt(TestUtils.getProperty("expected.ItemCount"));

    @Test
    public void addProductsToBasketProblemUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(problem_user,valid_password);
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
        checkoutInfoPage.enterFirstName("test");
        checkoutInfoPage.enterLastName("testlast");
        checkoutInfoPage.enterPostCode("SO15EE");
        checkoutInfoPage.clickOnContinue();
        CheckoutOverviewPage checkoutOverviewPage= new CheckoutOverviewPage();
        checkoutOverviewPage.clickOnFinishButton();
        validateOrderConfirmationText();
        validateOrderDispatched();

    }

    public void validateOrderConfirmationText() {
        OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage();
        String actualUiConfirmationText = orderConfirmationPage.getOrderConfirmationMessage();
        assertEquals(actualUiConfirmationText, expected_OrderConfirmationText,"Order Confirmation text  mismatched ");
    }
    public void validateOrderDispatched() {
        OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage();
        String actualUiDispatchedText = orderConfirmationPage.getOrderDispatchedMessage();
        assertEquals(actualUiDispatchedText, expected_OrderDispatchedText,"Order Confirmation text  mismatched ");
    }
    public void validateBasketCount() {
        HomePage homePage= new HomePage();
        int actualItemCount = homePage.getItemsInBasketCount();
        assertEquals(actualItemCount, expected_ItemCount,"Basket Count Mismatched for the user " +problem_user);
    }

}
