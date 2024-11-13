package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
  WebDriver driver;
  public Properties prop;

  private static final Logger logger = Logger.getLogger(Base.class.getName());

  public WebDriver initializeDriver() throws IOException {
    prop = new Properties();
    String propPath =
        System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
    FileInputStream fis = new FileInputStream(propPath);
    prop.load(fis);

    String browserName = prop.getProperty("browser").toLowerCase().trim();
    ;
    // Initialize the driver based on the browser name
    if (browserName.equalsIgnoreCase("chrome")) {
      WebDriverManager.chromedriver().setup(); // Set up ChromeDriver using WebDriverManager
      driver = new ChromeDriver();

    } else if (browserName.equalsIgnoreCase("firefox")) {
      WebDriverManager.firefoxdriver().setup(); // Set up FirefoxDriver using WebDriverManager
      driver = new FirefoxDriver();

    } else if (browserName.equalsIgnoreCase("edge")) {
      WebDriverManager.edgedriver().setup(); // Set up EdgeDriver using WebDriverManager
      driver = new EdgeDriver();

    } else {
      throw new IllegalArgumentException(
          "Browser not supported: " + browserName); // More informative error message
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    return driver;
  }

  public String takeScreenshot(String testName, WebDriver driver) throws IOException {
    // Create a folder named after the test method inside the screenshots folder
    String screenshotDir = System.getProperty("user.dir") + "\\screenshots\\" + testName;
    File dir = new File(screenshotDir);
    if (!dir.exists()) {
      dir.mkdirs(); // Create the directory if it doesn't exist
    }

    // Capture the screenshot
    File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String destinationFilePath = screenshotDir + "\\" + testName + ".png";
    FileUtils.copyFile(sourceFile, new File(destinationFilePath));

    // Log the screenshot path (optional)
    logger.info(("Screenshot saved at: " + destinationFilePath));
    ;

    return destinationFilePath;
  }
}
