//the import below is used to import the RestAssured library
import io.restassured.RestAssured;

//the import before all below is used to import the BeforeAll annotation
import org.junit.jupiter.api.BeforeAll;

//the import below is used to import the Test annotation
import org.junit.jupiter.api.Test;

//the import assertequals below is used to import the assertEquals method
import static org.junit.jupiter.api.Assertions.assertEquals;

public class integrationTest {

    //testcase 1
    @Test
    void testGetUser(){

        //test GET users ke public api https://regres.in/api/users?page=2
        //creating a new variable to contain the get response
        var Response = RestAssured.get("https://reqres.in/api/users?page=2");

          //validasi response code 200
        assertEquals(200, Response.getStatusCode());
    }
}
