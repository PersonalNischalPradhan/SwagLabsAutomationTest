package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;
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
    private static final String LIST_OF_INVENTORY_ITEM_NAME_XPATH = "//*[@class='inventory_item_name']";
    private static final String LIST_OF_INVENTORY_ITEM_PRICE_XPATH = "//*[@class='inventory_item_price']";
    private static final String HOME_PAGE_INVENTORY_ITEM_COUNT= "//*[@class='inventory_item']";
    private static final String LIST_HOME_PAGE_INVENTORY_ITEM_COUNT= "//*[@class='inventory_list']";

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
        Selenide.sleep(4000);
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
    public List<String> getItemListForAtoZ() {
        ArrayList<WebElement> initialList =new ArrayList<>($$(By.xpath(LIST_OF_INVENTORY_ITEM_NAME_XPATH)));
        // Print the text of each element in the list
        List<String> aToZList = new ArrayList<>();
        for (WebElement element : initialList) {
            aToZList.add(element.getText());
        }
        System.out.println(aToZList);
        return aToZList;
    }
    public List<String> getItemListForZtoA() {
        ArrayList<WebElement> initialList =new ArrayList<>($$(By.xpath(LIST_OF_INVENTORY_ITEM_NAME_XPATH)));
        // Print the text of each element in the list
        List<String> zToAList = new ArrayList<>();
        for (WebElement element : initialList) {
            zToAList.add(element.getText());
        }
        System.out.println("first time we get i.e actual result" +  zToAList);
       Collections.reverse(zToAList);
        System.out.println("Reverse Order for zToAList::" + zToAList);
        return zToAList;
    }
    public List<String> getItemListForPriceLowToHigh() {
        ArrayList<WebElement> initialList =new ArrayList<>($$(By.xpath(LIST_OF_INVENTORY_ITEM_PRICE_XPATH)));
        // Print the text of each element in the list
        List<String> lowToHighList = new ArrayList<>();
        for (WebElement element : initialList) {
            lowToHighList.add(element.getText());
        }
        System.out.println(lowToHighList);
        return lowToHighList;
    }
    public List<String> getItemListForPriceHighToLow() {
        ArrayList<WebElement> initialList =new ArrayList<>($$(By.xpath(LIST_OF_INVENTORY_ITEM_PRICE_XPATH)));
        // Print the text of each element in the list
        List<String> highToLowList = new ArrayList<>();
        for (WebElement element : initialList) {
            highToLowList.add(element.getText());
        }
        System.out.println("first time we get i.e actual result " +  highToLowList);
        Collections.reverse(highToLowList);
        System.out.println("Reverse Order for highToLowList::" + highToLowList);
        return highToLowList;
    }
}
