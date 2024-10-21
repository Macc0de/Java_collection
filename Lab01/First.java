package Lab01;
import java.util.Scanner; // пакет util

public class First {

     /*public static void name_year(int start, int year) {
        String[] animals = {
                "Крыса", "Корова", "Тигр", "Заяц", "Дракон",
                "Змея", "Лошадь", "Овца", "Обезьяна", "Курица",
                "Собака", "Свинья"
        //};

        int index = (year - start) % 12;
        System.out.printf("%s", animals[index]);
    }*/

    public static void find_name(int year) {
        switch (year) {
            case 0:
                System.out.println("Обезьяна");
                break;
            case 1:
                System.out.println("Курица");
                break;
            case 2:
                System.out.println("Собака");
                break;
            case 3:
                System.out.println("Свинья");
                break;
            case 4:
                System.out.println("Крыса");
                break;
            case 5:
                System.out.println("Корова");
                break;
            case 6:
                System.out.println("Тигр");
                break;
            case 7:
                System.out.println("Заяц");
                break;
            case 8:
                System.out.println("Дракон");
                break;
            case 9:
                System.out.println("Змея");
                break;
            case 10:
                System.out.println("Лошадь");
                break;
            case 11:
                System.out.println("Овца");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите год, начиная с 1996: ");

        int start = 1996, year;
        if (in.hasNextInt()) { // Проверка(1) ввода
            year = in.nextInt();
        }
        else {
            System.out.print("Вы ввели не целое число!");
            return;
        }

        if (year < start) { // Проверка(2)
            System.out.print("Ввод должен быть =1996 или больше!");
            return;
        }

        find_name(year % 12);
        //name_year(start, year);

        in.close();
    }
}