package tests;

import core.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import readProperties.ConfigProvider;


public class DboTest extends BaseTest {

  @Test
  public void checkLoadPage() throws InterruptedException {

    LoginPage loginPage = new LoginPage();
    loginPage.authorizationClient();
  }
}
