import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        try {
            // Step 1: Read a polynomial from the input file (formatted as "1+1x1+2x3")
            File inputFile = new File("polynomial_input.txt");
            Polynomial p1 = new Polynomial(inputFile);
            System.out.println("Polynomial read from file: " + p1);

            // Step 2: Save the polynomial to a new file
            p1.saveToFile("polynomial_output.txt");
            System.out.println("Polynomial saved to file 'polynomial_output.txt'.");

            // Step 3: Example of evaluating the polynomial at x = 2
            System.out.println("p1 evaluated at x = 2: " + p1.evaluate(2));

        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found.");
        }
    }
}


