package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ConnectAutoPayPage;
import pages.CreateAutoPayPage;
import pages.LoginPage;
import pages.MainPage;
import readProperties.ConfigProvider;


public class DboTest extends BaseTest {

  @Test
  public void checkLoadPageTest() throws InterruptedException {

    MainPage mainPage = new LoginPage().authorizationClient(ConfigProvider.GAGANOV, ConfigProvider.PASSWORD).printAllProduct();

    Assert.assertEquals(driver.getTitle(), "Интернет-банк РСХБ");
    Assert.assertEquals(mainPage.getName(), "Гаганов Константин");
  }

  @Test
  public void createAutoPayTest() throws InterruptedException {

    ConnectAutoPayPage connectAutoPayPage = new LoginPage()

            .authorizationClient(ConfigProvider.GAGANOV, ConfigProvider.PASSWORD)
            .clickAutoPayButton()
            .fillFormCreateAutoPay()
            .fillAndConfirmConnectAutoPay();

    MainPage mainPage = new MainPage();

    String expectedResult = connectAutoPayPage.getNameAutoPay();
    String actualResult = mainPage.getNameAutoPayFirst();

    Assert.assertEquals(actualResult, expectedResult);

    System.out.println("На странице создания: " + expectedResult);
//    System.out.println("На главной странице: " + mainPage.getNameAutoPayFirst());





  }
}
