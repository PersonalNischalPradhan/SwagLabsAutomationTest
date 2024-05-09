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

public class PlaceOrderWithProblemUserAddingAllItems extends BaseTest {
    KeywordManager keywordManager= new KeywordManager();
    private final String problem_user = TestUtils.getProperty("problem.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount = Integer.parseInt(TestUtils.getProperty("expected.ItemCount"));
    private final String first_name = TestUtils.getProperty("first.name");
    private final String last_name = TestUtils.getProperty("last.name");
    private final String valid_address = TestUtils.getProperty("valid.address");
    @Epic("Swag Labs")
    @Description("Place order with allowed items present with a problem user")
    @Test
    public void addProductsToBasketProblemUser() {
        keywordManager.loginPage.login(problem_user,valid_password);
        Selenide.sleep(4000);
        keywordManager.homePage.addToBasket("6");
        keywordManager.homePage.addToBasket("5");
        keywordManager.homePage.addToBasket("4");
        keywordManager.homePage.addToBasket("3");
        keywordManager.homePage.addToBasket("2");
        keywordManager.homePage.addToBasket("1");
        validateBasketCount();
        Selenide.sleep(2000);
        keywordManager.homePage.clickOnBasketIcon();
        keywordManager.yourCartPage.clickOnCheckoutButton();
        keywordManager.checkoutInfoPage.enterFirstName(first_name);
        keywordManager.checkoutInfoPage.enterLastName(last_name);
        keywordManager.checkoutInfoPage.enterPostCode(valid_address);
        keywordManager.checkoutInfoPage.clickOnContinue();
        keywordManager.checkoutOverviewPage.clickOnFinishButton();
        validateOrderConfirmationText();
        validateOrderDispatched();

    }
    @Step
    public void validateOrderConfirmationText() {
        String actualUiConfirmationText = keywordManager.orderConfirmationPage.getOrderConfirmationMessage();
        assertEquals(actualUiConfirmationText, expected_OrderConfirmationText,"Order Confirmation text  mismatched ");
    }
    @Step
    public void validateOrderDispatched() {
        String actualUiDispatchedText = keywordManager.orderConfirmationPage.getOrderDispatchedMessage();
        assertEquals(actualUiDispatchedText, expected_OrderDispatchedText,"Order Confirmation text  mismatched ");
    }
    //Here I am deliberately trying to fail the test cases to prove that there is a mismatch in the basket count due to a problematic user.
    @Step
    public void validateBasketCount() {
        int actualItemCount = keywordManager.homePage.getItemsInBasketCount();
        assertEquals(actualItemCount, expected_ItemCount,"Basket Count Mismatched for the user " +problem_user);
    }

}
