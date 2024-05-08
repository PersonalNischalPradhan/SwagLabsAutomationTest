package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {
    private static final String FINISH_BUTTON = "//*[contains(text(), 'FINISH')]";
    private static final String CANCEL_BUTTON = "//*[@class='cart_cancel_link btn_secondary']";
    private static final String CARD_DETAILS_TEXT = "(//*[@class='summary_value_label'])[1]";
    private static final String SHIPPING_INFORMATION_TEXT = "(//*[@class='summary_value_label'])[2]";
    private static final String TOTAL_ORDER_PRICE_TEXT = "//*[@class='summary_total_label']";
    public void clickOnFinishButton() {
        $(By.xpath(FINISH_BUTTON)).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);
    }
    public void clickOnCancelCartButton() {
        $(By.xpath(CANCEL_BUTTON)).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);
    }
    public String getCardDetailsText() {
        String cardDetailsInfo=$(By.xpath(CARD_DETAILS_TEXT)).shouldBe(Condition.visible).getText();
        String[] cardDetails = cardDetailsInfo.split("#");  //o/p reference SauceCard #31337
        String cardDetailNumber = cardDetails[1];
        Selenide.sleep(2000);
        System.out.println("Card number is "+ cardDetailNumber);
        return cardDetailNumber;
    }
    public String getShippingInfoDetailsText() {
        String shippingInfoDetailsInfo=$(By.xpath(SHIPPING_INFORMATION_TEXT)).shouldBe(Condition.visible).getText();
        System.out.println("Shipping info details info is "+ shippingInfoDetailsInfo);
        Selenide.sleep(2000);
        return shippingInfoDetailsInfo;
    }
    public double getOrderPriceDetailsText() {
        String orderPriceDetailsInfo=$(By.xpath(TOTAL_ORDER_PRICE_TEXT)).shouldBe(Condition.visible).getText();
        String[] price = orderPriceDetailsInfo.split("\\$");  //o/p reference Total: $8.63
        String orderPrice = price[1];
        double exactOrderPrice = Double.parseDouble(orderPrice);
        System.out.println("Order Price is "+ exactOrderPrice);
        Selenide.sleep(2000);
        return exactOrderPrice;
    }

}
