package api.tests;

import api.model.ColorData;
import api.model.RegisterData;
import api.model.UserData;
import api.model.UserTimeData;
import api.response.CreateUserResponse;
import api.response.SuccessRegistrationResponse;
import api.response.UnSuccessRegistrationResponse;
import api.response.UserTimeResponse;
import api.specification.Specification;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

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
            .get("api/users?page=1")
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

  @Test
  public void checkAvatarAndIdTestNoPojo(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());
    Response response = given()
            .when()
            .get("api/users?page=1")
            .then().log().body()
          //  .body("data.id", notNullValue())
           // .body("page", equalTo(1))
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    List<String> emails = jsonPath.get("data.email");
    List<Integer> ids = jsonPath.get("data.id");
    List<String> avatars = jsonPath.get("data.avatar");

    for (int i = 0; i < avatars.size(); i++) {
      Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
    }

    Assert.assertTrue(emails.stream().allMatch(x -> x.endsWith("@reqres.in")));
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
   * Тест успешной авторизации без POJO классов
   */
  @Test
  public void successLoginTestNoPojo(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());

    Map<String,String> user = new HashMap<>();
    user.put("email", "eve.holt@reqres.in");
    user.put("password", "cityslicka");

    Response response = given()
            .body(user)
            .when()
            .post("api/login")
            .then().log().body()
            //.body("token", equalTo("QpwL5tke4Pnpja7X4"));
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    String token = jsonPath.get("token");

    Assert.assertEquals(token, "QpwL5tke4Pnpja7X4");
  }

  /**
   * Тест НЕ успешной авторизации без POJO классов
   */
  @Test
  public void unSuccessLoginTestNoPojo(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationError400());

    Map<String, String> user = new HashMap<>();
    user.put("email", "peter@klaven");

    Response response = given()
            .body(user)
            .when()
            .post("api/login")
            .then().log().body()
            //.body("token", equalTo("QpwL5tke4Pnpja7X4"));
            .extract().response();

    JsonPath jsonPath = response.jsonPath();
    String error = jsonPath.get("error");

    Assert.assertEquals(error, "Missing password");
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

  @Test
  public void creationUserTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationUnique(201));

    String name = "morpheus";
    String job = "leader";

    UserTimeData user = new UserTimeData(name, job);

    CreateUserResponse createUserResponse = given()
            .body(user)
            .when()
            .post("api/users")
            .then().log().body()
            .extract().as(CreateUserResponse.class);

    Assert.assertEquals(createUserResponse.getName(), name);
    Assert.assertEquals(createUserResponse.getJob(), job);
    Assert.assertNotNull(createUserResponse.getId());
    Assert.assertNotNull(createUserResponse.getCreatedAt());
  }
  
}
