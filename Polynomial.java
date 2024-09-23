public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial other) {
        int thisLen = this.coefficients.length;
        int otherLen = other.coefficients.length;
        int maxDegree = Math.max(thisLen, otherLen);
        double[] result = new double[maxDegree];

        for (int i = 0; i < maxDegree; i++) {
            double thisCoefficient;
            if (i < thisLen) {
                thisCoefficient = this.coefficients[i];
            } else {
                thisCoefficient = 0;
            }

            double otherCoefficient;
            if (i < otherLen) {
                otherCoefficient = other.coefficients[i];
            } else {
                otherCoefficient = 0;
            }

            result[i] = thisCoefficient + otherCoefficient;
        }

        return new Polynomial(result);
    }

    public double evaluate(double xValue) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(xValue, i);
        }
        return result;
    }


    public boolean hasRoot(double xValue) {
        return evaluate(xValue) == 0;
    }
}