package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class ProductDetailsPage {
    private static final String ITEM_PRICE_PDP = "//*[@class='inventory_details_price']";
    private static final String ADD_TO_BASKET_BUTTON_XPATH_PDP= "(//*[contains(text(),'ADD TO CART')])[%s]";


    public String getTheItemPricePdp(String articleNumber) {
        String xpathClickOnItem= String.format(ITEM_PRICE_PDP, articleNumber);
        String itemPricePdp= $(By.xpath(format(ITEM_PRICE_PDP, articleNumber))).shouldBe(Condition.visible).getText();
        System.out.println("the xpath formed is "+xpathClickOnItem);
        System.out.println("Item clicked  "+ articleNumber);
        System.out.println("Item PDP Price is  "+ itemPricePdp);
        Selenide.sleep(2000);
        return itemPricePdp;
    }
    public void addToBasketFromPdp(String articleNumber) {

        String xpathClickOnItem= String.format(ADD_TO_BASKET_BUTTON_XPATH_PDP, articleNumber);
        $(By.xpath(format(ADD_TO_BASKET_BUTTON_XPATH_PDP, articleNumber))).shouldBe(Condition.visible).click();
        System.out.println("the xpath formed is "+xpathClickOnItem);
        System.out.println("Item clicked  "+ articleNumber);
    }
}
