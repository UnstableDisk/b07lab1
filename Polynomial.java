import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Polynomial {
    private double[] coefficients;
    private int[] exponents;

    
    public Polynomial() {
        this.coefficients = new double[1];
        this.exponents = new int[1];
        this.coefficients[0] = 0;
        this.exponents[0] = 0;
    }

    
    public Polynomial(double[] coefficients, int[] exponents) {
        if (coefficients == null || coefficients.length == 0 || exponents == null || exponents.length == 0) {
            // Handle null or empty arrays as a zero polynomial
            this.coefficients = new double[1];
            this.exponents = new int[1];
            this.coefficients[0] = 0;
            this.exponents[0] = 0;
        } else if (coefficients.length != exponents.length) {
            throw new IllegalArgumentException("Coefficients and exponents must have the same length.");
        } else {
            this.coefficients = coefficients.clone();
            this.exponents = exponents.clone();
        }
    }

  public Polynomial(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        sc.close();

        // To handle negative signs correctly, we split the input line while preserving the signs
        String[] terms = line.split("(?=[+-])");

        int size = terms.length;
        coefficients = new double[size];
        exponents = new int[size];

        
        for (int i = 0; i < size; i++) {
            String term = terms[i].trim();  // Trim any leading/trailing whitespace

            if (term.contains("x")) {
                // Term contains x, so split by 'x' to get the coefficient and exponent
                String[] parts = term.split("x");

                // Coefficient is the part before 'x', handle it if it's empty or just a sign
                coefficients[i] = parts[0].isEmpty() || parts[0].equals("+") ? 1 : 
                                  parts[0].equals("-") ? -1 : Double.parseDouble(parts[0]);

                // Exponent is the part after 'x', default to 1 if not provided
                exponents[i] = parts.length == 2 ? Integer.parseInt(parts[1]) : 1;
            } else {
                // No 'x', so it's a constant term
                coefficients[i] = Double.parseDouble(term);
                exponents[i] = 0;
            }
        }
    }

    
    public void saveToFile(String filename) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(filename));

        for (int i = 0; i < coefficients.length; i++) {
            if (i > 0 && coefficients[i] >= 0) {
                writer.print("+");
            }
            writer.print(coefficients[i]);
            if (exponents[i] > 0) {
                writer.print("x" + exponents[i]);
            }
        }

        writer.close();
    }



    
    public Polynomial multiply(Polynomial other) {
        if (this.isZeroPolynomial() || other.isZeroPolynomial()) {
            return new Polynomial(); // Return zero polynomial if either is zero
        }

        int newSize = this.coefficients.length * other.coefficients.length;
        double[] newCoefficients = new double[newSize];
        int[] newExponents = new int[newSize];

        int index = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                newCoefficients[index] = this.coefficients[i] * other.coefficients[j];
                newExponents[index] = this.exponents[i] + other.exponents[j];
                index++;
            }
        }
        return new Polynomial(newCoefficients, newExponents);
    }

    
    public double evaluate(double x) {
        if (isZeroPolynomial()) return 0; // If it's a zero polynomial, always return 0

        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }


    // Helper method to check if this is a zero polynomial
    private boolean isZeroPolynomial() {
        return coefficients.length == 1 && coefficients[0] == 0 && exponents[0] == 0;
    }

    // Optional: toString method to print the polynomial in a readable format
    public String toString() {
        if (isZeroPolynomial()) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            sb.append(coefficients[i]).append("x^").append(exponents[i]).append(" ");
        }
        return sb.toString().trim();
    }
}



