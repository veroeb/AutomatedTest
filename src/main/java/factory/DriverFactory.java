package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver driver;

    public static WebDriver initializeDriver(){
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().driverVersion("116.0.5845.96").setup();
//        driver = new ChromeDriver();
//        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
