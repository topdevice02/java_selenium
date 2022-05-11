package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

public class LoginPage extends BasePage{

  @FindBy(id = "textfield")
  private WebElement login;

  @FindBy(id = "passwordfield")
  private WebElement password;

  @FindBy(id = "field")
  private WebElement checkbox;

  @FindBy(xpath = "//div[@class='ib-button-text']")
  private WebElement enterButton;

  @FindBy(id = "modelNotOverrideSelect")
  private WebElement methodList;

  @FindBy(xpath = "//option[text()='SMS-Пароль']")
  private WebElement smsOption;

  @FindBy(name = "passwordField_login")
  private WebElement passwordSms;

  @FindBy(xpath = "//span[text()='Подтвердить']")
  private WebElement confirmButton;



  public LoginPage() {
    driver.get(ConfigProvider.URL);
    PageFactory.initElements(driver, this);
  }

  public LoginPage authorizationClient() throws InterruptedException {
    login.sendKeys(ConfigProvider.GAGANOV);
    password.sendKeys(ConfigProvider.PASSWORD);
    checkbox.click();
    enterButton.click();
    Thread.sleep(3000);
    methodList.click();
    smsOption.click();
    Thread.sleep(1000);
    passwordSms.sendKeys(ConfigProvider.PASSWORD_SMS, Keys.ENTER);
    return this;
  }
}
