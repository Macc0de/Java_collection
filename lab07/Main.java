package lab07;
import java.util.*;

public class Main {
    public static void print_pupils(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (0)
        System.out.print("(0)");
        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            byte num_class = entry.getKey();
            ArrayList<SchoolBoy> array_school = entry.getValue();
            System.out.println("\n" + num_class + " класс:");

            for(SchoolBoy school_boy : array_school)
                school_boy.print();

            SchoolBoy.write_file(array_school, num_class);
        }
    }

    public static void print_grade(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (1)
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

    public static void print_performance(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (2)
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

        middle_performance.sort_print();
    }

    public static void print_subject(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (3)
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

    public static void print_file(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (4)
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
                SchoolBoy.write_file2(subjects, array_school, num_class);
                break;
            }
        }
    }

    public static void find_pupil(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (5)
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

        SchoolBoy.read_file("lab07/data_school.txt", journal);

        print_pupils(journal); // (0)
        print_grade(journal); // (1)
        print_performance(journal); // (2)
        print_subject(journal); // (3)
        print_file(journal); // (4)
        find_pupil(journal); // (5)
        find_bestSubject(journal); // (6)
    }
}
