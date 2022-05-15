package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmAutoPayPage extends BasePage{

  @FindBy(xpath = "//span[text()='Подтвердить']")
  private WebElement confirmButton;


}
