import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UltimateQAHomePage extends BasePage {
    private WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Search']"));

    public UltimateQAHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isVisible() {
        return searchBar.isDisplayed();
    }
}
