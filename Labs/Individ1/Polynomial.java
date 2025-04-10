package Individ1;
import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Double> coefficients;

    public Polynomial() {
        coefficients = new ArrayList<>();
    }

    public void setCoefficient(int degree, double value) {
        while (degree >= coefficients.size()) {
            coefficients.add(0.0); // С конца в начало
        }
        coefficients.set(degree, value); // заменяет 0
    }

    public double getCoefficient(int degree) {
        if (degree >= 0 && degree < coefficients.size()) {
            return coefficients.get(degree);
        }
        return 0.0;
    }
    
    public int getDegree() {
        int max_degree = 0;
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            if (coefficients.get(i) != 0) { // ненулевой
                max_degree = i;
                break;
            }
        }
        return max_degree;
    }

    public void print() {
        for (int i = getDegree(); i >= 0; i--) {
            double coef = getCoefficient(i);
            if (coef != 0.0 && i != getDegree()) { // Знак
                System.out.print(coef < 0 ? " - " : " + ");
            } else if (coef < 0) {
                System.out.print("-");
            }

            double absCoef = Math.abs(coef);
            if(coef != 0.0) {
                if (i == 1)
                    System.out.printf("%.1fx", absCoef);
                else if (i != 0)
                    System.out.printf("%.1fx^%d", absCoef, i);
                else
                    System.out.printf("%.1f", absCoef);
            }
        }
        System.out.println();
    }

    public void printTable(double a, double b){
        System.out.printf("%5s   %5s\n", "x:", "f(x):");
        for (double i = a; i <= b; i++)
            System.out.printf("%5.2f   %5.2f\n", i, calculate(i));
    }

    public double calculate(double x) {
        double result = 0.0;
        for (int i = 0; i <= getDegree(); i++) {
            result += getCoefficient(i) * Math.pow(x, i);
        }
        return result;
    }

    public Polynomial sum(Polynomial polynom2) {
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(getDegree(), polynom2.getDegree());

        for (int i = 0; i <= maxDegree; i++) {
            double sum = getCoefficient(i) + polynom2.getCoefficient(i);
            result.setCoefficient(i, sum);
        }
        return result;
    }

    public Polynomial subtract(Polynomial polynom2) {
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(getDegree(), polynom2.getDegree());

        for (int i = 0; i <= maxDegree; i++) {
            double diff = getCoefficient(i) - polynom2.getCoefficient(i);
            result.setCoefficient(i, diff);
        }
        return result;
    }

    public Polynomial multiply(Polynomial polynom2) {
        int result_degree = getDegree() + polynom2.getDegree();
        Polynomial result = new Polynomial();

        for (int i = 0; i <= result_degree; i++)
            result.setCoefficient(i, 0.0);

        for (int i = 0; i <= getDegree(); i++) {
            for (int j = 0; j <= polynom2.getDegree(); j++) {
                double multiplication = getCoefficient(i) * polynom2.getCoefficient(j);
                double result_coef = result.getCoefficient(i + j);
                result.setCoefficient(i + j, result_coef + multiplication);
            }
        }
        return result;
    }

    public void differentiate() {
        if(getDegree() == 0)
            System.out.print("0");

        Polynomial derivative = new Polynomial();
        for (int i = 1; i <= getDegree(); i++) {
            double coef = getCoefficient(i) * i;
            derivative.setCoefficient(i - 1, coef);
        }
        derivative.print();
    }
}
