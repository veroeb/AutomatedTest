package pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebDriver getDriver(){
        return driver;
    }

    public WebDriverWait getWait(){
        return wait;
    }

    public void getUrl(String url){
        driver.get(url);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void isElementIntercepted(WebElement element){
        /**
         * If the element is intercepted, it will try to locate it again
         */
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
        }
        catch (ElementClickInterceptedException e){
            System.out.println("The element has been intercepted. It will retry.");
            getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }

    public void scrollToBottomOfPage(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void switchTabs(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }
}
