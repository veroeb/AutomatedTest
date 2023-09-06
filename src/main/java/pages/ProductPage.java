package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{
    private String price;
    //==============LOCATORS==================
    @FindBy(css = ".ui-pdp-buybox__quantity__available")
    private WebElement quantity;
    @FindBy(css = "#price .ui-pdp-price__second-line .andes-money-amount__fraction")
    private WebElement phonePrice;
    @FindBy(css = ".ui-pdp-actions__container .ui-pdp-action--primary")
    private WebElement buyNow;
    //========================================

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage(WebDriver driver, String price) {
        super(driver);
        this.price = price;
    }

    public Boolean verifySamePrice(){
        /**
         * Verifies that the price stays consistent throughout the flow
         */
        String productPrice =  getWait().until(ExpectedConditions.visibilityOf(phonePrice)).getText();
        int p = Integer.parseInt(productPrice.replaceAll("[^0-9]", ""));
        return p == Integer.parseInt(price.replaceAll("[^0-9]", ""));
    }

    public int verifyOneItemAvailable(){
        /**
         * Verifies that there's at least one item available
         */
        String productQuantityText =  getWait().until(ExpectedConditions.visibilityOf(quantity)).getText();
        return Integer.parseInt(productQuantityText.replaceAll("[^0-9]", ""));
    }

    public LoginPage clickBuyNow(){
        /**
         * Clicks on the Comprar Ahora button
         * return: new LoginPage
         */
        scrollToElement(buyNow);
        getWait().until(ExpectedConditions.elementToBeClickable(buyNow)).click();
        return new LoginPage(this.getDriver());
    }
}
