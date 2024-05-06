package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.lang.String.format;

public class HomePage {
    private static final String BASKET_ICON_XPATH = "//*[@data-icon='shopping-cart']";
    private static final String SORTING_DROPDOWN_XPATH = "//*[@class='product_sort_container']";
    private static final String OPEN_MENU_XPATH = "//*[contains(text(), 'Open Menu')]";
    private static final String ADD_TO_BASKET_BUTTON_XPATH = "(//*[contains(text(),'ADD TO CART')])[%s]";
    private static final String LOGOUT_BUTTON_XPATH = "//*[@id='logout_sidebar_link']";
    private static final String SHOPPING_CART_COUNTER = "//*[@id='shopping_cart_container']/a/span";
    private static final String CLICK_ON_ITEM_NAME = "(//*[@class='inventory_item_name'])[%s]";
    private static final String ITEM_PRICE_PLP = "(//*[@class='inventory_item_price'])[%s]";

    public void clickOnBasketIcon() {
        $(By.xpath(BASKET_ICON_XPATH)).shouldBe(Condition.visible).click();
    }

    public void addToBasket(String articleNumberNotAdded) {
        String xpathAddToBasket = String.format(ADD_TO_BASKET_BUTTON_XPATH, articleNumberNotAdded);
        $(By.xpath(format(ADD_TO_BASKET_BUTTON_XPATH, articleNumberNotAdded))).shouldBe(Condition.visible).click();
        System.out.println("the xpath formed is " + xpathAddToBasket);
        System.out.println("Basket clicked  " + articleNumberNotAdded);
        Selenide.sleep(2000);
    }

    public void clickOnOpenMenuButton() {
        $(By.xpath(OPEN_MENU_XPATH)).shouldBe(Condition.visible).click();
    }

    public void clickOnLogOutButton() {
        $(By.xpath(LOGOUT_BUTTON_XPATH)).shouldBe(Condition.visible).click();
    }

    public void handlePopUp() {
        String alertMessage = Selenide.switchTo().alert().getText();
        System.out.println("the alert message is " + alertMessage);
        switchTo().alert().accept();

    }

    public int getItemsInBasketCount() {
        String counterString = $(By.xpath(SHOPPING_CART_COUNTER)).shouldBe(Condition.visible).getText();
        return Integer.parseInt(counterString);
    }

    public void clickOnItem(String articleNumber) {
        String xpathClickOnItem = String.format(CLICK_ON_ITEM_NAME, articleNumber);
        $(By.xpath(format(CLICK_ON_ITEM_NAME, articleNumber))).shouldBe(Condition.visible).click();
        System.out.println("the xpath formed is " + xpathClickOnItem);
        System.out.println("Item clicked  " + articleNumber);
        Selenide.sleep(2000);
    }

    public String getTheItemPricePlp(String articleNumber) {
        String xpathClickOnItem = String.format(ITEM_PRICE_PLP, articleNumber);
        String itemPricePlp = $(By.xpath(format(ITEM_PRICE_PLP, articleNumber))).shouldBe(Condition.visible).getText();
        System.out.println("the xpath formed is " + xpathClickOnItem);
        System.out.println("Item clicked  " + articleNumber);
        System.out.println("Item PLP Price is  " + itemPricePlp);
        Selenide.sleep(2000);
        return itemPricePlp;
    }

    public void sortingFunction(String value) {
        $(By.xpath(SORTING_DROPDOWN_XPATH)).shouldBe(Condition.visible).click();
        Selenide.sleep(3000);
        switch (value) {
            case "az":
            case "za":
            case "lohi":
            case "hilo":
                $(By.xpath(SORTING_DROPDOWN_XPATH)).selectOptionByValue(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting value: " + value);
        }
    }
}
