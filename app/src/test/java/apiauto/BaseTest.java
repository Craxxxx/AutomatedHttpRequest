package apiauto;

//import restAssured.RestAssured;
import io.restassured.RestAssured;

//import requestSpecBuilder from restAssured
import io.restassured.builder.RequestSpecBuilder;

//import requestSpec from restAssured
import io.restassured.specification.RequestSpecification;

//import all testNG annotations
import org.testng.annotations.*;


public class BaseTest {
    //io.restassured.specification yang menyediakan metode-metode 
    //fluent untuk mengonfigurasi detail HTTP request dalam pengujian API menggunakan REST Assured.
    protected static RequestSpecification requestSpec;
    //using protected so that the child class can access this variable


    //setup method that will always initialize before the test
    @BeforeSuite
    public void setup()
    {
        //1. configuring base URI and port for the api (bisa di override via -D)
        RestAssured.baseURI = System.getProperty("api.baseUri","https://gorest.co.in/");
        RestAssured.port    = Integer.parseInt(System.getProperty("api.port", "443")); //default port for https

        //2. Relaxed HTTPS (untuk self-signed certificate atau cert dev)
        RestAssured.useRelaxedHTTPSValidation();
        //this is used for:  
            //a. Disables strict SSL/TLS certificate validation
            //b. Allows testing APIs with self-signed certificates
            //c. Useful in development/testing environments

        //DEFINE MY BEARER TOKEN (For gorest.co.in)
        String token = "a7911d0f53a0b598588709206faa2463f8773c871a63881024fccdb5b09ae027";

        //3. make the default requestSPecification
        requestSpec = new RequestSpecBuilder()
            .setContentType("application/json")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer " + token) //set the bearer token
            // .addFilter(new RequestLoggingFilter()) // log tiap request
            .build();

        //this is used for:
            //a. Set default request headers, content type, and other configurations
            //b. Create a reusable request specification for all tests
            //c. Simplifies the process of creating requests with common settings
        RestAssured.requestSpecification = requestSpec;

        //4. Automize the request and response logging
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(); //log request and response if validation fails
        
    }
}
