package pages;

import constans.Constant;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

public class ConnectAutoPayPage extends BasePage{

  @FindBy(xpath = "//span[@id='comboboxDropDown']//span[@id='sod_label']")
  private WebElement selectWriteOff;

  @FindBy(xpath = "//span[@id='comboboxDropDown']//span[contains(text(),'40817...6663')]")
  private WebElement optionWriteOff;

  @FindBy(xpath = "//span[@id='comboboxDropDown_0']//span[@id='sod_label']")
  private WebElement selectEnroll;

  @FindBy(xpath = "//span[@id='comboboxDropDown_0']//span[contains(text(),'40817...8048')]")
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

  @FindBy(xpath = "//span[text()='Отмена']")
  private WebElement cancelButton;

  @FindBy(xpath = "//input[@id='field']")
  private WebElement nameAutoPayField;

  public ConnectAutoPayPage() {
    PageFactory.initElements(driver,this);
  }

  public String getNameAutoPay(){
    return nameAutoPayField.getAttribute("value");
  }

  public ConnectAutoPayPage fillAndConfirmConnectAutoPay() throws InterruptedException {
    selectWriteOff.click();
    optionWriteOff.click();

    Thread.sleep(3000);

    try {
      PageFactory.initElements(driver,this);
      selectEnroll.click();
    }
    catch(org.openqa.selenium.StaleElementReferenceException ex)
    {
      PageFactory.initElements(driver,this);
      selectEnroll.click();
    }

    optionEnroll.click();

    Thread.sleep(3000);
    try {
      PageFactory.initElements(driver,this);
      sumField.clear();
      sumField.sendKeys(Constant.SUM_CREATE_AUTOPAY);
    }
    catch(org.openqa.selenium.StaleElementReferenceException ex)
    {
      PageFactory.initElements(driver,this);
      sumField.clear();
      sumField.sendKeys(Constant.SUM_CREATE_AUTOPAY);
    }

    waitElemetIsVisible(next1Button);
    next1Button.click();
    waitElemetIsVisible(next2Button);
    Thread.sleep(2000);
    next2Button.click();
    waitElemetIsVisible(confirmButton);
    Thread.sleep(2000);
    confirmButton.click();
    waitElemetIsVisible(okButton);
    Thread.sleep(2000);

    okButton.click();
    mainTab.click();
    return this;
  }

  public AutoPaysPage modifyAutoPay() throws InterruptedException {
    waitElemetIsVisible(sumField);
    Thread.sleep(2000);
    sumField.clear();
    Thread.sleep(1000);
    sumField.sendKeys(Constant.SUM_MODIFY_AUTOPAY);
    waitElemetIsVisible(next1Button);
    next1Button.click();
    waitElemetIsVisible(confirmButton);
    Thread.sleep(2000);
    confirmButton.click();
    Thread.sleep(2000);
    waitElemetIsVisible(confirmMethodList);
    confirmMethodList.click();
    waitElemetIsVisible(smsOption);
    smsOption.click();
    Thread.sleep(2000);
    passwordFieldSms.sendKeys(ConfigProvider.PASSWORD_SMS, Keys.ENTER);
    okButton.click();
    waitElemetIsVisible(cancelButton);
    cancelButton.click();
    return new AutoPaysPage();
  }
}
