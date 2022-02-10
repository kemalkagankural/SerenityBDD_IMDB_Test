package starter.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    public WebDriver driver ;


    public BasePage(WebDriver driver){
        this.driver = driver ;
    }

    // Those are web driver methods.I create like that because I can use every where.
    public WebElement find(By locator){return driver.findElement(locator);}
    public List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }
}
