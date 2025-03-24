package gherkin;

public class grading {
      
    public char grade(int score) {

        if (score < 0) {
            throw new IllegalArgumentException("Score must be greater than or equal to 0");
        }
        else if (score < 60) {
            return 'F';
        } else if (score < 70) {
            return 'D';
        } else if (score < 80) {
            return 'C';
        } else if (score < 90) {
            return 'B';
        } else {
            return 'A';
        }
    }
}
