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

  public MainPage authorizationClient(String loginClient, String passwordClient) throws InterruptedException {
    login.sendKeys(loginClient);
    password.sendKeys(passwordClient);
    checkbox.click();
    enterButton.click();
    Thread.sleep(2000);
    waitElemetIsVisible(methodList);
    methodList.click();
    Thread.sleep(2000);
    waitElemetIsVisible(smsOption);
    smsOption.click();
    Thread.sleep(2000);
    waitElemetIsVisible(passwordSms);
    passwordSms.sendKeys(ConfigProvider.PASSWORD_SMS, Keys.ENTER);
    Thread.sleep(10000);
    return new MainPage();
  }
}
