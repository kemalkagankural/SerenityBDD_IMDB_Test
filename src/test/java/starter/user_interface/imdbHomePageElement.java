package starter.user_interface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class imdbHomePageElement {
    public static Target LOGIN_BTN=Target.the("Login button").locatedBy("//body/div[@id='__next']/nav[@id='imdbHeader']/div[2]/div[5]/a[1]");
    public static Target MENU_FIELD=Target.the("Menu field").located(By.id("imdbHeader-navDrawerOpen--desktop"));
    public static Target TOPFILM_SECTON=Target.the("Top Films Secition").located(By.xpath("//body/div[@id='__next']/nav[@id='imdbHeader']/div[2]/aside[1]/div[1]/div[2]/div[1]/div[1]/span[1]/div[1]/div[1]/ul[1]/a[3]"));
    public static Target SEARCH_BOX=Target.the("Search box").located(By.xpath("//input[@id='suggestion-search']"));
    public static Target SEARCH_BTN=Target.the("Search button").located(By.id("suggestion-search-button"));
}
