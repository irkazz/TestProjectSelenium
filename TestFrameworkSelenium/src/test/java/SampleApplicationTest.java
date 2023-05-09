import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SampleApplicationTest {
    SampleApplicationPage saPage;
    WebDriverFactory fact = new WebDriverFactory();
    /**
     * Web driver - protocol that allows us talk to the browser ,
     * JUnit is a library to run tests,
     * Selenium is a library to invoke web driver calls
     * - change XPath to CSS selectors
     * - page object - it's a design pattern, object will be mapped to UI component
     * JUnit
     * Java Reflections - Scan classes, finds annotations/ classes and decides what to run
     * Test runner has separate parts - Execution and Reporting
     * <p>
     * Selenium:
     * Login page - what will be in the page object?
     * - All interactable component - WebElements, etc..
     * What do I want to happen when I instantiate my object
     * Implicit wait - time WD is trying to find element, configurable in WD
     * Good practice - set implicit wait to 0, and use Explicit Wait (ExpectedConditions, etc..)
     * Can configure Explicit Wait timeout on WD level
     * <p>
     * WebDriver.findElement (find inside HTML document root)vs WebElemnt.findElement(Find inside element)
     * WebElement - LoginForm1 and LoginForm2
     * <p>
     * Wait for Absence of the element
     * <p>
     * Stale Element - for example, form refreshed with missing data, we can't use same instance of the WebElement,
     * re-instantiate PageObject (or something else)
     * <p>
     * - Create repositotory in GIT
     * - add project
     * - Chem ya zanimalas' (ne gde uchastvovala, a chto delala)
     * - V chem ya vizhu svou rol' - esli est' regression backlog - create automation to ensure regression test, naladit' process
     * development to write tests, kotorye vojdut v regression suite, ustanavlivat' testing polices i create automation
     * - Rest API - kak vy testirovali rest API - chto i kak, na kakoi environment - chto, kak i pochemu
     * Ne ispol'zovali mocks, ispol'zovali real test environment
     * - Kak ponyat', chto testirovano horosho - test coverage, #of bugs, #of cases, bus with cases,
     */
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = fact.createWD(BrowserType.chrome);
        saPage = new SampleApplicationPage(driver);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    @Category(Basic.class)
    public void testFormSubmit() {
        openPageAndValidate(1);
        submitFormAndValidate(new TestUser("Irina", "S", null), false, false);
    }

    @Test
    @Category(Basic.class)
    public void testFormSubmitPage2() {
        openPageAndValidate(2);
        submitFormAndValidate(new TestUser("Irina", "S", null), true, false);

    }

    @Test
    @Category(Basic.class)
    public void testFormSubmitPage3() {
        openPageAndValidate(3);
        submitFormAndValidate
                (new TestUser("Irina", "S", SampleApplicationPage.Gender.female.getValue()),
                        true, true);
    }

    @Test
    @Category(Basic.class)
    public void testFormSubmitPage4() {
        saPage = new SampleApplicationPage4(driver);
        ((SampleApplicationPage4) saPage).setFormId(1);
        openPageAndValidate(4);
        submitFormAndValidate
                (new TestUser("Irina", "S", SampleApplicationPage.Gender.female.getValue()),
                        true, true);
    }

    @Test
    @Category(Basic.class)
    public void testECFormSubmitPage4() {
        saPage = new SampleApplicationPage4(driver);
        ((SampleApplicationPage4) saPage).setFormId(2);
        openPageAndValidate(4);
        submitFormAndValidate
                (new TestUser("Irina", "S", SampleApplicationPage.Gender.female.getValue()),
                        true, true);
    }

    private void openPageAndValidate(int sprintNum) {
        saPage.openPage(sprintNum);
        assertEquals("Sample Application Lifecycle - Sprint " + sprintNum + " - Ultimate QA", driver.getTitle());
    }

    private void submitFormAndValidate(TestUser user, boolean withLastName, boolean withGender) {
        assertTrue(saPage.submitForm(user, withLastName, withGender));
        boolean isPage4 = saPage instanceof SampleApplicationPage4;
        //TODO: Why URL does not have params?
        if (!isPage4) {
            assertTrue(driver.getCurrentUrl().contains(user.getFirstName()));

            if (withGender) {
                assertEquals(withGender, driver.getCurrentUrl().contains(user.getGender()));
            }
            if (withLastName) {
                assertEquals(withLastName, driver.getCurrentUrl().contains(user.getLastName())); //TODO add URL portion, cuz Fname could be = LName or Gender
            }
        }
    }
}
