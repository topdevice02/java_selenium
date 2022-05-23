package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AutoPaysPage;
import pages.ConnectAutoPayPage;
import pages.LoginPage;
import pages.MainPage;
import readProperties.ConfigProvider;


public class DboTest extends BaseTest {

  MainPage mainPage;

  @BeforeTest

  public void ensurePrecondition() throws InterruptedException {

    mainPage = new LoginPage().authorizationClient(ConfigProvider.GAGANOV, ConfigProvider.PASSWORD);
  }

  @Test
  public void checkLoadPageTest() {

    mainPage.printAllProduct();

    System.out.println("Личный кабинет загружен...");

    Assert.assertEquals(driver.getTitle(), "Интернет-банк РСХБ");
    Assert.assertEquals(mainPage.getName(), "Гаганов Константин");
  }

  @Test
  public void createAutoPayTest() throws InterruptedException {

    ConnectAutoPayPage connectAutoPayPage = mainPage.clickAutoPayButton()
            .fillFormCreateAutoPay()
            .fillAndConfirmConnectAutoPay();

    System.out.println("Автоплатеж создан...");

    String expectedResult = connectAutoPayPage.getNameAutoPay();
    String actualResult = mainPage.getNameAutoPayFirst();

    Assert.assertEquals(actualResult, expectedResult);

  }

  @Test

  public void suspendAutoPayTest() throws InterruptedException {
    AutoPaysPage autoPaysPage = mainPage.goToAutoPays().suspendAutoPay();

    String status = autoPaysPage.getStatus();

    Assert.assertEquals(status, "Приостановлен");
  }

  @Test

  public void resumeAutoPayTest() throws InterruptedException {
    AutoPaysPage autoPaysPage = mainPage.goToAutoPays().resumeAutoPay();

    String status = autoPaysPage.getStatus();

    Assert.assertEquals(status, "Активный");
  }
}
