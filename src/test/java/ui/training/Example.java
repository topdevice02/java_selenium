package ui.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Example {

  static WebDriver driver;

  @Test
  public void test1(){
    System.setProperty("webdriver.chrome.driver", "C:\\Windows\\System32\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();

    driver.get("https://www.w3schools.com/html/html_tables.asp");

    WebElement tableElement = driver.findElement(By.xpath("//table[@id='customers']"));
    Table table = new Table(tableElement, driver);

    System.out.println(table.getRows().size());

    System.out.println(table.getValueFromCell(1, 2));

    System.out.println(table.getValueFromCell(2, "Contact"));

    driver.quit();

  }
}
