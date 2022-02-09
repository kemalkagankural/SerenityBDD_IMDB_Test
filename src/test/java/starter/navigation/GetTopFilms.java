package starter.navigation;



import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.user_interface.imdbHomePageElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class GetTopFilms implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(imdbHomePageElement.MENU_FIELD, isClickable()),
                Click.on(imdbHomePageElement.MENU_FIELD),
                WaitUntil.the(imdbHomePageElement.TOPFILM_SECTON, isClickable()),
                Click.on(imdbHomePageElement.TOPFILM_SECTON)
        );
    }
    public static GetTopFilms getTopFilms() {return instrumented(GetTopFilms.class);
    }
}
