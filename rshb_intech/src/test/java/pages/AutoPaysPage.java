package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

import java.util.List;

public class AutoPaysPage extends BasePage {

  @FindBy(xpath = "//table[@id='dataGrid']//tbody//tr[contains(@class,'ib-datagrid-row')]")
  private List<WebElement> autoPayListEnabled;

  @FindBy(xpath = "//table[@id='dataGrid_0']//tbody//tr[contains(@class,'ib-datagrid-row')]")
  private List<WebElement> autoPayListDisabled;

  @FindBy(xpath = "//tr[contains(@class,'ib-datagrid-row')]//td[6]")
  private List<WebElement> allActionsButtonList;

  @FindBy(xpath = "//tr[contains(@class,'ib-datagrid-row')]//td[5]//a")
  private List<WebElement> statusList;

  @FindBy(xpath = "//div[@style='display: block;']//a//li[contains(text(),'Приостановить')]")
  private WebElement suspendButton;

  @FindBy(xpath = "//div[@style='display: block;']//a//li[contains(text(),'Возобновить')]")
  private WebElement resumeButton;

  @FindBy(xpath = "//div[@style='display: block;']//a//li[contains(text(),'Изменить')]")
  private WebElement modifyButton;

  @FindBy(xpath = "//div[@style='display: block;']//a//li[contains(text(),'Отключить')]")
  private WebElement disableButton;

  @FindBy(xpath = "//input[@value='Продолжить']")
  private WebElement confirmDisableButton;

  @FindBy(xpath = "//input[@name='Ok']")
  private WebElement okButton;

  private String status;


  public AutoPaysPage() {
    PageFactory.initElements(driver, this);
  }

  public String getStatusEnabledAutoPay(String str){
    for (WebElement autopay : autoPayListEnabled){
      List<WebElement> cells = autopay.findElements(By.tagName("td"));
      String name = cells.get(2).findElement(By.tagName("a")).getText();
      status = cells.get(4).findElement(By.tagName("a")).getText();

      if(str.equals(name)){
       break;
      }
      //System.out.println(operation + "->" + name);
    }
    return status;
  }


  public String getNameAutoPayEnabled(int index) {
    List<WebElement> nameAutoPay = driver.findElements(By.xpath("//tr[contains(@class,'ib-datagrid-row')]//td[3]//div[@class='autopayListName']"));

    String result = null;
    for (int i = 0; i < nameAutoPay.size(); i++) {
      if(i == index){
        result = nameAutoPay.get(i).getAttribute("title");
        break;
      }
    }
    return result;
  }

  public String getNameAutoPayDisabled(int index) {
    List<WebElement> nameAutoPay = driver.findElements(By.xpath("//table[@id='dataGrid_0']//tr[contains(@class,'ib-datagrid-row')]//td[3]//a"));

    String result = null;
    for (int i = 0; i < nameAutoPay.size(); i++) {
      if(i == index){
        result = nameAutoPay.get(i).getAttribute("title");
        break;
      }
    }
    return result;
  }

  public Boolean isPresent(String name){
    boolean isTrue = false;
    List<WebElement> nameAutoPay = driver.findElements(By.xpath("//table[@id='dataGrid_0']//tr[contains(@class,'ib-datagrid-row')]//td[3]//a"));

    for (int i = 0; i < nameAutoPay.size(); i++) {
      if(nameAutoPay.get(i).getAttribute("title").equals(name)){
        isTrue = true;
        break;
      }
    }
    return isTrue;
  }

  public String getStatus() {
    WebElement status = driver.findElement(By.xpath("//tr[contains(@class,'ib-datagrid-row')]//td[5]//a"));
    return status.getAttribute("title");
  }

  public String getSum(){
    WebElement sum = driver.findElement(By.xpath("//tr[contains(@class,'ib-datagrid-row')]//td[4]//a//div"));
    return sum.getText();
  }

  public AutoPaysPage suspendAutoPay() throws InterruptedException {
    for (int i = 0; i < statusList.size(); i++) {
      String statusText = statusList.get(i).getAttribute("title");
      if (statusText.equals("Активный")) {
        allActionsButtonList.get(i).click();
        break;
      } else {
        System.out.println((i + 1) + " автоплатеж в неподходящем статусе");
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

  public AutoPaysPage resumeAutoPay() throws InterruptedException {
    for (int i = 0; i < statusList.size(); i++) {
      String statusText = statusList.get(i).getAttribute("title");
      if (statusText.equals("Приостановлен")) {
        allActionsButtonList.get(i).click();
        break;
      } else {
        System.out.println((i + 1) + " автоплатеж в неподходящем статусе");
      }
    }
    resumeButton.click();
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

  public ConnectAutoPayPage goToModifyAutoPay() throws InterruptedException {
    for (int i = 0; i < statusList.size(); i++) {
      String statusText = statusList.get(i).getAttribute("title");
      if (statusText.equals("Активный") || statusText.equals("Приостановлен")) {
        allActionsButtonList.get(i).click();
        break;
      } else {
        System.out.println((i + 1) + " автоплатеж в неподходящем статусе");
      }
    }
    modifyButton.click();
    return new ConnectAutoPayPage();
  }

  public AutoPaysPage disableAutoPay() throws InterruptedException {
    allActionsButtonList.iterator().next().click();
    disableButton.click();
    Thread.sleep(2000);
    waitElemetIsVisible(confirmDisableButton);
    confirmDisableButton.click();
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

  @Override
  public String toString() {
    return "AutoPaysPage{" +
            "autoPayListEnabled=" + autoPayListEnabled +
            ", autoPayListDisabled=" + autoPayListDisabled +
            ", allActionsButtonList=" + allActionsButtonList +
            ", statusList=" + statusList +
            ", suspendButton=" + suspendButton +
            ", resumeButton=" + resumeButton +
            ", modifyButton=" + modifyButton +
            ", disableButton=" + disableButton +
            ", confirmDisableButton=" + confirmDisableButton +
            ", okButton=" + okButton +
            ", status='" + status + '\'' +
            '}';
  }
}
