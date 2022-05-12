package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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


  public MainPage() {
    PageFactory.initElements(driver,this);
  }

  public MainPage printProduts(){

    products.forEach(number -> System.out.println(number.getText()));
    System.out.println(products.get(0).getText());
    System.out.println(products.get(1).getText());
    System.out.println(products.get(2).getText());
    return this;
  }

  public String getName(){
    return name.getText();
  }
}
