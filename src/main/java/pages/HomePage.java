package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage{
    //==============LOCATORS==================
    @FindBy(css = "#UY")
    private WebElement uruguayFlag;
    @FindBy(css = "input.nav-search-input")
    private WebElement searchBar;
    @FindBy(css = ".andes-pagination__button--next")
    private WebElement nextPage;
    @FindBy(css = ".shops__item-title")
    private List<WebElement> results;
    @FindBy(css = ".ui-search-price__part:not(s) .andes-money-amount__fraction")
    private List<WebElement> prices;
    //========================================

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void isElementPresent(String element){
        /**
         * Verifies if the element is present and closes it
         */
        List<WebElement> webElementList = null;
        if (element.equals("popUp"))
            webElementList = getDriver().findElements(By.cssSelector(".pop-content .pop-close-btn"));
        else if (element.equals("subscriptionPopUp"))
            webElementList = getDriver().findElements(By.cssSelector("iframe ~ div div ~ img"));
        if (!webElementList.isEmpty())
            webElementList.get(0).click();
    }

    public void accessUruguaySite() {
        /**
         * Clicks on the uruguayan flag to go to that website
         */
        try {
            Thread.sleep(1);
            getWait().until(ExpectedConditions.elementToBeClickable(uruguayFlag)).click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String isUruguayanSiteDisplayed() {
        /**
         * Verifies that the uruguayan MercadoLibre page is displayed
         */
        return getPageTitle();
    }

    public void searchOnSearchBar(String product){
        /**
         * Searches for a product in the search bar
         * param product: String.
         */
        getWait().until(ExpectedConditions.elementToBeClickable(searchBar)).click();
        searchBar.sendKeys(product);
        searchBar.submit();
    }

    public void clickNextResultsPage(){
        /**
         * Clicks on the next page button to see more results
         */
//        scrollToBottomOfPage();
        scrollToElement(nextPage);
        isElementIntercepted(nextPage);
    }

    public List<WebElement> getResults(){
        /**
         * Gets all the results for the product
         */
        return getWait().until(ExpectedConditions.visibilityOfAllElements(results));
    }

    public ProductPage clickFirstCard(List<WebElement> productsList){
        /**
         * Clicks on the first card of the results list
         * param productsList: List<WebElement>
         * return: new ProductPage
         */
        String phonePrice = getWait().until(ExpectedConditions.visibilityOfAllElements(prices)).get(0).getText();
        getWait().until(ExpectedConditions.elementToBeClickable(productsList.get(0))).click();
        return new ProductPage(this.getDriver(), phonePrice);
    }
}
