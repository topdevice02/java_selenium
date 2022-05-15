package pages;

import helpers.StringModifier;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.StringModifier.*;

public class CreateAutoPayPage extends BasePage {

  @FindBy(id = "field")
  private WebElement nameAutoPayField;

  @FindBy(id = "docKind_hidden")
  private WebElement selectTypeAutoPay;

  @FindBy(xpath = "//option[@value='2']")
  private WebElement optionTransferBetweenYourAccounts;

  @FindBy(id = "ib-button-text")
  private WebElement nextButton;

  public CreateAutoPayPage() {
    PageFactory.initElements(driver,this);
  }

  public ConnectAutoPayPage fillFormCreateAutoPay() throws InterruptedException {
    String nameAutoPay = getUniqueString("name");
    nameAutoPayField.sendKeys("1234");
    selectTypeAutoPay.click();
    optionTransferBetweenYourAccounts.click();
    nextButton.click();
    Thread.sleep(5000);
    return new ConnectAutoPayPage();
  }


}
