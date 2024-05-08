package functional;

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

import java.util.List;

import static org.testng.Assert.assertEquals;

public class VerifySortingFunctionality extends BaseTest {
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    @Epic("Swag Labs")
    @Description("Validate sorting functionality in the product details page")
    @Test
    public void validateSorting() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        compareTwoItemsNameList();
        compareTwoItemsPriceList();
    }
    @Step
    public void compareTwoItemsNameList(){
        HomePage homePage=new HomePage();
        // Get items list for AZ
        homePage.sortingFunction("az");
        List<String> aToZList = homePage.getItemListForAtoZ();

        // Get items list for ZA
        homePage.sortingFunction("za");
        List<String> zToAList = homePage.getItemListForZtoA();

        // Assert that the two lists are equal
        Assert.assertEquals(aToZList, zToAList, "Lists are not equal");
    }
    @Step
    public void compareTwoItemsPriceList(){
        HomePage homePage=new HomePage();
        // Get items list for Low to high
        homePage.sortingFunction("lohi");
        List<String> lowToHighList = homePage.getItemListForPriceLowToHigh();

        // Get items list for high ToLow
        homePage.sortingFunction("hilo");
        List<String> highToLowList = homePage.getItemListForPriceHighToLow();

        // Assert that the two lists are equal
        Assert.assertEquals(lowToHighList, highToLowList, "Lists are not equal");
    }
}
