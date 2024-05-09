package pages;

public class KeywordManager {
    public LoginPage loginPage;
    public HomePage homePage;
    public OrderConfirmationPage orderConfirmationPage;
    public ProductDetailsPage productDetailsPage;
    public YourCartPage yourCartPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public  CheckoutInfoPage checkoutInfoPage;
    public KeywordManager(){
      this.loginPage= new LoginPage();
      this.homePage=new HomePage();
      this.checkoutInfoPage= new CheckoutInfoPage();
      this.checkoutOverviewPage= new CheckoutOverviewPage();
      this.productDetailsPage= new ProductDetailsPage();
      this.yourCartPage= new YourCartPage();
      this.orderConfirmationPage= new OrderConfirmationPage();
    }
}
