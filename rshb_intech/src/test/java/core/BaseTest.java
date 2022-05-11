package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.BasePage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

abstract public class BaseTest {
  protected WebDriver driver;

  @BeforeSuite
  public void setUp(){
    //Установка драйвера актуальной версии
    WebDriverManager.chromedriver().setup();

    //Инициализация Chromedriver
    driver = new ChromeDriver();

    //Открытие браузера в полноэкранном режиме
    driver.manage().window().maximize();

    //Добавление ожидания для загрузки страницы
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

    //Ожидание загрузки элементов на странице
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    BasePage.createDriver(driver);
  }

  @AfterSuite
  public void tearDown(){
    //Закрытие драйвера
    driver.close();

    //Закрытие браузера
    driver.quit();
  }
}
