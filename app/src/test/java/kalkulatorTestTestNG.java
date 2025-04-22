//adding all assertions
import static org.testng.Assert.*;
//adding all assertions


//all annotations that usually used on testNG
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
//importing the calculator class from gherkin package
import gherkin.Kalkulator;

public class kalkulatorTestTestNG {

    //other annotations to use for preparing the test
    //@beforeSuite = mainly used for global set up such ass initializing database conn, setting up a server
    //               Or Loading a global configurations

    private Kalkulator kalkulator; //declaring an object of the Kalkulator class

    //i can use the before class annotations to setup class object
    @BeforeClass
    public void setupClass() {
        System.out.println("Before Class");
        kalkulator = new Kalkulator();
    }
    //i can use the before class annotations to setup class object

    @BeforeMethod
    public void setupMethod() {
        System.out.println("Before Method: Preparing test-specific setup");
    }

    @Test
    public void testPenjumlahan() {
        System.out.println("TestPenjumlahan");
        assertEquals(2, kalkulator.penjumlahan(1, 1));
        assertTrue(2 == kalkulator.penjumlahan(1, 1));
    }

   
    @Test
    public void testPengurangan() {
        System.out.println("TestPengurangan");
        assertEquals(0, kalkulator.pengurangan(2, 2));
        assertTrue(0 == kalkulator.pengurangan(2, 2));
    }

    @Test
    public void testPerkalian() {
        System.out.println("TestPerkalian");
        assertEquals(4, kalkulator.perkalian(2, 2));
        assertTrue(4 == kalkulator.perkalian(2, 2));
    }

    @Test
    public void testPembagian() {
        System.out.println("TestPembagiam");
        assertEquals(1, kalkulator.pembagian(2, 2));
        assertTrue(1 == kalkulator.pembagian(2, 2));
    }


    //THE PLACEMENT OF THE CODE  DOES NOT AFFECT THEIR EXECUTION ORDER
    @AfterMethod
    public void tearDownMethod() {
        System.out.println("After Method: Cleaning up after test");
    }


    @AfterClass
    public void tearDownClass() {
        System.out.println("After Class: Releasing resources");
        kalkulator = null; //cleaning up the object
    }
    //THE PLACEMENT OF THE CODE  DOES NOT AFFECT THEIR EXECUTION ORDER
}
