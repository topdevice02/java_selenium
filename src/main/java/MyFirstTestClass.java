import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MyFirstTestClass {

  public static void main(String[] args) throws InterruptedException {

    System.setProperty("webdriver.chrome.driver","C:\\Windows\\System32\\chromedriver.exe");
    ChromeDriver driver = new ChromeDriver();

    driver.get("https://www.avito.ru/ufa/transport");
    Thread.sleep(1000);

    WebElement elementSuzuki = driver.findElement(By.xpath("//a[contains(@href, 'ufa/avtomobili/suzuki-ASgBAgICAUTgtg2omSg?cd=1')]"));
    elementSuzuki.click();

    WebElement elementJinmy = driver.findElement(By.xpath("//a[contains(@href, 'ufa/avtomobili/suzuki/jimny-ASgBAgICAkTgtg2omSjitg2gqCg?cd=1&radius=200')]"));
    elementJinmy.click();


    List<WebElement> allPrice = driver.findElements(By.cssSelector("span.price-text-_YGDY.text-text-LurtD.text-size-s-BxGpL"));
    List<WebElement> allYears = driver.findElements(By.xpath("//h3[@itemprop]"));
    //Thread.sleep(2000);

    for (int i = 0; i < allPrice.size(); i++) {
      System.out.println(allYears.get(i).getText() + " , Цена - " + allPrice.get(i).getText());
    }

    driver.quit();

  }
}
