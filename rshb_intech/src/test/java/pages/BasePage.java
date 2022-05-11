package pages;

import org.openqa.selenium.WebDriver;

abstract public class BasePage {

  protected static WebDriver driver;

  public static void createDriver(WebDriver webDriver){
    driver = webDriver;
  }
}
