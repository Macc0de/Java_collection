package Lab05;
import java.util.ArrayList;

public class Main {
    // Ввод мест на олимпиадах
    public static void fillOlympiad(SchoolBoy schoolboy, int oblast, int school, int city) {
        schoolboy.setOlympiadOblast(oblast);
        schoolboy.setOlympiadSchool(school);
        schoolboy.setOlympiadCity(city);
    }

    public static void printGirls(ArrayList<SchoolBoy> arraySchool) { // (1)
        System.out.print("(1) Девочки получившие первые места:\n");
        int flag = 0;
        for (SchoolBoy schoolBoy : arraySchool) {
            if (schoolBoy.isFirst()) {
                System.out.printf("%s ", schoolBoy.getName());
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.print("Нет таких девочек");
        System.out.println();
    }

    public static void printStudents(ArrayList<Student> arrayStudent) { // (2)
        System.out.print("(2) Студенты имеющие оценки за курсовые:\n");
        int flag = 0;
        for (Student student : arrayStudent) {
            if (student.isCourseWork()) {
                System.out.printf("%s ", student.getName());
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.print("Нет таких студентов");
        System.out.println();
    }

    public static void printScolars(ArrayList<Studying> allPersons) { // (3)
        System.out.print("(3) Получают Стипендию:\n");
        int flag = 0;
        for(Studying scolar: allPersons) {
            if(scolar.isScholarship()) {
                System.out.println(scolar.getName());
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.print("Никто");
    }

    public static void main(String[] args) {
        String name1 = "Максим", name2 = "Саня", name3 = "Жанна", name4 = "Света", name5 = "Олег";
        char gender1 = 'м', gender2 = 'ж';
        byte age1 = 19, age2 = 21, age3 = 13, age4 = 15, age5 = 11;

        // 1. Школьник
        SchoolBoy schoolboy1 = new SchoolBoy(name2, gender1, age2);
        SchoolBoy schoolboy2 = new SchoolBoy(name3, gender2, age3);
        SchoolBoy schoolboy3 = new SchoolBoy(name4, gender2, age4);

        int[] schoolboy_grades1 =  {5, 5, 5, 5, 4, 4}; // матем, рус, история, англ | биология, география
        int[] schoolboy_grades2 =  {5, 5, 5, 5, 3, 4};
        int[] schoolboy_grades3 =  {5, 5, 5, 5, 4, 4};
        schoolboy1.setGrades(schoolboy_grades1);
        schoolboy2.setGrades(schoolboy_grades2);
        schoolboy3.setGrades(schoolboy_grades3);
        fillOlympiad(schoolboy1, 2, 2, 1); // 0 - не участвовал
        fillOlympiad(schoolboy2, 0, 0, 1);
        fillOlympiad(schoolboy3, 0, 0, 1);

        // (1) Массив школьников
        ArrayList<SchoolBoy> arraySchool = new ArrayList<>();
        arraySchool.add(schoolboy1);
        arraySchool.add(schoolboy2);
        arraySchool.add(schoolboy3);

        printGirls(arraySchool); // (1)

        // 2. Студент
        Student student1 = new Student(name1, gender1, age1);
        Student student2 = new Student(name5, gender1, age2);

        int[] student_grades1 = {5, 4, 5, 5};
        int[] student_grades2 = {5, 4, 5, 5};
        int[] student_coursework1 = {5, 5};
        int[] student_coursework2 = {4, 5};
        student1.setGrades(student_grades1);
        student2.setGrades(student_grades2);
        student1.setGradesCoursework(student_coursework1);
        student2.setGradesCoursework(student_coursework2);

        // (2) Массив студентов
        ArrayList<Student> arrayStudent = new ArrayList<>();
        arrayStudent.add(student1);
        arrayStudent.add(student2);

        printStudents(arrayStudent); // (2)

        // (3) Список Стипендиатов
        ArrayList<Studying> allPersons = new ArrayList<>();
        allPersons.addAll(arraySchool);
        allPersons.addAll(arrayStudent);

        printScolars(allPersons); // (3)

        // (*) Есть ли стипендия?
        /*student1.isScholarship();
        System.out.print("\n");
        schoolboy1.isScholarship();*/
    }
}
