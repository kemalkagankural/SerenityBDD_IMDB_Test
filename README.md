# Getting started with Serenity and Cucumber

Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.

The latest version of Serenity supports Cucumber 6.x.

## The starter project
The best place to start with Serenity and Cucumber is to clone or download the starter project on Github ([https://github.com/serenity-bdd/serenity-cucumber-starter](https://github.com/serenity-bdd/serenity-cucumber-starter)). This project gives you a basic project setup, along with some sample tests and supporting classes. There are two versions to choose from. The master branch uses a more classic approach, using action classes and lightweight page objects, whereas the **[screenplay](https://github.com/serenity-bdd/serenity-cucumber-starter/tree/screenplay)** branch shows the same sample test implemented using Screenplay.

### The project directory structure
The project has build scripts for both Maven , and follows the standard directory structure used in most Serenity projects:
```Gherkin
src
  + main
  + test
    + java                        Test runners and supporting code
    + resources
      + features                  Feature files
        + ımdb                  Feature file subdirectories
             ımdb_login.feature
```

Serenity 2.2.13 introduced integration with WebdriverManager to download webdriver binaries.

## The sample scenario
Both variations of the sample project uses the sample Cucumber scenario. In this scenario, Sergey (who likes to search for stuff) is performing a search on the internet:

```Gherkin
Feature: Login ımdb

  Scenario: Login imdb
    Given User launch browser and open the main page
    When User logged in IMDB
    Then User logged in successfully
```

### The Screenplay implementation
The sample code in the master branch uses the Screenplay pattern. The Screenplay pattern describes tests in terms of actors and the tasks they perform. Tasks are represented as objects performed by an actor, rather than methods. This makes them more flexible and composable, at the cost of being a bit more wordy. Here is an example:
```java
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
```

Screenplay classes emphasise reusable components and a very readable declarative style, whereas Lean Page Objects and Action Classes (that you can see further down) opt for a more imperative style.

The `NavigateTo` class is responsible for opening the Wikipedia home page:
```java
public class NavigateTo {
    public static Performable theIMDBaHomePage() {
        return Task.where("{0} opens the IMDB home page",
                Open.browserOn().the(IMDBHomePage.class));
    }
}
```

The `Login As User` class does the actual login:
```java
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

```

In Screenplay, we keep track of locators in light weight page or component objects, like this one:
```java
IMDB Home Page Elements
------------------------
public class imdbHomePageElement {
    public static Target LOGIN_BTN=Target.the("Login button").locatedBy("//body/div[@id='__next']/nav[@id='imdbHeader']/div[2]/div[5]/a[1]");
}
------------------------
IMDB Login Page Elements
------------------------
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
```

The Screenplay DSL is rich and flexible, and well suited to teams working on large test automation projects with many team members, and who are reasonably comfortable with Java and design patterns. 

## Generating the reports
Since the Serenity reports contain aggregate information about all of the tests, they are not generated after each individual test (as this would be extremenly inefficient). Rather, The Full Serenity reports are generated by the `serenity-maven-plugin`. You can trigger this by running `mvn serenity:aggregate` from the command line or from your IDE.

They reports are also integrated into the Maven build process: the following code in the `pom.xml` file causes the reports to be generated automatically once all the tests have completed when you run `mvn verify`?

```
             <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.maven.version}</version>
                <configuration>
                    <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## Simplified WebDriver configuration and other Serenity extras
The sample projects both use some Serenity features which make configuring the tests easier. In particular, Serenity uses the `serenity.conf` file in the `src/test/resources` directory to configure test execution options.  
### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
    driver = chrome
}
headless.mode = true

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```

Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.


## Want to learn more?
For more information about Serenity BDD, you can read the [**Serenity BDD Book**](https://serenity-bdd.github.io/theserenitybook/latest/index.html), the official online Serenity documentation source. Other sources include:
* **[Learn Serenity BDD Online](https://expansion.serenity-dojo.com/)** with online courses from the Serenity Dojo Training Library
* **[Byte-sized Serenity BDD](https://www.youtube.com/channel/UCav6-dPEUiLbnu-rgpy7_bw/featured)** - tips and tricks about Serenity BDD
* For regular posts on agile test automation best practices, join the **[Agile Test Automation Secrets](https://www.linkedin.com/groups/8961597/)** groups on [LinkedIn](https://www.linkedin.com/groups/8961597/) and [Facebook](https://www.facebook.com/groups/agiletestautomation/)
* [**Serenity BDD Blog**](https://johnfergusonsmart.com/category/serenity-bdd/) - regular articles about Serenity BDD
