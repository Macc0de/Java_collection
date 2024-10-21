package lab07;
import java.util.*;
import java.io.*;

public class Main {
    public static void print_pupils (TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (0)
        System.out.print("(0)");
        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            byte num_class = entry.getKey(),  i = 0;
            ArrayList<SchoolBoy> array_school = entry.getValue();
            System.out.println("\n" + num_class + " класс:");

            for(SchoolBoy school_boy : array_school) {
                if(i == array_school.size() - 1)
                    System.out.printf("%s %s ", school_boy.getSurname(), school_boy.getName());
                else {
                    System.out.printf("%s %s, ", school_boy.getSurname(), school_boy.getName());
                    i++;
                }
            }

            try(FileWriter writer = new FileWriter("class" + num_class + ".txt")) {
                for(SchoolBoy school_boy : array_school)
                    writer.write(school_boy.getSurname() + " " + school_boy.getName() + "\n"); // запись в файл
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        }
    }

    public static void print_grade(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (1)
        System.out.print("\n\n(1)\nВведите оценку: ");
        int grade; Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) // Проверка
            grade = in.nextInt();
        else {
            System.out.print("Вы ввели не целое число!");
            return;
        }

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue();
            boolean check = false;
            System.out.println("\n" + entry.getKey() + " класс:");

            for(SchoolBoy school_boy : array_school) {
                if(school_boy.getGrade() == grade) {
                    System.out.printf("%s %s, ", school_boy.getSurname(), school_boy.getName());
                    check = true;
                }
            }
            if(!check)
                System.out.print("Нет учеников с такой оценкой");
        }
    }

    public static void print_performance(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (2)
        System.out.print("\n\n(2)\n");
        ArrayList<Map.Entry<Byte, Double>> average_grade = new ArrayList<>();
        // <Класс, средняя оценка>

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue(); // Массив учеников одного класса
            int count = 0, sum = 0;
            for(SchoolBoy pupil : array_school) {
                sum += pupil.getGrade();
                count++;
            }

            double avg_grade = (double) sum / count; // Средняя оценка класса
            average_grade.add(new AbstractMap.SimpleEntry<>(entry.getKey(), avg_grade)); // В массив
        }
        // Сортировка по avg_grade
        average_grade.sort((a,b) -> Double.compare(b.getValue(), a.getValue()));

        for(Map.Entry<Byte, Double> entry : average_grade) {
            System.out.printf("%d класс - %.2f\n", entry.getKey(), entry.getValue());
        }
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
                if(school_boy.getSubject().equals(subject)) {
                    System.out.printf("%s %s, ", school_boy.getSurname(), school_boy.getName());
                    //System.out.printf("%s %s - (%s), ", school_boy.getSurname(), school_boy.getName(), school_boy.getSubject());
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
            System.out.print("Вы ввели не целое число!");
            return;
        }
        if(num_class < 1 || num_class > 11) {
            System.out.print("Значение класса должно быть от 1 до 11!");
            return;
        }
        if(!journal.containsKey(num_class)) {
            System.out.print("Такого класса нет в журнале!");
            return;
        }

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue();

            TreeSet<String> subjects = new TreeSet<>(); // Отсортирован
            for(SchoolBoy school_boy : array_school) { // Добавление предметов
                subjects.add(school_boy.getSubject());
            }

            if(entry.getKey() == num_class) {
                try(FileWriter writer = new FileWriter("Your class" + num_class + ".txt")) {
                    for(String subject : subjects) { // Список предметов
                        writer.write(subject + ":\n");
                        for (SchoolBoy school_boy : array_school) {
                            if (school_boy.getSubject().equals(subject)) // Ученик с этим предметом
                                writer.write(school_boy.getSurname() + " " + school_boy.getName() + " " + school_boy.getGrade() + "\n");
                        }
                        writer.write("\n");
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка записи в файл: " + e.getMessage());
                }
                break;
            }
        }
    }

    public static void find_pupil(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (5)
        String name, surname; Scanner in = new Scanner(System.in);
        boolean check = false;

        System.out.print("\n(5)\nВведите фамилию: ");
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
                if(school_boy.getSurname().equals(surname) && school_boy.getName().equals(name)) {
                    System.out.printf("%s %s учится в %d классе\n ", surname, name, entry.getKey());
                    check = true;
                }
            }
        }
        if(!check)
            System.out.print("Такого ученика нет!");
    }

    public static void find_bestSubject(TreeMap<Byte, ArrayList<SchoolBoy>> journal) { // (6)
        System.out.print("\n\n(6)\n");

        HashMap<String, Integer> sum = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();

        for(Map.Entry<Byte, ArrayList<SchoolBoy>> entry : journal.entrySet()) {
            ArrayList<SchoolBoy> array_school = entry.getValue(); // Массив учеников одного класса

            for (SchoolBoy school_boy : array_school) {
                String subject = school_boy.getSubject();
                int grade = school_boy.getGrade();

                sum.put(subject, sum.getOrDefault(subject, 0) + grade);
                count.put(subject, count.getOrDefault(subject, 0) + 1);
            }
        }

        double highest_grade = 0.0, avg_grade;
        String best_subject = null;
        for(String subject : sum.keySet()) { // count.keySet()
            avg_grade = (double) sum.get(subject) / count.get(subject); // Средняя оценка класса
            //System.out.printf("(%s) - %.2f\n", subject, avg_grade);

            if(avg_grade > highest_grade) {
                highest_grade = avg_grade;
                best_subject = subject;
            }
        }
        System.out.printf("Предмет с лучшей средней успеваемостью(%.2f) - (%s)\n", highest_grade, best_subject);
    }

    public static void main(String[] args) {
        TreeMap<Byte, ArrayList<SchoolBoy>> journal = new TreeMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader("data_school.txt"))) { // для чтения построчно
            String line;
            while ((line = reader.readLine()) != null) { // Пока есть строки
                String[] word = line.split("\\s+"); // Парсинг

                String surname = word[0];
                String name = word[1];
                byte num_class = Byte.parseByte(word[2]);
                String subject = word[3];
                byte grade = Byte.parseByte(word[4]);

                // объект ученика
                SchoolBoy pupil = new SchoolBoy(surname, name, num_class, subject, grade);

                // существует ли ключ(num_class), если такого нет то создает новый элемент с этим ключом и пустым списком(добавляет в этот список ученика)
                // если ключ уже существует -> добавить ученика в список
                journal.computeIfAbsent(num_class, key -> new ArrayList<>()).add(pupil);
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        // Класс - список учеников
        print_pupils(journal); // (0)
        print_grade(journal); // (1)
        print_performance(journal); // (2)
        print_subject(journal); // (3)
        print_file(journal); // (4)
        find_pupil(journal); // (5)
        find_bestSubject(journal); // (6)
    }
}
