package readProperties;

import org.testng.annotations.Test;

import java.io.IOException;

public class PropertiesTest {

  @Test
  public void readFromProperties() throws IOException {
    System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
    String urlFromProp = System.getProperty("url");
    System.out.println(urlFromProp);

    String user = System.getProperty("users.client.login");
    System.out.println(user);

    String password = System.getProperty("users.client.password");
    System.out.println(password);
  }
  @Test
  public void readFromConf(){
    String urlFromConf = ConfigProvider.URL;
    String client = ConfigProvider.GAGANOV;
    Boolean isAdmin = ConfigProvider.IS_ADMIN;
    Integer age = ConfigProvider.readConfig().getInt("age");
    System.out.println(age);
    System.out.println(urlFromConf);
    System.out.println(client);
    System.out.println(isAdmin);
  }
}
