package starter.user_interface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class imdbLoginPageElements {
    public static Target CREATENEW_BTN= Target.the("Create new account button").locatedBy("//a[contains(text(),'Create a New Account')]");
    public static Target USERNAME_FIELD= Target.the("Username field").located(By.id("ap_customer_name"));
    public static Target EMAIL_FIELD= Target.the("Email field").located(By.id("ap_email"));
    public static Target PASSWORD_FIELD= Target.the("Password field").located(By.id("ap_password"));
    public static Target REPASSWORD_FIELD= Target.the("Password field").located(By.id("ap_password_check"));
    public static Target CREATE_BTN= Target.the("Create button").locatedBy("//input[@id='continue']");
    public static Target PUZZLE_BTN = Target.the("Puzzle button").located(By.id("home_children_button"));
    public static Target IMG_FIELD = Target.the("Image field").located(By.tagName("img"));

}
