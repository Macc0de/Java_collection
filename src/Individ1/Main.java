package Individ1; // 36

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void read_polynom(BufferedReader reader, Polynomial polynom) throws IllegalArgumentException, IOException {
        int size = Integer.parseInt(reader.readLine());
        int index = size;
        String[] line = reader.readLine().split(" ");
        if((size + 1) != line.length)
            throw new IllegalArgumentException("Неверное значение размера мн-на!");

        for (int j = 0; size >= 0; size--, j++) {
            double coef = Double.parseDouble(line[j]);
            polynom.setCoefficient(size, coef);
        }

        if(polynom.getCoefficient(index) == 0.0 || polynom.getCoefficient(index) == 0.0)
            throw new IllegalArgumentException("Пустой мн-н!");
    }

    public static void main(String[] args) {
        Polynomial polynom1 = new Polynomial();
        Polynomial polynom2 = new Polynomial();

        try(BufferedReader reader = new BufferedReader(new FileReader("polynom.txt"))) {
            try {
                read_polynom(reader, polynom1);
                read_polynom(reader, polynom2);
            } catch (IllegalArgumentException e) {
                System.out.println("Исключение: " + e.getMessage());
                return;
            }
        }
        catch (IOException e) {
            System.out.println("Исключение: " + e.getMessage());
            return;
        }

        System.out.print("Первый мн-н: ");
        polynom1.print();

        System.out.print("Второй мн-н: ");
        polynom2.print();

        System.out.print("\n(1) Сумма мн-нов: ");
        Polynomial sum = polynom1.sum(polynom2);
        sum.print();

        System.out.print("(2) Разность мн-нов: ");
        Polynomial subtract = polynom1.subtract(polynom2);
        subtract.print();

        System.out.print("(3) Таблица значений каждого мн-на:\n");
        polynom1.print_table(1, 4);
        System.out.println();
        polynom2.print_table(3, 6);

        System.out.print("\n(Дополнительно)\n(1) Произведение мн-нов:\n");
        Polynomial multiplication = polynom1.multiply(polynom2);
        multiplication.print();

        System.out.print("\n(2) Производные мн-нов:\n");
        polynom1.differentiate();
        polynom2.differentiate();
    }
}
