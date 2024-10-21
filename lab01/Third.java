// 5 задача
package lab01;
import java.util.Scanner;

public class Third {

    public static int check(int n) {
        Scanner in = new Scanner(System.in);

        if (in.hasNextInt()) { // Проверка(1) ввода
            n = in.nextInt();
        }
        else {
            System.out.print("Вы ввели не целое число!");
            return 0;
        }

        if(n <= 100000 || n >= 999999) { // Проверка на 6-значность
            System.out.print("Вы ввели не шестизначное число!");
            return 0;
        }

        return n;
    }

    public static void main(String[] args) {
        int N = 0, M = 0, num1, num2, num3, num4, num5, num6, count = 0;

        System.out.print("Введите меньший номер билета: ");
        N = check(N); // Ввод
        if (N == 0)
            return;

        System.out.print("Введите больший номер билета: ");
        M = check(M); // Ввод
        if (M == 0)
            return;

        if(N > M) { // Проверка
            System.out.print("Меньший номер не может быть больше!");
            return;
        }

        for (int i = N; i <= M; i++) {
            num1 = (i / 100000) % 10;
            num2 = (i / 10000) % 10;
            num3 = (i / 1000) % 10;
            num4 = (i / 100) % 10;
            num5 = (i / 10) % 10;
            num6 = i % 10;

            if (num1 + num2 + num3 == num4 + num5 + num6) { // Проверка
                //System.out.printf("Счастливый билет - %d\n", i);
                count++;
            }
        }

        if(count == 0)
            System.out.print("Нет счастливых билетов!");
        else
            System.out.printf("Кол-во счастливых билетов : (%d)", count);
    }
}
