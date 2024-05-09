package functional;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.KeywordManager;
import pages.LoginPage;
import utils.TestUtils;

import static org.testng.Assert.assertEquals;

public class UserAuthenticationTesting extends BaseTest {
    KeywordManager keywordManager= new KeywordManager();
    private final String standard_username = TestUtils.getProperty("standard.username");
    private final String valid_password = TestUtils.getProperty("valid.password");
    private final String performance_glitch_user = TestUtils.getProperty("performance.glitch.username");
    private final String locked_out_user = TestUtils.getProperty("locked.out.username");
    private final String problem_user = TestUtils.getProperty("problem.username");
    private final String invalid_password = TestUtils.getProperty("invalid.password");
    private final String blank_password = TestUtils.getProperty("blank.password");
    private final String blank_username = TestUtils.getProperty("blank.username");
    private final String invalid_username = TestUtils.getProperty("invalid.username");
    private final String error_message1 = TestUtils.getProperty("error.message1");
    private final String error_message2 = TestUtils.getProperty("error.message2");
    private final String error_message4 = TestUtils.getProperty("error.message4");
    private final String error_message5 = TestUtils.getProperty("error.message5");
    @Epic("Swag Labs")
    @Description("Unhappy path for login functionality")
    @Test(dataProvider = "userDataUnhappyPath")
    public void testLoginUnhappyPath(String username,String password,String expectedErrorMessage) {
        keywordManager.loginPage.login(username,password);
        Selenide.sleep(4000);
        uiMessageValidation(username,password,expectedErrorMessage);

    }
    @Epic("Swag Labs")
    @Description("Happy path for login functionality")
    @Test(dataProvider = "userDataHappyPath")
    public void testLoginHappyPath(String username,String password) {
        keywordManager.loginPage.login(username,password);
        Selenide.sleep(4000);

    }

    @Step
    public void uiMessageValidation(String username,String password,String expectedErrorMessage) {

       String actualUiErrorMessage= keywordManager.loginPage.getUIErrorText();
            assertEquals(actualUiErrorMessage, expectedErrorMessage,"Error message mismatch for username: " + username);
        }


    @DataProvider(name = "userDataUnhappyPath")
    public Object[][] userDataProviderUnhappyPath() {
        return new Object[][] {
                {locked_out_user, valid_password,error_message2},
                {blank_username, blank_password,error_message1},
                {invalid_username, valid_password,error_message4},
                {standard_username,blank_password,error_message5}
        };
    }
    @DataProvider(name = "userDataHappyPath")
    public Object[][] userDataProviderHappyPath() {
        return new Object[][] {
                {standard_username,valid_password},
                {problem_user, valid_password},
                {performance_glitch_user, valid_password},
        };
    }
}
