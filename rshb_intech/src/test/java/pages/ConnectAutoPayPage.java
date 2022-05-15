package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConnectAutoPayPage extends BasePage{

  @FindBy(id = "comboboxDropDown_hidden")
  private WebElement selectWriteOff;

  @FindBy(xpath = "//select[@id='comboboxDropDown_hidden']//option[contains(text(),'40817...6663')]")
  private WebElement optionWriteOff;

  @FindBy(id = "comboboxDropDown_0_hidden")
  private WebElement selectEnroll;

  @FindBy(xpath = "//select[@id='comboboxDropDown_0_hidden']//option[contains(text(),'40817...3693')]")
  private WebElement optionEnroll;

  @FindBy(id = "maskField")
  private WebElement sumField;

  @FindBy(xpath = "//span[text()='Далее']")
  private WebElement next1Button;

  @FindBy(xpath = "//span[text()='Далее']")
  private WebElement next2Button;

  @FindBy(xpath = "//span[text()='Подтвердить']")
  private WebElement confirmButton;

  @FindBy(xpath = "//input[@name='Ok']")
  private WebElement okButton;

  @FindBy(id = "nonsubmitImpl_4")
  private WebElement mainTab;

  public ConnectAutoPayPage() {
    PageFactory.initElements(driver,this);
  }

  public MainPage fillAndConfirmConnectAutoPay() throws InterruptedException {
    selectWriteOff.click();
    optionWriteOff.click();
    selectEnroll.click();
    optionEnroll.click();
    next1Button.click();
    Thread.sleep(5000);
    next2Button.click();
    Thread.sleep(5000);
    confirmButton.click();
    Thread.sleep(5000);
    okButton.click();
    mainTab.click();
    return new MainPage();
  }
}
