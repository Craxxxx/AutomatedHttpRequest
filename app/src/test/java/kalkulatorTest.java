import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import gherkin.Kalkulator;

public class kalkulatorTest {
    
    @Test //in junit 5 u dont need to make it public and dont need to return anything so u can use void
    //TEST SCENARIO 1
    void testPenjumlahan() {
        //kalkulator on the left most is the data type and the kalkulator next to it its the variable name 
        var kalkulator = new Kalkulator();
        //the code above is creating a new object from the kalkulator class and insert it into the kalkulator variable
        
        //check if 1+1 = 2
        assertEquals(2, kalkulator.penjumlahan(1, 1) );

        //another ways to write the code above u can use asserttrue
        assertTrue(2 == kalkulator.penjumlahan(1, 1));




        // in a unit test we need to check for multiple conditions like above
        // if ur code is written this way

        //  public int penjumlahan(int a, int b) {
        //      return a * b;
        //  } 

        //  if u use asserttrue it will return true because 1*1 = 1
        //  but if u use assertequals it will return false because 1*1 != 2
        // thats why u need to use more than one condition to check the code
        // if atleast one of the condition is false then the test will fail

    }


    //TEST the pennjumlahan method

    //test case 1
    @Test
    void testpennjumlahan_1plus1equals2() {
        Kalkulator kalkulator = new Kalkulator();
        assertEquals(4, kalkulator.pennjumlahan(2, 2) );
        //assertTrue(2 == kalkulator.pennjumlahan(1, 1));

    }


    //test case 2
    @Test
    void testpennjumlahan_7plus3equals10() {
        Kalkulator kalkulator = new Kalkulator();
        assertEquals(10, kalkulator.pennjumlahan(7, 3) );
        //assertTrue(10 == kalkulator.pennjumlahan(7, 3));

    }


}
