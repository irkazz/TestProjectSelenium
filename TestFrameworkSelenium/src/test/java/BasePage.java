import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;
    protected final StringBuilder baseUrl = new StringBuilder().append("https://ultimateqa.com/");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
