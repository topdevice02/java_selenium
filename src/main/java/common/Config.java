package common;

/**
 * Конфигурация проекта
 */
public class Config {

  public static final String PLATFORM_AND_BROWSER = "win.chrome";

  /**
   * Очистить куки браузера после каждой итерацией
   * Если true - очистить куки
   */
  public static final Boolean CLEAR_COOKIES_AND_STORAGE = true;

  /**
   * Держать браузер открытым после выполнения теста
   * Если true - браузер закрыть
   */
  public static final Boolean HOLD_BROWSER_OPEN = true;
}
