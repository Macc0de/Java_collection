package Individ1;
import lab07.SchoolBoy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// 36

public class Main {
    /*public boolean check_input(int n) {
    }*/
    public static void main(String[] args) {
        Polynomial polynom = new Polynomial(); // 3x^2 + 2x + 1

        try(BufferedReader reader = new BufferedReader(new FileReader("polynom"))) { // для чтения построчно
            String str;
            while((str = reader.readLine()) != null) { // Пока есть строки
                String[] word = str.split("\\s+"); // Парсинг

                String surname = word[0];
                String name = word[1];
                byte num_class = Byte.parseByte(word[2]);
                String subject = word[3];
                byte grade = Byte.parseByte(word[4]);

                SchoolBoy school_boy = new SchoolBoy(surname, name, num_class, subject, grade);

                // существует ли ключ(num_class), если такого нет то создает новый элемент с этим ключом и пустым списком(+добавляет в этот список ученика)
                // если ключ уже существует -> добавить ученика в список
                journal.computeIfAbsent(num_class, key -> new ArrayList<>()).add(school_boy);
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        int n = 1;
        //double coef;


        /*for(int i = n; i >= 0; i--) {
            System.out.printf("Введите коэффициент для (x^%d): ", i);

            polynom.setCoefficient(i, coef);
        }*/

        System.out.print("Первый мн-н: ");
        polynom.print();

        System.out.print("\n(2) Второй мн-н: ");
        Polynomial polynom2 = new Polynomial();
        coef = 1;
        for(int i = n; i >= 0; i--, coef += 2) // второй мн-н
            polynom2.setCoefficient(i, coef);
        polynom2.print();

        System.out.print("\n(3) Сумма мн-нов: ");
        Polynomial sum = new Polynomial();
        sum.add(polynom2);
        sum.print();
    }
}
