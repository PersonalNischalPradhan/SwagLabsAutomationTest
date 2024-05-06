package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class YourCartPage {
    private static final String CHECKOUT_BUTTON_XPATH = "//*[contains(text(), 'CHECKOUT')]";


    public void clickOnCheckoutButton() {
        $(By.xpath(CHECKOUT_BUTTON_XPATH)).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);
    }
}
