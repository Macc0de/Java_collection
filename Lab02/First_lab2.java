package Lab02;
import java.util.Random;

public class First_lab2 {

    public static boolean isSymm(int elem) {
        String elemStr = Integer.toString(elem); // Число в строку
        int length = elemStr.length(); // Длина строки

        for(int i = 0; i < length / 2; i++) { // charAt() элемент по индексу
            if(elemStr.charAt(i) != elemStr.charAt(length - 1 - i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = new int[20]; // записаны 0
        int count = 0, sum = 0;
        Random random = new Random();

        for(int i = 0; i < array.length; i++)
            array[i] = random.nextInt(1001);

        for(int i : array) { //for each
            if((i > 99 && i < 1000) && isSymm(i)) {
                count++;
                sum += i;
                System.out.println(i);
            }
        }
        System.out.printf("Сумма симметричных чисел: %d\nКол-во симметричных чисел: %d", sum, count);
    }
}
