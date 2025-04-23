package apiauto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.startsWith;


import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class APItestFail extends BaseTest {
    

    private static String UserID;

    @BeforeClass
    public void StartingPoint()
    {
        System.out.println("THIS IS THE NEGATIVE TEST CASES FOR GOREST.CO.IN API TESTING");
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


    //GET NEGATIVE TEST
    @Test(description = "Non Existent User ID", priority = 1)
    public void testGetUsersById1()
    {
        System.out.println("1. Negative get : Non Existent User ID");
        
        given().
        when().
            get("/public/v2/users/123") //get the user with the id that was created before
        .then()
        .log().body()
            .statusCode(404); //THE RESULTS SHOULD BE 404 NOT FOUND BECAUSE THE ID IS NON EXISTENT

        System.out.println();
    }

    @Test(description = "2. Invalid User ID", priority = 2)
    public void testGetUsersById2()
    {
        System.out.println("Negativee get : Invalid User ID");

        given().
        when().
            get("/public/v2/users/abc"). //Using invalid USER ID
        then().
        log().body()
            .statusCode(anyOf(equalTo(400),equalTo(404))); //the results should be eiter 400 or 404
    }
    //GET NEGATIVE TEST


    //POST NEGATIVE TEST
    @Test(description = "3. Missing required Fields", priority = 3)
    public void testPostAddingUser1()
    {
        System.out.println("Negative Post : Missing required fields");

        //creating request body  for gorest.co.in
        String name = "Yanti";
        //missing email fields
        String gender   = "male";
        String status   = "active";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("gender", gender);
        requestBody.put("status", status);

        given().
            body(requestBody.toString()).
        when().
            post("/public/v2/users").
        then().
        log().body()//log the response body 
            .assertThat().statusCode(422).
            //BROAD CONTRACT CHECKING
            assertThat().body("message", hasItem("can't be blank"));
    } 

    @Test(description = "4. INVALID ENUM VALUE", priority = 4)
    public void testPostAddingUser2()
    {
        System.out.println("Negative Post : invalid enum value for gender");

        //creating request body  for gorest.co.in
        String name = "Yanti";
        String email = "Rantiningsih@gmail.com";
        String gender   = "non-binary"; //invalid enum value should be female or male
        String status  = "active";

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("gender", gender);
        requestBody.put("status", status);

        given().
            body(requestBody.toString()).
        when().
            post("/public/v2/users").
        then().
        log().body()//log the response body 
            .assertThat().statusCode(422).
            //BROAD CONTRACT CHECKING
            assertThat().body("message", hasItem("can't be blank, can be male of female"));  
    } 

    @Test(description = "Duplicate email", priority = 5)
    public void testPostAddingUser3()
    {
        System.out.println("5. Negative Post : Duplicate emails");

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
        log().body()//check the log all
            //VALUE CHECKING
            .assertThat().statusCode(422) //Data validation failed
            .assertThat().body("message", hasItem("has already been taken")) //the email is already been taken
        .extract() //<- pull out the validated response
        .response(); //<- now assignable to Response

    }
    //POST NEGATIVE TEST



    //PUT NEGATIVE TEST
    @Test(description = "Missing all updateable field (request body is empty)", priority = 6)
    public void testPutUser1()
    {
        System.out.println("6. Negative Put : Missing all updateable field (request body is empty)");

        //creating request body  for gorest.co.in
        JSONObject requestBody = new JSONObject();//empty request body

        given().
            body(requestBody.toString()).
        when().
            put("/public/v2/users/" + UserID).
        then().
            log().body()
            .assertThat().statusCode(422); //this somehow will still succed and return 200
            //because GoRest’s implementation treats PUT as a merge (partial update) rather than a strict full-replace. When you send {} it applies “no changes” to the resource and still returns success. 

    }


     //PUT NEGATIVE TEST
    @Test(description = "updating the enum field to invalid value", priority = 7)
    public void testPutUser2()
    {
        System.out.println("7. updating the enum field to invalid value");


        String gender =  "Asexual";
        //creating request body  for gorest.co.in
        JSONObject requestBody = new JSONObject();//empty request body
        requestBody.put("gender", gender);

        given().
            body(requestBody.toString()).
        when().
            put("/public/v2/users/" + UserID).
        then().
            log().body()
            .assertThat().statusCode(422).
            assertThat().body("message", hasItem("can't be blank, can be male of female"));

    }
    //PUT NEGATIVE TEST



    //PATCH NEGATIVE TEST
    @Test(description = "Setting email to existing email", priority = 8)
    public void testPatch1()
    {
        System.out.println("8. Setting email to existing email");

        String email = "YantiTanjung@gmail.com";
        //creating request body  for gorest.co.in
        JSONObject requestBody = new JSONObject();//empty request body
        requestBody.put("email", email);

        given().
           body(requestBody.toString()).
        when().
            patch("/public/v2/users/" + UserID).
        then().
            log().body()
            .assertThat().statusCode(200); //success again

    }
    //PATCH NEGATIVE TEST



    //CLEAR TEST RESOURCES
    @Test(description = "Deleting the user", priority = 9)
    public void testDeleteUser()
    {
        System.out.println("9. Menghapus User dengan metode DELETE");
        //DELETE METHOD
        given().
        when().
            delete("/public/v2/users/" + UserID ).
        then().
            log().body()//log all the response
            .statusCode(204); //check if the status code is 204

        //removing the value of USerID so that it can be reused in the next test
        UserID = null; //set the UserID to null so that it can be reused in the next test
    }
    //CLEAR TEST RESOURCES


 
    
}
