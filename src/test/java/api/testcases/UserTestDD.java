package api.testcases;

import api.endpoints.UserEndpoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTestDD {

    @Test(priority=1,dataProvider = "AllData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId, String UserName, String fname, String lname, String email, String pwd, String phone)
    {

        UserPayload userPayload = new UserPayload();

        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(lname);
        userPayload.setPassword(email);
        userPayload.setPhone(phone);
        Response response = UserEndpoints.createUser(userPayload);

        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority=3,dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username)
    {

        Response response = UserEndpoints.DeleteUser(username);

        System.out.println("Delete User Data.");

        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);



    }

    @Test(priority=2,dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testGetUserData(String username)
    {

        Response response = UserEndpoints.GetUser(username);

        //System.out.println("Get User Data.");

        //log response
        response.then().log().all();


        //validation
        Assert.assertEquals(response.getStatusCode(),200);


    }

}
