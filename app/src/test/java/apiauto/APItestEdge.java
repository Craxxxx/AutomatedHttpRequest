package apiauto;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.json.JSONObject;


public class APItestEdge extends BaseTest {

    private static String UserID;
    private static String UserID2;

    //setup the data 
     @BeforeClass
    public void StartingPoint()
    {
        System.out.println("THIS IS THE Edge TEST CASES FOR GOREST.CO.IN API TESTING");
        System.out.println("Setting up all the prerequisites for the test");
        System.out.println(".");
        System.out.println("...");
        System.out.println("....");
        System.out.println(".....");
        System.out.println("Make sure u already add the token in 'baseTest.java' file");
        System.out.println("or else it wont work...");
        System.out.println("....");
        System.out.println("Starting the test");
        System.out.println(" ");
        System.out.println("  ");
        System.out.println("   ");

        //setup the data for DUPLICATION TESTING

        //creating request body for gorest.co.in
        String name   =  "Yanti Tanjung";
        String email  =  "YantiTanjung@gmail.com";
        String gender =  "female";
        String status =  "active"; 

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("gender", gender);
        requestBody.put("status", status);


          Response response =
        given().
            body(requestBody.toString()).
        when().
            post("/public/v2/users").
        then().
        log().body()
        .extract() //<- pull out the validated response
        .response(); //<- now assignable to Response

        //take the id field from json
        UserID = response.jsonPath().getString("id"); //get the id from the response and assign it to the UserID variable 
        System.out.println("");

    }

    
    // 1. Very Large Payloads
     @Test(description = "POST with extremely long name")
     public void testPostVeryLargeName() {
         String largeName = "A".repeat(10_000);
         JSONObject body = new JSONObject();
         body.put("name", largeName);
         body.put("email", "edgecase1@example.com");
         body.put("gender", "male");
         body.put("status", "active");
 
         given()
             .body(body.toString())
         .when()
             .post("/public/v2/users")
         .then()
             .log().body()
             .statusCode(anyOf(equalTo(201), equalTo(422))); // depends on API validation
     }

    //2. Insert the data same as the length boundaries of the field
    @Test(description = "POST with max-length name and email")
    public void testPostMaxLengthFields() {
        String longName = "X".repeat(200); // adjust based on actual API docs 200 characters
        String longEmail = "a".repeat(64) + "@example.com";

        JSONObject body = new JSONObject();
        body.put("name", longName);
        body.put("email", longEmail);
        body.put("gender", "female");
        body.put("status", "active");


        Response response =
        given()
            .body(body.toString())
        .when()
            .post("/public/v2/users")
        .then()
            .log().body()
            .statusCode(anyOf(equalTo(201), equalTo(422)))
            .extract()
            .response(); //<- now assignable to Response

        UserID2 = response.jsonPath().getString("id");
    }


    //Patching with emptu status 
    @Test(description = "PATCH with empty string status", priority = 1)
    public void testPatchEmptyStatus() {
        JSONObject body = new JSONObject();
        body.put("status", "");
 
        given()
            .body(body.toString())
        .when()
            .patch("/public/v2/users/" + UserID)
        .then()
            .log().body()
            .statusCode(anyOf(equalTo(200), equalTo(422)));
     }
 

     //CLEAR TEST RESOURCES
    @Test(description = "Deleting the user", priority = 2, dependsOnMethods = "testPatchEmptyStatus")   
    public void testDeleteUser()
    {
        System.out.println("Menghapus User dengan metode DELETE");
        //DELETE METHOD
        given().
        when().
            delete("/public/v2/users/" + UserID).
        then().
            log().body()//log all the response
            .statusCode(204); //check if the status code is 204

        //removing the value of USerID so that it can be reused in the next test
        UserID = null; //set the UserID to null so that it can be reused in the next test
    }
    //CLEAR TEST RESOURCES

    
}
