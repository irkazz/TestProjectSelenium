import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SampleApplicationPage4 extends SampleApplicationPage {
    private int formId;

    @Override
    protected By getFirstNameInput() {
        return By.cssSelector("input#f" + formId + "[name='firstname']");
    }

    @Override
    public By getLastNameInput() {
        return By.cssSelector("input#l" + formId + "[name='lastname']");
    }

    @Override
    public By getSubmitButton() {
        return By.cssSelector("input#submit" + formId);
    }

    public void setFormId(int id) {
        formId = id;
    }


    public SampleApplicationPage4(WebDriver driver) {
        super(driver);
    }

    protected final By getRadioButton(String gender) {
        return By.cssSelector("input[id*='radio" + formId + "'][value='" + gender + "']");
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

}
