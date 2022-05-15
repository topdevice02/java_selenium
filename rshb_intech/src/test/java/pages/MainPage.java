package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.chrono.MinguoChronology;
import java.util.List;

public class MainPage extends BasePage{

  @FindBy(id = "nonsubmitImpl_4")
  private WebElement mainTab;

  @FindBy(xpath = "//div[@class='product-diagram-name']")
  private WebElement titleFinancialStructure;

  @FindBy(xpath = "//label[@class='lt-loyalty-title-label']")
  private WebElement titleLoyaltyProgram;

  @FindBy(xpath="//div[@class='product-section-name']")
  private List<WebElement> products;

  @FindBy(id="textfieldnimble-text")
  private WebElement name;

  @FindBy(xpath = "//a[@class='product-info-main-title']")
  private List<WebElement> nameProduct;

  @FindBy(xpath = "//form[contains(@id,'retailCardForm')]//div[contains(@class,'product-info-main-cardinfo')]//a")
  List<WebElement> allCards;

  @FindBy(xpath = "//form[contains(@id,'retailAccountForm')]//div[contains(@class,'product-info-main')]//a")
  List<WebElement> allAccounts;

  @FindBy(xpath = "//form[contains(@id,'retailDepositForm')]//div[contains(@class,'product-info-main')]//a")
  List<WebElement> allDeposits;

  @FindBy(xpath = "//form[contains(@id,'metallicForm')]//div[contains(@class,'product-info-main')]//a")
  List<WebElement> allMetals;

  @FindBy(xpath = "//form[contains(@id,'retailLoanForm')]//div[contains(@class,'product-info-main')]//a")
  List<WebElement> allCredits;

  @FindBy(xpath = "//img[@class='cross']")
  private WebElement autoPayButton;

  public MainPage() {
    PageFactory.initElements(driver,this);
  }


  public MainPage printCards(){
      allCards.forEach(card -> System.out.println(card.getText()));
      return this;
  }

  public MainPage printAccounts(){
    //allAccounts.forEach(account -> System.out.println(account.getText()));
    for (WebElement account : allAccounts){
      if (account.isDisplayed()){
        System.out.println(account.getText());
      }
    }
    return this;
  }

  public MainPage printDeposits(){
//    allDeposits.forEach(deposit -> System.out.println(deposit.getText()));
    for (WebElement deposit : allDeposits){
      if (deposit.isDisplayed()){
        System.out.println(deposit.getText());
      }
    }
    return this;
  }

  public MainPage printMetals(){
    allMetals.forEach(metal -> System.out.println(metal.getText()));
    return this;
  }

  public MainPage printCredits(){
    allCredits.forEach(credit -> System.out.println(credit.getText()));
    return this;
  }

  public MainPage printProduts(){
    products.forEach(product -> System.out.println(product.getText()));
    return this;
  }

  public MainPage printAllProduct() {
    for (int i = 0; i < products.size(); i++) {
      switch (i) {
        case 0:
          System.out.println("==="+products.get(0).getText()+"===");
          printCards();
          break;
        case 1:
          System.out.println("==="+products.get(1).getText()+"===");
          printAccounts();
          break;
        case 2:
          System.out.println("==="+products.get(2).getText()+"===");
          printDeposits();
          break;
        case 3:
          if (products.get(3).getText().equals("Драгоценные металлы")){
          System.out.println("==="+products.get(3).getText()+"===");
          printMetals();
          break;
        } else
          System.out.println("==="+products.get(3).getText()+"===");
          printCredits();
          break;

        case 4:
          if (products.get(4).getText().equals("Кредиты")){
            System.out.println("==="+products.get(4).getText()+"===");
            printCredits();
            break;
          } else
            System.out.println("==="+products.get(4).getText()+"===");
            printMetals();
            break;
      }
    }
    return this;
  }

  public CreateAutoPayPage clickAutoPayButton() throws InterruptedException {
    autoPayButton.click();
    Thread.sleep(5000);
  return new CreateAutoPayPage();
  }


  public String getName(){
    return name.getText();
  }

  public String getTitleFinancialStructure(){
    return titleFinancialStructure.getText();
  }

  public String getTitleLoyaltyProgram(){
    return titleLoyaltyProgram.getText();
  }
}
