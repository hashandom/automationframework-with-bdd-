package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.CreateAccEnterEmailPage;
import pageobjects.CreateAccEnterPwdPage;
import pageobjects.HomePage;
import resources.Base;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CreateAccountTest extends Base {

    private static final Logger logger = LoggerFactory.getLogger(CreateAccountTest.class);

    public WebDriver driver;
    public Properties createAccountProp;
    private CreateAccEnterEmailPage createAccEnterEmailPage;
    private CreateAccEnterPwdPage createAccEnterPwdPage;
    private HomePage homePage;

    @BeforeClass
    public void setUpApplication() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        logger.info("Navigated to URL: {}", prop.getProperty("url"));

        homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.registerBtn()));
        homePage.registerBtn().click();

        logger.info("Clicked on the register button");

        createAccEnterEmailPage = new CreateAccEnterEmailPage(driver);
        createAccEnterPwdPage = new CreateAccEnterPwdPage(driver);
    }

    @Test(enabled = false, dataProvider = "getEmailData", dataProviderClass = resources.testdata.CreateAccountTestData.class)
    public void emailVerification(String email, String expected) throws IOException, InterruptedException {
        logger.info("Starting email verification test with email: {} and expected result: {}", email, expected);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        wait.until(ExpectedConditions.visibilityOf(createAccEnterEmailPage.email())).clear();
        createAccEnterEmailPage.email().sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(createAccEnterEmailPage.continueWithEmailBtn())).click();
        wait.until(ExpectedConditions.visibilityOf(createAccEnterEmailPage.emailNote()));

        String emailNote = createAccEnterEmailPage.emailNote().getText();
        logger.info("Email note retrieved: {}", emailNote);

        if (expected.equals("invalid")) {
            Assert.assertEquals(emailNote, "Make sure the email address you entered is correct.");
        }
    }

    @Test(dataProvider = "getAllUserRegData", dataProviderClass = resources.testdata.CreateAccountTestData.class)
    public void testUserRegister(String email, String password, String confirmPassword, String expected) throws InterruptedException {
        logger.info("Starting user registration test with email: {}, password: {}, confirmPassword: {}, expected: {}", email, password, confirmPassword, expected);

        createAccEnterEmailPage.email().sendKeys(email);
        Thread.sleep(1000);
        createAccEnterEmailPage.continueWithEmailBtn().click();
        Thread.sleep(1000);
        createAccEnterPwdPage.password().sendKeys(password);
        Thread.sleep(1000);
        createAccEnterPwdPage.confirmPassword().sendKeys(confirmPassword);
        Thread.sleep(1000);
        createAccEnterPwdPage.createAccountBtn().click();
        Thread.sleep(1000);

        String text = homePage.confirmRegestraction().getText();
        String alterText = text.split(",")[0].trim();
        logger.info("Registration confirmation text retrieved: {}", alterText);

        if (expected.equals("valid")) {
            Assert.assertEquals(alterText, "where to next");
        }
    }
}
