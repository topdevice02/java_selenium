package api;

import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

  private final static String URL = "https://reqres.in/";

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

  @Test
  public void successRegistrationTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());
    Integer id = 4;
    String token = "QpwL5tke4Pnpja7X4";

    RegisterData user = new RegisterData("eve.holt@reqres.in", "pistol");

    SuccessRegistration successRegistration = given()
            .body(user)
            .when()
            .post("api/register")
            .then().log().all()
            .extract().as(SuccessRegistration.class);
    Assert.assertEquals(id, successRegistration.getId());
    Assert.assertEquals(token, successRegistration.getToken());
  }
}
