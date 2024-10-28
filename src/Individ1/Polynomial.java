package Individ1;
import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Double> coefficients;  // По степени хранит коэффициенты

    public Polynomial() { // многочлен нулевой степени
        coefficients = new ArrayList<>();
        coefficients.add(0.0); // Изначально многочлен равен 0
    } // Можно не хранить 0?

    // Метод для установки коэффициента при заданной степени
    public void setCoefficient(int degree, double value) {
        while (degree >= coefficients.size()) {
            coefficients.add(0.0); // Расширение списка коэффициентов до нужной степени
        }
        coefficients.set(degree, value);
    }

    // Метод для получения коэффициента при заданной степени
    public double getCoefficient(int degree) {
        if (degree >= 0 && degree < coefficients.size()) {
            return coefficients.get(degree);
        }
        return 0.0;
    }

    // Метод для получения степени многочлена (максимальная степень с ненулевым коэффициентом)
    public int degree() { // Размер массива = Максимальная степень
        int maxDegree = 0;
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            if (coefficients.get(i) != 0) { // ?
                maxDegree = i;
                break;
            }
        }
        return maxDegree;
    }

    public void print() {
        // Значение + степень x
        for(int i = degree(); i >= 0; i--) {
            if(getCoefficient(i) != 0.0) {
                if(i == 1)
                    System.out.printf("%.1fx + ", getCoefficient(i));
                else if(i != 0)
                    System.out.printf("%.1fx^%d + ", getCoefficient(i), i);
                else if(i == 0)
                    System.out.printf("%.1f", getCoefficient(i));
            }
        }
    }

    public void print_table(Polynomial polynom, double a, double b){
        System.out.printf("%5s   %5s\n", "x:", "f(x):");
        for (double i = a; i <= b; i++)
            System.out.printf("%5.2f   %5.2f\n", i, polynom.calculate(i));
    }

    public double calculate(double x) { // вычисляет значение многочлена при заданном значении переменной x.
        double result = 0.0;
        for (int i = 0; i <= degree(); i++) {
            result += getCoefficient(i) * Math.pow(x, i); // 3x^2 + 5
        }
        return result;
    }

    // Метод для добавления другого многочлена
    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(degree(), other.degree());
        for (int i = 0; i <= maxDegree; i++) {
            double sum = getCoefficient(i) + other.getCoefficient(i);
            result.setCoefficient(i, sum);
        }
        return result;
    }

    public Polynomial subtract(Polynomial other) { // вычисляет разность двух многочленов аналогично методу add
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(degree(), other.degree());

        for (int i = 0; i <= maxDegree; i++) {
            double diff = getCoefficient(i) - other.getCoefficient(i); // вычитает коэффициенты
            result.setCoefficient(i, diff);
        }
        return result;
    }

}
