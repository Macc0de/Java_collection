package Individ1;
import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Double> coefficients;  // По степени хранит коэффициенты

    public Polynomial() { // многочлен нулевой степени
        coefficients = new ArrayList<>();
        coefficients.add(0.0);
    }

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
        for (int i = degree(); i >= 0; i--) {
            double coef = getCoefficient(i);
            if (coef != 0.0 && i != degree()) { // Знак
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

    public void print_table(double a, double b){
        System.out.printf("%5s   %5s\n", "x:", "f(x):");
        for (double i = a; i <= b; i++)
            System.out.printf("%5.2f   %5.2f\n", i, calculate(i));
    }

    public double calculate(double x) { // вычисляет значение многочлена при заданном значении переменной x.
        double result = 0.0;
        for (int i = 0; i <= degree(); i++) {
            result += getCoefficient(i) * Math.pow(x, i); // 3x^2 + 5
        }
        return result;
    }

    // Метод для добавления другого многочлена
    public Polynomial sum(Polynomial polynom2) {
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(degree(), polynom2.degree());

        for (int i = 0; i <= maxDegree; i++) {
            double sum = getCoefficient(i) + polynom2.getCoefficient(i);
            result.setCoefficient(i, sum);
        }
        return result;
    }

    public Polynomial subtract(Polynomial polynom2) { // вычисляет разность двух многочленов аналогично методу add
        Polynomial result = new Polynomial();
        int maxDegree = Math.max(degree(), polynom2.degree());

        for (int i = 0; i <= maxDegree; i++) {
            double diff = getCoefficient(i) - polynom2.getCoefficient(i); // вычитает коэффициенты
            result.setCoefficient(i, diff);
        }
        return result;
    }

    public Polynomial multiply(Polynomial polynom2) {
        int resultDegree = degree() + polynom2.degree();
        Polynomial result = new Polynomial();

        // Устанавливаем коэффициенты в результирующем многочлене для каждой степени до resultDegree
        for (int i = 0; i <= resultDegree; i++)
            result.setCoefficient(i, 0.0);

        // Перемножаем коэффициенты текущего многочлена и переданного
        for (int i = 0; i <= degree(); i++) {
            for (int j = 0; j <= polynom2.degree(); j++) {
                double product = this.getCoefficient(i) * polynom2.getCoefficient(j);
                double currentCoef = result.getCoefficient(i + j); // текущий коэффициент в сумме степеней
                result.setCoefficient(i + j, currentCoef + product);
            }
        }
        return result;
    }

    public void differentiate() {
        if(degree() == 0)
            System.out.print("0");

        Polynomial derivative = new Polynomial();
        for (int i = 1; i <= degree(); i++) {
            double coef = getCoefficient(i) * i;
            derivative.setCoefficient(i - 1, coef);
        }
        derivative.print();
    }
}
