package gherkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class gradingTest {

    //create 1 test case
    @Test
    void fiftyNineShouldReturnF() {
        var grading = new grading();
        assertEquals('F', grading.grade(59));
    }

    //create 2 test case
    @Test
    void sixtyNineShouldReturnD() {
        var grading = new grading();
        assertEquals('D', grading.grade(69));
    }

    //create 3 test case 
    @Test
    void seventyNineShouldReturnC() {
        var grading = new grading();
        assertEquals('C', grading.grade(79));
    }

    //create 4 test case
    @Test
    void eightyNineShouldReturnB() {
        var grading = new grading();
        assertEquals('B', grading.grade(89));
    }

    //create 5 test case
    @Test
    void ninetyNineShouldReturnA() {
        var grading = new grading();
        assertEquals('A', grading.grade(99));
    }

    //create 6 test case
    @Test
    void negativeScoreShouldThrowException() {
        var grading = new grading();
        try {
            grading.grade(-1);
        } catch (IllegalArgumentException e) {
            assertEquals("Score must be greater than or equal to 0", e.getMessage());
        }
    }

    

    //create one edge case
    // @Test
    // void eightyScoreShouldReturnB() {
    //     var grading = new grading();
    //     assertEquals('B', grading.grade(80));
    // }


    //EDGE CASE U CAN USE BOUNDARY VALUE ANALYSIS IN THIS
    //create all test case for edge case
    @Test
    void zeroScoreShouldReturnF() {
        var grading = new grading();
        assertEquals('F', grading.grade(0));
    }

    @Test
    void sixtyScoreShouldReturnD() {
        var grading = new grading();
        assertEquals('D', grading.grade(60));
    }

    @Test
    void seventyScoreShouldReturnC() {
        var grading = new grading();
        assertEquals('C', grading.grade(70));
    }

    @Test
    void eightyScoreShouldReturnB() {
        var grading = new grading();
        assertEquals('B', grading.grade(80));
    }

    @Test
    void ninetyScoreShouldReturnA() {
        var grading = new grading();
        assertEquals('A', grading.grade(90));
    }

    @Test
    void oneHundredScoreShouldReturnA() {
        var grading = new grading();
        assertEquals('A', grading.grade(100));
    }
}
