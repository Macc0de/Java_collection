package lab02;
import java.util.Arrays;

public class Fifth_lab2 {

    public static void main(String[] args) {
        int size = 1_000_000;
        String[] array = new String[size];

        for (int i = 0; i < size; i++)
            array[i] = "str ";

        // Копия массива вручную
        long startTime = System.nanoTime(); // текущее системное время
        String[] copyArray = new String[size];
        for (int i = 0; i < size; i++)
            copyArray[i] = array[i];

        long copyTime = System.nanoTime() - startTime;

        // Копия массива с помощью Arrays
        startTime = System.nanoTime();
        String[] arraysCopyArray = Arrays.copyOf(array, size);
        long arraysCopyTime = System.nanoTime() - startTime;

        // Копия массива с помощью System
        startTime = System.nanoTime();
        String[] systemCopyArray = new String[size];
        System.arraycopy(array, 0, systemCopyArray, 0, size);
        long systemCopyTime = System.nanoTime() - startTime;

        // Перевод в секунды
        float copySeconds = copyTime / 1_000_000_000.0f;
        float arraysCopySeconds = arraysCopyTime / 1_000_000_000.0f;
        float systemCopySeconds = systemCopyTime / 1_000_000_000.0f;

        // Выводим время выполнения
        System.out.printf("(1)Copy time: %f s or %d ns\n", copySeconds, copyTime);
        System.out.printf("(2)Time of copy with Arrays: %f s or %d ns\n", arraysCopySeconds, arraysCopyTime);
        System.out.printf("(3)Time of copy with System: %f s or %d ns", systemCopySeconds, systemCopyTime);
    }
}
