package starter.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import starter.helper.Helper;
import starter.page.BasePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFilmList extends BasePage {
    List<String> storesNames;
    public GetFilmList(WebDriver driver) {
        super(driver);
    }

    public void getAllFilmsName() throws IOException {
        storesNames = new ArrayList<>();
            for (int i=0;i<getAllFilmsNames().size();i++){
                storesNames.add(getAllFilmsNames().get(i).getText()+"-"+getAllFilmsNames().get(i).getAttribute("href"));
            }
        Helper.writeToCSV(String.valueOf(storesNames));
    }

    public List<WebElement> getAllFilmsNames(){
        By filmsNamesLocator = new By.ByCssSelector("td.titleColumn:nth-child(2) > a:nth-child(1)");
        return findAll(filmsNamesLocator);
    }
}
