package api.testcases;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import api.endpoints.UserEndPoints2;
import api.payload.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest2 {

    Faker faker;
    UserPayload userPayload;
    public static Logger logger;

    @BeforeClass
    public void generateTestData()
    {
        faker = new Faker();
        userPayload = new UserPayload();

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

    @Test(priority=1)
    public void testCreateUser()
    {
        Response response = UserEndPoints2.createUser(userPayload);

        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);

        //log
        logger.info("Create User executed.");
    }


    @Test(priority=2)
    public void testGetUserData()
    {
        Response response = UserEndPoints2.getUser(this.userPayload.getUsername());

        System.out.println("Read User Data.");
        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);

        //log
        logger.info("Get User Data executed.");
    }

    @Test(priority=3)
    public void testUpdateUser()
    {
        userPayload.setFirstName(faker.name().firstName());
        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);


        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);

        //Read User data to check if first name is updated

        Response responsePostUpdate = UserEndPoints2.getUser(this.userPayload.getUsername());

        System.out.println("After Update User Data.");

        responsePostUpdate.then().log().all();

        //log
        logger.info("Update User executed.");

    }

    @Test(priority=4)
    public void testDeleteUser()
    {

        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());

        System.out.println("Delete User Data.");

        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);


        //log
        logger.info("Delete User executed.");


    }

}
