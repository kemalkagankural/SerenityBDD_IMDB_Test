package starter.navigation;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.user_interface.imdbFilmPageElement;
import starter.user_interface.imdbHomePageElement;
import starter.user_interface.imdbSearchPageElement;


import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class Search implements Task {

    private static String searchTerm;

    @Override
    public <T extends Actor> void performAs(T actor) {
       actor.attemptsTo(
               WaitUntil.the(imdbHomePageElement.SEARCH_BOX, isClickable()),
               Click.on(imdbHomePageElement.SEARCH_BOX),
               SendKeys.of(searchTerm).into(imdbHomePageElement.SEARCH_BOX),
               WaitUntil.the(imdbHomePageElement.SEARCH_BTN, isClickable()),
               Click.on(imdbHomePageElement.SEARCH_BTN),
               WaitUntil.the(imdbSearchPageElement.FIRSTRESULT_LINK, isClickable()),
               Click.on(imdbSearchPageElement.FIRSTRESULT_LINK)

       );
    }
    public Search(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    public static Search search(String searchTerm) {return instrumented(Search.class,searchTerm);}
}
