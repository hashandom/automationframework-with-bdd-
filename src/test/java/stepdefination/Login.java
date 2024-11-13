package stepdefination;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import resources.Base;

import java.io.IOException;

public class Login extends Base {
    WebDriver driver;
    @Given("Open any Browser")
    public void open_any_browser() throws IOException {
            driver = initializeDriver();
    }
    @Given("Navigate to Login page")
    public void navigate_to_login_page() {
    driver.get(prop.getProperty("url"));
    }
    @When("User enters username as {string} and password as {string} into the fields")
    public void user_enters_username_as_and_password_as_into_the_fields(String email, String password) {

    }
    @When("User clicks on Login button")
    public void user_clicks_on_login_button() {

    }
    @Then("Verify user is able to successfully login")
    public void verify_user_is_able_to_successfully_login() {

    }

    @After
    public void closeBrowser(){
        driver.close();
    }


}
