package starter.user_interface;

import net.serenitybdd.screenplay.targets.Target;

public class imdbFilmPageElement {
    public static Target TOPFILMS_HEADER = Target.the("Top Films Header").locatedBy("//h1[contains(text(),'IMDb Top 250 Movies')]");
}
