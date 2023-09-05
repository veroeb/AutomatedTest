package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void before(){
        driver = DriverFactory.initializeDriver();
    }

    @After
    public void after(){
        driver.quit();
    }
}
