package lab09;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static String inputString(Scanner in) {
        String str = in.nextLine();

        if(str.isEmpty()) {
            System.out.print("Пустая строка!");
            return null;
        }
        return str;
    }

    public static String deleteCapitals(String str) {
        if(str.contains(" ")) {
            System.out.print("Должно быть одно слово!");
            return null;
        }

        str = str.replaceAll("[А-Я]", ""); // regex
        return str;
    }

    public static String decryptWords(String str) {
        String[] words = str.split(" "); // "[\\s]+"

        String[] decrypted_words = new String[words.length]; // массив слов
        for(int i = 0; i < words.length; i++) {
            decrypted_words[i] = new StringBuilder(words[i]).reverse().toString(); // возвращает строку
        }

        return String.join(" ", decrypted_words);
    }

    public static int findThreeWords(String str) {
        String[] words = str.split("[, ]+");

        int count = 0;
        for(int i = 0; i < words.length; i++) {
            if(words[i].length() == 3)
                count++;
        }
        return count;
    }

    public static boolean checkPassword(String str) {
        if((str.length() < 8 || str.length() > 12)) {
            System.out.println("Пароль должен иметь длину от 8 до 12 символов!");
            return false;
        }
        /*
        ".*" — любую последовательность символов(в том числе и пустую)
        '.' — любой одиночный символ.
        '*' — любое количество повторений
        Второе ".*" после набора символов говорит, что после специального символа
        может быть любое количество любых символов
        */ // Если не содержит хотя бы одного
        if(!str.matches(".*[#$@%^&*<>].*")) {
            System.out.println("Пароль должен содержать спец. символы!");
            return false;
        }
        if(!str.matches(".*[.,?!:;'\"-].*")) { // экранирование - \"
            System.out.println("Пароль должен содержать знаки препинания!");
            return false;
        }
        if(!str.matches(".*[a-z].*")) {
            System.out.println("Пароль должен содержать прописные латинские буквы!");
            return false;
        }
        if(!str.matches(".*[A-Z].*")) {
            System.out.println("Пароль должен содержать заглавные латинские буквы!");
            return false;
        }

        return true;
    }

    public static int sumNumbers(String str) {
        String[] words = str.split("\\D+");

        int sum = 0;
        for(String i: words) {
            if(!i.isEmpty())
                sum += Integer.parseInt(i); // строка-число в int
        }
        return sum;
    }

    public static void findKeyWord(Scanner in) {
        ArrayList<String> sentences = new ArrayList<>();

        while(true) {
            String sentence = in.nextLine();
            if(sentence.endsWith(".") || sentence.endsWith("!") || sentence.endsWith("?"))
                sentences.add(sentence);
            else {
                break;
            }
        }
        if(sentences.isEmpty()) {
            System.out.println("Вы не ввели предложение!");
            return;
        }

        System.out.print("Ключевое слово: ");
        String key_word = in.nextLine();
        if(key_word.isEmpty()) {
            System.out.println("Вы не ввели ключевое слово!");
            return;
        }

        int flag = 0;
        for (String sentence : sentences) { // sentence - массив предложений
            String[] words = sentence.split("[\\s.!?]+"); // чтобы знаки не считались частью слова \\s (пробел, табуляция, перенос строки)
            for (String word : words) {
                if (word.equals(key_word)) {
                    System.out.println("Предложение с ключевым словом: " + sentence.substring(0, sentence.length() - 1));
                    flag = 1;
                }
            }
        }
        if(flag == 0)
            System.out.println("Нет предложений с таким словом!");
    }

    public static int costTelegram(String str) {
        String[] words = str.split("[\\s]+"); // делит на слова по пробелам

        int cost = 0;
        for(String word: words) {
            if ((!word.matches("[a-zA-Z,]+")) && (!word.matches("[а-яА-Я,]+"))) {
                System.out.println("Слова должны состоять только из русских или латинских букв!");
                return -1;
            }
            if(!word.equals(",") && word.contains(","))
                cost += 10;
            if((word.length() > 2) || word.equals(",")) // запятая тоже слово
                cost += 10;
        }

        return cost;
    }

    public static String deleteWords(String str) {
        String[] words = str.split("[\\s]+");

        for (int i = 0; i < words.length; i++) { // words[i] - слово
            if (words[i].length() < 3 && !words[i].equals(",")) {
                words[i] = "";
            } else {
                words[i] = words[i].replace(",", "зпт");
            }
        }

        // Соединяет words пробелами
        str = String.join(" ", words).replaceAll("\\s+", " "); // replace чтоб 1 пробел был только
        return str;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("(1) Введите слово: ");
        String str1 = inputString(in);
        if(str1 == null)
            return;

        // (1) Удалить заглавные
        str1 = deleteCapitals(str1);
        if(str1 == null)
            return;
        System.out.println(str1);

        System.out.print("\n(2) Введите текст, в котором слова(зашифрованы) разделены пробелами: ");
        String str2 = inputString(in);
        if(str2 == null)
            return;

        // (2) Расшифровать - околом тибюл тоК
        str2 = decryptWords(str2);
        System.out.println(str2);

        System.out.print("\n(3) Введите предложение, в котором слова разделены пробелами и запятыми: ");
        String str3 = inputString(in);
        if(str3 == null)
            return;

        // (3) Найти кол-во трехбуквенных слов - Принял ясно отл ичн о хюр вау .ю11
        int count = findThreeWords(str3);
        System.out.print("Кол-во трехбуквенных слов: ");
        System.out.println(count);

        System.out.print("\n(4) Пароль должен иметь длину от 8 до 12 символов, содержать прописные и заглавные латинские буквы,\n" +
                "спец. символы(#$@%^&*<>) и знаки препинания(.,?!:;'\"-)\nВведите: ");
        String str4 = inputString(in);
        if(str4 == null)
            return;

        // (4) Проверка пароля
        // maxym123 maxYm123!@
        if(checkPassword(str4))
            System.out.println("Хороший пароль");

        System.out.print("\n(5) Введите текст, содержащий целые числа: ");
        String str5 = inputString(in);
        if(str5 == null)
            return;

        // (5) Подсчитать сумму всех целых чисел в нем(все символы кроме цифр можно считать разделителями)
        // ap6le8 banana 123 and 456x789
        int sum = sumNumbers(str5);
        System.out.printf("Сумма целых чисел в тексте: %d\n", sum);

        System.out.print("\n(6) Введите текст: ");

        // (6)
        findKeyWord(in);
        /*
Welcome to SoftUni!
You will learn programming, algorithms, problem solving and software technologies.
You need to allocate for study 20-30 hours weekly.
Good luck!
I am fan of Motorhead.
To be or not to be — that is the question.
TO DO OR NOT?

        to
         */

        System.out.print("\n(7) Введите текст телеграммы: ");
        String str6 = inputString(in);
        if(str6 == null)
            return;

        // (7) Power en erg, max o q , ,
        int cost = costTelegram(str6);
        if(cost == -1)
            return;

        System.out.printf("Стоимость телеграммы - %d рублей\n", cost);
        String str = deleteWords(str6);
        System.out.println("Результат: " + str);

        in.close();
    }
}
