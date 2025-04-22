package apiauto;

//get all testNG Annotations
import org.testng.annotations.*;

import netscape.javascript.JSObject;

//importing the restAssured Libary
import static io.restassured.RestAssured.*;

//importing response from restAssured to capture the json response
import io.restassured.response.Response;




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

    //a variable that holds the user id that is going to be reused to finish the test
    private static String UserID;

    //GET METHOD
    // @Test(description = "Getting all users")
    // public void testGetAllUsers() {
    //     given().
    //     when().
    //         get("/public/v2/users")
    //     .then()
    //     .log().all() //log all the response
    //         .statusCode(200); //check if the status code is 200
    // }



    //GET THE SPECIFIC USER THAT JUST GOT CREATED
    @Test(description = "Getting all data from users with the id" , priority = 2, dependsOnMethods = "testPostAddingUser")
    public void testGetUsersById()
    {
        System.out.println("2. Mendapatkan Data User Dengan ID yang sudah di generate sebelumnya");
        System.out.println();
        given().
        when().
            get("/public/v2/users/" + UserID) //get the user with the id that was created before
        .then()
        .log().body()
            .statusCode(200); //check if the status code is 200
    }
    //GET METHOD


    //POST METHOD (THIS ONE MUST BE STARTED 1ST TO GET THE USERID BEFORE OTHER TEST)
    @Test(description = "Adding a new user" , priority = 1)
    public void testPostAddingUser()
    {
        System.out.println("1. Menambahkan User Baru Dengan Metode Post");
        System.out.println();
        //u need to create the requestBody first for the post Method
        // String name = "Radit";
        // String job = "Qa engineer";

          //now create a jason with the value above
        //   JSONObject requestBody = new JSONObject();
        //   requestBody.put("name", name);
        //   requestBody.put("job", job);  

        //creating request body  for gorest.co.in
        String name     = "Radit";
        String email    = "radityastanjung@gmail.com";
        String gender   = "male";
        String status   = "active";

        //creating jsonObject for gorest.co.in
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("gender", gender);
        requestBody.put("status", status);  

        
        //USER ASSERTION TO VALIDATE ALL KINDS OF THINGS U WANT TO VALIDATE IN THE RESPONSE
        //if one of the assertion is false then the test will fail

        Response response =
        given().
            body(requestBody.toString()).
        when().
            post("/public/v2/users").
        then().
        log().body()//check the log all
            .assertThat().statusCode(201) //check if the status code is 201
            .assertThat().body("name", equalTo(name)) //check if the name is the same as the name in the request body
            .assertThat().body("email", equalTo(email)) //check if the job is the same as the job in the request body
            .assertThat().body("gender", equalTo(gender))
            .assertThat().body("status", equalTo(status)) //check if the status is the same as the status in the request body
            .assertThat().body("$", hasKey("id")) //check if the response has the id key
        .extract() //<- pull out the validated response
        .response(); //<- now assignable to Response

        //take the id field from json
        UserID = response.jsonPath().getString("id"); //get the id from the response and assign it to the UserID variable
        //System.out.println(UserID);
        //System.out.println(response.getBody().asPrettyString());
    }       
    //POST METHOD

    //PUT METHOD
    @Test(description = "Updating a user", priority = 3, dependsOnMethods = "testPostAddingUser")
    public void testPutUpdateUser()
    {
       
        //NOTE THAT IN GOREST WHEN USING PUT IT STILL BEHAVES LIKE PATCH SO IN THIS INSTANCE
        //I WILL USE THE BEST PRACTICE TO USE PATCH METHOD 

        System.out.println("3. Mengupdate User dengan Metode PUT");
        System.out.println();

         String name     = "Herdiyanti";
         String email    = "herdiyantisitorus@gmail.com";
         String gender   = "female";
         String status   = "active";
 
         //creating jsonObject for gorest.co.in
         JSONObject requestPutBody = new JSONObject();
         requestPutBody.put("name", name);
         requestPutBody.put("email", email);
         requestPutBody.put("gender", gender);
         requestPutBody.put("status", status);  



        given().
            body(requestPutBody.toString()).
        when().
            put("/public/v2/users/" + UserID).
        then().
        log().all()//log all the response
            .assertThat().statusCode(200) //check if the status code is 200
            .assertThat().body("name", equalTo(name))
            .assertThat().body("email", equalTo(email)) //check if the job is the same as the job in the request body
            .assertThat().body("gender", equalTo(gender))
            .assertThat().body("status", equalTo(status)) //check if the status is the same as the status in the request body
            .assertThat().body("$", hasKey("id")); //check if the name is the same as the name in the request body
            //.assertThat().body("job", equalTo(job)); //check if the job is the same as the job in the request body
             //check if the response has the updatedAt key


    }
    //PUT METHOD

    //PATCH METHOD
    @Test(description = "Updating a user" , priority = 4, dependsOnMethods = "testPostAddingUser")
    public void testPatchUpdateUser()
    {
        System.out.println("4. Mengupdate User dengan metode PATCH");
        System.out.println();

        String name     = "Raditya Ratiningsih";
        String email    = "Radityanti@gmail.com";
        String gender   = "female";
        String status   = "active";

        //creating jsonObject for gorest.co.in
        JSONObject requestPatchBody = new JSONObject();
        requestPatchBody.put("name", name);
        requestPatchBody.put("email", email);
        requestPatchBody.put("gender", gender);
        requestPatchBody.put("status", status);  

        given().
            body(requestPatchBody.toString()).
        when().
            patch("/public/v2/users/" + UserID).
        then().
        log().all()//log all the response
            .assertThat().statusCode(200) //check if the status code is 200
            .assertThat().body("name", equalTo(name)); //check if the name is the same as the name in the request body

    }
    //PATCH METHOD

    //DELETE METHOD
    @Test(description = "Deleting a user", priority = 5, dependsOnMethods = "testPostAddingUser")
    public void testDeleteUser()
    {
        System.out.println("5. Menghapus User dengan metode DELETE");
        System.out.println();
        //DELETE METHOD
        given().
        when().
            delete("/public/v2/users/" + UserID ).
        then().
            log().all()//log all the response
            .statusCode(204); //check if the status code is 204

        //removing the value of USerID so that it can be reused in the next test
        UserID = null; //set the UserID to null so that it can be reused in the next test
    }
    //DELETE METHOD
    

    
}
