package starter.user_interface;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class imdbFilmPageElement {
    public static Target TOPFILMS_HEADER = Target.the("Top Films Header").locatedBy("//h1[contains(text(),'IMDb Top 250 Movies')]");
    public static Target FILM_TITLE= Target.the("Film Title").located(By.xpath("//h1[contains(text(),'The Shawshank Redemption')]"));
}
