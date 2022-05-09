package ui.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import pages.base.BasePage;
import pages.cian_home.CianHomePage;
import pages.listing.CianListingPage;

import static common.CommonActions.createDriver;
import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static common.Config.HOLD_BROWSER_OPEN;

public class BaseTest {
  protected WebDriver driver = createDriver();
  //
  protected BasePage basePage = new BasePage(driver);
  protected CianHomePage cianHomePagePage = new CianHomePage(driver);
  protected CianListingPage cianListingPage = new CianListingPage(driver);

  @AfterTest
  //Очистка куки и истории перед запуском тестов
  public void clearCookiesAndLocalStorage(){
    if(CLEAR_COOKIES_AND_STORAGE){
      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
      driver.manage().deleteAllCookies();
      javascriptExecutor.executeScript("window.sessionStorage.clear()");
    }
  }

  @AfterSuite (alwaysRun = true)
  //Закрытие браузера после выполнения теста
  public void close(){
    if(HOLD_BROWSER_OPEN){
      driver.quit();
    }
  }
}
