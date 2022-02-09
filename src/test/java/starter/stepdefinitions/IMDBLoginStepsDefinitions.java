package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import starter.navigation.LoginAsUser;
import starter.navigation.NavigateTo;
import starter.user_interface.imdbLoginPageElements;

public class IMDBLoginStepsDefinitions {
    Actor actor = Actor.named("kemal");
    @Managed
    WebDriver webDriver;

    @Given("user launch browser and open the main page")
    public void user_launch_browser_and_open_the_main_page() {
        actor.can(BrowseTheWeb.with(webDriver));
        actor.attemptsTo(NavigateTo.theIMDBaHomePage());
    }
    @When("user logged in IMDB")
    public void user_logged_in_ımdb() {
        actor.attemptsTo(LoginAsUser.loginThePage("kemal","rajeevraj4184@gmail.com","tester97"));
    }
    @Then("user logged in successfully")
    public void user_logged_in_successfully() {
        actor.attemptsTo(Ensure.that(imdbLoginPageElements.IMG_FIELD).isDisplayed());
    }

}
