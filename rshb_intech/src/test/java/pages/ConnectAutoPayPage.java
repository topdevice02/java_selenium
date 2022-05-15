package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConnectAutoPayPage extends BasePage{

  @FindBy(xpath = "//span[text()='Выберите счет/вклад/карту списания']")
  private WebElement selectWriteOff;

  @FindBy(xpath = "//span[@id='comboboxDropDown']//span[contains(text(),'40817...6663')]")
  private WebElement optionWriteOff;

  @FindBy(id = "//span[text()='Выберите счет/вклад/карту зачисления']")
  private WebElement selectEnroll;

  @FindBy(xpath = "//span[@id='comboboxDropDown_0']//span[contains(text(),'40817...6663')]")
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
//    waitElemetIsVisible(selectEnroll);
//    selectEnroll.click();
//    optionEnroll.click();
    waitElemetIsVisible(sumField);
    sumField.sendKeys("100");
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
