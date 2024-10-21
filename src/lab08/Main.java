package lab08;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int[] march = {-2, -5, -2, -4, 3, -6, -2, -1, 5, 1, 1, 0, -1, 0, 3,
                -1, 2, 5, 2, 4, 4, 0, 6, 1, 4, 6, -1, 2, 4, 7, 11};

        // Используем Stream API
        // Arrays.stream() для создания потока (Stream) из массива температур
        long negative_tmp = Arrays.stream(march)
                .filter(temp -> (temp < 0))
                .count();
        System.out.println("|1 задание|\n(1)\nКоличество дней с отрицательной температурой: " + negative_tmp);

        boolean more_tenDegrees = Arrays.stream(march)
                .anyMatch(temp -> (temp > 10));

        if(more_tenDegrees)
            System.out.println("(2)\nДа, были дни когда температура оказалась выше 10");
        else
            System.out.println("(2)\nНет, не было!");

        // индексы
        OptionalInt max_tmp = IntStream.range(0, march.length)
                .filter(i -> i < 7)
                .map(i -> march[i]) // индекс в значение(преобразование в другой тип)
                .max();
        System.out.printf("(3)\nМаксимальная температура в первую неделю: %d\n", max_tmp.getAsInt());

        OptionalDouble avg_tmp = Arrays.stream(march)
                .average();
        System.out.printf("(*)\nСредняя температура за месяц - %.2f°C\n", avg_tmp.getAsDouble());

        System.out.printf("\n|2 задание|\nВведите предложение: ");
        // They used 233 features including 227 stylometric features and six novel social network-specific features like character-based ones numbers of alphabets, uppercase characters, special characters, word-based ones the total number of words, average word length, the number of words with 1 char, syntactic ones numbers of punctuation marks and functional words, the total number of sentences and many others

        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();
        if(sentence.isEmpty()) {
            System.out.print("Пустая строка!");
            return;
        }

        long count = Arrays.stream(sentence.split("[, ]+"))
                .filter(word -> word.endsWith("es"))
                .count();

        if(count == 0)
            System.out.println("(1)\nНет таких слов!");
        else
            System.out.printf("(1)\nКол-во слов, заканчивающихся на 'es': %d\n", count);

        String sorted_sentence = Arrays.stream(sentence.split("[, ]+"))
                .sorted((a, b) -> Integer.compare(a.length(), b.length())) // сортировка по длине слова
                .reduce((a, b) -> a + " " + b) // // объединить отсортированные слова в одну строку
                .orElse(""); // Если нет слов, вернуть пустую строку
        System.out.printf("(2)\n%s\n", sorted_sentence);

        long count_nums = Arrays.stream(sentence.split("\\D+"))
                .mapToInt(word -> Integer.parseInt(word)) // Integer::parseInt
                .sum();
        System.out.printf("(*)\nСумма целых чисел: %s", count_nums);
    }


}
