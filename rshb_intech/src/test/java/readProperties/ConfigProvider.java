package readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
  Config config = readConfig();

  static Config readConfig(){
    return ConfigFactory.systemProperties().hasPath("testProfile")
            ? ConfigFactory.load(ConfigFactory.systemProperties().getConfig("testProfile"))
            : ConfigFactory.load("application.conf");
  }

  String URL = readConfig().getString("url");

  String GAGANOV = readConfig().getString("usersParams.client.login");
  Boolean IS_ADMIN = readConfig().getBoolean("usersParams.admin.isAdmin");


}
