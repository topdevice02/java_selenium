package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BasePage {

  @FindBy(id = "nonsubmitImpl_4")
  public WebElement mainTab;

  @FindBy(id = "modelNotOverrideSelect")
  public WebElement confirmMethodList;

  @FindBy(xpath = "//option[text()='SMS-Пароль']")
  public WebElement smsOption;

  @FindBy(xpath = "//span[text()='Подтвердить']")
  public WebElement confirmButton;

  protected static WebDriver driver;

  public static void createDriver(WebDriver webDriver){
    driver = webDriver;
  }

  public static WebElement waitElemetIsVisible(WebElement element){
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    return element;
  }
}
