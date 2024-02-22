package api.testcases;

import api.endpoints.UserEndpoints;
import api.payload.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    Faker faker;
    UserPayload userPayload;
    public static Logger logger;

    @BeforeClass
    public void generateTestData() {
        faker = new Faker();
        userPayload = new UserPayload();

        /*
        Generate Random Data each time using javafaker by creating Object of Faker Class
         */
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //obtain logger

        logger = LogManager.getLogger("RestAssuredAutomationFramework_test");
    }

    @Test(priority = 0)
    public void testCreateUser() {
        Response response = UserEndpoints.createUser(userPayload);
        // Log Response
        response.then().log().all();
        // Validation
        Assert.assertEquals(response.getStatusCode(), 200, "Unsuccessful Creating User");
    }

    @Test(priority = 1)
    public void testGetUser() {
        Response response = UserEndpoints.GetUser(this.userPayload.getUsername());
        // Log response
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Unsuccessful Getting User");
    }

    @Test(priority = 2)
    public void testUpdateUser() {
        userPayload.setFirstName(faker.name().firstName());
        Response response = UserEndpoints.UpdateUser(this.userPayload.getUsername(), userPayload);
        // Log response
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "Unsuccessful Updating User");
    }

    @Test(priority = 3)
    public void testDeleteUser() {
        Response response = UserEndpoints.DeleteUser(this.userPayload.getUsername());
        // Log response
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "Unsuccessful Updating User");
    }

}
