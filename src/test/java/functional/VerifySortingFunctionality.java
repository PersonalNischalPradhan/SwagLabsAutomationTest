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
    KeywordManager keywordManager= new KeywordManager();
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String sorting_aToz = TestUtils.getProperty("sorting.aToz");
    private final String sorting_zToa = TestUtils.getProperty("sorting.zToa");
    private final String sorting_lohi = TestUtils.getProperty("sorting.lohi");
    private final String sorting_hilo = TestUtils.getProperty("sorting.hilo");
    @Epic("Swag Labs")
    @Description("Validate sorting functionality in the home page")
    @Test
    public void validateSorting() {
        keywordManager.loginPage.login(standard_username, valid_password);
        Selenide.sleep(4000);
        compareTwoItemsNameList();
        compareTwoItemsPriceList();
    }
    @Step
    public void compareTwoItemsNameList(){
        // Get items list for AZ
        keywordManager.homePage.sortingFunction(sorting_aToz);
        List<String> aToZList = keywordManager.homePage.getItemListForAtoZ();

        // Get items list for ZA
        keywordManager.homePage.sortingFunction(sorting_zToa);
        List<String> zToAList = keywordManager.homePage.getItemListForZtoA();

        // Assert that the two lists are equal
        Assert.assertEquals(aToZList, zToAList, "Lists are not equal");
    }
    @Step
    public void compareTwoItemsPriceList(){
        // Get items list for Low to high
        keywordManager.homePage.sortingFunction(sorting_lohi);
        List<String> lowToHighList = keywordManager.homePage.getItemListForPriceLowToHigh();

        // Get items list for high ToLow
        keywordManager.homePage.sortingFunction(sorting_hilo);
        List<String> highToLowList = keywordManager.homePage.getItemListForPriceHighToLow();

        // Assert that the two lists are equal
        Assert.assertEquals(lowToHighList, highToLowList, "Lists are not equal");
    }
}
