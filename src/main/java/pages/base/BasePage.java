package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constant.TimeoutVariable.EXPLICIT_WAIT;

/**
 * Базовый класс с основной логикой
 */

public class BasePage {
  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  //Метод открытия страниц
  public void open(String url){
    driver.get(url);
  }

  //Метод для ожидания видимости элементов
  public WebElement waitElemetIsVisible(WebElement element){
    new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
    return element;
  }
}
