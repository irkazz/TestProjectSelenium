import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final StringBuilder baseUrl = new StringBuilder().append("https://ultimateqa.com/");
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
