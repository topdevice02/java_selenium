package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import readProperties.ConfigProvider;


public class DboTest extends BaseTest {

  @Test
  public void checkLoadPage() throws InterruptedException {

    MainPage mainPage = new LoginPage().authorizationClient(ConfigProvider.GAGANOV, ConfigProvider.PASSWORD).printAllProduct();

    Assert.assertEquals(driver.getTitle(), "Интернет-банк РСХБ");
//    Assert.assertEquals(mainPage.getName(), "Гаганов Константин");

  }
}
