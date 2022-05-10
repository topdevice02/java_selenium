package pages.cian_home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class CianHomePage extends BasePage {

  public CianHomePage(WebDriver driver) {
    super(driver);
  }

  private final By countRooms = By.xpath("//button[@title='1, 2 комн.']");
  private final By option1Rooms = By.xpath("//button[text()='2']");
  private final By findButton = By.xpath("//a[text()='Найти']");

  public CianHomePage enterCountRooms(){
    driver.findElement(countRooms).click();
    driver.findElement(option1Rooms).click();

    return this;
  }

  public CianHomePage clickToFind(){
    //После того как элемент будет найдем кликаем по нему
    waitElemetIsVisible(driver.findElement(findButton)).click();
    return this;
  }
}
