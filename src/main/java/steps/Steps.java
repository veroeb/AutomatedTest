package steps;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

import java.util.List;

public class Steps {
    private HomePage homePage;
    private List<WebElement> results;
    private ProductPage productPage;
    private LoginPage loginPage;

    @Given("I access MercadoLibre website")
    public void accessMercadoLibreWebpage() {
        /**
         * Access the MercadoLibre website
         */
        WebDriver driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        homePage.getUrl("https://www.mercadolibre.com/");
    }

    @When("I click on the Uruguayan flag")
    public void accessUruguaySite() {
        /**
         * Clicks on the uruguayan flag to go to that website
         */
        homePage.accessUruguaySite();
    }

    @Then("I can see the Uruguayan version of the website")
    public void isUruguayanSiteDisplayed() {
        /**
         * Verifies that the uruguayan MercadoLibre page is displayed
         */
        String title = homePage.isUruguayanSiteDisplayed();
        Assert.assertEquals(title, "Mercado Libre Uruguay - Envíos Gratis en el día", "The website is not displayed.");
    }

    @When("I search for {string} in the search bar")
    public void searchOnSearchBar(String product) {
        /**
         * Searches for a product in the search bar
         * param product: String.
         */
        homePage.searchOnSearchBar(product);
    }

    @When("I navigate to the next search results page")
    public void clickNextResultsPage() {
        /**
         * Clicks on the next page button to see more results
         */
        homePage.clickNextResultsPage();
    }

    @Then("I can see all search results")
    public void getResults() {
        /**
         * Gets all the results for the product
         */
        results = homePage.getResults();
        Assert.assertFalse(results.isEmpty(), "There are no results");
    }

    @When("I click on the first product card")
    public void clickFirstCard() {
        /**
         * Clicks on the first card of the results list
         */
        productPage = homePage.clickFirstCard(results);
    }

    @Then("I can verify that the price remains unchanged")
    public void verifySamePrice() {
        /**
         * Verifies that the price stays consistent throughout the flow
         */
        Assert.assertTrue(productPage.verifySamePrice(), "The price is not the same");
    }

    @Then("I can verify that there's at least one item available")
    public void verifyOneItemAvailable() {
        /**
         * Verifies that there's at least one item available
         */
        int quantity = productPage.verifyOneItemAvailable();
        Assert.assertTrue(quantity >= 1, "There's no stock");
    }

    @When("I click on Comprar Ahora")
    public void clickBuyNow() {
        /**
         * Clicks on the Comprar Ahora button
         */
        loginPage = productPage.clickBuyNow();
    }

    @Then("I can see the login page")
    public void isLoginPageDisplayed() {
        /**
         * Verifies that the login page is displayed
         */
        String title = loginPage.isLoginPageDisplayed();
        Assert.assertEquals(title, "¡Hola! Para comprar, ingresa a tu cuenta", "The website is not displayed.");
    }
}
