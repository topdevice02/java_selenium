package api.tests;

import api.model.ColorData;
import api.model.RegisterData;
import api.model.UserData;
import api.model.UserTimeData;
import api.response.SuccessRegistrationResponse;
import api.response.UnSuccessRegistrationResponse;
import api.response.UserTimeResponse;
import api.specification.Specification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

  private final static String URL = "https://reqres.in/";

  /**
   * Проверка того что аватар содержит айди
   */
  @Test
  public void checkAvatarAndIdTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());
    List<UserData> users = given()
            .when()
            .get("api/users?page=2")
            .then().log().all()
            .extract().body().jsonPath().getList("data", UserData.class);
    /**
     * Проверка того что аватар содержит айди
     */
    users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

    /**
     * Проверка того что емайл закончивается на @reqres.in
     */
    Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

    /**
     * Получение списка с аватарами
     */
    List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
    /**
     * Получение списка с айди
     */
    List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

    /**
     * Проверка в цикле того что аватар с индексом i содержит айди с индексом i
     */
    for (int i = 0; i < avatars.size(); i++) {
      Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
    }
  }

  /**
   * Тест успешной регистрации
   */
  @Test
  public void successRegistrationTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());
    Integer id = 4;
    String token = "QpwL5tke4Pnpja7X4";

    RegisterData user = new RegisterData("eve.holt@reqres.in", "pistol");

    SuccessRegistrationResponse successRegistrationResponse = given()
            .body(user)
            .when()
            .post("api/register")
            .then().log().all()
            .extract().as(SuccessRegistrationResponse.class);
    Assert.assertNotNull(successRegistrationResponse.getId());
    Assert.assertNotNull(successRegistrationResponse.getToken());
    Assert.assertEquals(id, successRegistrationResponse.getId());
    Assert.assertEquals(token, successRegistrationResponse.getToken());
  }

  /**
   * Тест НЕ успешной регистрации
   */
  @Test
  public void unSuccessRegistrationTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationError400());

    RegisterData user = new RegisterData("sydney@fife");

    UnSuccessRegistrationResponse unSuccessRegistration = given()
            .body(user)
            .when()
            .post("api/register")
            .then().log().body()
            .extract().as(UnSuccessRegistrationResponse.class);

    Assert.assertEquals(unSuccessRegistration.getError(), "Missing password");
  }

  /**
   * Тест сравнения полученных годов с отсортированными годами
   */
  @Test
  public void sortedYearsTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());
    /**
     * Получаем список с цветами
     */
    List<ColorData> colors = given()
            .when()
            .get("api/unknown")
            .then().log().body()
            .extract().body().jsonPath().getList("data", ColorData.class);
    /**
     * Из общего списка выделяем список из годов
     */
    List<Integer> years = colors.stream().map(ColorData::getYear).collect(Collectors.toList());
    /**
     * Сортируем список с годами по возрастанию
     */
    List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
    /**
     * Проверяем эквивалентны ли полученный список и отсортированный
     */
    Assert.assertEquals(years, sortedYears);
  }

  /**
   * Тест удаления пользователя со второй страницы
   */
  @Test
  public void deleteUserTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationUnique(204));
    given()
            .when().delete("api/users/2")
            .then().log().body();
  }

  /**
   * Тест проверки серверной и системной дат пользователя
   */
  @Test
  public void timeUserTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());
    UserTimeData user  = new UserTimeData("morpheus","zion resident");

    UserTimeResponse userTimeResponse = given()
            .body(user)
            .when()
            .put("/api/users/2")
            .then().log().body()
            .extract().as(UserTimeResponse.class);

    /**
     * Регулярное выражение которое выделяет последние пять символов в дате
     */
    String regex = "(.{5})$";
    /**
     * Получаем системную дату и заменяем последние 5 символов на "ничего"
     */
    String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
    /**
     * Получаем серверную дату и заменяем последние 5 символов на "ничего"
     */
    String updatedAt = userTimeResponse.getUpdatedAt().replaceAll(regex, "");
    /**
     * Проверяем что серверная дата эквивалентна системной дате (возможна погрешность)
     */
    Assert.assertEquals(updatedAt, currentTime);
  }
}
