package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
  WebDriver driver;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//span[normalize-space()='Register']")
  private WebElement registerBtn;

  @FindBy(xpath = "//span[@class='f49c3e3e97 fb4fcd9c72 b940d0c9d3']")
  private WebElement confirmRegestraction;

  @FindBy(xpath = "//span[normalize-space()='Sign in']")
  private WebElement signInBtn;

  public WebElement registerBtn() {
    return registerBtn;
  }

  public WebElement confirmRegestraction() {
    return confirmRegestraction;
  }

  public WebElement signInBtn() {
    return signInBtn;
  }
}
