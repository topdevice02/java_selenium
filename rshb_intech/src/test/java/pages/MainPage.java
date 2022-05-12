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


  public MainPage() {
    PageFactory.initElements(driver,this);
  }

  public MainPage printProduts(){
    products.forEach(product -> System.out.println(product.getText()));
    return this;
  }

  public MainPage printAllProduct(){
    for (int i = 0; i < products.size(); i++) {
      System.out.println(products.get(i).getText());

      for(int j = 0; j < nameProduct.size(); j++){
        System.out.println(nameProduct.get(j).getText());
      }
    }
    return this;
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
