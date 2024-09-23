public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        ceofficients = new double[]{0};
    }

    public Polynomial(double[] ceofficients) {
        this.ceofficients = ceofficients;
    }

    public add(Polynomial other) {
        int thisLen = this.coefficients.length;
        int otherLen = other.coefficients.length
        int maxDegree = Math.max(thisLen, otherLen);
        double[] result = new double[maxDegree];
        for (int i = 0; i < maxDegree; i++) {
            double thisCoefficient;
            if (index < thisLen) {
                thisCoefficient = this.coefficients[index];
            } else {
                thisCoefficient = 0;
            }
            double otherCoefficient;
            if (index < otherLen) {
                otherCoefficient = other.coefficients[index];
            } else {
                otherCoefficient = 0;
            }

            result[index] = thisCoefficient + otherCoefficient;
        }
        return new Polynomial(result)
    }

    public evaluate(double xValue) {
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