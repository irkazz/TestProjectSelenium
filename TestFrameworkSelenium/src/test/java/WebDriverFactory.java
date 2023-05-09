import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    //TODO: Move to separate project once I figure out path to the driver
    WebDriver driver;

    public WebDriver createWD(BrowserType browser) {
        if (browser == BrowserType.chrome) {
            System.setProperty("webdriver.chrome.driver", "/C:/Users/irkaz/chromedriver_win32/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            return new ChromeDriver(options);
        }
        throw new IllegalArgumentException();
    }
}
