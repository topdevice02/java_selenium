package pages.listing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.base.BasePage;

public class CianListingPage extends BasePage {

  public CianListingPage(WebDriver driver) {
    super(driver);
  }

  private final By card = By.xpath("//div[@class='_93444fe79c--wrapper--W0WqH']/article[@data-name='CardComponent']");

  public CianListingPage checkCountCards(){
    int countCard = driver.findElements(card).size();
    Assert.assertEquals(countCard, 28);
    return this;
  }
}
