package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutInfoPage {
    private static final String FIRST_NAME_TEXTBOX = "//*[@id='first-name']";
    private static final String LAST_NAME_TEXTBOX = "//*[@id='last-name']";
    private static final String POST_CODE_TEXTBOX = "//*[@id='postal-code']";
    private static final String CONTINUE_BUTTON = "//*[contains(text(), 'CONTINUE')]|//*[@value='CONTINUE']";


    public void enterFirstName(String firstName) {
        $(By.xpath(FIRST_NAME_TEXTBOX)).shouldBe(Condition.visible).setValue(firstName);
        Selenide.sleep(2000);
    }
    public void enterLastName(String lastName) {
        $(By.xpath(LAST_NAME_TEXTBOX)).shouldBe(Condition.visible).setValue(lastName);
        Selenide.sleep(2000);
    }
    public void enterPostCode(String postCode) {
        $(By.xpath(POST_CODE_TEXTBOX)).shouldBe(Condition.visible).setValue(postCode);
        Selenide.sleep(4000);
    }
    public void clickOnContinue() {
        $(By.xpath(CONTINUE_BUTTON)).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);
    }
}
