package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OrderConfirmationPage {
    private static final String THANK_YOU_FOR_YOUR_ORDER_TEXT_XPATH = "//*[@id='checkout_complete_container']/h2";
    private static final String ORDER_DISPATCHED_TEXT_XPATH = "//*[@id='checkout_complete_container']/div";
    public String getOrderConfirmationMessage() {
       String orderConfirmationText= $(By.xpath(THANK_YOU_FOR_YOUR_ORDER_TEXT_XPATH)).shouldBe(Condition.visible).getText();
        Selenide.sleep(2000);
        return  orderConfirmationText;
    }
    public String getOrderDispatchedMessage() {
        String orderDispatchedText= $(By.xpath(ORDER_DISPATCHED_TEXT_XPATH)).shouldBe(Condition.visible).getText();
        Selenide.sleep(2000);
        return  orderDispatchedText;
    }
}
