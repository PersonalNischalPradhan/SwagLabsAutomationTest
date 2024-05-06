package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static final String USERNAME_FIELD_XPATH = "//*[@id='user-name']";
    private static final String PASSWORD_FIELD_XPATH =  "//*[@id='password']";
    private static final String LOGIN_BUTTON_XPATH = "//*[@id='login-button']";
    private static final String UI_LOGIN_ERROR_MESSAGE =  "//*[contains(text(), 'Epic sadface')]";


    public void login(String username, String password) {
        $(By.xpath(USERNAME_FIELD_XPATH)).setValue(username);
        $(By.xpath(PASSWORD_FIELD_XPATH)).setValue(password);
        $(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }
    public String getUIErrorText() {
        if ( $(By.xpath(UI_LOGIN_ERROR_MESSAGE)).exists()) {
            String errorMessageText = $(By.xpath(UI_LOGIN_ERROR_MESSAGE)).getText();
            System.out.println("The error message is " + errorMessageText);
        } else{
            System.out.println("Login is successful ");
        }
        return $(By.xpath(UI_LOGIN_ERROR_MESSAGE)).getText();
    }
}

