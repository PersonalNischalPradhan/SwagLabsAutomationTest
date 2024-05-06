import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.*;
import utils.TestUtils;

import static org.testng.Assert.assertEquals;

public class VerifySortingFunctionality extends BaseTest{
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String expected_OrderConfirmationText = TestUtils.getProperty("expected.OrderConfirmationText");
    private final String expected_OrderDispatchedText = TestUtils.getProperty("expected.OrderDispatchedText");
    private final int expected_ItemCount = Integer.parseInt(TestUtils.getProperty("expected.ItemCount"));

    @Test
    public void addProductsToBasketStdUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        HomePage homePage=new HomePage();
        homePage.sortingFunction();
    }
}