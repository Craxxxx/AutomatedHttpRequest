package apiauto;

//get all testNG Annotations
import org.testng.annotations.*;

//importing the restAssured Libary
import static io.restassured.RestAssured.*;




import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.*; //importing all matchers from hamcrest library

import static org.hamcrest.MatcherAssert.*; //importing all matchers from hamcrest library
import org.json.JSONObject;


public class APItest extends BaseTest {

    //given() : start building your http request
    //when() : Indicated that you are ready to send the request
    //get() : send the get request to the specified endpoint
    //then() : used to validate the response of the request
    //statusCode() : used to check the status code of the response
    //body() : used to check the body of the response
    //assertThat() : used to assert the response of the request
    

    //GET METHOD
    @Test(description = "Getting all users")
    public void testGetAllUsers() {
        given().
        when().
            get("/api/users?page=1")
        .then()
        .log().all() //log all the response
            .statusCode(200); //check if the status code is 200
    }



    @Test(description = "Getting all data from users with the id 2")
    public void testGetUsersById()
    {
        given().
        when().
            get("/api/users/2")
        .then()
        .log().body()
            .statusCode(200); //check if the status code is 200
    }
    //GET METHOD


    //POST METHOD
    @Test(description = "Adding a new user")
    public void testPostAddingUser()
    {
        //u need to create the requestBody first for the post Method
        String name = "Radit";
        String job = "Qa engineer";

        //now create a jason with the value above
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("job", job);  
        
        //USER ASSERTION TO VALIDATE ALL KINDS OF THINGS U WANT TO VALIDATE IN THE RESPONSE

        given().
            body(requestBody.toString()).
        when().
            post("/api/users").
        then().
        log().all()//check the log all
            .assertThat().statusCode(201) //check if the status code is 201
            .assertThat().body("name", equalTo(name)) //check if the name is the same as the name in the request body
            .assertThat().body("job", equalTo(job)) //check if the job is the same as the job in the request body
            .assertThat().body("$", hasKey("id")) //check if the response has the id key
            .assertThat().body("$", hasKey("createdAt")); //check if the response has the createdAt key;

    }       
    //POST METHOD
    

    
}
