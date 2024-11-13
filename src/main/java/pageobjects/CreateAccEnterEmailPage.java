package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccEnterEmailPage {
  WebDriver driver;

  public CreateAccEnterEmailPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//input[@id='username']")
  private WebElement email;

  @FindBy(xpath = "//div[@id='username-note']")
  private WebElement emailNote;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement continueWithEmailBtn;

  public WebElement email() {
    return email;
  }

  public WebElement emailNote() {
    return emailNote;
  }

  public WebElement continueWithEmailBtn() {
    return continueWithEmailBtn;
  }
}
