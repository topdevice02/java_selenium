package api.tests;

import api.model.UserData;
import api.model.UserGorestData;
import api.specification.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GorestTest {

  private final static String URL = "https://gorest.co.in/";

  @Test
  public void getUsersTest(){
    Specification.installSpecification(Specification.requestSpecification(URL), Specification.responseSpecificationOK200());

    List<UserGorestData> users = given()
            .when()
            .get("public/v2/users")
            .then().log().all()
            .extract().body().jsonPath().getList("", UserGorestData.class);

    String status = "active";
    String gender = "male";

    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getStatus().equals(status) && (users.get(i).getGender().equals(gender))
              && (users.get(i).getName().contains("ch")) && (users.get(i).getId().toString().endsWith("0")))

        Assert.assertEquals(users.get(i).getId().toString(), "2390");
    }
  }
}
