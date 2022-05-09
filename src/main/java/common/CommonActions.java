package common;

import constants.Constant;
import constants.Constant.TimeoutVariable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static common.Config.PLATFORM_AND_BROWSER;
import static constants.Constant.TimeoutVariable.*;

/**
 *
 */
public class CommonActions {

  /**
   * Метод отвечающий за создание драйвера
   */
  public static WebDriver createDriver(){
    WebDriver driver = null;

    switch (PLATFORM_AND_BROWSER) {
      case "win.chrome":
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        break;

      default: //Поведение системы по умолчанию
        Assert.fail("Incorrect platform of browser name: " + PLATFORM_AND_BROWSER);
    }
    //Открытие полноэкранного режима окна
    driver.manage().window().maximize();
    //Задание неявного ожидания
    driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    return driver;
  }
}
