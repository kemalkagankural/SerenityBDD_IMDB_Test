package starter.navigation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import starter.user_interface.imdbHomePageElement;
import starter.user_interface.imdbLoginPageElements;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoginAsUser implements Task {
    private final String username;
    private final String email_text;
    private final String password_text;

    @Step("{0} Login with email '#email_text' and password '#password_text'")
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(imdbHomePageElement.LOGIN_BTN, isClickable()),
                Click.on(imdbHomePageElement.LOGIN_BTN),
                WaitUntil.the(imdbLoginPageElements.CREATENEW_BTN,isClickable()).forNoMoreThan(2).seconds(),
                Click.on(imdbLoginPageElements.CREATENEW_BTN),
                WaitUntil.the(imdbLoginPageElements.USERNAME_FIELD,isClickable()).forNoMoreThan(2).seconds(),
                SendKeys.of(this.username).into(imdbLoginPageElements.USERNAME_FIELD),
                Click.on(imdbLoginPageElements.EMAIL_FIELD),
                SendKeys.of(this.email_text).into(imdbLoginPageElements.EMAIL_FIELD),
                Click.on(imdbLoginPageElements.PASSWORD_FIELD),
                SendKeys.of(this.password_text).into(imdbLoginPageElements.PASSWORD_FIELD),
                Click.on(imdbLoginPageElements.REPASSWORD_FIELD),
                SendKeys.of(this.password_text).into(imdbLoginPageElements.REPASSWORD_FIELD),
                Click.on(imdbLoginPageElements.CREATE_BTN)
        );
    }
    public LoginAsUser(String username,String email_text, String password_text) {
        this.username=username;
        this.email_text = email_text;
        this.password_text = password_text;
    }
    public static LoginAsUser loginThePage(String username,String email_text, String password_text) {
        return instrumented(LoginAsUser.class,username,email_text,password_text);
    }
}
