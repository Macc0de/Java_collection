package lab02;
import java.util.Arrays;
import java.util.Random;

public class Third_lab2 {

    public static int[] sortDescending(int[] array) {
        for (int i = 0; i < array.length / 2; i++) { // убывающий порядок
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

    public static boolean isThree(int elem) {
        String elemStr = Integer.toString(elem); // Число в строку
        int length = elemStr.length(); // Длина строки

        if(elemStr.charAt(length - 1) != '3')
            return false;
        return true;
    }

    public static int[] createArr(int[] array) {
        int size = 0;

        // Узнать нужный размер + Проврка на наличие
        for(int i : array) {
            if(isThree(i)) {
                size++;
            }
        }
        if(size == 0)
            return null;

        int[] subArr = new int[size];
        for(int i = 0, j = 0; i < array.length; i++) {
            if(isThree(array[i])) // Оканчивается ли на 3?
                subArr[j++] = array[i]; // j=0...
        }

        return subArr;
    }

    public static void main(String[] args) {
        int[] array = new int[20];
        Random random = new Random();

        for(int i = 0; i < array.length; i++)
            array[i] = random.nextInt(1000) + 1;

        for(int i : array)
            System.out.print(i + " ");
        System.out.println();

        // Новый массив
        int[] subArr = createArr(array);
        if(subArr == null) {
            System.out.print("Массив пуст!");
            return;
        }
        else {
            Arrays.sort(subArr);
            sortDescending(subArr);

            System.out.print("Новый массив:\n");
            for (int i : subArr)
                System.out.print(i + " ");
        }
    }
}
