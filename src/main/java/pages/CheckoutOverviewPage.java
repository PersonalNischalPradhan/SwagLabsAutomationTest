package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {
    private static final String FINISH_BUTTON = "//*[contains(text(), 'FINISH')]";
    public void clickOnFinishButton() {
        $(By.xpath(FINISH_BUTTON)).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);
    }
}
