package starter.navigation;

import net.serenitybdd.screenplay.Consequence;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import starter.helper.Helper;
import starter.page.BasePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFilmList extends BasePage {
    List<String> filmNames;
    public GetFilmList(WebDriver driver) {
        super(driver);
    }

    public void getAllFilmsName() throws IOException {
        filmNames = new ArrayList<>();
            for (int i=0;i<getAllFilmsNames().size();i++){
                filmNames.add((getAllFilmsNames().get(i).getText()+"-"+getAllFilmsNames().get(i).getAttribute("href")));
            }
        Helper.writeToCSV(String.valueOf(filmNames));
    }

    public List<WebElement> getAllFilmsNames(){
        By filmsNamesLocator = new By.ByCssSelector("td.titleColumn:nth-child(2) > a:nth-child(1)");
        return findAll(filmsNamesLocator);
    }
}
