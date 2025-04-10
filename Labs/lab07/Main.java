package lab07;
import java.util.*;

public class Main {
    public static void printPupils(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (0)
        System.out.print("(0)");
        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            byte num_class = entry.getKey();
            ArrayList<SchoolBoy> array_school = entry.getValue();
            System.out.println("\n" + num_class + " класс:");

            for(SchoolBoy school_boy : array_school)
                school_boy.print();

            SchoolBoy.writeFile(array_school, num_class);
        }
    }

    public static void printGrade(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (1)
        System.out.print("\n\n(1)\nВведите оценку: ");

        byte grade; Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) // Проверка
            grade = in.nextByte();
        else {
            System.out.print("Вы ввели не целое число!");
            return;
        }

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            boolean check = false;
            System.out.println("\n" + entry.getKey() + " класс:");

            for(SchoolBoy school_boy : array_school) {
                if(school_boy.equal(grade)) {
                    school_boy.print();
                    check = true;
                }
            }
            if(!check)
                System.out.print("Нет учеников с такой оценкой");
        }
    }

    public static void printPerformance(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (2)
        System.out.print("\n\n(2)\n");
        MiddlePerformance middle_performance = new MiddlePerformance();

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue(); // Массив учеников одного класса
            int count = 0, sum = 0;
            for(SchoolBoy school_boy : array_school) {
                sum += school_boy.getGrade();
                count++;
            }

            double avg_grade = (double) sum / count; // Средняя оценка класса
            middle_performance.add(entry.getKey(), avg_grade);
        }

        middle_performance.sortPrint();
    }

    public static void printSubject(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (3)
        System.out.print("\n(3)\nВведите предмет: ");

        Scanner in = new Scanner(System.in);
        String subject = in.nextLine();
        if(subject.isEmpty()) {
            System.out.print("Пустая строка!");
            return;
        }

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            boolean check = false;
            System.out.println("\n" + entry.getKey() + " класс:");

            for(SchoolBoy school_boy : array_school) {
                if(school_boy.equal(subject)) {
                    school_boy.print();
                    check = true;
                }
            }
            if(!check)
                System.out.print("Нет учеников с оценкой по этому предмету");
        }
    }

    public static void printFile(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (4)
        System.out.print("\n\n(4)\nВведите класс: ");

        byte num_class; Scanner in = new Scanner(System.in);
        if (in.hasNextByte()) // Проверка
            num_class = in.nextByte();
        else {
            System.out.print("Вы ввели не целое число!\n");
            return;
        }
        if(num_class < 1 || num_class > 11) {
            System.out.print("Значение класса должно быть от 1 до 11!\n");
            return;
        }
        if(!journal.containsKey(num_class)) {
            System.out.print("Такого класса нет в журнале!\n");
            return;
        }

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue();

            TreeSet<String> subjects = new TreeSet<>(); // Отсортирован
            for(SchoolBoy school_boy : array_school)
                subjects.add(school_boy.getSubject());

            if(entry.getKey() == num_class) {
                SchoolBoy.writeFile2(subjects, array_school, num_class);
                break;
            }
        }
    }

    public static void findPupil(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (5)
        System.out.print("\n(5)\nВведите фамилию: ");
        String name, surname; Scanner in = new Scanner(System.in);
        boolean check = false;

        surname = in.nextLine();
        if(surname.isEmpty()) {
            System.out.print("Пустая строка!");
            return;
        }
        System.out.print("Введите имя: ");
        name = in.nextLine();
        if(name.isEmpty()) {
            System.out.print("Пустая строка!");
            return;
        }

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) { // Поиск
            ArrayList<SchoolBoy> array_school = entry.getValue();
            for(SchoolBoy school_boy : array_school) {
                if(school_boy.equal(surname, name)) {
                    System.out.printf("%s %s учится в %d классе\n ", surname, name, entry.getKey());
                    check = true;
                }
            }
        }
        if(!check)
            System.out.print("Такого ученика нет!");
    }

    public static void findBestSubject(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (6)
        System.out.print("\n\n(6)\n");
        BestSubject best_subject = new BestSubject();

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue(); // Массив учеников одного класса
            for(SchoolBoy school_boy : array_school) {
                best_subject.add(school_boy);
            }
        }

        best_subject.find();
    }

    public static void main(String[] args) {
        TreeMap<Byte, ArrayList<SchoolBoy>> journal = new TreeMap<>();

        SchoolBoy.readFile("lab07/data_school.txt", journal);

        printPupils(journal); // (0)
        printGrade(journal); // (1)
        printPerformance(journal); // (2)
        printSubject(journal); // (3)
        printFile(journal); // (4)
        findPupil(journal); // (5)
        findBestSubject(journal); // (6)
    }
}
