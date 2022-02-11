package starter.stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import starter.helper.Helper;
import starter.navigation.NavigateTo;
import starter.navigation.Search;
import starter.user_interface.imdbFilmPageElement;



public class ImdbSearchFilmStepDefinitions {
    Actor actor = Actor.named("kemal");
    @Managed
    WebDriver webDriver;

    @Given("user launch browser and open the ımdb main page")
    public void user_launch_browser_and_open_the_ımdb_main_page() {
        actor.can(BrowseTheWeb.with(webDriver));
        actor.attemptsTo(NavigateTo.theIMDBaHomePage());
    }

    @When("user search  a film")
    public void userSearchAFilm()  {
        actor.attemptsTo(Search.search("The Shawshank Redemption"));
        Helper.waitFor(1);
    }

    @Then("user should see the film review")
    public void userShouldSeeTheFilmReview() {
        actor.attemptsTo(Ensure.that(imdbFilmPageElement.FILM_TITLE).isDisplayed());
    }
}
