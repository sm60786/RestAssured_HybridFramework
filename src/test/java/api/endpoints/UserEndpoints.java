package api.endpoints;

import api.payload.UserPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndpoints {

    public static Response createUser(UserPayload payload)
    {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;
    }


    public static Response GetUser(String userName)
    {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)

                .when()
                .get(Routes.get_url);

        return response;
    }


    public static Response UpdateUser(String userName, UserPayload payload)
    {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)


                .when()
                .put(Routes.put_url);

        return response;
    }


    public static Response DeleteUser(String userName)
    {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)


                .when()
                .delete(Routes.delete_url);

        return response;
    }

}
