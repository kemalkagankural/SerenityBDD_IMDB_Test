package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import starter.navigation.GetFilmList;
import starter.navigation.GetTopFilms;
import starter.navigation.NavigateTo;
import starter.user_interface.imdbFilmPageElement;

import java.io.IOException;

public class ImdbGetTopFilmsStepDefinitions {
    GetFilmList getFilmList;
    Actor actor = Actor.named("kemal");
    @Managed
    WebDriver webDriver;

    @Given("User open IMDB main Page")
    public void user_open_ımdb_main_page() {
        actor.can(BrowseTheWeb.with(webDriver));
        actor.attemptsTo(NavigateTo.theIMDBaHomePage());
    }
    @Given("User click on Top Film link")
    public void user_click_on_top_film_link() {
        actor.attemptsTo(GetTopFilms.getTopFilms());
    }

    @Then("User should see IMDB Top films")
    public void user_should_see_ımdb_top_films() {
        actor.attemptsTo(Ensure.that(imdbFilmPageElement.TOPFILMS_HEADER).isDisplayed());
    }

    @Then("User get the list of films on CSV file")
    public void user_get_the_list_of_films_on_csv_file() throws IOException {
        getFilmList=new GetFilmList(webDriver);
        getFilmList.getAllFilmsName();
    }
}
