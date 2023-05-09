import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SampleApplicationPage extends BasePage {
    public SampleApplicationPage(WebDriver driver) {
        super(driver);
    }

    protected By getFirstNameInput() {
        return By.cssSelector("input[name='firstname']");
    }

    protected By getSubmitButton() {
        return By.cssSelector("input[type='submit']");
    }

    protected By getLastNameInput() {
        return By.cssSelector("input[name='lastname']");
    }

    public String getPageURL(int sprint) {
        String url = "sample-application-lifecycle-sprint-" + sprint + "/";
        return new StringBuilder().append(baseUrl)
                .append(url).toString();
    }

    protected By getRadioButton(String gender) {
        return By.cssSelector("input[value ='" + gender + "']");
    }

    public void openPage(int sprint) {
        driver.navigate().to(getPageURL(sprint));
        driver.manage().window().maximize();
    }

    public boolean submitForm(TestUser user, boolean withLastName, boolean withGender) {
        driver.findElement(getFirstNameInput()).sendKeys(user.getFirstName());
        if (withLastName)
            driver.findElement(getLastNameInput()).sendKeys(user.getLastName());
        if (withGender)
            driver.findElement(getRadioButton(user.getGender())).click();
        driver.findElement(getSubmitButton()).submit();
        return new UltimateQAHomePage(driver).isVisible();
    }

    public enum Gender {
        male("male"),
        female("female"),
        other("other");

        private final String value;

        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
