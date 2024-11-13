package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.CreateAccEnterEmailPage;
import pageobjects.HomePage;
import resources.Base;

import java.io.IOException;
import java.time.Duration;

public class UserSignInTest extends Base {

    private static final Logger logger = LoggerFactory.getLogger(UserSignInTest.class);

   public  WebDriver driver;
    private HomePage homePage;
    private CreateAccEnterEmailPage createAccEnterEmailPage;

    @BeforeClass
    public void setUpApplication() throws IOException {
        logger.info("Initializing WebDriver and opening the application URL.");
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        logger.debug("Navigated to URL: {}", prop.getProperty("url"));

        homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.signInBtn()));
        logger.info("Sign-in button is clickable, proceeding to click.");
        homePage.signInBtn().click();

        createAccEnterEmailPage = new CreateAccEnterEmailPage(driver);
    }

    @Test(dataProvider = "getAllUserLoginData", dataProviderClass = resources.testdata.CreateAccountTestData.class)
    public void successfullyUserSignin(String email, String expected) throws IOException {
        logger.info("Starting user sign-in test with email: {}", email);
        createAccEnterEmailPage.email().sendKeys(email);
        logger.debug("Entered email: {}", email);
        createAccEnterEmailPage.continueWithEmailBtn().click();

        String text = homePage.confirmRegestraction().getText();
        String alterText = text.split(",")[0].trim();
        logger.debug("Received confirmation text: {}", text);

        if ("valid".equals(expected)) {
            Assert.assertEquals(alterText, "where to next");
            logger.info("Sign-in test passed for email: {}", email);
        }
    }

//    @AfterMethod
//    public void closure(){
//        if (driver != null) {
//            driver.close();
//            logger.info("Browser closed.");
//        }
//    }
}
