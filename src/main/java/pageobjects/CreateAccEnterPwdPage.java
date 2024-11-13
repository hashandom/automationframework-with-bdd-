package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccEnterPwdPage {
  WebDriver driver;

  public CreateAccEnterPwdPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//input[@id='new_password']")
  private WebElement password;

  @FindBy(xpath = "//input[@id='confirmed_password']")
  private WebElement confirmpassword;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement createAccountBtn;

  public WebElement password() {
    return password;
  }

  public WebElement confirmPassword() {
    return confirmpassword;
  }

  public WebElement createAccountBtn() {
    return createAccountBtn;
  }
}
