package pages;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String isLoginPageDisplayed() {
        /**
         * Verifies that the login page is displayed
         */
        return getPageTitle();
    }
}
