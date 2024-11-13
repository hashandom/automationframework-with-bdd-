package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the search room hotel page within the application.
 * This class contains methods to interact with elements on the search room hotel page.
 */
public class SearchRoomHotelPage {
  WebDriver driver;

  public SearchRoomHotelPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//span[normalize-space()='Register']")
  private WebElement registerBtn;

  public WebElement registerBtn() {
    return registerBtn;
  }
}
