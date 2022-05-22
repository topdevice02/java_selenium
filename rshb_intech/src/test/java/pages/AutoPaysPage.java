package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

import java.util.List;

public class AutoPaysPage extends BasePage{

  @FindBy(xpath = "//tr[contains(@class,'ib-datagrid-row')]")
  private List<WebElement> autoPayList;

  @FindBy(xpath = "//tr[contains(@class,'ib-datagrid-row')]//td[6]")
  private List<WebElement> allActionsButtonList;

  @FindBy(xpath = "//tr[contains(@class,'ib-datagrid-row')]//td[5]//a")
  private List<WebElement> statusList;

  @FindBy(xpath = "//div[@style='display: block;']//a[2]")
  private WebElement suspendButton;

  @FindBy(name = "passwordField")
  private WebElement passwordFieldSms;

  @FindBy(xpath = "//input[@name='Ok']")
  private WebElement okButton;



  public AutoPaysPage() {
    PageFactory.initElements(driver, this);
  }

  public AutoPaysPage getNameAutoPays(){
    List<WebElement> nameAutoPay = driver.findElements(By.xpath("//tr[contains(@class,'ib-datagrid-row')]//td[3]//div[@class='autopayListName']"));

    for (int i = 0; i < nameAutoPay.size(); i++) {
      System.out.println(nameAutoPay.get(i).getText());
    }
    return this;
  }

  public String getStatus(){
    WebElement status = driver.findElement(By.xpath("//tr[contains(@class,'ib-datagrid-row')]//td[5]//a"));
    return status.getAttribute("title");
  }

  public AutoPaysPage suspendAutoPay() throws InterruptedException {
    for (int i = 0; i < statusList.size(); i++) {
      String statusText = statusList.get(i).getAttribute("title");
      if(statusText.equals("Активный")){
        allActionsButtonList.get(i).click();
        break;
      } else {
        System.out.println((i+1) + " автоплатеж в неподходящем статусе");
      }
    }
    //allActionsButtonList.iterator().next().click();
    suspendButton.click();
    waitElemetIsVisible(confirmMethodList);
    confirmMethodList.click();
    waitElemetIsVisible(smsOption);
    smsOption.click();
    Thread.sleep(2000);
    passwordFieldSms.sendKeys(ConfigProvider.PASSWORD_SMS, Keys.ENTER);
    Thread.sleep(2000);
    waitElemetIsVisible(okButton);
    okButton.click();
    Thread.sleep(3000);
    return this;
  }
}
