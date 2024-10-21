package Lab02;
import java.util.Random;

public class Second_lab2 {

    public static int findMaxChet(int[] array) {
        int max = -1;
        for(int i : array) {
            if(i % 2 == 0) { // Для сравнения первый четный элемент
                max = i;
                break;
            }
        }

        if(max == -1) // Нет четных
            return max;
        // Есть четные
        for(int i : array) {
            if((i % 2 == 0)  && i > max)
                max = i;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[20];
        Random random = new Random();

        for(int i = 0; i < array.length; i ++)
            array[i] = random.nextInt(1001);

        for(int i : array) { // Вывод четных элементов
            if(i % 2 == 0)
                System.out.print(i + " ");
        }

        int res = findMaxChet(array); // ищет
        if(res == -1) {
            System.out.print("\nНет четных элементов в массиве!");
            return;
        }
        else
            System.out.print("\nМаксимальный четный элемент - " + res);
    }
}
