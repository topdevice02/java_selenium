package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BasePage {

  protected static WebDriver driver;

  public static void createDriver(WebDriver webDriver){
    driver = webDriver;
  }

  public static WebElement waitElemetIsVisible(WebElement element){
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    return element;
  }
}
