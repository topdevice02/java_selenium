package pages;

import helpers.StringModifier;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.StringModifier.*;

public class CreateAutoPayPage extends BasePage {

  @FindBy(id = "field")
  private WebElement nameAutoPayField;

  @FindBy(id = "docKind")
  private WebElement selectTypeAutoPay;

  @FindBy(xpath = "//span[text()='Перевод между своими счетами']")
  private WebElement optionTransferBetweenYourAccounts;

  @FindBy(xpath = "//span[text()='Далее']")
  private WebElement nextButton;

  public CreateAutoPayPage() {
    PageFactory.initElements(driver,this);
  }

  public ConnectAutoPayPage fillFormCreateAutoPay() throws InterruptedException {
    String nameAutoPay = getUniqueString("autopay");
    nameAutoPayField.sendKeys(nameAutoPay);
    waitElemetIsVisible(selectTypeAutoPay);
    selectTypeAutoPay.click();
    waitElemetIsVisible(optionTransferBetweenYourAccounts);
    optionTransferBetweenYourAccounts.click();
    nextButton.click();
    Thread.sleep(5000);
    return new ConnectAutoPayPage();
  }


}
