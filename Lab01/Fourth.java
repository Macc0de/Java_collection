package Lab01;
import java.util.Scanner;

public class Fourth {

    public static int check(int n) {
        Scanner in = new Scanner(System.in);

        if (in.hasNextInt()) { // Проверка(1) ввода
            n = in.nextInt();
        }
        else {
            System.out.print("Вы ввели не целое число!");
            return 0;
        }

        if (n < 1) { // Проверка(2)
            System.out.print("Вы ввели число < 1!");
            return 0;
        }

        return n;
    }

    public static void main(String[] args) {
        int N = 0;

        System.out.print("Введите кол-во этажей: ");
        N = check(N); // Ввод
        if (N == 0)
            return;

        int M = 0;
        System.out.print("Введите номер квартиры: ");
        M = check(M); // Ввод
        if (M == 0)
            return;

        if(M > (N*3)) { // Проверка
            System.out.print("Номер квартиры больше допустимого!");
            return;
        }

        // На этаже 3 квартиры
        for (int i = 1, min = 0, max = 3; i <= N; i++) { // По каждому этажу
            // В интервале между минимальным и максимальным номером квартиры на этаже
            if (M >= min && M <= max) {
                if (i % 2 != 0) {
                    System.out.printf("Это нечетный этаж - %d", i);
                }
                else
                    System.out.printf("Это четный этаж - %d!\n" +
                            "А лифт останавливается только на нечетных этажах!", i);

                break;
            }
            min += 3; // Мин. номер квартиры
            max = min + 3; // Макс. номер квартиры
        }
    }
}
