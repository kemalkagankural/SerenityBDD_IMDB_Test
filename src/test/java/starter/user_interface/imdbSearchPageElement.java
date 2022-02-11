package starter.user_interface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class imdbSearchPageElement {
    public static Target  FIRSTRESULT_LINK = Target.the("first result link").located(By.xpath("//a[contains(text(),'The Shawshank Redemption')]"));
}
